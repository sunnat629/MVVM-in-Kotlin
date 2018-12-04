package com.sunnat629.mvvmroomexample.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.sunnat629.mvvmroomexample.repository.dao.UserDAO
import com.sunnat629.mvvmroomexample.model.Users


@Database(entities = [Users::class], version = 1)
abstract class UsersRoomDatabase: RoomDatabase(){
    abstract fun userDAO(): UserDAO

    // static members
    companion object {
        @Volatile private var INSTANCE: UsersRoomDatabase? = null

        fun getDatabase(context: Context): UsersRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(UsersRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        synchronized(UsersRoomDatabase::class){
                        // Create database here
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                            UsersRoomDatabase::class.java,
                            "mvvm_room_users")
                            .build()
                        }
                    }
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}