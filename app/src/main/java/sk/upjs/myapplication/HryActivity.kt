package sk.upjs.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class HryActivity  : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hry)

        val malovanie_Button = findViewById<View>(R.id.malovanie_Button)
        malovanie_Button.setOnClickListener {
            prepinanie=true
            val intent = Intent(this, MalovanieActivity::class.java)
            startActivity(intent)
        }
        val omalovanky_button = findViewById<View>(R.id.omalovanky_button)
        omalovanky_button.setOnClickListener {
            prepinanie=false
            val intent = Intent(this, MalovanieActivity::class.java)
            startActivity(intent)
        }
        val pexeso_button = findViewById<View>(R.id.pexeso_button)
        pexeso_button.setOnClickListener {
            val intent = Intent(this, PexesoActivity::class.java)
            startActivity(intent)
        }

        val motorika_button = findViewById<View>(R.id.motorika_button)
        motorika_button.setOnClickListener {
            val intent = Intent(this,ChodenieKreslenieActivity::class.java)
            startActivity(intent)
        }
    }

    }