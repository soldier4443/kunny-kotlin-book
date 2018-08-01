package com.androidhuman.example.simplegithub.data

import android.arch.persistence.room.Room
import android.content.Context

/**
 * Created by tura on 2018-08-01.
 */

private var instance: SimpleGithubDatabase? = null

fun provideSearchHistoryDao(context: Context): SearchHistoryDao = provideDatabase(context).searchHistoryDao()

fun provideDatabase(context: Context): SimpleGithubDatabase {
    if (instance == null) {
        instance = Room.databaseBuilder(context,
            SimpleGithubDatabase::class.java, "simple_github.db")
            .build()
    }

    return instance!!
}