package com.ashish.multiplateformapp.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.ashish.db.AppDatabase
import com.ashish.multiplateformapp.database.DatabaseFactory
import com.ashish.multiplateformapp.repository.UserRepository
import com.ashish.multiplateformapp.repository.WebRepository
import com.ashish.multiplateformapp.viewModel.UserViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformModule: Module
fun appModule() = module{
    single { DatabaseFactory(get()).database }
    single<UserService> { UserServiceImpl(get()) }
    single<WebRepository> { WebRepository() }
    single<UserRepository> { UserRepository(get()) }
    viewModelOf(::UserViewModel)
}

sealed interface UserService{
    fun getUserName(): String
}

class UserServiceImpl(private val dbClient: DbClient) : UserService{
    override fun getUserName(): String {
        return "Ashish"
    }
}
