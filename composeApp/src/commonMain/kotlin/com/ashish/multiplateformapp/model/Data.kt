package com.ashish.multiplateformapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val _id: Int,
    val address: Address,
    val company: Company,
    val email: String,
    val name: String,
    val phone: String,
    val role: String,
    val status: String,
    val username: String,
    val website: String
)