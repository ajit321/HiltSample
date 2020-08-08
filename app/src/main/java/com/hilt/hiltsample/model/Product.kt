package com.hilt.hiltsample.model

import android.os.Parcelable
import androidx.room.*
import com.hilt.hiltsample.data.GenreConverter
import com.hilt.hiltsample.model.ProductsModel.Companion.TABLE_NAME
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable


@Entity(tableName = TABLE_NAME)
@JsonClass(generateAdapter = true)

@Serializable
@Parcelize
data class ProductsModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,
    @ColumnInfo(name = "categories")
    val categories: List<Category>,
    @ColumnInfo(name = "rankings")
    val rankings: List<Ranking>
) : Parcelable {
    companion object {
        const val TABLE_NAME = "product_posts"
    }
}

@Serializable
@Parcelize
data class Category(
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String
) : Parcelable

@Serializable
@Parcelize
data class Ranking(
    @ColumnInfo(name = "ranking")
    val ranking: String

) : Parcelable

@Serializable
data class Product(
    val date_added: String,
    val id: Int,
    val name: String,
    val tax: Tax,
    val variants: List<Variant>
)

@Serializable
data class Tax(
    val name: String,
    val value: Double
)

@Serializable
data class Variant(
    val color: String,
    val id: Int,
    val price: Int,
    val size: Int
)

@Serializable
data class RankingProduct(
    val id: Int,
    val order_count: Int,
    val shares: Int,
    val view_count: Int
)