import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.samiksha_task.data.models.network.HoldingsRepository
import com.example.samiksha_task.data.models.network.RetrofitInstance
import kotlinx.coroutines.launch
import com.example.assignmentholdings.models.UserHolding


class HoldingViewModel: ViewModel() {

    var holdings by mutableStateOf<List<UserHolding>>(emptyList())
        private set

    private val repository = HoldingsRepository(RetrofitInstance.api)

    fun loadHoldings() {
        viewModelScope.launch {
            try {
                val data = repository.fetchHoldings()
                holdings = data.map { dto ->
                    UserHolding(
                        symbol = dto.symbol,
                        quantity = dto.quantity,
                        ltp = dto.ltp,
                        avgPrice = dto.avgPrice
                    )
                }
                Log.d("TESTEST", "${holdings}")
            } catch (e: Exception) {
                Log.d("TESTEST", "Exception")
                // TODO: handle errors
            }
        }
    }
}