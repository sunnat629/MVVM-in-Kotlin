package com.sunnat629.mvvmroomexample.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.sunnat629.mvvmroomexample.repository.dao.UserDAO
import com.sunnat629.mvvmroomexample.db.UsersRoomDatabase
import com.sunnat629.mvvmroomexample.model.Users

class UsersRepository(application: Application) {
    private var database: UsersRoomDatabase ?= null

    private var mUserDAO: UserDAO  ?= null
    var mAllUsers: LiveData<List<Users>> ?= null

    init {
        database = UsersRoomDatabase.getDatabase(application)!!
        mUserDAO = database!!.userDAO()
        mAllUsers = mUserDAO!!.getAllUsers()
    }

    fun insert(users: Users){
        InsertAsyncTask(mUserDAO!!).execute(users)
    }

    companion object {
        class InsertAsyncTask(): AsyncTask<Users, Void, Void>() {
            private var mAsyncTaskDao: UserDAO ?= null

            constructor(userDAO: UserDAO): this(){
                mAsyncTaskDao = userDAO
            }

            override fun doInBackground(vararg params: Users?): Void? {
                mAsyncTaskDao!!.addNewUser(params[0]!!)
                return null
            }
        }
    }
}