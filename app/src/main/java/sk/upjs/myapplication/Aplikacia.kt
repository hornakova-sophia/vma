package sk.upjs.myapplication
import android.app.Application
import jazyk.JazykRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import omalovanky.OmalovankyRepository

class Aplikacia : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { Databaza.getDatabase(this,applicationScope) }
    val jazykRepository by lazy { JazykRepository(database.jazykDao()) }
    val omalovankyRepository by lazy { OmalovankyRepository(database.omalovankyDao()) }
}
