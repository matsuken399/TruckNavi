package com.websarva.wings.android.trucknavi.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var date: Int,
    var course: Int,
    var no: Int,
    var name: String,
    var lat: Int,
    var lng: Int
) : Parcelable