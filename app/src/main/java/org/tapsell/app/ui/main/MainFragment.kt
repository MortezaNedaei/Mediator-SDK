package org.tapsell.app.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.tapsell.app.databinding.FragmentMainBinding
import org.tapsell.sdk.addmediator.AdMediator

@AndroidEntryPoint
class MainFragment : Fragment() {


    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRequestAd.setOnClickListener {
            requestAd()
        }

        binding.btnShowAd.setOnClickListener {
            showAd()
        }
    }

    private fun requestAd() {
        AdMediator
            .getInstance()
            .requestAd(requireActivity())
    }

    private fun showAd() {
        AdMediator
            .getInstance()
            .showAdd(requireActivity())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}