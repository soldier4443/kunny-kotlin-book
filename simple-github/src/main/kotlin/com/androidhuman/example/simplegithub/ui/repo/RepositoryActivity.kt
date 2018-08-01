package com.androidhuman.example.simplegithub.ui.repo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.androidhuman.example.simplegithub.R
import com.androidhuman.example.simplegithub.api.GithubApi
import com.androidhuman.example.simplegithub.api.provideGithubApi
import com.androidhuman.example.simplegithub.extensions.plusAssign
import com.androidhuman.example.simplegithub.ui.AutoClearedDisposable
import com.androidhuman.example.simplegithub.ui.GlideApp
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_repository.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class RepositoryActivity : AppCompatActivity() {

    companion object {
        const val KEY_USER_LOGIN = "user_login"

        const val KEY_REPO_NAME = "repo_name"
    }

    internal val api: GithubApi by lazy { provideGithubApi(this) }

    internal val disposables = AutoClearedDisposable(this)

    internal val dateFormatInResponse = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.getDefault())

    internal val dateFormatToShow = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        val login = intent.getStringExtra(KEY_USER_LOGIN)
            ?: throw IllegalArgumentException("No login info exists in extras")
        val repo = intent.getStringExtra(KEY_REPO_NAME)
            ?: throw IllegalArgumentException("No repo info exists in extras")

        showRepositoryInfo(login, repo)
    }

    private fun showRepositoryInfo(login: String, repoName: String) {
        disposables += api.getRepository(login, repoName)
            .subscribeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showProgress() }
//                .doOnTerminate { hideProgress() }
            .doOnError { hideProgress(true) }
            .doOnComplete { hideProgress(true) }
            .subscribe({ repo ->
                GlideApp.with(this@RepositoryActivity)
                    .load(repo.owner.avatarUrl)
                    .into(ivActivityRepositoryProfile)

                tvActivityRepositoryName.text = repo.fullName
                tvActivityRepositoryStars.text = resources.getQuantityString(R.plurals.star, repo.stars, repo.stars)
                tvActivityRepositoryDescription.text = repo.description ?: getString(R.string.no_description_provided)
                tvActivityRepositoryLanguage.text = repo.language ?: getString(R.string.no_language_specified)

                try {
                    val lastUpdate = dateFormatInResponse.parse(repo.updatedAt)
                    tvActivityRepositoryLastUpdate.text = dateFormatToShow.format(lastUpdate)
                } catch (e: ParseException) {
                    tvActivityRepositoryLastUpdate.text = getString(R.string.unknown)
                }

            }) { error ->
                showError(error.message)
            }
    }

    private fun showProgress() {
        llActivityRepositoryContent.visibility = View.GONE
        pbActivityRepository.visibility = View.VISIBLE
    }

    private fun hideProgress(isSucceed: Boolean) {
        llActivityRepositoryContent.visibility = if (isSucceed) View.VISIBLE else View.GONE
        pbActivityRepository.visibility = View.GONE
    }

    private fun showError(message: String?) {
        with(tvActivityRepositoryMessage) {
            text = message ?: "Unexpected Error."
            visibility = View.VISIBLE
        }
    }
}
