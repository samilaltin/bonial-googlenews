package com.samilaltin.bonialnews.ui.main.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.samilaltin.bonialnews.R
import kotlinx.android.synthetic.main.activity_article_detail_webview.*
import com.samilaltin.bonialnews.ui.components.BonialProgressBar


class ArticleDetailWebView : AppCompatActivity() {
    var bonialProgressBar = BonialProgressBar.instance
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail_webview)
        if (intent != null && intent.getStringExtra("url") != null) {
            val url = intent.getStringExtra("url")
            webView.loadUrl(url)
        }
        bonialProgressBar.showProgress(this)
        webView.visibility = View.GONE
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.domStorageEnabled = true
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                bonialProgressBar.hideProgress()
                view.visibility = View.VISIBLE
            }
        }
    }
}
