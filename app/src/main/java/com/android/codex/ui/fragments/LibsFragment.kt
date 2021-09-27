package com.android.codex.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.codex.R
import com.android.codex.databinding.FragmentLibsBinding
import com.android.codex.ui.viewmodels.LibsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LibsFragment : Fragment(R.layout.fragment_libs) {

    private val libsViewModel: LibsViewModel by viewModels()
    private var _binding: FragmentLibsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLibsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}