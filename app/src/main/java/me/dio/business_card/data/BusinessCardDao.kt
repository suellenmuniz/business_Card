package me.dio.business_card.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface BusinessCardDao {

    @Query("SELECT * FROM card")
    fun getAll(): LiveData<List<Card>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(card: Card)

    @Delete
    fun delete(card: Card)
}