package com.mozidev.firstproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by y.storchak on 1/15/14.
 */
public class WebFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "WebFragment";

    private Button ok;
    private EditText etScheme;
    WebView mWebView;

    public WebFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_web, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mWebView = (WebView) view.findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new HelloWebViewClient());

        etScheme = (EditText) view.findViewById(R.id.etScheme);
    }

    @Override
    public void onClick(View v) {
        String uri = etScheme.getText().toString();

        if (!uri.startsWith("http://")) {
            Toast.makeText(getActivity(), "ERROR ADRESS", Toast.LENGTH_SHORT).show();

        } else {
            // указываем страницу загрузки
            mWebView.loadUrl(uri);

        }
    }


    /*public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return getActivity().onKeyDown(keyCode, event);
    }*/

    private class HelloWebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            view.loadUrl(url);
            return true;
        }
    }
}