import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HoldingsScreen(viewModel: HoldingViewModel = viewModel()) {
    val holdings = viewModel.holdings

    LazyColumn {
        items(holdings) { holding ->
            Text(text = "${holding.symbol} - Qty: ${holding.quantity} - LTP: ₹${holding.ltp}")
        }
    }
}
