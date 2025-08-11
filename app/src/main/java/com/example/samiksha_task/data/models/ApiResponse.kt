package com.example.assignmentholdings.models

data class ApiResponse(
    var data : Data? = Data()
)

data class Data(
    var userHolding : ArrayList<UserHolding> = arrayListOf()
)

data class UserHolding(
    var symbol   : String? = null,
    var quantity : Double?    = null,
    var ltp      : Double? = null,
    var avgPrice : Double?    = null,
    var close    : Double?    = null
)