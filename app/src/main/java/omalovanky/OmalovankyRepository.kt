package omalovanky
import kotlinx.coroutines.flow.first
import java.util.UUID


class OmalovankyRepository(private val omalovankyDao: OmalovankyDao) {

    val omalovankyy = omalovankyDao.getAllOmalovanky()
    lateinit var omalovanky: Omalovanky

    suspend fun getByUUID(uuid: UUID) {
        omalovanky = omalovankyDao.getByUUID(uuid).first()
    }
    suspend fun delete(omalovanky: Omalovanky) {
        omalovankyDao.delete(omalovanky)
    }

}
