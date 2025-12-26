package com.ashish.multiplateformapp.di

import app.cash.sqldelight.db.SqlDriver

actual class DbClient{
    actual fun createDriver(): SqlDriver? {
//        return NativeSqliteDriver(
//            schema = AppDatabase.Schema,
//            name = "app.db"
//        )
        return null
    }

}