package ru.kryu.vkpokemon.feature.pokemonlist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.kryu.vkpokemon.R
import ru.kryu.vkpokemon.databinding.FragmentPokemonListBinding
import ru.kryu.vkpokemon.feature.pokemoninfo.presentation.PokemonInfoFragment
import ru.kryu.vkpokemon.feature.pokemonlist.domain.model.PokemonPack
import ru.kryu.vkpokemon.feature.pokemonlist.presentation.recycler.PokemonAdapter

@AndroidEntryPoint
class PokemonListFragment : Fragment() {

    private var _binding: FragmentPokemonListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PokemonListViewModel by viewModels()
    private var pokemonListAdapter: PokemonAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonListAdapter = PokemonAdapter { pokemonItem ->
            findNavController().navigate(
                R.id.action_pokemonListFragment_to_pokemonInfoFragment,
                PokemonInfoFragment.createArgs(pokemonItem.id)
            )
        }
        binding.recyclerView.adapter = pokemonListAdapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        viewModel.toastLiveData.observe(viewLifecycleOwner) {
            showToast(it)
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.screenState.collect { state ->
                    when (state) {
                        is PokemonListState.Content -> showContent(
                            state.content,
                            state.isBottomLoading
                        )

                        PokemonListState.Error -> showError()
                        PokemonListState.Loading -> showLoading()
                    }
                }
            }
        }
        binding.btnRefresh.setOnClickListener {
            viewModel.refreshClicked()
        }
        binding.recyclerView.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0) {
                        val position =
                            (binding.recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                        val itemsCount = pokemonListAdapter!!.itemCount
                        if (position >= itemsCount - 1) {
                            if (viewModel.screenState.value is PokemonListState.Content &&
                                !(viewModel.screenState.value as PokemonListState.Content).isBottomLoading
                            ) {
                                viewModel.getNextPage()
                            }
                        }
                    }
                }
            }
        )
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showContent(content: PokemonPack, bottomLoading: Boolean) {
        binding.recyclerView.visibility = View.VISIBLE
        binding.progressBarCenter.visibility = View.GONE
        binding.ivError.visibility = View.GONE
        binding.btnRefresh.visibility = View.GONE
        if (bottomLoading) {
            binding.progressBarBottom.visibility = View.VISIBLE
        } else {
            binding.progressBarBottom.visibility = View.GONE
        }
        pokemonListAdapter!!.addPokemonList(content.pokemonList)
    }

    private fun showError() {
        binding.recyclerView.visibility = View.GONE
        binding.progressBarBottom.visibility = View.GONE
        binding.progressBarCenter.visibility = View.GONE
        binding.ivError.visibility = View.VISIBLE
        binding.btnRefresh.visibility = View.VISIBLE
    }

    private fun showLoading() {
        binding.recyclerView.visibility = View.GONE
        binding.progressBarBottom.visibility = View.GONE
        binding.progressBarCenter.visibility = View.VISIBLE
        binding.ivError.visibility = View.GONE
        binding.btnRefresh.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        pokemonListAdapter = null
    }
}