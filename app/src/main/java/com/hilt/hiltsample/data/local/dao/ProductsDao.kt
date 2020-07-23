package com.hilt.hiltsample.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hilt.hiltsample.model.ProductsModel
import kotlinx.coroutines.flow.Flow
@Dao
interface ProductsDao {
    /**
     * Inserts [posts] into the [Post.TABLE_NAME] table.
     * Duplicate values are replaced in the table.
     * @param posts Posts
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: ProductsModel)

    /**
     * Deletes all the posts from the [Post.TABLE_NAME] table.
     */
    @Query("DELETE FROM ${ProductsModel.TABLE_NAME}")
    fun deleteAllPosts()

    /**
     * Fetches the post from the [Post.TABLE_NAME] table whose id is [postId].
     * @param postId Unique ID of [Post]
     * @return [Flow] of [Post] from database table.
     */
    @Query("SELECT * FROM ${ProductsModel.TABLE_NAME} WHERE ID = :postId")
    fun getPostById(postId: Int): Flow<ProductsModel>

    /**
     * Fetches all the posts from the [Post.TABLE_NAME] table.
     * @return [Flow]
     */
    @Query("SELECT * FROM ${ProductsModel.TABLE_NAME}")
    fun getAllProducts(): Flow<ProductsModel>
}