package com.clasemovil.sorting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.clasemovil.sorting.databinding.ActivityMainBinding
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val listNum = mutableListOf<Float>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // For button
        binding.addToListButton.setOnClickListener{ verifyNumberIn() }

        // In case of wanting to add number by clicking enter
        binding.inputNumber.setOnKeyListener(View.OnKeyListener{ _, keyCode, event ->
            if(keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP)
            { verifyNumberIn() }
            false
        })

        binding.ascButton.setOnClickListener{ sortList() }

        binding.descButton.setOnClickListener{ sortListDesc() }

        binding.userList.adapter = Adapter(this, listNum)

        binding.reset.setOnClickListener{ resetList() }

    }

    private fun verifyNumberIn(){
        val numInStr : String = binding.inputNumber.text.toString()

        // Remove dot and sign to check if it has 5 decimals
        val tempStr = numInStr.replace(".","").replace("-","")

        // Checks if range from 0 to 5
        if(tempStr.length in 0..5){
            val numIn : Float? = numInStr.toFloatOrNull()
            if(numIn != null) {
                listNum.add(numIn)

                // Update listview
                binding.userList.invalidate()
                binding.userList.adapter = Adapter(this, listNum)

                // Exit function
                return
            }
        }
        Toast.makeText(this, R.string.numError, Toast.LENGTH_LONG).show()
    }

    private fun sortList(){
        if(listNum.size > 1){
            binding.userList.invalidate()
            binding.userList.adapter = Adapter(this, listNum.sorted())
        }
        else Toast.makeText(this, R.string.sizeError, Toast.LENGTH_LONG).show()
    }

    private fun sortListDesc(){
        if(listNum.size > 1){
            binding.userList.invalidate()
            binding.userList.adapter = Adapter(this, listNum.sortedDescending())
        }
        else Toast.makeText(this, R.string.sizeError, Toast.LENGTH_LONG).show()
    }

    private fun resetList(){
        listNum.clear()
        binding.userList.invalidate()
        binding.userList.adapter = Adapter(this, listNum.sorted())
    }
}