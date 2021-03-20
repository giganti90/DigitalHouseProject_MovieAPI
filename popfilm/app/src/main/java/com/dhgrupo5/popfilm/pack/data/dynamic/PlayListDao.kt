package com.dhgrupo5.popfilm.data.dynamic

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dhgrupo5.popfilm.model.PlayList


@Dao
interface PlayListDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    fun insertAll( playLists: List<PlayList> )


    @Query( value = "SELECT * FROM PlayList" )
    fun getAll() : List<PlayList>?

    @Query( value = "DELETE FROM PlayList" )
    fun deleteAll()
}