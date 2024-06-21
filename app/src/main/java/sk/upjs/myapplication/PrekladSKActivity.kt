package sk.upjs.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import jazyk.JazykViewModel
import android.widget.TextView
import androidx.lifecycle.viewModelScope
import jazyk.Jazyk
import kotlinx.coroutines.launch

class PrekladSKActivity : AppCompatActivity() {
    var idCislo: Int = 0
    var vysledok: String = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_textove)

        val jazykViewModel : JazykViewModel by viewModels {
            JazykViewModel.JazykViewModelFactory((application as
                    Aplikacia).jazykRepository)
        }
        val textView = findViewById<TextView>(R.id.vstupy)
        val vpisuj_pole = findViewById<EditText>(R.id.vpisuj_pole)

        vpisuj_pole.inputType = InputType.TYPE_CLASS_TEXT

        if(prepinanie){
            jazykViewModel.jazyky.observe(this, Observer { jazyky ->
                textView.text = jazyky[idCislo].slovensky
            })
        }else{
            jazykViewModel.jazyky.observe(this, Observer { jazyky ->
                textView.text = jazyky[idCislo].anglicky
            })
        }

        fun dalesie() {
            idCislo+=1
            jazykViewModel.jazyky.observe(this, Observer { jazyky ->
                if(idCislo>=jazyky.size){
                    idCislo=0
                }
                if(prepinanie){
                    textView.text = jazyky[idCislo].slovensky
                }
                if(!prepinanie){
                    textView.text = jazyky[idCislo].anglicky

                }
            })

            vpisuj_pole.text.clear()
        }

        val dalej_Button = findViewById<View>(R.id.dalej_Button)
        dalej_Button.setOnClickListener {
            dalesie()
        }


        val skontroluj_Button = findViewById<View>(R.id.skontroluj_Button)
        skontroluj_Button.setOnClickListener {
            val userInput = vpisuj_pole.text.toString()
            val userInputUpperCase = userInput.uppercase()
            if(prepinanie){
                jazykViewModel.jazyky.observe(this, Observer { jazyky ->
                    vysledok = jazyky[idCislo].anglicky
                })
            }else{
                jazykViewModel.jazyky.observe(this, Observer { jazyky ->
                    vysledok = jazyky[idCislo].slovensky
                })
            }

            if (userInputUpperCase.equals(vysledok)){
                showToast("Spravne")
                dalesie()
            }else{
                showToast("Nespravne")
            }
        }

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}