package jazyk
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jazyk.Jazyk
import java.util.UUID
import kotlinx.coroutines.flow.Flow

@Dao
interface JazykDao {
    @Query("SELECT * FROM Jazyk")
    fun getAllJazyky(): Flow<List<Jazyk>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(Jazyk: Jazyk)

    @Delete
    suspend fun delete(Jazyk: Jazyk)

    @Query("DELETE FROM Jazyk WHERE uuid=:uuid")
    suspend fun delete(uuid: UUID)

    @Query("DELETE FROM Jazyk")
    suspend fun deleteAll()

    @Query("SELECT * FROM Jazyk WHERE uuid=:uuid")
    fun getByUUID(uuid: UUID): Flow<Jazyk>
}