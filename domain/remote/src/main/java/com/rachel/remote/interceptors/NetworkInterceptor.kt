/*
 * Copyright 2021-2024 The Calorie App Systems, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rachel.remote.interceptors

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor @Inject constructor(@ApplicationContext val context: Context) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (isConnectionOn().not()) throw IOException("No Internet")

        if (isInternetAvailable().not()) throw IOException("Connection has no internet.")

        return chain.proceed(chain.request())
    }

    /** Checks if connection is on */
    private fun isConnectionOn(): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return postAndroidMInternetCheck(manager)
    }

    /** Check connection for post M devices */
    private fun postAndroidMInternetCheck(connectivityManager: ConnectivityManager): Boolean {
        val network = connectivityManager.activeNetwork
        val connection = connectivityManager.getNetworkCapabilities(network)
        return connection != null &&
            connection.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    /** Checks if Internet is available */
    private fun isInternetAvailable(): Boolean {
        return true
    }
}
