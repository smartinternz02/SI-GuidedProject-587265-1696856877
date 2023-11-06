package com.myapp.moneytracker

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class TransactionDetails : AppCompatActivity() {

    private lateinit var tvAmountDetails: TextView
    private lateinit var tvTypeDetails: TextView
    private lateinit var tvTitleDetails: TextView
    private lateinit var tvDateDetails: TextView
    private lateinit var tvNoteDetails: TextView
    private lateinit var tvCategoryDetails: TextView
    private lateinit var detailsTitle: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_details)
        val backButton: ImageButton = findViewById(R.id.backBtn)
        backButton.setOnClickListener {
            finish()
        }
        val updateData: ImageButton = findViewById(R.id.updateData)
        updateData.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("title").toString()
            )
        }
        deleteData()

        initView()
        setValuesToViews()
    }

    private fun deleteData() {
        val deleteData: ImageButton = findViewById(R.id.deleteData)
        val alertBox = AlertDialog.Builder(this)
        deleteData.setOnClickListener {
            alertBox.setTitle("Are you sure?")
            alertBox.setMessage("Do you want to delete this transaction?")
            alertBox.setPositiveButton("Yes") { _: DialogInterface, _: Int ->
                deleteRecord(
                    intent.getStringExtra("transactionID").toString()
                )
            }
            alertBox.setNegativeButton("No") { _: DialogInterface, _: Int -> }
            alertBox.show()
        }
    }

    private fun deleteRecord(transactionID: String) {
        val user = Firebase.auth.currentUser
        val uid = user?.uid
        if (uid != null) {
            val dbRef = FirebaseDatabase.getInstance().getReference(uid).child(transactionID)
            val mTask = dbRef.removeValue()

            mTask.addOnSuccessListener {
                Toast.makeText(this, "Transaction Data Deleted", Toast.LENGTH_LONG).show()
                finish()
            }.addOnFailureListener { error ->
                Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initView() {
        tvAmountDetails = findViewById(R.id.tvAmountDetails)
        tvTypeDetails = findViewById(R.id.tvTypeDetails)
        tvTitleDetails = findViewById(R.id.tvTitleDetails)
        tvDateDetails = findViewById(R.id.tvDateDetails)
        tvNoteDetails = findViewById(R.id.tvNoteDetails)
        tvCategoryDetails = findViewById(R.id.tvCategoryDetails)
        detailsTitle = findViewById(R.id.transactionDetailsTitle)
    }

    private fun setValuesToViews(){

        tvTitleDetails.text =  intent.getStringExtra("title")
        val type: Int = intent.getIntExtra("type",0)
        val amount: Double = intent.getDoubleExtra("amount", 0.0)
        tvAmountDetails.text = amount.toString()

        if (type == 1) {
            tvTypeDetails.text = "Expense Transaction"
            tvAmountDetails.setTextColor(Color.parseColor("#060A61"))
            detailsTitle.setBackgroundResource(R.drawable.blue_background)
            window.statusBarColor = ContextCompat.getColor(this, R.color.blue)
        }else{
            tvTypeDetails.text = "Income Transaction"
            tvAmountDetails.setTextColor(Color.parseColor("#ff9f1c"))
            detailsTitle.setBackgroundResource(R.drawable.bg_details_expense)
            window.statusBarColor = ContextCompat.getColor(this, R.color.blue)
        }

        val date: Long = intent.getLongExtra("date", 0)
        val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
        val result = Date(date)
        tvDateDetails.text = simpleDateFormat.format(result)

        tvCategoryDetails.text =  intent.getStringExtra("category")
        tvNoteDetails.text =  intent.getStringExtra("note")

    }

    private fun openUpdateDialog(
        title: String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog, null)

        mDialog.setView(mDialogView)
        val etTitle = mDialogView.findViewById<EditText>(R.id.titleUpdate)
        val etCategory = mDialogView.findViewById<AutoCompleteTextView>(R.id.categoryUpdate)
        val etAmount = mDialogView.findViewById<EditText>(R.id.amountUpdate)
        val etDate = mDialogView.findViewById<EditText>(R.id.dateUpdate)
        val btnUpdateData = mDialogView.findViewById<Button>(R.id.updateButton)
        val etNote = mDialogView.findViewById<EditText>(R.id.noteUpdate)
        etTitle.setText(intent.getStringExtra("title").toString())
        etAmount.setText(intent.getDoubleExtra("amount", 0.0).toString())
        etNote.setText(intent.getStringExtra("note")).toString()

        val type: Int = intent.getIntExtra("type",0)
        val transactionID = intent.getStringExtra("transactionID")
        
        val categoryOld = (intent.getStringExtra("category"))
        etCategory.setText(categoryOld)

        val listExpense = CategoryOptions.expenseCategory()
        val expenseAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listExpense)
        etCategory.setAdapter(expenseAdapter)

        if (type == 1) {
            etCategory.setAdapter(expenseAdapter)
        }
        if (type == 2){

            val listIncome = CategoryOptions.incomeCategory()
            val incomeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listIncome)
            etCategory.setAdapter(incomeAdapter)
        }
        val date: Long = intent.getLongExtra("date", 0)
        val cal = Calendar.getInstance()
        val getDate = Date(date)
        cal.time = getDate

        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val resultParse = simpleDateFormat.format(getDate)
        etDate.setText(resultParse)

        var dateUpdate: Long = intent.getLongExtra("date", 0)
        var invertedDate: Long = dateUpdate * -1
        etDate.setOnClickListener {
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this,
                { _, selectedYear, selectedMonth, selectedDayOfMonth ->

                    val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                    etDate.text = null
                    etDate.hint = selectedDate

                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                    val theDate = sdf.parse(selectedDate)
                    dateUpdate = theDate!!.time
                    invertedDate = dateUpdate * -1
                },
                year,
                month,
                day
            )
            dpd.show()
        }

        mDialog.setTitle("Updating $title's Transaction")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {

            updateTransactionData(
                transactionID.toString(),
                type,
                etTitle.text.toString(),
                etCategory.text.toString(),
                etAmount.text.toString().toDouble(),
                dateUpdate,
                etNote.text.toString(),
                invertedDate
            )
            Toast.makeText(applicationContext, "Transaction Data Updated", Toast.LENGTH_LONG).show()
            tvTitleDetails.text = etTitle.text.toString()
            tvAmountDetails.text = etAmount.text.toString()
            tvNoteDetails.text = etNote.text.toString()
            tvCategoryDetails.text = etCategory.text.toString()

            val date: Long = dateUpdate
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val result = Date(date)
            tvDateDetails.text = simpleDateFormat.format(result)
            alertDialog.dismiss()
        }
    }

    private fun updateTransactionData(
        transactionID:String,
        type: Int,
        title: String,
        category: String,
        amount: Double,
        date: Long,
        note: String,
        invertedDate: Long
    ){

        val user = Firebase.auth.currentUser
        val uid = user?.uid
        if (uid != null) {
            val dbRef = FirebaseDatabase.getInstance().getReference(uid)
            val transactionInfo = TransactionModel(transactionID, type, title, category, amount, date, note, invertedDate)
            dbRef.child(transactionID).setValue(transactionInfo)
        }
    }
}



