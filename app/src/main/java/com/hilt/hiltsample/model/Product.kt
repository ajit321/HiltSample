package com.hilt.hiltsample.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.hilt.hiltsample.data.GenreConverter
import com.hilt.hiltsample.model.ProductsModel.Companion.TABLE_NAME
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable


@Entity(tableName = TABLE_NAME)
@JsonClass(generateAdapter = true)
@Serializable
data class ProductsModel(

    @field:PrimaryKey(autoGenerate = true)
    var id: Int? = 0,
    @Embedded
    val categories: List<Category>,
    @Embedded
    val rankings: List<Ranking>
) {
    companion object {
        const val TABLE_NAME = "product_posts"
    }
    constructor() : this(0, emptyList(),emptyList())

}

@Serializable
data class Category(
    /*@TypeConverters(GenreConverter::class)
    @Nu
    val child_categories: List<Int>,*/
    val id: Int,
    val name: String
    /*@TypeConverters(GenreConverter::class)
    @NonNull
    @Embedded
    val products: List<Product>*/
){
    constructor() : this(0, "")
}

@Serializable
data class Ranking(
   /* @TypeConverters(GenreConverter::class)
    @Embedded
    val products: List<RankingProduct>,*/
    val ranking: String

){
    constructor() : this( "")
}

@Serializable
data class Product(
    val date_added: String,
    val id: Int,
    val name: String,
    val tax: Tax,
    @TypeConverters(GenreConverter::class)
    @Embedded
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