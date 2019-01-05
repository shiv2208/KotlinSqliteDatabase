package com.kotlinsqlitedatabase.datamodel

data class UserModel(var name: String, val address: String, val phone: Long) {

    constructor(name: String, address: String, phone: Long, id: Int) : this(name, address, phone)
}