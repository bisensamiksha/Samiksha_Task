package com.example.assignmentholdings.models

data class UserHolding(
    var symbol   : String? = null,
    var quantity : Int?    = null,
    var ltp      : Double? = null,
    var avgPrice : Int?    = null,
    var close    : Int?    = null
)
