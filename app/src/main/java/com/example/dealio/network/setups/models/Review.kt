package com.example.dealio.network.setups.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Review(val name: String,
                  val rating: String,
                  val title: String,
                  val review: String,
                  val date: String)
