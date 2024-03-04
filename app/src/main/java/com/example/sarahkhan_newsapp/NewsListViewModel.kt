package com.example.sarahkhan_newsapp
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsListViewModel : ViewModel() {
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> get() = _articles

    private val apiService: APIService by lazy {
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }

    init {
        fetchNewsArticles("sports")
    }

    fun fetchNewsArticles(category: String) {
        val call = apiService.getTopHeadlines(category = category)
        call.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful) {
                    _articles.value = response.body()?.articles
                } else {
                    Log.d("API Error", "Server Response Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("API Call Failure", "Call Failed: ${t.message}")
            }
        })
    }
}
