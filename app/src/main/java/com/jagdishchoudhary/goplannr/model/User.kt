package com.jagdishchoudhary.goplannr.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import java.io.Serializable

@Entity(tableName = "user_table")
class User(@field:ColumnInfo(name = "name")
           val name: String, @field:ColumnInfo(name = "phone")
           val phone: String, @field:ColumnInfo(name = "age")
           val age: Int?, @field:ColumnInfo(name = "salary")
           val salary: Int?) : Serializable {

    @PrimaryKey(autoGenerate = true)
    private val uid: Int = 0

}
