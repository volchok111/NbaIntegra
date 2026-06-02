package com.metra.nbaintegra.core.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.metra.nbaintegra.R

@Composable
fun NbaNoInternetDialog(
    onOpenSettingsClick: () -> Unit,
    onDismissClick: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissClick,
        title = {
            NbaText(
                text = stringResource(id = R.string.no_internet_title),
                style = MaterialTheme.typography.headlineMedium,
            )
        },
        text = {
            NbaText(
                text = stringResource(id = R.string.no_internet_message),
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        confirmButton = {
            TextButton(
                onClick = onOpenSettingsClick,
            ) {
                NbaText(
                    text = stringResource(id = R.string.no_internet_open_settings),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissClick,
            ) {
                NbaText(
                    text = stringResource(id = R.string.common_close),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        },
    )
}
