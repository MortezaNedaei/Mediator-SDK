package org.tapsell.app.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.tapsell.app.databinding.FragmentMainBinding
import org.tapsell.app.ui.base.BaseFragment
import org.tapsell.admediator.main.AdMediator

@AndroidEntryPoint
class MainFragment : BaseFragment() {


    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var canShowAd = false

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
            if (!canShowAd) {
                snack("You should request ad first")
                return@setOnClickListener
            }

            showAd()
        }
    }

    private fun requestAd() {
        AdMediator
            .getInstance()
            .requestAd(requireActivity()) { isSuccess: Boolean ->
                canShowAd = isSuccess
                snack(if (isSuccess) "Successful Request Ad" else "Failed Request Ad")
            }
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