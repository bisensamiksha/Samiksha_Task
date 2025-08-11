import androidx.lifecycle.ViewModel
import com.example.assignmentholdings.models.UserHolding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue



class HoldingViewModel: ViewModel() {

    var holdings by mutableStateOf<List<UserHolding>>(emptyList())
        private set

    init {
        loadDummyData()
    }

    private fun loadDummyData() {
        holdings = listOf(
            UserHolding("TCS", 10, 3500.0, 30, 45 ),
            UserHolding("INFY", 5, 1500.0, 12, 15 ),
            UserHolding("HDFC", 8, 2800.0, 25, 35 )
        )
    }
}