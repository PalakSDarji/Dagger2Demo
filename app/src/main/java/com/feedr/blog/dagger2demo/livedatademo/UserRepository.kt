package com.feedr.blog.dagger2demo.livedatademo

import androidx.lifecycle.MutableLiveData

class UserRepository{

    var userList = MutableLiveData<MutableList<User>>(mutableListOf())
    var searchedUserList = MutableLiveData<MutableList<User>>(mutableListOf())

    fun addUserToRepo(user: User){
        userList.value?.add(user)
        userList.postValue(userList.value)
    }

    fun searchUserByName(nameQuery : String) : MutableLiveData<MutableList<User>> {

        if(searchedUserList.value?.isNotEmpty()!!) searchedUserList.value?.clear()

        for(user in userList.value!!){
            if(user.name.equals(nameQuery)){
                searchedUserList.value?.add(user)
            }
        }

        return searchedUserList
    }

    fun getAllUsers() : MutableLiveData<MutableList<User>>{
        return userList
    }

    fun getUserById(selectedUserId: Int?) : MutableLiveData<User>? {

        for(user in userList.value!!){
            if(user.id == selectedUserId){
                return MutableLiveData(user)
            }
        }

        return null
    }
}