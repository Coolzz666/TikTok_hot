package com.tiktok


import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.tiktok.util.showToast


class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val url = intent.getStringExtra("url=")
        val webView = findViewById<WebView>(R.id.web_view)
        val list = mutableListOf<String>()
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = WebViewClient()


        if (url != null) {
            webView.loadUrl(url)
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId== android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    internal class MyPlayerOnCompletionListener : OnCompletionListener {
        override fun onCompletion(mp: MediaPlayer) {
            "播放完成".showToast()
        }
    }
}