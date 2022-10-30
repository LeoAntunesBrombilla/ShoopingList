package com.example.shoopinglist.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.shoopinglist.R
import com.example.shoopinglist.data.Item
import com.example.shoopinglist.databinding.ActivityMainBinding
import com.example.shoopinglist.ui.addItem.ItemRegistration

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val dataReturn = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        if (activityResult.resultCode === RESULT_OK) {
            activityResult.data?.let {
                if (it.hasExtra(RETORNO)) {
                    Log.i("Debbug", "RETORNO: ${it.getParcelableExtra<Item>(RETORNO)}")
                    viewModel.saveItem(it.getParcelableExtra(RETORNO)!!)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initializeData()
        listenerConfig()
        observerConfig()
    }

    private fun observerConfig() {
        reciclerViewConfig()
    }

    private fun reciclerViewConfig() {
        viewModel.itemList.observe(this) { list ->
            listUpdate(list)
        }
    }

    private fun listUpdate(list: List<Item>) {
        if (list.isNullOrEmpty()) {
            binding.itemsList.visibility = View.GONE
            binding.tvMessageEmptyList.visibility = View.VISIBLE
        } else {
            binding.tvMessageEmptyList.visibility = View.GONE
            binding.itemsList.visibility = View.VISIBLE
            binding.itemsList.adapter = ItemsAdapter(itemList = list)
        }

    }

    private fun initializeData() {
        viewModel.initializeData()
    }

    private fun listenerConfig() {
        actionButtonConfig()
    }

    private fun actionButtonConfig() {
        binding.addActionBtn.setOnClickListener {
            Intent(this, ItemRegistration::class.java).let {
                dataReturn.launch(it)
            }
        }

        binding.addActionBtn.setOnLongClickListener {
            viewModel.clearItemList()
            Toast.makeText(
                this, R.string.success_clear,
                Toast.LENGTH_LONG
            ).show()
            it.isLongClickable
        }
    }

    companion object {
        const val RETORNO = "retorno frases"
    }
}