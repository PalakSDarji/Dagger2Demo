package com.feedr.blog.dagger2demo.livedatademo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.feedr.blog.dagger2demo.R
import kotlinx.android.synthetic.main.activity_live_data_demo.*
import kotlinx.android.synthetic.main.layout_user_item.view.*
import kotlin.random.Random

class LiveDataDemoActivity : AppCompatActivity() {

    private lateinit var userViewModel : UserViewModel
    private val userList: MutableList<User> = mutableListOf()
    private val userSearchList: MutableList<User> = ArrayList()
    private lateinit var userWholeAdapter: UserAdapter
    private lateinit var userSearchAdapter: UserAdapter

    private var i = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data_demo)

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        btnAdd.setOnClickListener {
            userViewModel.addUserToList(User(i,etName.text.toString(),12))
            i++
        }

        userViewModel.getAllUsers().observe(this, Observer {
            if(userList.isNotEmpty()) userList.clear()
            userList.addAll(it)
            userWholeAdapter.notifyDataSetChanged()
        })

        userViewModel.userDataTrans.observe(this, Observer {
            string ->
            run {
                if (!string.isNullOrEmpty()) {
                    Toast.makeText(this@LiveDataDemoActivity, string, Toast.LENGTH_LONG).show()
                }
            }
        })

        userViewModel.getSearchUserList().observe(this, Observer {
            if(userSearchList.isNotEmpty()) userSearchList.clear()
            userSearchList.addAll(it)
            userSearchAdapter.notifyDataSetChanged()
        })

        btnSearch.setOnClickListener{
            userViewModel.searchUserByName(etName.text.toString())
        }

        rcUsers.layoutManager = LinearLayoutManager(this)
        rcUsers.setHasFixedSize(true)
        userWholeAdapter = UserAdapter(this, userList)
        rcUsers.adapter = userWholeAdapter
        userWholeAdapter.onItemClick = { view,user ->

            when(view.id){
                R.id.tvUserName ->{
                    userViewModel.setSelectedUserId(user.id)
                    val intent = Intent(this@LiveDataDemoActivity,UserDetailActivity::class.java)
                    intent.putExtra("selectedUserId",user.id)
                    startActivity(intent)
                }
            }
        }

        rcSearchUsers.layoutManager = LinearLayoutManager(this)
        rcSearchUsers.setHasFixedSize(true)
        userSearchAdapter = UserAdapter(this, userSearchList)
        rcSearchUsers.adapter = userSearchAdapter
    }
}

class UserAdapter(activity: Activity,val userList: MutableList<User>) : RecyclerView.Adapter<UserAdapter.UserHolder>() {

    var onItemClick: ((View,User) -> Unit)? = null
    private var inflater = LayoutInflater.from(activity)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(inflater.inflate(R.layout.layout_user_item,parent,false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.tvUserName.text = userList[position].name
        holder.tvUserId.text = userList[position].id.toString()

    }

    inner class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val tvUserName: TextView = itemView.tvUserName
        val tvUserId : TextView = itemView.tvUserId

        init {
            tvUserName.setOnClickListener {
                onItemClick?.invoke(tvUserName,userList[adapterPosition])
            }
        }
    }
}
