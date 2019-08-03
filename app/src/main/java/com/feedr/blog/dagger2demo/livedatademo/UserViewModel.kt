package com.feedr.blog.dagger2demo.livedatademo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    private val query = MutableLiveData<String>()
    var userRepository = UserRepository()
    private var userData = MutableLiveData<User>()
    private lateinit var listOfUsers :MutableLiveData<MutableList<User>>;

    init {
        listOfUsers = userRepository.getAllUsers()

    }
    var userDataTrans : LiveData<String> = Transformations.map(userData) {
            user -> "user ${user.name} added"
    }

    var userSearchDataTrans : LiveData<List<User>> = Transformations.map(query){
        userRepository.searchUserByName(it).value
    }

    fun addUser(user: User) {
        userData.value = user
    }

    fun addUserToList(user: User){
        userRepository.addUserToRepo(user)
    }

    fun searchUserByName(name : String) = apply { query.value = name }

    fun getAllUsers() : MutableLiveData<MutableList<User>>{
        return listOfUsers
    }
}