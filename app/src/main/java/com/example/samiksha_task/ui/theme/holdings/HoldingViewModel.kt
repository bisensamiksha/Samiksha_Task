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
import com.example.samiksha_task.data.models.Summary


class HoldingViewModel: ViewModel() {

    var holdings by mutableStateOf<List<UserHolding>>(emptyList())
        private set

    var summary by mutableStateOf<Summary?>(null)
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
                        avgPrice = dto.avgPrice,
                        close = dto.close
                    )
                }
                summary = calculateSummary(holdings)

                Log.d("TESTEST", "Holdings: $holdings")
                Log.d("TESTEST", "Summary: $summary")
            } catch (e: Exception) {
                Log.d("TESTEST", "Exception")
                // TODO: handle errors
            }
        }
    }

    private fun calculateSummary(data: List<UserHolding>): Summary {
        val currentValue = data.sumOf { it.quantity * it.ltp }
        val totalInvestment = data.sumOf { it.quantity * it.avgPrice }
        val totalPnl = currentValue - totalInvestment
        val todayPnl = data.sumOf { it.quantity * (it.ltp - it.close) }

        return Summary(currentValue, totalInvestment, totalPnl, todayPnl)
    }

}