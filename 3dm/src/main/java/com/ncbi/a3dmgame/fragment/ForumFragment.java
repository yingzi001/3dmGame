package com.ncbi.a3dmgame.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ncbi.a3dmgame.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForumFragment extends Fragment {

    private WebView forumWeb;
    private View view;
    private String forumUrl = "http://bbs.3dmgame.com/forum.php";

    public ForumFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_forum, container, false);
        forumWeb = (WebView) view.findViewById(R.id.forum_web);
        forumWeb.loadUrl(forumUrl);
        forumWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(forumUrl);
                return true;
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}
