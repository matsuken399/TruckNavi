package com.websarva.wings.android.trucknavi.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.websarva.wings.android.trucknavi.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

//    @Query("SELECT * FROM user_table ORDER BY id ASC")
    @Query("SELECT * FROM user_table WHERE date = :dateSet")
    fun readAllData(dateSet: Int): LiveData<List<User>>
}