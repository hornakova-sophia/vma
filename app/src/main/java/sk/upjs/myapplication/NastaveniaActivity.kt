package sk.upjs.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity

class NastaveniaActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nastavenie_activity)
        val switchAction: Switch = findViewById(R.id.switch1)
        switchAction.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val intent = Intent(this, BackgroundSoundService::class.java)
                startService(intent)
            } else {
                val intent = Intent(this, BackgroundSoundService::class.java)
                stopService(intent)
            }
        }
        val webView: WebView = findViewById(R.id.webView)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://github.com/hornakova-sophia/vma")
    }
}
