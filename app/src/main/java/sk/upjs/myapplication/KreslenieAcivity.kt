package sk.upjs.myapplication
import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Path
import android.widget.FrameLayout
import android.widget.TextView

class KreslenieAcivity : AppCompatActivity() {
    var pismeno: Int = 65
    var cislo : Int=0
    private lateinit var customView: CustomView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_obtahovanie)

        val textView = findViewById<TextView>(R.id.textView4)
        if(prepinanie){
            textView.text = pismeno.toChar().toString()
        }else{
            textView.text= cislo.toString()
        }

        customView  = CustomView(this)
        val frameLayout = findViewById<FrameLayout>(R.id.fragment_kreslenie)

        frameLayout.addView(customView)

        val return_Button = findViewById<View>(R.id.return_Button)
        return_Button.setOnClickListener {
            val intent = Intent(this, UcenieActivity::class.java)
            startActivity(intent)
            finish()
        }
        val nasledujuce_Button = findViewById<View>(R.id.nasledujuce_Button)
        nasledujuce_Button.setOnClickListener {
            customView.clearDrawing()
            if(prepinanie){
                pismeno += 1
                if(pismeno==91){
                    pismeno = 97
                }
                if(pismeno==123){
                    pismeno = 65
                }
                textView.text = pismeno.toChar().toString()
                customView.updatePismeno(pismeno)
            }else{
                cislo += 1
                if(cislo==100){
                    cislo =0
                }
                textView.text= cislo.toString()
                customView.updatePismeno(cislo)
            }


        }
        val predosle_Button = findViewById<View>(R.id.predosle_Button)
        predosle_Button.setOnClickListener {
            customView.clearDrawing()
            if(prepinanie){
                pismeno -= 1
                if(pismeno==64){
                    pismeno = 122
                }
                if(pismeno==96){
                    pismeno = 90
                }
                textView.text = pismeno.toChar().toString()
                customView.updatePismeno(pismeno)
            }else{
                cislo -= 1
                if(cislo==-1){
                    cislo =99
                }
                textView.text= cislo.toString()
                customView.updatePismeno(cislo)
            }


        }
        val zmazat_Button = findViewById<View>(R.id.zmazat_Button)
        zmazat_Button.setOnClickListener {
            customView.clearDrawing()
        }
    }

    inner class CustomView(context: Context) : View(context) {

        private val paint = Paint().apply {
            color = resources.getColor(android.R.color.holo_blue_dark, null)
            strokeWidth = 10f
            style = Paint.Style.STROKE
        }
        private val path = Path()

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            canvas.drawPath(path, paint)
        }
        fun updatePismeno(newPismeno: Int) {
            pismeno = newPismeno
            invalidate() // Vynutí prekreslenie CustomView
        }

        fun clearDrawing() {
            path.reset() // Vymaže cestu
            invalidate() // Prekreslí CustomView
        }
        override fun onTouchEvent(event: MotionEvent): Boolean {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    path.moveTo(event.x, event.y)
                }
                MotionEvent.ACTION_MOVE -> {
                    path.lineTo(event.x, event.y)
                }
                MotionEvent.ACTION_UP -> {
                    // Optionally handle touch up
                }
            }
            invalidate() // This will call onDraw to redraw the view
            return true
        }
    }
}
