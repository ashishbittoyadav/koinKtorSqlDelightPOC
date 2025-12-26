package com.ashish.multiplateformapp.database

import com.ashish.db.UserEntity
import com.ashish.multiplateformapp.model.Address
import com.ashish.multiplateformapp.model.Company
import com.ashish.multiplateformapp.model.Data
import com.ashish.multiplateformapp.model.Geo

fun UserEntity.toUser(): Data {
    return Data(
        _id = id.toInt(),
        name = name,
        username = username,
        email = email,
        address = Address(
            street = street,
            suite = suite,
            city = city,
            zipcode = zipcode,
            geo = Geo(lat, lng)
        ),
        phone = phone,
        website = website,
        company = Company(
            name = companyName,
            catchPhrase = companyCatchPhrase,
            bs = companyBs
        ),
        role = role,
        status = status
    )
}
