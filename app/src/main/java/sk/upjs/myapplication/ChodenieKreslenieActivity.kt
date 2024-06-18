package sk.upjs.myapplication
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class ChodenieKreslenieActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var customView: CustomView
    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        customView = CustomView(this)
        setContentView(customView)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onResume() {
        super.onResume()
        accelerometer?.also { accelerometer ->
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Not used in this example
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            if (it.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                val x = it.values[0]
                val y = it.values[1]

                // Adjust path movement based on accelerometer data
                customView.updatePath(-x, y) // Invert x for natural drawing orientation
            }
        }
    }

    inner class CustomView(context: Context) : View(context) {

        private val paint = Paint().apply {
            color = resources.getColor(android.R.color.holo_blue_dark, null)
            strokeWidth = 10f
            style = Paint.Style.STROKE
        }
        private val path = Path()
        private var isPathInitialized = false
        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            canvas.drawPath(path, paint)
        }
        override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
            super.onSizeChanged(w, h, oldw, oldh)
            if (!isPathInitialized) {
                path.moveTo(width / 2f, height / 2f)
                isPathInitialized = true
            }
        }
        fun updatePath(deltaX: Float, deltaY: Float) {
            if (isPathInitialized) {
                // Calculate new position based on accelerometer data
                val newX = (width / 2 + deltaX * 50)
                val newY = (height / 2 + deltaY * 100)
                path.lineTo(newX, newY)
                invalidate()
            }
        }
    }
}
