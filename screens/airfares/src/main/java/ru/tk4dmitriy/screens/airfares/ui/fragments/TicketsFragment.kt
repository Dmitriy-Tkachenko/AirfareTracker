package ru.tk4dmitriy.screens.airfares.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.tk4dmitriy.core.utils.Utils
import ru.tk4dmitriy.screens.airfares.databinding.FragmentTicketsBinding
import ru.tk4dmitriy.screens.airfares.di.AirfaresComponentHolder
import ru.tk4dmitriy.screens.airfares.ui.AirfaresViewModel
import ru.tk4dmitriy.screens.airfares.ui.adapter.GlobalAdapter
import ru.tk4dmitriy.screens.airfares.ui.adapter.offers_tickets.OfferTicketDelegateItem
import ru.tk4dmitriy.screens.airfares.ui.adapter.offers_tickets.OffersTicketsDelegate
import ru.tk4dmitriy.screens.airfares.ui.adapter.tickets.TicketDelegateItem
import ru.tk4dmitriy.screens.airfares.ui.adapter.tickets.TicketsDelegate
import ru.tk4dmitriy.screens.airfares.ui.utils.OfferTicketItemDecoration
import ru.tk4dmitriy.screens.airfares.ui.utils.TicketItemDecoration
import javax.inject.Inject

internal class TicketsFragment : Fragment() {
    private lateinit var binding: FragmentTicketsBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(
            owner = this,
            factory = viewModelFactory
        )[AirfaresViewModel::class.java]
    }

    private val adapter: GlobalAdapter by lazy(LazyThreadSafetyMode.NONE) {
        GlobalAdapter()
    }

    private var departure = ""
    private var arrival = ""
    private var departureDate = ""
    private var numberPassenger = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AirfaresComponentHolder.getComponent().inject(this)
        if (savedInstanceState == null) viewModel.getTickets()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTicketsBinding.inflate(inflater, container, false)

        arguments?.let {
            departure = it.getString(DEPARTURE_KEY) ?: ""
            departureDate = it.getString(DEPARTURE_DATE_KEY) ?: ""
            numberPassenger = it.getString(NUMBER_PASSENGER_KEY) ?: ""
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.apply {
            addDelegate(TicketsDelegate())
        }
        binding.apply {
            tvDepartureArrival.text = "$departure-$arrival"
            tvAdditionalInfo.text = "$departureDate, $numberPassenger"
            rvAllTickets.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = this@TicketsFragment.adapter
                addItemDecoration(TicketItemDecoration())
            }
        }
        ticketsLaunch()
    }

    private fun ticketsLaunch() {
        viewModel.ticketsState.onEach {
            adapter.submitList(it.map { ticket ->
                TicketDelegateItem(id = ticket.id, value = ticket)
            })
        }.launchIn(lifecycleScope)
    }

    companion object {
        private const val DEPARTURE_KEY = "DEPARTURE_KEY"
        private const val ARRIVAL_KEY = "ARRIVAL_KEY"
        private const val DEPARTURE_DATE_KEY = "DEPARTURE_DATE_KEY"
        private const val NUMBER_PASSENGER_KEY = "NUMBER_PASSENGER_KEY"
        fun newInstance(
            departure: String,
            arrival: String,
            departureDate: String,
            numberPassenger: String,
        ): TicketsFragment {
            val args = Bundle().apply {
                putString(DEPARTURE_KEY, departure)
                putString(ARRIVAL_KEY, arrival)
                putString(DEPARTURE_DATE_KEY, departureDate)
                putString(NUMBER_PASSENGER_KEY, numberPassenger)
            }
            val fragment = TicketsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}