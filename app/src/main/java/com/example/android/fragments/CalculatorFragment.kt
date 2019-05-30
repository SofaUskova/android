package com.example.android.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.android.R
import kotlinx.android.synthetic.main.fragment_calculator.*
import java.util.*

class CalculatorFragment : Fragment() {
    private val stackNumber = Stack<Int>()
    private val stackOperand = Stack<Char>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)

        view.findViewById<Button>(R.id.zero).setOnClickListener(this::getTextOnButton)
        view.findViewById<Button>(R.id.one).setOnClickListener(this::getTextOnButton)
        view.findViewById<Button>(R.id.two).setOnClickListener(this::getTextOnButton)
        view.findViewById<Button>(R.id.three).setOnClickListener(this::getTextOnButton)
        view.findViewById<Button>(R.id.four).setOnClickListener(this::getTextOnButton)
        view.findViewById<Button>(R.id.five).setOnClickListener(this::getTextOnButton)
        view.findViewById<Button>(R.id.six).setOnClickListener(this::getTextOnButton)
        view.findViewById<Button>(R.id.seven).setOnClickListener(this::getTextOnButton)
        view.findViewById<Button>(R.id.eight).setOnClickListener(this::getTextOnButton)
        view.findViewById<Button>(R.id.nine).setOnClickListener(this::getTextOnButton)

        view.findViewById<Button>(R.id.plus).setOnClickListener(this::getTextOnButton)
        view.findViewById<Button>(R.id.minus).setOnClickListener(this::getTextOnButton)
        view.findViewById<Button>(R.id.divide).setOnClickListener(this::getTextOnButton)
        view.findViewById<Button>(R.id.multiply).setOnClickListener(this::getTextOnButton)

        val equally = view.findViewById<Button>(R.id.equally)

        view.findViewById<Button>(R.id.cancel).setOnClickListener {
            result.text = ""
            expression.text = ""
        }

        equally.setOnClickListener(this::clickSolution)

        return view
    }

    private fun getTextOnButton(v: View) {
        val button = v as Button
        expression.append(button.text.toString())
    }

    private fun clickSolution(v: View) {
        val expressionFinal = expression.text.toString()
        if (!expressionFinal.matches(Regex("""(([0-9])+[+ - * /]([0-9])+)+"""))) {
            result.text = "0"
            return
        }

        var number = ""
        var results = 0

        for (char in expressionFinal) {
            if (char.isDigit()) {
                number += char
            } else {
                stackNumber.push(number.toInt())
                number = ""
                stackOperand.push(char)
            }
        }

        stackNumber.push(number.toInt())
        while (stackNumber.size != 1) {
            results = twoNumbers(stackNumber.pop(), stackNumber.pop(), stackOperand.pop())
            stackNumber.push(results)
        }

        result.text = results.toString()
        stackNumber.pop()
    }

    private fun twoNumbers(one: Int, two: Int, operand: Char): Int {
        return when (operand) {
            '*' ->
                two * one
            '/' ->
                two / one
            '+' ->
                two + one
            '-' ->
                two - one
            else ->
                0
        }
    }
}