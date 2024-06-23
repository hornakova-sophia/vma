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

                                it.jazykDao().insert(Jazyk("Zviera","MAČKA","CAT","t1"))
                                it.jazykDao().insert(Jazyk("Zviera","PES","DOG","t2"))
                                it.jazykDao().insert(Jazyk("Zviera","KÔŇ","HORSE","t3"))
                                it.jazykDao().insert(Jazyk("Zviera","PRASA","PIG","t4"))
                                it.jazykDao().insert(Jazyk("Zviera","RYBA","FISH","t5"))
                                it.jazykDao().insert(Jazyk("Zviera","HAD","SNAKE","t6"))
                                it.jazykDao().insert(Jazyk("Zviera","SLON","ELEPHANT","t7"))
                                it.jazykDao().insert(Jazyk("Zviera","OVCA","SHEEP","t8"))
                                it.jazykDao().insert(Jazyk("Zviera","ZAJAC","RABBIT","t9"))
                                it.jazykDao().insert(Jazyk("Zviera","KOZA","GOAT","t10"))
                                it.jazykDao().insert(Jazyk("Zviera","LEV","LION","t11"))
                                it.jazykDao().insert(Jazyk("Zviera","ŽIRAFA","GIRAPHE","t12"))

                                it.omalovankyDao().deleteAll()
                                it.omalovankyDao().insert(Omalovanky("o1"))
                                it.omalovankyDao().insert(Omalovanky("o2"))
                                it.omalovankyDao().insert(Omalovanky("o3"))
                                it.omalovankyDao().insert(Omalovanky("o4"))
                                it.omalovankyDao().insert(Omalovanky("o5"))
                                it.omalovankyDao().insert(Omalovanky("o6"))
                                it.omalovankyDao().insert(Omalovanky("o7"))
                                it.omalovankyDao().insert(Omalovanky("o8"))
                                it.omalovankyDao().insert(Omalovanky("o9"))
                                it.omalovankyDao().insert(Omalovanky("o10"))
                                it.omalovankyDao().insert(Omalovanky("o11"))
                                it.omalovankyDao().insert(Omalovanky("o12"))

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
