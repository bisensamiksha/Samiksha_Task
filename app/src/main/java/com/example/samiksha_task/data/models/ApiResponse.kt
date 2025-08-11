data class ApiResponse(
    var data : Data? = Data()
)

data class Data(
    var userHolding : ArrayList<UserHolding> = arrayListOf()
)

data class UserHolding(
    var symbol   : String,
    var quantity : Double,
    var ltp      : Double,
    var avgPrice : Double,
    var close    : Double
)