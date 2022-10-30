package com.example.shoopinglist.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(var name: String, var quantity: Float): Parcelable