package com.dicoding.myunlimitedquotes.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeys>)

    @Query("SELECT * from remote_keys where id = :id")
    suspend fun getRemoteKeyById(id: String): RemoteKeys

    @Query("DELETE FROM remote_keys")
    suspend fun deleteRemoteKeys()
}