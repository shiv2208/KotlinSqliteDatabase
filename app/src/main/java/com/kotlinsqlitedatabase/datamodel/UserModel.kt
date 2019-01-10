package com.kotlinsqlitedatabase.datamodel

data class UserModel(var name: String, var address: String, var phone: Long) {

    lateinit var userName: String
    lateinit var userAddress: String
    var userPhone: Long = 0
    var userID: Int = 0

    constructor(name: String, address: String, phone: Long, id: Int) : this(name, address, phone) {
        this.userName = name
        this.userAddress = address
        this.userPhone = phone
        this.userID = id
    }
}