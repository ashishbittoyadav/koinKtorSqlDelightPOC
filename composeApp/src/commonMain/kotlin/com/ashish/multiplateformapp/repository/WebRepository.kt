package com.ashish.multiplateformapp.repository

import com.ashish.multiplateformapp.apiHandler.ApiHandler
import com.ashish.multiplateformapp.model.Data
import com.ashish.multiplateformapp.model.UserResponse
import io.ktor.client.statement.bodyAsText


class WebRepository {
    //"https://jsondata.reactbd.com/api"
    suspend fun hitUrl(url : String): UserResponse{
//        return ApiHandler.hitUrl(url)?.call?.response?.bodyAsText()
        return ApiHandler.get<UserResponse>(url)
    }

}