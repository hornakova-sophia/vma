package omalovanky
import androidx.room.Entity
// import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.UUID

@Entity(tableName = "Omalovanky")
data class Omalovanky(
    val nazov: String,
    val obrazok: String
) : Serializable {

    @PrimaryKey
    var uuid: UUID = UUID.randomUUID()


}
