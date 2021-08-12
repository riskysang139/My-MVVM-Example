package com.example.bookroom.Webview;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookroom.R;
import com.example.bookroom.Common.Social;


public class WebViewActivity extends AppCompatActivity {
    private WebView webView;
    private TextView tvTiltle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = findViewById(R.id.webView);
        tvTiltle = findViewById(R.id.tvTiltle);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new Callback());
        settingWebAccess();
        Bundle bundle=getIntent().getExtras();
        Social social= (Social) bundle.get("link");
        webView.loadUrl(social.getLink());
        tvTiltle.setText(social.getName());



    }
    private class Callback extends WebViewClient{
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }
    private void settingWebAccess() {
        WebViewSetting.settingWebView(webView);
        WebViewSetting.setMobile(webView);
    }

}