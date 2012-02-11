package com.demo.webview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class WebViewDemoActivity extends Activity {

	private final static String LOG_TAG="WebViewDemoActivity";
	
	private WebView mWebView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);

        
        mWebView.loadUrl("http://www.baidu.com");
        
        setReponseInWebView();
        
        
        Button button1 = (Button)findViewById(R.id.button1);
        
        button1.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Log.d(LOG_TAG, "onClicked");
				
				String searchKeywords="test search";
				
				/*
				 * http://www.baidu.com -->
				 * 
				<form name="f" action="/s">
				<span class="s_ipt_wr">
				<input type="text" name="wd" id="kw" maxlength="100" class="s_ipt">
				</span>
				<input type="hidden" name="rsv_bp" value="0">
				<input type="hidden" name="rsv_spt" value="3">
				<span class="s_btn_wr">
				<input type="submit" value="百度一下" id="su" class="s_btn" onmousedown="this.className='s_btn s_btn_h'" onmouseout="this.className='s_btn'">
				</span>
				</form>
				
				Note, m.baidu.com search form name is "f1"
				 */

				final String WEB_FORM_NAME="f1";
				
				mWebView.loadUrl("javascript: {"
						+ "document.getElementById('kw').value = '"+searchKeywords +"'; \n"
						//+ "document.getElementById('password').value = '"+password+"';"
						+ "var frms = document.getElementsByName('" + WEB_FORM_NAME+"'); \n"
						+ "frms[0].submit(); \n" 
						+ "};"
						);
				Log.d(LOG_TAG, "form subitted");
			}
		});
    }
      
    //IF we want the submit respone being showing in the same webview. please call below API
    //Otherwise, leave the shouldOverrideUrlLoading() to return false will use the system broswer to display the results
    //
    
    private void setReponseInWebView(){
    	mWebView.setWebViewClient(new WebViewClient() {
    		@Override
    		public boolean shouldOverrideUrlLoading(WebView view, String url) {
    			Log.d(LOG_TAG, "shouldOverrideUrlLoading (url=" + url+")");
    			view.loadUrl(url);
    			return false;
    		}
    		
    		@Override
    		public void onPageFinished(WebView view, String url) {
    			//please refer evernote to know how to inject java script in webview
    			//view.loadUrl("javascript:(function(){" + 
    			//		MyBrowser.jsFormInjectCode + "})()");
    		}
    	});


        
    }
    
    
}