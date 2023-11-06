package com.myapp.moneytracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.myapp.moneytracker.databinding.ActivitySignUpBinding


class SignUp : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.signupBtn.setOnClickListener {

            val email = binding.email.text.toString()
            val pass = binding.password.text.toString()
            val confirmPass = binding.passwordRetype.text.toString()

            if (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                if(pass.isNotEmpty() && confirmPass.isNotEmpty()){
                    if (pass == confirmPass){
                        binding.progressBar.visibility = View.VISIBLE
                        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                            if (it.isSuccessful){
                                val intent = Intent(this, MainActivity::class.java)
                                Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_LONG).show()
                                binding.progressBar.visibility = View.GONE
                                startActivity(intent)
                            }else{
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                            }
                        }
                    }else{
                        Toast.makeText(this, "Password id not Matching", Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(this, "Empty Fields Are no Allowed", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "Invalid or Empty Email", Toast.LENGTH_LONG).show()
            }
        }

        binding.haveAccount.setOnClickListener {
            finish()
        }

    }
}