package com.websarva.wings.android.trucknavi.repository

import androidx.lifecycle.LiveData
import com.websarva.wings.android.trucknavi.NaviSettingActivity
import com.websarva.wings.android.trucknavi.data.UserDao
import com.websarva.wings.android.trucknavi.model.User

class UserRepository(private val userDao: UserDao) {

    val dateSet = 2
    val courseSet = 2

    val readAllData: LiveData<List<User>> = userDao.readAllData(dateSet = dateSet,courseSet = courseSet)

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUser() {
        userDao.deleteAllUsers()
    }
}