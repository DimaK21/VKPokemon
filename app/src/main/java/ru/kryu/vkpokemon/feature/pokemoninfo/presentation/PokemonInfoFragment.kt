package ru.kryu.vkpokemon.feature.pokemoninfo.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.kryu.vkpokemon.R
import ru.kryu.vkpokemon.databinding.FragmentPokemonInfoBinding
import ru.kryu.vkpokemon.feature.pokemoninfo.domain.model.Pokemon

@AndroidEntryPoint
class PokemonInfoFragment : Fragment() {

    private var _binding: FragmentPokemonInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PokemonInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.toastLiveData.observe(viewLifecycleOwner) {
            showToast(it)
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.screenState.collect { state ->
                    when (state) {
                        is PokemonInfoState.Content -> showContent(state.content)
                        PokemonInfoState.Error -> showError()
                        PokemonInfoState.Loading -> showLoading()
                    }
                }
            }
        }
        binding.btnRefresh.setOnClickListener {
            viewModel.refreshClicked()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showContent(pokemon: Pokemon) {
        binding.groupSuccess.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
        binding.groupError.visibility = View.GONE
        binding.tvName.text = pokemon.name
        binding.tvHeight.text = getString(R.string.height, pokemon.height)
        binding.tvWeight.text = getString(R.string.weight, pokemon.weight)
        binding.tvForms.text = getString(R.string.forms, pokemon.forms.toString())
        Glide.with(requireContext())
            .load(pokemon.icon)
            .placeholder(R.drawable.pokemon_placeholder)
            .transform(
                CenterCrop(),
            )
            .into(binding.ivIcon)
    }

    private fun showError() {
        binding.groupSuccess.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.groupError.visibility = View.VISIBLE
    }

    private fun showLoading() {
        binding.groupSuccess.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
        binding.groupError.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val POKEMON_ID = "POKEMON_ID"
        fun createArgs(pokemonId: Int): Bundle =
            bundleOf(POKEMON_ID to pokemonId)
    }
}
