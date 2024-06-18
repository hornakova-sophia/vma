package sk.upjs.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
public var prepinanie = true
class UcenieActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ucenie)

        val return_Button = findViewById<View>(R.id.return_Button)
        return_Button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val Mat1_button = findViewById<View>(R.id.Mat1_button)
        Mat1_button.setOnClickListener {
            prepinanie = true
            val intent = Intent(this, MatematikaZaciatocnikActivity::class.java)
            startActivity(intent)
            finish()
    }
        val Mat2_button = findViewById<View>(R.id.Mat2_button)
        Mat2_button.setOnClickListener {
            prepinanie = false
            val intent = Intent(this, MatematikaZaciatocnikActivity::class.java)
            startActivity(intent)
            finish()
        }
        val Pismena_button = findViewById<View>(R.id.Pismena_button)
        Pismena_button.setOnClickListener {
            prepinanie = true
            val intent = Intent(this,KreslenieAcivity::class.java)
            startActivity(intent)
            finish()
        }
        val Cisla_button = findViewById<View>(R.id.Cisla_button)
        Cisla_button.setOnClickListener {
            prepinanie = false
            val intent = Intent(this,KreslenieAcivity::class.java)
            startActivity(intent)
            finish()
        }
        val preklad_Button = findViewById<View>(R.id.preklad_Button)
        preklad_Button.setOnClickListener {
            val intent = Intent(this, PrekladSKActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}