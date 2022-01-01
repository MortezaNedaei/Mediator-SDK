package org.tapsell.admediator.di

import org.tapsell.admediator.data.remote.api.ApiService


object ApiServiceModule {

    fun provideApiService(): ApiService =
        NetworkModule.provideRetrofit().create(ApiService::class.java)
}
