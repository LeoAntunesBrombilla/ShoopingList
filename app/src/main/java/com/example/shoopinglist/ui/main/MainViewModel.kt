package com.example.shoopinglist.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoopinglist.data.Item
import com.example.shoopinglist.data.repository.MemoryRepository

class MainViewModel: ViewModel() {
    private var memoryRepository: MemoryRepository = MemoryRepository(mutableListOf())

    private val _itemList = MutableLiveData<List<Item>>()

    val itemList: LiveData<List<Item>> = _itemList

    fun initializeData() {
        _itemList.value = memoryRepository.returnList()
    }

    fun saveItem(item: Item) {
       Log.i("AppLog", "Item recebido $item")

        memoryRepository.save(item)
        updateItemList()
    }

    fun clearItemList() {
        Log.i("AppLog", "Limpeza do repositório")
        memoryRepository.clearList()
        updateItemList()
    }

    private fun updateItemList() {
        _itemList.value = memoryRepository.returnList()
    }

}