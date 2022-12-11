package com.bikram.cvbuilder.ui.webview

import android.os.Bundle
import android.util.Log
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.bikram.cvbuilder.R
import com.bikram.cvbuilder.databinding.CustomWebviewActivityBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: CustomWebviewActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = CustomWebviewActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.title = "${this.getString(R.string.app_name)} WebView"

        val url = intent.extras!!.getString("url")
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl(url!!)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.setSupportZoom(true)
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack())
            binding.webView.goBack()
        else
            super.onBackPressed()
    }
}