package org.tapsell.sdk.di

import org.tapsell.sdk.data.remote.api.ApiService


object ApiServiceModule {

    fun provideApiService(): ApiService =
        NetworkModule.provideRetrofit().create(ApiService::class.java)
}
