package co.karpenko.cats.data.db.cats

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.karpenko.cats.data.mapper.Cat

@Dao
interface CatsDao {

    @Query("SELECT * FROM cat ORDER BY name")
    fun postAllCats(): PagingSource<Int, Cat>

    @Query("SELECT * FROM cat  WHERE id = :id ORDER BY name")
    fun getFilteredCat(id: String): Cat

    @Query("SELECT * FROM cat WHERE id = :id")
    fun getCat(id: String): Cat

    @Query("SELECT DISTINCT countryCode FROM cat")
    fun getCountryCodes(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(listIds: List<Cat>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cat: Cat)

    @Query("DELETE FROM cat")
    suspend fun clearBooks()
}
