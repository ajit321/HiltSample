package com.hilt.hiltsample.data.repository

import com.hilt.hiltsample.data.local.dao.ProductsDao
import com.hilt.hiltsample.data.remote.api.HiltService
import com.hilt.hiltsample.model.ProductsModel
import com.hilt.hiltsample.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class ProductRepository @Inject constructor(
    private val postsDao: ProductsDao,
    private val hiltService: HiltService
) {
    fun getAllProducts(): Flow<State<ProductsModel>> {
        return object : NetworkBoundRepository<ProductsModel, ProductsModel>() {

            override suspend fun saveRemoteData(response: ProductsModel) =
                postsDao.insertPosts(response)

            override fun fetchFromLocal(): Flow<ProductsModel> = postsDao.getAllProducts()

            override suspend fun fetchFromRemote(): Response<ProductsModel> = hiltService.getProducts()

        }.asFlow().flowOn(Dispatchers.IO)
    }
}