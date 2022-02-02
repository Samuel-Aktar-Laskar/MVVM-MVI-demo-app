package com.example.playground.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Blogs")
class BlogCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id : Int,

    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "body")
    var body: String,
    @ColumnInfo(name = "category")
    var category: String,
    @ColumnInfo(name = "image")
    var image: String,


    ){


}