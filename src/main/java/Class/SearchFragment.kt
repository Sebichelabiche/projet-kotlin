package com.example.giphy2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.giphy2.databinding.FragmentSearchBinding
import dataclass.Gif
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SearchFragment : Fragment(R.layout.fragment_search) {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val gifAdapter by lazy { GifAdapter() }
    private val giphyService by lazy { RetrofitClient.instance.create(GiphyService::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        binding.etSearch.setOnEditorActionListener { _, _, _ ->
            val query = binding.etSearch.text.toString()
            if (query.isNotEmpty()) {
                searchGifs(query)
            }
            true
        }
    }

    private fun setupRecyclerView() {
        binding.rvGifs.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = gifAdapter
        }
    }

    private fun searchGifs(query: String) {
        lifecycleScope.launch {
            try {

                val response = giphyService.searchGifs(apiKey = "gFmLZYKDU0vOwHXd5J7jQXuoQunNz0Yv", query = query)


                val gifs = response.data.map { gifData ->

                    Gif(
                        gifData.images.original.url,
                        gifData.title
                    )
                }
                gifAdapter.updateData(gifs)
            } catch (e: HttpException) {

                Toast.makeText(requireContext(), "Erreur API: ${e.message}", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {

                Toast.makeText(requireContext(), "Une erreur est survenue: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
