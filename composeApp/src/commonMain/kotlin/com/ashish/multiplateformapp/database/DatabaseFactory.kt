package com.ashish.multiplateformapp.database

import com.ashish.db.AppDatabase
import com.ashish.multiplateformapp.di.DbClient

class DatabaseFactory(
    driverFactory: DbClient
) {
    val database: AppDatabase? = driverFactory.createDriver()?.let {
        AppDatabase(
            driver = it
        )
    }
}