package com.ashish.multiplateformapp.apiHandler

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object ApiHandler {

    val client=
        HttpClient(CIO){
            install(Logging){
                logger = Logger.DEFAULT
                level = LogLevel.HEADERS
                filter { request ->
                    request.url.host.contains("ktor.io")
                }
                sanitizeHeader { header -> header == HttpHeaders.Authorization }
            }

            install(ContentNegotiation){
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
        }

    suspend fun hitUrl(url : String): HttpResponse?{
        val response: HttpResponse = client.get(url)
        client.close()
        return if(response.status.value in 200..299){
            response
        }else{
            null
        }
    }

    suspend inline fun <reified T> get(url: String): T {
        return client.get(url).body()
    }
}