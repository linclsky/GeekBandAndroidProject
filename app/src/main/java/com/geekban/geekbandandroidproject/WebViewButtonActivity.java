package com.geekban.geekbandandroidproject;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.JavascriptInterface;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SslErrorHandler;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebHistoryItem;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class WebViewButtonActivity extends AppCompatActivity {

    private WebView mWebView;

    public class TestJSEvent{
        @JavascriptInterface
        public void showToast(String toast){
            Toast.makeText(WebViewButtonActivity.this,"js调用了我",Toast.LENGTH_LONG).show();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_button);
        mWebView = findViewById(R.id.web_view);
        //用chrome调试
        WebView.setWebContentsDebuggingEnabled(true);
        mWebView.loadUrl("http://www.baidu.com");
       // mWebView.loadUrl("file:///android_asset/test.html");
        mWebView.getSettings().setJavaScriptEnabled(true);
        //js调用原生app
        mWebView.addJavascriptInterface(new TestJSEvent(),"TestApp");
        //原生调用js
        mWebView.loadUrl("javascript:javaCallJS()");



        mWebView.setWebViewClient(new WebViewClient(){


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //拦截网页，重新定向
               // view.loadUrl("http://www.163.com");
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //页面开始，显示loading动画
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //页面结束，隐藏loading动画
                super.onPageFinished(view, url);
            }


        });
        mWebView.setWebChromeClient(new TestWebChromeClient());
    }
    public class TestWebChromeClient extends WebChromeClient{

        public TestWebChromeClient() {
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
        }

        @Override
        public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
            super.onReceivedTouchIconUrl(view, url, precomposed);
        }
    }

    //  页面回退
    @Override
    public void onBackPressed() {
        if (mWebView != null && mWebView.canGoBack()){
            //获取历史记录
//            WebBackForwardList webBackForwardList = mWebView.copyBackForwardList();
//            WebHistoryItem historyItem = webBackForwardList.getItemAtIndex(0);
//            String historyUrl = historyItem.getUrl();
            mWebView.goBack();//后退
            mWebView.goForward();//前进
            mWebView.goBackOrForward(+1);//前进后退
            mWebView.reload();//刷新
        }else {
            super.onBackPressed();
        }

    }
}
