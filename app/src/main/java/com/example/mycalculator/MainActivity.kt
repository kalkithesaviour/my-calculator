package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var lastNumeric = false
    private var lastDot = false
    private var lastEqual = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        if(!lastEqual) {
            tvInput.append((view as AppCompatButton).text)
            lastNumeric = true
        }
    }

    fun onClear(view: View) {
        tvInput.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view: View) {
        if(lastNumeric && !lastDot) {
            tvInput.append(".")
            lastDot = true
            lastNumeric = false
        }
    }

    fun onEqual(view: View) {
        if(lastNumeric) {
            var tvValue = tvInput.text.toString()
            var prefix = ""
            try {
                if(tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                when {
                    tvValue.contains("-") -> {
                        val splitValue = tvValue.split("-")

                        var one = splitValue[0]
                        val two = splitValue[1]

                        if(prefix.isNotEmpty()) {
                            one = prefix + one
                        }

                        tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())

                        lastEqual = true
                    }
                    tvValue.contains("+") -> {
                        val splitValue = tvValue.split("+")

                        var one = splitValue[0]
                        val two = splitValue[1]

                        if(prefix.isNotEmpty()) {
                            one = prefix + one
                        }

                        tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())

                        lastEqual = true
                    }
                    tvValue.contains("/") -> {
                        val splitValue = tvValue.split("/")

                        var one = splitValue[0]
                        val two = splitValue[1]

                        if(prefix.isNotEmpty()) {
                            one = prefix + one
                        }

                        tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())

                        lastEqual = true
                    }
                    tvValue.contains("*") -> {
                        val splitValue = tvValue.split("*")

                        var one = splitValue[0]
                        val two = splitValue[1]

                        if(prefix.isNotEmpty()) {
                            one = prefix + one
                        }

                        tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())

                        lastEqual = true
                    }
                }

            } catch(e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String): String {
        var value = ""
        if(result.contains(".0")) {
            value = result.substring(0, result.length - 2)
        }
        return value
    }
}