package com.metra.nbaintegra

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.metra.nbaintegra.core.network.NetworkMonitor
import com.metra.nbaintegra.core.theme.NbaIntegraTheme
import com.metra.nbaintegra.core.ui.NbaNoInternetDialog
import com.metra.nbaintegra.navigation.NbaNavHost
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val networkMonitor: NetworkMonitor by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NbaIntegraTheme {
                val isOnline by networkMonitor.isOnline.collectAsStateWithLifecycle(
                    initialValue = true,
                )

                var isNoInternetDialogDismissed by rememberSaveable {
                    mutableStateOf(false)
                }

                LaunchedEffect(isOnline) {
                    if (isOnline) {
                        isNoInternetDialogDismissed = false
                    }
                }

                NbaNavHost()

                if (!isOnline && !isNoInternetDialogDismissed) {
                    NbaNoInternetDialog(
                        onOpenSettingsClick = {
                            startActivity(
                                Intent(Settings.ACTION_WIRELESS_SETTINGS),
                            )
                        },
                        onDismissClick = {
                            isNoInternetDialogDismissed = true
                        },
                    )
                }
            }
        }
    }
}
