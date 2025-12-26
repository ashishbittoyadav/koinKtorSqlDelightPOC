package com.ashish.multiplateformapp.repository

import com.ashish.db.AppDatabase
import com.ashish.multiplateformapp.database.toUser
import com.ashish.multiplateformapp.model.Data
import kotlinx.coroutines.flow.Flow

class UserRepository(
    private val database: AppDatabase
) {

//    fun observeUsers(): Flow<List<Data>> =
//        database.userQueries
//            .selectAllUsers()
//            .executeAsList()
//            .map { list -> list.map { it.toUser() } }

    fun observeUsers(): List<Data> {
        return database.userQueries.selectAllUsers()
            .executeAsList()
            .map {
                it.toUser()
            }
//        .map { list -> list.map { it.toUser() } }
    }

    suspend fun saveUsers(users: List<Data>) {
        database.userQueries.transaction {
            database.userQueries.deleteAllUsers()
            users.forEach { user ->
                database.userQueries.insertUser(
                    id = user._id.toLong(),
                    name = user.name,
                    username = user.username,
                    email = user.email,
                    street = user.address.street,
                    suite = user.address.suite,
                    city = user.address.city,
                    zipcode = user.address.zipcode,
                    lat = user.address.geo.lat,
                    lng = user.address.geo.lng,
                    phone = user.phone,
                    website = user.website,
                    companyName = user.company.name,
                    companyCatchPhrase = user.company.catchPhrase,
                    companyBs = user.company.bs,
                    role = user.role,
                    status = user.status
                )
            }
        }
    }
}
