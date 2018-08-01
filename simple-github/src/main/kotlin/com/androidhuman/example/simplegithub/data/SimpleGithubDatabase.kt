package com.androidhuman.example.simplegithub.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.androidhuman.example.simplegithub.api.model.GithubRepo

/**
 * Created by tura on 2018-08-01.
 */

@Database(entities = [(GithubRepo::class)], version = 1)
abstract class SimpleGithubDatabase : RoomDatabase() {
    abstract fun searchHistoryDao(): SearchHistoryDao
}