package com.example.dealio.network.setups.models



import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Store(val name: String,
                 val price: String,
                 val link: String,
                 val currency: String,
                 val currencySymbol: String)
