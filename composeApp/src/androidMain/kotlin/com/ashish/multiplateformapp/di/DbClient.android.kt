package com.ashish.multiplateformapp.di

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.ashish.db.AppDatabase

actual class DbClient(private val context: Context) {
    actual fun createDriver(): SqlDriver? {
        return AndroidSqliteDriver(
            schema = AppDatabase.Schema,
            context = context,
            name = "app.db"
        )
    }
}

// for ios
//return NativeSqliteDriver(
//schema = AppDatabase.Schema,
//name = "app.db"
//)