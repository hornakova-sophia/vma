package sk.upjs.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.annotation.SuppressLint
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import jazyk.JazykViewModel
import androidx.lifecycle.Observer

class ObrazoktextActivity : AppCompatActivity() {
    var cislo : Int = 0
    var cislo_buttona : Int = 0
    var cislo_buttona2 : Int = 0
    var cislo_buttona3 : Int = 0
    var cislo_buttona4 : Int = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_obrazok_text)
        val jazykViewModel : JazykViewModel by viewModels {
            JazykViewModel.JazykViewModelFactory((application as
                    Aplikacia).jazykRepository)
        }
        val imageView = findViewById<ImageView>(R.id.Obrazocek_ImageView)

        fun obrazocek() {
            jazykViewModel.jazyky.observe(this, Observer { jazyky ->
                if(cislo>=jazyky.size){
                    cislo=0
                }
                var imageNameWithExtension:String=""
                if ( jazyky.isEmpty()){
                    imageNameWithExtension = "o1"
                }else{
                    imageNameWithExtension = jazyky[cislo].obrazok
                }
                val imageName = imageNameWithExtension.substringBeforeLast(".")
                val drawableId = try {
                    R.drawable::class.java.getField(imageName).getInt(null)
                } catch (e: Exception) {
                    R.drawable.o1
                }
                imageView.setImageResource(drawableId)
            })
        }
        val button = findViewById<View>(R.id.button)as Button
        val button2 = findViewById<View>(R.id.button2)as Button
        val button3 = findViewById<View>(R.id.button3)as Button
        val button4 = findViewById<View>(R.id.button4)as Button
        fun random(){
            jazykViewModel.jazyky.observe(this, Observer { jazyky ->
                if (jazyky.isEmpty()) {
                    button.setText("")
                    button2.setText("")
                    button3.setText("")
                    button4.setText("")
                } else {
                    val indices = (0 until jazyky.size).shuffled().take(4)
                    val randomButtonIndex = kotlin.random.Random.nextInt(4) + 1

                    cislo_buttona = indices[0]
                    cislo_buttona2 = indices[1]
                    cislo_buttona3 = indices[2]
                    cislo_buttona4 = indices[3]
                    if (cislo_buttona!=cislo && cislo_buttona2!=cislo && cislo_buttona3!=cislo && cislo_buttona4!=cislo){
                        when (randomButtonIndex) {
                            1 -> cislo_buttona = cislo
                            2 -> cislo_buttona2 = cislo
                            3 -> cislo_buttona3 = cislo
                            4 -> cislo_buttona4 = cislo
                        }
                    }
                    if (prepinanie){
                        button.text = jazyky[cislo_buttona].slovensky
                        button2.text = jazyky[cislo_buttona2].slovensky
                        button3.text = jazyky[cislo_buttona3].slovensky
                        button4.text = jazyky[cislo_buttona4].slovensky
                    }else{
                        button.text = jazyky[cislo_buttona].anglicky
                        button2.text = jazyky[cislo_buttona2].anglicky
                        button3.text = jazyky[cislo_buttona3].anglicky
                        button4.text = jazyky[cislo_buttona4].anglicky
                    }

                }
            })
        }
        obrazocek()
        random()

        button.setOnClickListener {
            if(cislo==cislo_buttona){
                cislo+=1
                obrazocek()
                random()
            }else{
                showToast("Nesprávne")
            }
        }
        button2.setOnClickListener {
            if(cislo==cislo_buttona2){
                cislo+=1
                obrazocek()
                random()
            }else{
                showToast("Nesprávne")
            }
        }
        button3.setOnClickListener {
            if(cislo==cislo_buttona3){
                cislo+=1
                obrazocek()
                random()
            }else{
                showToast("Nesprávne")
            }
        }
        button4.setOnClickListener {
            if(cislo==cislo_buttona4){
                cislo+=1
                obrazocek()
                random()
            }else{
                showToast("Nesprávne")
            }
        }
        val dalej_Button = findViewById<View>(R.id.dalej_Button)
        dalej_Button.setOnClickListener {
            cislo+=1
            obrazocek()
            random()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}