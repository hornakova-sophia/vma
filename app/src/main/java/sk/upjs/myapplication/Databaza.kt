package sk.upjs.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import jazyk.Jazyk
import jazyk.JazykDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import omalovanky.Omalovanky
import omalovanky.OmalovankyDao
import java.util.UUID

@Database(entities = [Jazyk::class, Omalovanky::class], version = 1, exportSchema = false)
@TypeConverters(UuidConverter::class)
abstract class Databaza : RoomDatabase() {

    abstract fun jazykDao(): JazykDao
    abstract fun omalovankyDao(): OmalovankyDao
    companion object {
        @Volatile
        private var INSTANCE: Databaza? = null

        fun getDatabase(context: Context, scope: CoroutineScope): Databaza {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Databaza::class.java,
                    "VD_database"
                ).addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        INSTANCE?.let {
                            scope.launch {
                                it.jazykDao().deleteAll()
                                it.omalovankyDao().deleteAll()
                                it.jazykDao().insert(Jazyk("Zviera","MAČKA","CAT",""))
                                it.jazykDao().insert(Jazyk("Zviera","PES","DOG",""))
                                it.jazykDao().insert(Jazyk("Zviera","KÔŇ","HORSE",""))
                                it.jazykDao().insert(Jazyk("Zviera","PRASA","PIG",""))
                                it.jazykDao().insert(Jazyk("Zviera","RYBA","FISH",""))
                                it.jazykDao().insert(Jazyk("Zviera","HAD","SNAKE",""))
                                it.jazykDao().insert(Jazyk("Zviera","SLON","ELEPHANT",""))

                                it.omalovankyDao().insert(Omalovanky("o1"))
                                it.omalovankyDao().insert(Omalovanky("o2"))

                            }
                        }
                    }
                })
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}
class UuidConverter {

    @TypeConverter
    fun uuidToString(uuid: UUID) = uuid.toString()

    @TypeConverter
    fun stringToUuid(string: String) = UUID.fromString(string)
}
