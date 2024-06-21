package sk.upjs.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        intent = Intent(this, BackgroundSoundService::class.java)
        startService(intent)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ucenie = findViewById<View>(R.id.ucenie)
        ucenie.setOnClickListener {
            val intent = Intent(this, UcenieActivity::class.java)
            startActivity(intent)
    }
        val hry = findViewById<View>(R.id.hry)
        hry.setOnClickListener {
            val intent = Intent(this, HryActivity::class.java)
            startActivity(intent)
        }
}
}