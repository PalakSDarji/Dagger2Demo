package com.feedr.blog.dagger2demo.livedatademo

import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class UserViewModel : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private var selectedUserId = MutableLiveData<Int>()
    private val query = MutableLiveData<String>()
    var userRepository = UserRepository()
    private var listOfUsers = userRepository.getAllUsers()

    //This is the way we can use Coroutines.
    fun getCurrentUserData(){
        launch(Dispatchers.Main){
            val currentUser= withContext(Dispatchers.IO) {
                //async call here...
            }
            //user currentUser in sync.
        }
    }

    var userDataTrans: LiveData<String?> = Transformations.map(listOfUsers) {
        userList ->
        run{
            if(!userList.isNullOrEmpty()){
                "user ${userList[userList.size-1].name} added."
            }
            else null
        }
    }

    private var userData : LiveData<User> = Transformations.switchMap(selectedUserId){
        selectedUserId ->
        run{
            userRepository.getUserById(selectedUserId)
        }
    }

    var userSearchDataTrans : LiveData<MutableList<User>> = Transformations.switchMap(query){
        userRepository.searchUserByName(it)
    }

    /*fun addUser(user: User) {
        userData.value = user
    }*/

    fun addUserToList(user: User){
        viewModelScope.launch(Dispatchers.IO){
            userRepository.addUserToRepo(user)
        }
    }

    fun searchUserByName(name : String) = apply { query.value = name }

    fun getAllUsers() : MutableLiveData<MutableList<User>>{
        return listOfUsers
    }

    fun getSearchUserList() : LiveData<MutableList<User>>{
        return userSearchDataTrans
    }

    fun setSelectedUserId(id: Int){
        selectedUserId.value = id
    }

    fun getUserData() : LiveData<User>{
        return userData
    }

}