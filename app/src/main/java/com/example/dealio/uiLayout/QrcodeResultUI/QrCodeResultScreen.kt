package com.example.dealio.uiLayout.QrcodeResultUI



import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QrCodeResultScreen(
    qrCodeValue: String,
    onRestartCamera: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "QR Code: $qrCodeValue", fontSize = 20.sp, modifier = Modifier.padding(16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRestartCamera) {
            Text(text = "Scan Again")
        }
    }
}
