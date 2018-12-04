package com.sunnat629.mvvmroomexample.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.sunnat629.mvvmroomexample.model.Users
import com.sunnat629.mvvmroomexample.repository.UsersRepository
import java.io.Serializable

open class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var mRepository: UsersRepository = UsersRepository(application)
    private var mAllUsers: LiveData<List<Users>>

    init {
        mAllUsers = mRepository.mAllUsers!!
    }

    fun getAllUsers(): LiveData<List<Users>>{
        return mAllUsers
    }

    open fun insert(users: Users){
        mRepository.insert(users)
    }
}