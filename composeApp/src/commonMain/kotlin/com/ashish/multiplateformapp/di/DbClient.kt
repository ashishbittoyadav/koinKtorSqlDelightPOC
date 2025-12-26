package com.ashish.multiplateformapp.di

import app.cash.sqldelight.db.SqlDriver

expect class DbClient {
    fun createDriver(): SqlDriver?
}