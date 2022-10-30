package com.example.shoopinglist.data.repository

import com.example.shoopinglist.data.Item

class MemoryRepository(novaLista: MutableList<Item>) {
    private val listDb: MutableList<Item> = novaLista

    fun save(item: Item) {
        listDb.add(item)
    }

    fun clearList() {
        listDb.clear()
    }

    fun returnList() = listDb.toList()
}