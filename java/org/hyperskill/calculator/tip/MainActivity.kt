package org.hyperskill.calculator.tip

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var value = "0"
        var new = ""
        edit_text.addTextChangedListener(object : TextWatcher {
            var old = ""
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                old = s?.toString() ?: ""
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                new = s?.toString() ?: ""
                update(new, value)
            }
        })
        slider.addOnChangeListener { _, x: Float, _ ->
            x.toInt().toString().also { value = it }
            update(new, value)
        }
    }
    private fun update (new: String, value: String) {
        if (new != "") {
            var tip = (new.toBigDecimal() * value.toBigDecimal() / BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)
            text_view.text = "Tip amount: $tip"
        }
    }
}

