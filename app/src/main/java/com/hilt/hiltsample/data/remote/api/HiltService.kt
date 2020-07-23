package com.hilt.hiltsample.data.remote.api

import com.hilt.hiltsample.model.ProductsModel
import retrofit2.Response
import retrofit2.http.GET

interface HiltService {
    companion object {
        const val HILT_API_URL = "https://stark-spire-93433.herokuapp.com"
    }

    @GET("/json")
    suspend fun getProducts(): Response<ProductsModel>
}