package com.example.ecommerceapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.ecommerceapp.databinding.ActivityIntroBinding

open class IntroActivity : BaseActivity() {


    private lateinit var binding:ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityIntroBinding.inflate(layoutInflater)


        setContentView(binding.root)
        binding.startBtn.setOnClickListener {
            Log.d("IntroActivity", "Start button clicked!")
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}