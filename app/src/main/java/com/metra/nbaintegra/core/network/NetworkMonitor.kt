package com.metra.nbaintegra.core.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged

class NetworkMonitor(
    context: Context,
) {
    private val connectivityManager =
        context.applicationContext.getSystemService(ConnectivityManager::class.java)

    val isOnline: Flow<Boolean> =
        callbackFlow {
            trySend(isCurrentlyOnline())

            val callback =
                object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        trySend(isCurrentlyOnline())
                    }

                    override fun onLost(network: Network) {
                        trySend(isCurrentlyOnline())
                    }

                    override fun onCapabilitiesChanged(
                        network: Network,
                        networkCapabilities: NetworkCapabilities,
                    ) {
                        val hasInternet =
                            networkCapabilities.hasCapability(
                                NetworkCapabilities.NET_CAPABILITY_INTERNET,
                            )

                        val isValidated =
                            networkCapabilities.hasCapability(
                                NetworkCapabilities.NET_CAPABILITY_VALIDATED,
                            )

                        trySend(hasInternet && isValidated)
                    }
                }

            val request =
                NetworkRequest
                    .Builder()
                    .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    .build()

            connectivityManager.registerNetworkCallback(request, callback)

            awaitClose {
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged()

    private fun isCurrentlyOnline(): Boolean {
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        val hasInternet = capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        val isValidated = capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)

        return hasInternet && isValidated
    }
}
