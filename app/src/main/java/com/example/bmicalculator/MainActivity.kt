package com.example.bmicalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.example.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // click listener for button
        binding.btn.setOnClickListener{checkFields()}
    }
    private fun checkFields(){
        // get weight value from text field
        val weight = binding.weight.editText?.text.toString().toFloatOrNull()
        // get height value from text field
        val height = binding.height.editText?.text.toString().toFloatOrNull()

        if (weight==null){
            binding.height.error = null
            binding.weight.error = "This Field cannot be empty"
        }else if (height==null){
            binding.weight.error = null
            binding.height.error = "This Field cannot be empty"
        }else{
            binding.weight.error = null
            binding.height.error = null
            calculateBMI(weight,height)
        }
    }

    private fun calculateBMI(weight: Float, height: Float) {
        // Hide keyboard
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.weight.windowToken, 0)

        //convert height from cm to metre
        val heightInMeter = height/100
        //calculate BMI
        val result = weight/(heightInMeter*heightInMeter)

        //set result text view
        if(result<18.5){
            //underweight
        binding.weightResult.text = getString(R.string.underweight)
        }else if(result>25){
            //overweight
            binding.weightResult.text = getString(R.string.overweight)
        }else{
            //normal weight
            binding.weightResult.text = getString(R.string.normal)
        }

        //set result value text view
        binding.result.text = getString(R.string.decimal).format(result)
    }
}