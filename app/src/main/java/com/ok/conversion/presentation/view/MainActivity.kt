package com.ok.conversion.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.ok.conversion.App
import com.ok.conversion.R
import com.ok.conversion.domain.model.ExhangeUIState
import com.ok.conversion.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var mainViewModel: MainViewModel

    lateinit var amountTextView: TextInputLayout
    lateinit var basedButtonView: Button
    lateinit var targetButtonView: Button
    lateinit var convertButtonView: Button
    lateinit var view: View

    private var amount = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        App.applicationComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        amountTextView = this.findViewById(R.id.amount)
        basedButtonView = this.findViewById(R.id.selectBased)
        targetButtonView = this.findViewById(R.id.selectTarget)
        convertButtonView = this.findViewById(R.id.convert)
        view = this.findViewById(R.id.main)

        basedButtonView.text = "From ${App.userPreferences.basedCode}"
        targetButtonView.text = "To ${App.userPreferences.targetCode}"

        amountTextView.editText?.doOnTextChanged{ inputText, _, _, _ ->
            amount = inputText.toString().toFloatOrNull() ?: 0f
            convertButtonView.isEnabled = inputText!!.isNotEmpty()
        }

        convertButtonView.setOnClickListener {
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
                        is ExhangeUIState.Succes -> Log.d("Exhange", exchange.exchange.toString())
                        else -> {}
                    }
                }
            }
        }
    }

    fun showException(exception: String){
        Snackbar
            .make(view, exception, Snackbar.LENGTH_SHORT)
            .setAction("Close"){}
            .show()
    }
}