package omalovanky
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.UUID
import kotlinx.coroutines.flow.Flow
import omalovanky.Omalovanky

@Dao
interface OmalovankyDao {
    @Query("SELECT * FROM Omalovanky")
    fun getAllOmalovanky(): Flow<List<Omalovanky>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(Omalovanky: Omalovanky)

    @Delete
    suspend fun delete(Omalovanky: Omalovanky)

    @Query("DELETE FROM Omalovanky WHERE uuid=:uuid")
    suspend fun delete(uuid: UUID)

    @Query("DELETE FROM Omalovanky")
    suspend fun deleteAll()

    @Query("SELECT * FROM Omalovanky WHERE uuid=:uuid")
    fun getByUUID(uuid: UUID): Flow<Omalovanky>
}
