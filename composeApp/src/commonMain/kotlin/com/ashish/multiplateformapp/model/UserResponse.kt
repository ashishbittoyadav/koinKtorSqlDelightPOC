package com.ashish.multiplateformapp.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val currentPage: Int,
    val `data`: List<Data>,
    val perPage: Int,
    val totalPages: Int,
    val totalUsers: Int
)