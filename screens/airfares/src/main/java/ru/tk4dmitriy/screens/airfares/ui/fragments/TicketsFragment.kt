package ru.tk4dmitriy.screens.airfares.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.tk4dmitriy.screens.airfares.databinding.FragmentTicketsBinding

internal class TicketsFragment : Fragment() {
    private lateinit var binding: FragmentTicketsBinding

    private var departure = ""
    private var arrival = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTicketsBinding.inflate(inflater, container,false)

        arguments?.let {
            departure = it.getString(DEPARTURE_KEY) ?: ""
            arrival = it.getString(ARRIVAL_KEY) ?: ""
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchRoute.departureEditText.setText(departure)
        binding.searchRoute.arrivalEditText.setText(arrival)
    }

    companion object {
        private const val DEPARTURE_KEY = "DEPARTURE_KEY"
        private const val ARRIVAL_KEY = "ARRIVAL_KEY"
        fun newInstance(departure: String, arrival: String): TicketsFragment {
            val args = Bundle().apply {
                putString(DEPARTURE_KEY, departure)
                putString(ARRIVAL_KEY, arrival)
            }
            val fragment = TicketsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}