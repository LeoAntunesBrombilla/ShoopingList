package com.example.shoopinglist.ui.addItem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shoopinglist.R
import com.example.shoopinglist.data.Item
import com.example.shoopinglist.databinding.ActivityItemRegistrationBinding
import com.example.shoopinglist.ui.main.MainActivity

class ItemRegistration : AppCompatActivity() {

    private lateinit var binding: ActivityItemRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemRegistrationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        listenerConfig()
    }

    private fun listenerConfig() {
        configListenerCancelBtn()
        configListenerAddBtn()
    }

    private fun configListenerAddBtn() {
        binding.addItemBtn.setOnClickListener {
            saveItem()
        }
    }

    private fun saveItem() {

        binding.apply {
            val item = tiProductName.text.toString()
            val quantityOfItem = quantity.text.toString()

            tiProductName.error = if (item.isEmpty()) {
                getString(R.string.error_on_item)
            } else {
                null
            }

            quantity.error = if(quantityOfItem.isEmpty()) {
                getString(R.string.error_on_quantity)
            } else {
                null
            }

            if(item.isNotEmpty() && quantityOfItem.isNotEmpty()) {
                Intent().apply {
                    putExtra(MainActivity.RETORNO, Item(item, quantityOfItem.toFloat()))
                    setResult(RESULT_OK, this)
                }
                finish()
            }

        }

    }

    private fun configListenerCancelBtn() {
        binding.cancelButton.setOnClickListener {
            finish()
        }
    }
}