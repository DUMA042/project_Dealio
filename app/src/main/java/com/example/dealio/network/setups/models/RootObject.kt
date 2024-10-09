package com.example.dealio.network.setups.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RootObject(val products: List<Product>)
