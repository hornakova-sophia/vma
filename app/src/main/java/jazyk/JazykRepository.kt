package jazyk
import jazyk.Jazyk
import jazyk.JazykDao
import kotlinx.coroutines.flow.first
import java.util.UUID


class JazykRepository(private val jazykDao: JazykDao) {

    val jazyky = jazykDao.getAllJazyky()
    lateinit var jazyk: Jazyk

    suspend fun getByUUID(uuid: UUID) {
        jazyk = jazykDao.getByUUID(uuid).first()
    }
    suspend fun delete(jazyk: Jazyk) {
        jazykDao.delete(jazyk)
    }

}
