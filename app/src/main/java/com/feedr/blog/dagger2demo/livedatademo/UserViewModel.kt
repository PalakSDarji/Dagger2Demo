package com.feedr.blog.dagger2demo.livedatademo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    private val query = MutableLiveData<String>()
    var userRepository = UserRepository()
    private var userData = MutableLiveData<User>()
    private var listOfUsers = userRepository.getAllUsers()

    var userDataTrans: LiveData<String?> = Transformations.map(listOfUsers) {
        userList ->
        run{
            if(!userList.isNullOrEmpty()){
                "user ${userList[userList.size-1].name} added"
            }
            else null
        }
    }

    var userSearchDataTrans : LiveData<MutableList<User>> = Transformations.switchMap(query){
        userRepository.searchUserByName(it)
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

    fun getSearchUserList() : LiveData<MutableList<User>>{
        return userSearchDataTrans
    }
}