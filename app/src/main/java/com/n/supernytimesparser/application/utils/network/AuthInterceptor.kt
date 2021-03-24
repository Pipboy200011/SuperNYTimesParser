package com.n.supernytimesparser.application.utils.network

import com.n.supernytimesparser.R
import com.n.supernytimesparser.application.utils.resource.ResourceManager
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 * Interceptor for adding apiKey
 *
 * @author Pavel Apanovskii on 23/03/2021.
 */
class AuthInterceptor(private val resourceManager: ResourceManager) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val newUrl: HttpUrl = request.url.newBuilder()
            .addQueryParameter("api-key", resourceManager.getString(R.string.api_key))
            .build()
        request = request.newBuilder()
            .url(newUrl)
            .build()
        return chain.proceed(request)
    }
}