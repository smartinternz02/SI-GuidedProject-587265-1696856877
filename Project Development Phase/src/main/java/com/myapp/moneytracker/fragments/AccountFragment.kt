package com.myapp.moneytracker.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.util.Pair
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.PercentFormatter
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.myapp.moneytracker.*
import com.myapp.moneytracker.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AccountFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var auth: FirebaseAuth = Firebase.auth
    private var user = Firebase.auth.currentUser
    private val uid = user?.uid
    private var dbRef: DatabaseReference = FirebaseDatabase.getInstance().getReference(uid!!)

    var amountExpense: Double = 0.0
    var amountIncome: Double = 0.0
    var allTimeExpense: Double = 0.0
    var allTimeIncome: Double = 0.0

    private var dateStart: Long = 0
    private var dateEnd: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logout()
        accountDetails()
        setInitDate()
        chartMenu()
        Handler().postDelayed({
            showAllTimeRecap()

            setupBarChart()
        }, 200)

        dateRangePicker()
    }

    private fun swipeRefresh() {
        val swipeRefreshLayout: SwipeRefreshLayout = requireView().findViewById(R.id.swipeRefresh)
        swipeRefreshLayout.setOnRefreshListener {
            accountDetails()
            showAllTimeRecap()

            setupBarChart()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun chartMenu() {
        val barChart: BarChart = requireView().findViewById(R.id.barChart)
        barChart.visibility = View.VISIBLE
    }

    private fun setInitDate() {
        val dateRangeButton: Button = requireView().findViewById(R.id.buttonDate)
        val currentDate = Date()
        val cal: Calendar = Calendar.getInstance(TimeZone.getDefault())
        cal.time = currentDate

        val startDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH)
        cal.set(Calendar.DAY_OF_MONTH, startDay)
        val startDate = cal.time
        dateStart= startDate.time
        

        val endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
        cal.set(Calendar.DAY_OF_MONTH, endDay)
        val endDate = cal.time
        dateEnd= endDate.time

        fetchAmount(dateStart, dateEnd)
        dateRangeButton.text = "This Month"
    }

    private fun dateRangePicker() {
        val dateRangeButton: Button = requireView().findViewById(R.id.buttonDate)
        dateRangeButton.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Select Date")
                .setSelection(
                    Pair(
                        dateStart,
                        dateEnd
                    )
                ).build()
            datePicker.show(parentFragmentManager, "DatePicker")
            datePicker.addOnPositiveButtonClickListener {
                val dateString = datePicker.selection.toString()
                val date: String = dateString.filter { it.isDigit() }
                val pickedDateStart = date.substring(0,13).toLong()
                val pickedDateEnd  = date.substring(13).toLong()
                dateRangeButton.text = convertDate(pickedDateStart, pickedDateEnd)
                fetchAmount(pickedDateStart, pickedDateEnd)

                Handler().postDelayed({

                    setupBarChart()
                }, 200)
            }
        }
    }

    private fun accountDetails() {
        val tvName: TextView = requireView().findViewById(R.id.tvName)
        val tvEmail: TextView = requireView().findViewById(R.id.tvEmail)
        val tvPicture: TextView = requireView().findViewById(R.id.picture)
        val verified: CardView = requireView().findViewById(R.id.verified)


        user?.reload()
        user?.let {

            val userName = user!!.displayName
            val email = user!!.email
            val splitValue = email?.split("@")
            val name = splitValue?.get(0)
            tvName.text = name.toString()
            tvPicture.text = name?.get(0).toString().uppercase()
            tvEmail.text = email.toString()

            if (userName != null) {
                tvName.text = userName.toString()
                tvPicture.text = userName[0].toString().uppercase()
            }

        }
    }

    private fun logout() {
        val btnLogout: ImageButton = requireView().findViewById(R.id.btnLogout)
        btnLogout.setOnClickListener {
            auth.signOut()
            Intent(this.activity, Login::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                activity?.startActivity(it)
            }
        }
    }

    private fun showAllTimeRecap() {
        val tvNetAmount: TextView = requireView().findViewById(R.id.netAmount)
        val tvAmountExpense: TextView = requireView().findViewById(R.id.expenseAmount)
        val tvAmountIncome: TextView = requireView().findViewById(R.id.incomeAmount)

        tvNetAmount.text = "${allTimeIncome-allTimeExpense}"
        tvAmountExpense.text = "$allTimeExpense"
        tvAmountIncome.text = "$allTimeIncome"
    }

    private fun setupBarChart() {
        val netAmountRangeDate: TextView = requireView().findViewById(R.id.netAmountRange)
        netAmountRangeDate.text = "${amountIncome-amountExpense}"

        val barChart: BarChart = requireView().findViewById(R.id.barChart)

        val barEntries = arrayListOf<BarEntry>()
        barEntries.add(BarEntry(1f, amountExpense.toFloat()))
        barEntries.add(BarEntry(2f, amountIncome.toFloat()))

        val xAxisValue= arrayListOf<String>("","Expense", "Income")
        barChart.animateXY(500, 500)
        barChart.description.isEnabled = false
        barChart.setDrawValueAboveBar(true)
        barChart.setDrawBarShadow(false)
        barChart.setPinchZoom(false)
        barChart.isDoubleTapToZoomEnabled = false
        barChart.setScaleEnabled(false)
        barChart.setFitBars(true)
        barChart.legend.isEnabled = false
        barChart.axisRight.isEnabled = false
        barChart.axisLeft.isEnabled = false
        barChart.axisLeft.axisMinimum = 0f
        val xAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 1f
        xAxis.valueFormatter = com.github.mikephil.charting.formatter.IndexAxisValueFormatter(xAxisValue)
        val barDataSet = BarDataSet(barEntries, "")
        barDataSet.setColors(
            resources.getColor(R.color.blue),
            resources.getColor(R.color.blue_secondary)
        )
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 15f
        barDataSet.isHighlightEnabled = false
        val barData = BarData(barDataSet)
        barData.barWidth = 0.5f

        barChart.data = barData
    }
    private fun fetchAmount(dateStart: Long, dateEnd: Long) {
        var amountExpenseTemp = 0.0
        var amountIncomeTemp = 0.0
        val transactionList: ArrayList<TransactionModel> = arrayListOf<TransactionModel>()
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                transactionList.clear()
                if (snapshot.exists()) {
                    for (transactionSnap in snapshot.children) {
                        val transactionData =
                            transactionSnap.getValue(TransactionModel::class.java)
                        transactionList.add(transactionData!!)
                    }
                }
                for ((i) in transactionList.withIndex()){
                    if (transactionList[i].type == 1 &&
                        transactionList[i].date!! > dateStart-86400000 &&
                        transactionList[i].date!! <= dateEnd){
                        amountExpenseTemp += transactionList[i].amount!!
                    }else if (transactionList[i].type == 2 &&
                        transactionList[i].date!! > dateStart-86400000 &&
                        transactionList[i].date!! <= dateEnd){
                        amountIncomeTemp += transactionList[i].amount!!
                    }
                }
                amountExpense= amountExpenseTemp
                amountIncome = amountIncomeTemp

                var amountExpenseTemp = 0.0
                var amountIncomeTemp = 0.0

                //take all amount expense and income :
                for ((i) in transactionList.withIndex()){
                    if (transactionList[i].type == 1 ){
                        amountExpenseTemp += transactionList[i].amount!!
                    }else if (transactionList[i].type == 2){
                        amountIncomeTemp += transactionList[i].amount!!
                    }
                }
                allTimeExpense = amountExpenseTemp
                allTimeIncome = amountIncomeTemp

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

    }

    private fun convertDate(dateStart: Long, dateEnd: Long): String {
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val date1 = Date(dateStart)
        val date2 = Date(dateEnd)
        val result1 = simpleDateFormat.format(date1)
        val result2 = simpleDateFormat.format(date2)
        return "$result1 - $result2"
    }

    override fun onResume() {
        super.onResume()
        showAllTimeRecap()
        setupBarChart()
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

