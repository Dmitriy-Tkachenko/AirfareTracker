package ru.tk4dmitriy.screens.airfares.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.tk4dmitriy.screens.airfares.databinding.FragmentAllTicketsBinding

class AllTicketsFragment : Fragment() {
    private lateinit var binding: FragmentAllTicketsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }
}