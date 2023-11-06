package com.myapp.moneytracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.myapp.moneytracker.databinding.ActivityMainBinding
import com.myapp.moneytracker.fragments.AccountFragment
import com.myapp.moneytracker.fragments.TransactionFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val transactionFragment = TransactionFragment()
        val accountFragment = AccountFragment()
        binding.chipAppBar.setItemSelected(R.id.ic_transaction,true)
        makeCurrentFragment(transactionFragment)
        binding.chipAppBar.setOnItemSelectedListener {
            when (it){
                R.id.ic_transaction -> makeCurrentFragment(transactionFragment)
                R.id.ic_account -> makeCurrentFragment(accountFragment)
            }
            val b = true
            b
        }

    }


    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
    }


    fun floating_button(view: View){
        val intent = Intent(this, InsertionActivity::class.java)
        startActivity(intent)
    }
}
