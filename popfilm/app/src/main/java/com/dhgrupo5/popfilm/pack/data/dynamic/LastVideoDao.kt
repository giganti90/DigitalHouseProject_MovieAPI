package com.dhgrupo5.popfilm.data.dynamic

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dhgrupo5.popfilm.model.LastVideo


@Dao
interface LastVideoDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    fun insert( lastVideo: LastVideo)

    @Query( value = "SELECT * FROM LastVideo LIMIT 1" )
    fun get() : LastVideo?

    @Query( value = "DELETE FROM LastVideo" )
    fun delete()
}