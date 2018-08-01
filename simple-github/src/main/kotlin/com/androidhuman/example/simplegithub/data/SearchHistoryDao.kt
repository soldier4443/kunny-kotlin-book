package com.androidhuman.example.simplegithub.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.androidhuman.example.simplegithub.api.model.GithubRepo
import io.reactivex.Flowable

/**
 * Created by tura on 2018-08-01.
 */

@Dao
interface SearchHistoryDao {
    // 이미 데이터가 있는 경우 덮어씌움
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(repo: GithubRepo)

    // Flowable - DB에 변경이 있으면 알림을 받아 항상 최신의 자료로 가져옴
    // Flowablw은 Observable과 비슷하지만 "Back Pressure"에 대처할 수 있음.
    // 백프레셔는 이벤트를 만들어내는 속도가 처리하는 속도보다 빠를 때 나타나는 현상
    // -> 아직 처리되지 않은 이벤트가 큐에 계속 쌓이게 됨.
    //
    // Flowable은 이럴 경우 최대한 쌓아두거나, 일부 이벤트를 버리는 방식으로 이를 해결함.
    // DB에서는 일반적으로 많은 수의 자료를 다루어서 백프레셔가 일어나기 쉽다고 함. 그래서 Flowable을 사용.
    @Query("SELECT * FROM repositories")
    fun getHistory() : Flowable<List<GithubRepo>>

    @Query("DELETE FROM repositories")
    fun clearAll()
}