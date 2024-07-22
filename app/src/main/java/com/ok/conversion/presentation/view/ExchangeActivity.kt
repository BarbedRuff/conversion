package com.ok.conversion.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ok.conversion.databinding.ActivityExchangeBinding

class ExchangeActivity : AppCompatActivity() {
    lateinit var binding: ActivityExchangeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExchangeBinding.inflate(layoutInflater)
        val view = binding.main
        setContentView(view)

        binding.exchange.text = "You receive: ${intent.getFloatExtra("Receive", 0f)} ${intent.getStringExtra("Code")}"
        binding.back.setOnClickListener {
            finish()
        }
    }
}