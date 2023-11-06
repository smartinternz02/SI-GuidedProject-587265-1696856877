package com.myapp.moneytracker

data class TransactionModel(
    var transactionID: String? =null,
    var type: Int? =null,
    var title: String? =null,
    var category: String? =null,
    var amount: Double? =null,
    var date: Long? =null,
    var note: String? =null,
    var invertedDate: Long?=null
)