package com.example.sarahkhan_newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sarahkhan_newsapp.databinding.FragmentNewslistfragmentBinding

class NewsListFragment : Fragment() {
    private var binding: FragmentNewslistfragmentBinding? = null
    private val viewModel: NewsListViewModel by viewModels()
    private val newsListAdapter = NewsListAdapter(emptyList())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewslistfragmentBinding.inflate(inflater, container, false)
        binding?.newsrecyclerView?.layoutManager = LinearLayoutManager(context)
        binding?.newsrecyclerView?.adapter = newsListAdapter
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            newsListAdapter.updateArticles(articles)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

