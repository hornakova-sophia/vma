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

        val return_Button = findViewById<View>(R.id.return_Button)
        return_Button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val malovanie_Button = findViewById<View>(R.id.malovanie_Button)
        malovanie_Button.setOnClickListener {
            val intent = Intent(this, MalovanieActivity::class.java)
            startActivity(intent)
            finish()
        }

        val motorika_button = findViewById<View>(R.id.motorika_button)
        motorika_button.setOnClickListener {
            val intent = Intent(this,ChodenieKreslenieActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    }