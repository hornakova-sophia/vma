package sk.upjs.myapplication
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.annotation.SuppressLint
import android.text.InputType
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class MatematikaZaciatocnikActivity : AppCompatActivity() {
    var a: Int = 1
    var b: Int = 1
    var znamienko: String = ""
    var vysledok: Int = 2
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_textove)

        val textView = findViewById<TextView>(R.id.vstupy)
        textView.text = a.toString()+ " + " + b.toString() + " ="

        val vpisuj_pole = findViewById<EditText>(R.id.vpisuj_pole)
        vpisuj_pole.inputType = InputType.TYPE_CLASS_NUMBER

        val dalej_Button = findViewById<View>(R.id.dalej_Button)
        dalej_Button.setOnClickListener {
            random()
            textView.text = a.toString()+ " " + znamienko + " " + b.toString() + " ="
            vpisuj_pole.text.clear()
        }

        val skontroluj_Button = findViewById<View>(R.id.skontroluj_Button)
        skontroluj_Button.setOnClickListener {
            val userInput = vpisuj_pole.text.toString()
            if (userInput.equals(vysledok.toString())){
                showToast("Správne")
                random()
                textView.text = a.toString()+ " " + znamienko + " " + b.toString() + " ="
                vpisuj_pole.text.clear()
            }else{
                showToast("Nesprávne")
            }
        }
    }
    private fun random() {
        a =  kotlin.random.Random.nextInt(20) + 1
        b =  kotlin.random.Random.nextInt(20) + 1
        var list: List<String>
        if(prepinanie){
            list = listOf("+", "-")
        }else{
            list = listOf("+", "-",".","/")
        }
        znamienko = list.random()
        if((znamienko.equals("-") && a<b)){
            val c = a
            a=b
            b=c
        }
        if (znamienko.equals("/")){
            while(a % b != 0){
                a =  kotlin.random.Random.nextInt(20) + 1
                b =  kotlin.random.Random.nextInt(20)+ 1
            }
            vysledok= a / b
        }
        if(znamienko.equals("+")){
            vysledok= a + b
        }
        if(znamienko.equals("-")){
            vysledok= a - b
        }
        if(znamienko.equals(".")){
            vysledok= a * b
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}