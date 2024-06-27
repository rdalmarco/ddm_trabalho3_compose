package com.example.ddm_trabalho3.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ddm_trabalho3.data.dao.MaquinaDao
import com.example.ddm_trabalho3.data.dao.OrdemDao
import com.example.ddm_trabalho3.data.dao.UserDao
import com.example.ddm_trabalho3.domain.Maquina
import com.example.ddm_trabalho3.domain.Ordem
import com.example.ddm_trabalho3.domain.User

@Database(entities = [Ordem::class, Maquina::class, User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun maquinaDao() : MaquinaDao
    abstract fun ordemDao(): OrdemDao
    abstract fun userDao(): UserDao

    companion object {

        private const val DATABASE_NAME: String = "smartain-database8"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}