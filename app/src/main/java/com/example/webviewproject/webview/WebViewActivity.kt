package com.example.webviewproject.webview

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.webviewproject.R
import com.example.webviewproject.databinding.ActivityMainBinding
import com.example.webviewproject.databinding.ActivityWebviewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding:ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWebviewBinding.inflate(layoutInflater)

        enableEdgeToEdge()

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.webView.settings.apply {
            javaScriptEnabled = true
            allowFileAccess = true
            domStorageEnabled = true
        }
//ghhghhg
        binding.webView.webViewClient = object :WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding.progress.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.progress.visibility = View.GONE

            }
        }

        binding.webView.loadUrl("https://www.makemytrip.com/")
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        }
        else{
            super.onBackPressed()
        }
    }
}