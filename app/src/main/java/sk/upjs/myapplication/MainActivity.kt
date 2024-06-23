package sk.upjs.myapplication
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity(), SensorEventListener {

    private var sensorManager: SensorManager? = null
    private var lightSensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        intent = Intent(this, BackgroundSoundService::class.java)
        startService(intent)

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
        val nastavenia = findViewById<View>(R.id.nastavenia)
        nastavenia.setOnClickListener {
            val intent = Intent(this, NastaveniaActivity::class.java)
            startActivity(intent)
        }
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lightSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_LIGHT)

        if (lightSensor == null) {
            showToast("No light sensor available")
        }
    }

    override fun onResume() {
        super.onResume()
        lightSensor?.also { sensor ->
            sensorManager?.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager?.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {

        if (event?.sensor?.type == Sensor.TYPE_LIGHT) {

            val lightLevel = event.values[0]
            val darkThreshold = 10.0

            if (lightLevel <= darkThreshold) {
                showToast("Stmieva sa, choď spať!")
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}


