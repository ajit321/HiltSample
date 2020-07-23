package com.hilt.hiltsample.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hilt.hiltsample.data.GenreConverter
import com.hilt.hiltsample.data.local.dao.ProductsDao
import com.hilt.hiltsample.model.ProductsModel

@Database(
    entities = [ProductsModel::class],
    version = DatabaseMigrations.DB_VERSION,exportSchema = false
)
@TypeConverters(GenreConverter::class)
abstract class HiltSampleDatabase : RoomDatabase() {

    abstract fun getPostsDao(): ProductsDao

    companion object {
        private const val DB_NAME = "hilt_database"

        @Volatile
        private var INSTANCE: HiltSampleDatabase? = null

        fun getInstance(context: Context): HiltSampleDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HiltSampleDatabase::class.java,
                    DB_NAME
                )
                    .addMigrations(*DatabaseMigrations.MIGRATIONS)
                    .build()

                INSTANCE = instance
                return instance
            }
        }

    }
}