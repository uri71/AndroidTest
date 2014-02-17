package com.mozidev.firstproject;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class WebFragment extends Fragment implements View.OnClickListener {

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
            mWebView.loadUrl(uri);
        }
    }

    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){

            view.loadUrl(url);

            return true;
        }
    }
}