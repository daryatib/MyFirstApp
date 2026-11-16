package com.example.myfirstapp.presentation.appdetails

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfirstapp.ui.theme.MyFirstAppTheme

@Composable
fun InstallButton(
    installState: InstallState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val text = when (installState) {
        is InstallState.NotInstalled -> "Установить"
        is InstallState.Installing -> "Отменить (${installState.progress}%)"
        is InstallState.Installed -> "Установлено"
    }

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(vertical = 12.dp),
        enabled = installState !is InstallState.Installed,
        modifier = modifier,
    ) {
        Text(text = text)
    }
}

@Preview
@Composable
private fun Preview() {
    MyFirstAppTheme {
        InstallButton(
            installState = InstallState.Installing(45),
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}