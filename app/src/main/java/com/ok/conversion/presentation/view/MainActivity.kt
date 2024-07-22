package com.ok.conversion.presentation.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.ok.conversion.App
import com.ok.conversion.databinding.ActivityMainBinding
import com.ok.conversion.domain.model.ExhangeUIState
import com.ok.conversion.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var mainViewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding
    private var amount = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        App.applicationComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.main
        setContentView(view)

        binding.selectBased.text = "From ${App.userPreferences.basedCode}"
        binding.selectTarget.text = "To ${App.userPreferences.targetCode}"

        binding.amount.editText?.doOnTextChanged{ inputText, _, _, _ ->
            amount = inputText.toString().toFloatOrNull() ?: 0f
            binding.convert.isEnabled = inputText!!.isNotEmpty()
        }

        binding.convert.setOnClickListener {
            mainViewModel.getExchangeAmount(
                App.userPreferences.basedCode,
                App.userPreferences.targetCode,
                amount
            )
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                mainViewModel.exchange.collect{ exchange ->
                    when (exchange) {
                        is ExhangeUIState.Error -> showException(exchange.exception)
                        is ExhangeUIState.Succes -> startExchangeActivity(
                            exchange.exchange,
                            App.userPreferences.targetCode
                        )
                        else -> {}
                    }
                }
            }
        }
    }

    private fun showException(exception: String){
        Snackbar
            .make(binding.main, exception, Snackbar.LENGTH_SHORT)
            .setAction("Close"){}
            .show()
    }

    private fun startExchangeActivity(exchange: Float, code: String){
        Intent(
            this,
            ExchangeActivity::class.java
        ).also {
            it.putExtra("Receive", exchange)
            it.putExtra("Code", code)
            startActivity(it)
        }
    }
}