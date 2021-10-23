/*
* Author:       Zhencheng Chen
* Program:      Mini Project
* Course:       Mobile App Development II
* Date:         10/23/2021
* */

package com.chen.zhencheng.miniproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.text.Editable
import android.text.TextWatcher
import android.widget.SeekBar
import com.chen.zhencheng.miniproject.databinding.FragmentCalculatorBinding
import kotlin.math.pow

class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)

        binding.interestRateBar.setOnSeekBarChangeListener(interestRateSeekBarWatcher)
        binding.loanAmountEdit.addTextChangedListener(loanAmountEditWatcher)

        return binding.root
    }

    //Raise the event everytime the textEdit's value is changed
    private val loanAmountEditWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            //using if/else to avoid NaN
            if(binding.interestRateBar.progress == 0)
                changeText(1)
            else
                changeText(binding.interestRateBar.progress)
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }//loanAmountEditWatcher

    //Raise the event everytime the SeekBar's value is changed
    private val interestRateSeekBarWatcher = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
            // Display the current progress of SeekBar
            binding.interestRateText.text = "$i%"

            //avoid NaN in this calculation
            if(i == 0) {
                changeText(1)
            }
            else{
                changeText(i)
            }
        }
        override fun onStartTrackingTouch(seekBar: SeekBar) {
        }
        override fun onStopTrackingTouch(seekBar: SeekBar) {
        }
    }//interestRateSeekBarWatcher

    //Change the textView's texts
    fun changeText(i : Int) {
        val amount : Double = try{
            binding.loanAmountEdit.text.toString().toDouble()
        } catch(e:NumberFormatException) {
            0.0
        }

        var emi : Double = calculateEMI(5, i, amount)
        var total : Double = emi * 5 * 12
        binding.yearFiveEMIText.setText(String.format("$%.2f", emi))
        binding.yearFiveTotalAmountText.setText(String.format("$%.2f", total))

        emi = calculateEMI(10, i, amount)
        total = emi * 10 * 12
        binding.yearTenEMIText.setText(String.format("$%.2f", emi))
        binding.yearTenTotalAmountText.setText(String.format("$%.2f", total))

        emi = calculateEMI(15, i, amount)
        total = emi * 15 * 12
        binding.yearFifteenEMIText.setText(String.format("$%.2f", emi))
        binding.yearFifteenTotalAmountText.setText(String.format("$%.2f", total))

        emi = calculateEMI(20, i, amount)
        total = emi * 20 * 12
        binding.yearTwentyEMIText.setText(String.format("$%.2f", emi))
        binding.yearTwentyTotalAmountText.setText(String.format("$%.2f", total))


        emi = calculateEMI(25, i, amount)
        total = emi * 25 * 12
        binding.yearTwentyFiveEMIText.setText(String.format("$%.2f", emi))
        binding.yearTwentyFiveTotalAmountText.setText(String.format("$%.2f", total))

        emi = calculateEMI(30, i, amount)
        total = emi * 30 * 12
        binding.yearThirtyEMIText.setText(String.format("$%.2f", emi))
        binding.yearThirtyTotalAmountText.setText(String.format("$%.2f", total))
    }//changeText

    //calculate the EMI
    private fun calculateEMI(year : Int, rate: Int, amount : Double) : Double{
        //EMI = ( P × r × (1+r)n ) / ((1+r)n − 1)
        val interest = rate.toDouble() / 1200
        val period = year * 12
        return (amount * interest * (1.0 + interest).pow(period)) / ((1.0 + interest).pow(period) - 1)
    }//calculateEMI
}