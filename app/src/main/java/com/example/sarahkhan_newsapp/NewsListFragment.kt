package com.example.sarahkhan_newsapp

import com.example.sarahkhan_newsapp.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sarahkhan_newsapp.databinding.FragmentNewslistfragmentBinding

class NewsListFragment : Fragment() {
    private var binding: FragmentNewslistfragmentBinding? = null
    private val viewModel: NewsListViewModel by viewModels()
    private val categories = arrayOf("business", "technology", "sports", "health", "science", "entertainment")
    private var newsListAdapter = NewsListAdapter(emptyList()) { articles -> onArticleClick(articles) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewslistfragmentBinding.inflate(inflater, container, false)
        binding?.newsrecyclerView?.layoutManager = LinearLayoutManager(context)
        binding?.newsrecyclerView?.adapter = newsListAdapter
        spinnercreate()
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()

    }

    private fun spinnercreate() {
        binding?.categoryspinner?.let { spinner ->
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, categories)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val selectedCategory = parent?.getItemAtPosition(position)?.toString() ?: return
                    viewModel.fetchNewsArticles(selectedCategory)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
        }
    }
    private fun observeViewModel() {
        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            newsListAdapter.updateArticles(articles)
        }
    }

    private fun onArticleClick(articles: Article) {
        val detailfragment = NewsDetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable("article", articles)
            }
        }
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailfragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

