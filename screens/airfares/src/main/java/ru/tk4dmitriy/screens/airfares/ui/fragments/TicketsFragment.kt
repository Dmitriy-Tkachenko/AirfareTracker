package ru.tk4dmitriy.screens.airfares.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.tk4dmitriy.core.utils.Utils.formatDate
import ru.tk4dmitriy.core.utils.ui.DatePickerHelper
import ru.tk4dmitriy.screens.airfares.databinding.FragmentTicketsBinding
import java.util.Date

internal class TicketsFragment : Fragment() {
    private lateinit var binding: FragmentTicketsBinding

    private val returnTickerPickerListener = object : DatePickerHelper.DatePickerListener {
        override fun onDateSelected(date: Date) {
            binding.btnReturnTicket.text = date.formatDate("d MMM', 'E")
            binding.btnReturnTicket.setCompoundDrawables(null, null, null, null)
        }
    }

    private val departureDatePickerListener = object : DatePickerHelper.DatePickerListener {
        override fun onDateSelected(date: Date) {
            binding.btnDepartureDate.text = date.formatDate("d MMM', 'E")
        }
    }


    private val returnTicketClickListener = OnClickListener {
        DatePickerHelper.showDatePickerDialog(requireActivity(), returnTickerPickerListener)
    }

    private val departureDateClickListener = OnClickListener {
        DatePickerHelper.showDatePickerDialog(requireActivity(), departureDatePickerListener)
    }

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
        binding.apply {
            searchRoute.departureEditText.setText(departure)
            searchRoute.arrivalEditText.setText(arrival)
            btnReturnTicket.setOnClickListener(returnTicketClickListener)
            btnDepartureDate.setOnClickListener(departureDateClickListener)
        }
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