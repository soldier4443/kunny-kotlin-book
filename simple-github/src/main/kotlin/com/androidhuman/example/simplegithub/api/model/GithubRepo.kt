package com.androidhuman.example.simplegithub.api.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * not-null type으로 설정하더라도 서버에서 null을 반환할 수 있으므로 nullable type로 설정해야 함.
 */
@Entity(tableName = "repositories")
class GithubRepo(val name: String,

                 @SerializedName("full_name")
                 @PrimaryKey
                 @ColumnInfo(name = "full_name")
                 val fullName: String,

                 val owner: GithubOwner,
                 val description: String?,
                 val language: String?,

                 @SerializedName("updated_at")
                 @ColumnInfo(name = "updated_at")
                 val updatedAt: String,

                 @SerializedName("stargazers_count")
                 val stars: Int)
