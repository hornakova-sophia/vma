package jazyk
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.Flow

class JazykRepository(private val jazykDao: JazykDao) {

    val jazyky = jazykDao.getAllJazyky()
    lateinit var jazyk: Jazyk

    suspend fun getByUUID(uuid: Int) {
        jazyk = jazykDao.getByUUID(uuid).first()
    }
    suspend fun delete(jazyk: Jazyk) {
        jazykDao.delete(jazyk)
    }

    fun getAllJazyky(): Flow<List<Jazyk>> {
        return jazykDao.getAllJazyky()
    }

}
