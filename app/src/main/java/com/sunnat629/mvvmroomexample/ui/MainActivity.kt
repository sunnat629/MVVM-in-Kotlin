package com.sunnat629.mvvmroomexample.ui

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.sunnat629.mvvmroomexample.Config
import com.sunnat629.mvvmroomexample.R
import com.sunnat629.mvvmroomexample.model.Users
import com.sunnat629.mvvmroomexample.ui.adapter.UserListAdapter
import com.sunnat629.mvvmroomexample.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val requestCode: Int = 1

    private lateinit var mMainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel(application)::class.java)
        val userListAdapter = UserListAdapter(this)
        recyclerview.adapter = userListAdapter
        recyclerview.layoutManager =  LinearLayoutManager(this)


        mMainViewModel.getAllUsers().observe(this,
            Observer<List<Users>> {
                    userList -> userListAdapter.addUser(userList!!)
            })


        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewUserActivity::class.java)
            startActivityForResult(intent, requestCode)

            /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()*/
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == this.requestCode && resultCode == Activity.RESULT_OK){
            val users: Users = data!!.extras.getParcelable(Config().EXTRA_REPLY) as Users
            mMainViewModel.insert(users)
        }
    }
}
