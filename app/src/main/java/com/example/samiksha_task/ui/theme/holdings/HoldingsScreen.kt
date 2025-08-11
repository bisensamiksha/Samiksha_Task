import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.samiksha_task.data.models.Summary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HoldingsScreen(viewModel: HoldingViewModel = viewModel()) {
    val holdings = viewModel.holdings
    val summary = viewModel.summary

    var showSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)


    LaunchedEffect(Unit) {
        viewModel.loadHoldings()
    }


    Scaffold(
        bottomBar = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = { showSheet = !showSheet }
            ) {
                Text(if (showSheet) "Close Summary" else "Open Summary")
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {

            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text(
                    text = "Portfolio",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp, top = 35.dp),
                    color = Color(0xFF4CAF50)
                )

                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(holdings) { holding ->
                        HoldingItem(holding)
                        Divider(modifier = Modifier.padding(vertical = 8.dp))
                    }
                }
            }

            Log.d("TESTTEST", "$showSheet and $summary")
            // Bottom sheet for summary
            if (showSheet && summary != null) {
                ModalBottomSheet(
                    onDismissRequest = { showSheet = false },
                    sheetState = sheetState
                ) {
                    SummarySection(summary)
                }
            }
        }
    }
}

@Composable
fun HoldingItem(holding: UserHolding) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = holding.symbol,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text("LTP: ₹${holding.ltp}", style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("NET QTY: ${holding.quantity}", style = MaterialTheme.typography.bodySmall)
            //Text("LTP: ₹${holding.ltp}", style = MaterialTheme.typography.bodySmall)
            val pnlColor = if (holding.ltp >= 0) Color(0xFF4CAF50) else Color(0xFFF44336) //check holding.pln
            Text(
                text = "P&L: ₹${"%.2f".format(holding.ltp)}", //holding.pnl
                color = pnlColor,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun SummarySection(summary: Summary) {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        SummaryRow("Current value", summary.currentValue, null, false, summary.totalInvestment)
        SummaryRow("Total investment", summary.totalInvestment, null, false, summary.totalInvestment)
        SummaryRow("Today's Profit & Loss", summary.todayPnl, summary.todayPnl, false, summary.totalInvestment)
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            thickness = 1.dp,
            color = Color.Gray
        )
        SummaryRow("Profit & Loss", summary.totalPnl, summary.totalPnl, true, summary.totalInvestment,)
    }
}

@Composable
fun SummaryRow(label: String, amount: Double, colorValue: Double?, isBold: Boolean = false, totalInvestmentForPercentage: Double?) {
    val pnlColor = when {
        colorValue == null -> Color.Unspecified
        colorValue >= 0 -> Color(0xFF4CAF50) // green
        else -> Color(0xFFF44336) // red
    }
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal)
        Text(
            text = "₹${"%.2f".format(amount)}" +
                    if (isBold && totalInvestmentForPercentage != null && totalInvestmentForPercentage != 0.0) {
                        " (${String.format("%.2f", amount / totalInvestmentForPercentage * 100)}%)"
                    } else {
                        ""
                    },
            color = pnlColor,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
        )
    }
}




