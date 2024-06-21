package sk.upjs.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import androidx.activity.viewModels
import omalovanky.OmalovankyViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import androidx.lifecycle.Observer
public var pomocnaFarba = Color.parseColor("#000000")

class MalovanieActivity : AppCompatActivity(){

    private lateinit var customView: CustomView
    private lateinit var frameLayout: FrameLayout
    private lateinit var predosle_Button: ImageButton
    private lateinit var nasledujuce_Button: ImageButton
    private val STORAGE_PERMISSION_CODE = 1000
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        var cislo : Int = 0
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_malovanie)
        val omalovankyViewModel : OmalovankyViewModel by viewModels {
            OmalovankyViewModel.OmalovankyViewModelFactory((application as
                    Aplikacia).omalovankyRepository)
        }
        customView  = CustomView(this)
        frameLayout = findViewById<FrameLayout>(R.id.fragment_kreslenie)
        frameLayout.addView(customView)

        val nasledujuce_Button = findViewById<View>(R.id.nasledujuce_Button)
        val predosle_Button = findViewById<ImageButton>(R.id.predosle_Button)
        if(!prepinanie){
            val imageView = findViewById<ImageView>(R.id.Omalovanka_ImageView)
            fun obrazocek() {
                omalovankyViewModel.omalovankyy.observe(this, Observer { omalovankyy ->
                    if(cislo>=omalovankyy.size){
                        cislo=0
                    }
                    if(cislo==-1){
                        cislo=omalovankyy.size -1
                    }
                    var imageNameWithExtension:String=""
                    if ( omalovankyy.isEmpty()){
                        imageNameWithExtension = "o1"
                    }else{
                        imageNameWithExtension = omalovankyy[cislo].obrazok
                    }
                    val imageName = imageNameWithExtension.substringBeforeLast(".")
                    val drawableId = try {
                        R.drawable::class.java.getField(imageName).getInt(null)
                    } catch (e: Exception) {
                        R.drawable.o2
                    }
                    imageView.setImageResource(drawableId)
                })
                customView.clearDrawing()
            }
            obrazocek()

            nasledujuce_Button.setOnClickListener {
                cislo+=1
                obrazocek()
            }

            predosle_Button.setOnClickListener {
                cislo-=1
                obrazocek()
            }
        }else{
            predosle_Button.visibility = View.GONE
            nasledujuce_Button.visibility = View.GONE
        }


       val saveButton = findViewById<ImageButton>(R.id.save_Button)
        saveButton.setOnClickListener {
            checkPermission()
            saveFrameLayoutToGallery()
        }
        val zmazat_Button = findViewById<View>(R.id.zmazat_Button)
        zmazat_Button.setOnClickListener {
            customView.clearDrawing()
        }
        val cierna_button = findViewById<View>(R.id.cierna_button)
        cierna_button.setOnClickListener {
            pomocnaFarba = Color.parseColor("#000000")
            customView.updatePaint()
        }
        val siva_button = findViewById<View>(R.id.siva_button)
        siva_button.setOnClickListener {
            pomocnaFarba = Color.parseColor("#616161")
            customView.updatePaint()
        }
        val hneda_button = findViewById<View>(R.id.hneda_button)
        hneda_button.setOnClickListener {
            pomocnaFarba = Color.parseColor("#6A3F00")
            customView.updatePaint()
        }
        val cervena_button = findViewById<View>(R.id.cervena_button)
        cervena_button.setOnClickListener {
            pomocnaFarba = Color.parseColor("#B60000")
            customView.updatePaint()
        }
        val modra_tmava_button = findViewById<View>(R.id.modra_tmava_button)
        modra_tmava_button.setOnClickListener {
            pomocnaFarba = Color.parseColor("#1A2FA3")
            customView.updatePaint()
        }
        val modra_button = findViewById<View>(R.id.modra_button)
        modra_button.setOnClickListener {
            pomocnaFarba = Color.parseColor("#2196F3")
            customView.updatePaint()
        }
        val zelena_button = findViewById<View>(R.id.zelena_button)
        zelena_button.setOnClickListener {
            pomocnaFarba = Color.parseColor("#4CAF50")
            customView.updatePaint()
        }
        val zelena_tmava_button = findViewById<View>(R.id.zelena_tmava_button)
        zelena_tmava_button.setOnClickListener {
            pomocnaFarba = Color.parseColor("#036A61")
            customView.updatePaint()
        }

        val zlta_button = findViewById<View>(R.id.zlta_button)
        zlta_button.setOnClickListener {
            pomocnaFarba = Color.parseColor("#FFEB3B")
            customView.updatePaint()
        }
        val oranzova_button = findViewById<View>(R.id.oranzova_button)
        oranzova_button.setOnClickListener {
            pomocnaFarba = Color.parseColor("#FF5722")
            customView.updatePaint()
        }
        val ruzova_button = findViewById<View>(R.id.ruzova_button)
        ruzova_button.setOnClickListener {
            pomocnaFarba = Color.parseColor("#E61CD3")
            customView.updatePaint()
        }
        val fialova_button = findViewById<View>(R.id.fialova_button)
        fialova_button.setOnClickListener {
            pomocnaFarba = Color.parseColor("#673AB7")
            customView.updatePaint()
        }




    }
    private fun saveFrameLayoutToGallery() {
        // Create a bitmap from the FrameLayout
        val bitmap = Bitmap.createBitmap(frameLayout.width, frameLayout.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        frameLayout.draw(canvas)

        // Save the bitmap to the gallery
        val filename = "screenshot_${System.currentTimeMillis()}.jpg"
        var fos: OutputStream? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val resolver = contentResolver
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            }
            val imageUri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            if (imageUri != null) {
                fos = resolver.openOutputStream(imageUri)
            }
        } else {
            val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }
        fos?.use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            // Toast to show that the image has been saved
            Toast.makeText(this, "Saved to Gallery", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                requestPermissions(permissions, STORAGE_PERMISSION_CODE)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Permission was granted
                saveFrameLayoutToGallery()
            } else {
                // Permission was denied
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
    inner class CustomView(context: Context) : View(context) {
        private val paths = mutableListOf<Pair<Path, Paint>>()
        private var currentPath = Path()
        private var currentPaint = Paint().apply {
            color = pomocnaFarba
            strokeWidth = 10f
            style = Paint.Style.STROKE
        }

        init {
            paths.add(Pair(currentPath, currentPaint))
        }

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            for ((path, paint) in paths) {
                canvas.drawPath(path, paint)
            }
        }

        fun updatePaint() {
            currentPath = Path()
            currentPaint = Paint().apply {
                color = pomocnaFarba
                strokeWidth = 10f
                style = Paint.Style.STROKE
            }
            paths.add(Pair(currentPath, currentPaint))
        }

        fun clearDrawing() {
            paths.clear()
            currentPath = Path()
            currentPaint = Paint().apply {
                color = pomocnaFarba
                strokeWidth = 10f
                style = Paint.Style.STROKE
            }
            paths.add(Pair(currentPath, currentPaint))
            invalidate()
        }

        override fun onTouchEvent(event: MotionEvent): Boolean {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    currentPath.moveTo(event.x, event.y)
                }
                MotionEvent.ACTION_MOVE -> {
                    currentPath.lineTo(event.x, event.y)
                }
                MotionEvent.ACTION_UP -> {
                    // Optionally handle touch up
                }
            }
            invalidate()
            return true
        }
    }
}