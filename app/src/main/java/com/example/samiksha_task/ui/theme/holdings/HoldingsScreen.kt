import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HoldingsScreen(viewModel: HoldingViewModel = viewModel()) {
    val holdings = viewModel.holdings

    LaunchedEffect(Unit) {
        viewModel.loadHoldings()
    }

    LazyColumn {
        items(holdings) { holding ->
            Text("${holding.symbol} - Qty: ${holding.quantity} - LTP: ₹${holding.ltp}")
        }
    }
}

