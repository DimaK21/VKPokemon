package ru.kryu.vkpokemon.feature.pokemonlist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.kryu.vkpokemon.R
import ru.kryu.vkpokemon.databinding.FragmentPokemonListBinding
import ru.kryu.vkpokemon.feature.pokemoninfo.presentation.PokemonInfoFragment

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
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        pokemonListAdapter = null
    }
}