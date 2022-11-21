package com.cit.k_webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.cit.k_webview.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.myWebView.webViewClient = WebViewClient()

        // this will load the url of the website
        binding.myWebView.loadUrl("https://www.geeksforgeeks.org/")

        // this will enable the javascript settings, it can also allow xss vulnerabilities
        binding.myWebView.settings.javaScriptEnabled = true

        // if you want to enable zoom feature
        binding.myWebView.settings.setSupportZoom(true)

        with(binding.myWebView) {
            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    view?.evaluateJavascript("document.documentElement.outerHTML") {
                        val html = it.replace("\\u003C", "<")

                        //   val errorObj = JSONObject(html)
                        // val message = errorObj.getString("hr

                        Log.i("HTML", "onPageFinished: $html")
                    }
                }
            }
        }




    }

}