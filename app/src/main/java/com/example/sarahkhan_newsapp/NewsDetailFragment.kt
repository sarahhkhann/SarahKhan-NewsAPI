package com.example.sarahkhan_newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sarahkhan_newsapp.databinding.FragmentNewsdetailBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [newsdetail.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsDetailFragment : Fragment() {
    private var _binding: FragmentNewsdetailBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private lateinit var news: News


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = arguments?.getSerializable("article") as? Article
        article?.let {
            binding.articletitle.text = it.title
            binding.articleauthor.text = it.author
            binding.articledate.text= it.publishedAt
            binding.articledescription.text=it.description

        }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsdetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance(article: Article): NewsDetailFragment {
            val args = Bundle().apply {
                putSerializable("article", article)
            }
            return NewsDetailFragment().apply {
                arguments = args
            }
        }
    }
}