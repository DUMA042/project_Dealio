package com.example.dealio.network.setups.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Product(val barcodeNumber: String,
                   val barcodeFormats: String,
                   val mpn: String,
                   val model: String,
                   val asin: String,
                   val title: String,
                   val category: String,
                   val manufacturer: String,
                   val brand: String,
                   val contributors: List<Any>,
                   val ageGroup: String,
                   val ingredients: String,
                   val nutritionFacts: String,
                   val color: String,
                   val format: String,
                   val multipack: String,
                   val size: String,
                   val length: String,
                   val width: String,
                   val height: String,
                   val weight: String,
                   val releaseDate: String,
                   val description: String,
                   val features: List<String>,
                   val images: List<String>,
                   val stores: List<Store>,
                   val reviews: List<Review>)
