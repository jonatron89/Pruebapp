package com.poli.pruebapp.ui.web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.poli.pruebapp.databinding.FragmentWebBinding

class WebFragment : Fragment() {

    private var _binding: FragmentWebBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val webViewModel =
            ViewModelProvider(this).get(WebViewModel::class.java)

        _binding = FragmentWebBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val webView = binding.webView;
        val searchBtn = binding.searchBtn;
        val dictionaryBtn = binding.dictionaryBtn;
        val translatorBtn = binding.translatorBtn;
        val searchView = binding.searchView;
        webView.webViewClient = object : WebViewClient() {

        }

        val settings = webView.settings
        settings.javaScriptEnabled = true
        webView.loadUrl("https://www.google.com/")


        searchBtn.setOnClickListener {
            val BASE_URL = "https://google.com"
            val SEARCH_PATH = "/search?q="

            val searchQuery = searchView.query.toString()
            if(searchQuery != "") {
                if(URLUtil.isValidUrl(searchQuery)){
                    webView.loadUrl(searchQuery)
                }else {
                    webView.loadUrl("$BASE_URL$SEARCH_PATH$searchQuery")
                }
            }
        }

        dictionaryBtn.setOnClickListener {
            searchView.setQuery("", false);
            webView.loadUrl("https://www.wordreference.com/")
        }

        translatorBtn.setOnClickListener {
            searchView.setQuery("", false);
            webView.loadUrl("https://www.deepl.com/")
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}