package com.androidhuman.example.simplegithub.api.model

import com.google.gson.annotations.SerializedName

/**
 * not-null type으로 설정하더라도 서버에서 null을 반환할 수 있으므로 nullable type로 설정해야 함.
 */
class GithubRepo(val name: String,
                 @field:SerializedName("full_name") val fullName: String,
                 val owner: GithubOwner,
                 val description: String?,
                 val language: String?,
                 @field:SerializedName("updated_at") val updatedAt: String,
                 @field:SerializedName("stargazers_count") val stars: Int)
