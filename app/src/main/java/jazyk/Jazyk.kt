package jazyk
import androidx.room.Entity
// import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.UUID

@Entity(tableName = "Jazyk")
data class Jazyk(
    val kategoria: String,
    val slovensky: String,
    val anglicky: String,
    val obrazok: String
) : Serializable {

    @PrimaryKey
    var uuid: UUID = UUID.randomUUID()


}
