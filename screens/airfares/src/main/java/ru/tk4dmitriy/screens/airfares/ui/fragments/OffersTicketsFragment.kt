package ru.tk4dmitriy.screens.airfares.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.tk4dmitriy.core.utils.Utils.formatDate
import ru.tk4dmitriy.core.utils.Utils.getCurrentDate
import ru.tk4dmitriy.core.utils.ui.DatePickerHelper
import ru.tk4dmitriy.screens.airfares.R
import ru.tk4dmitriy.screens.airfares.databinding.FragmentOffersTicketsBinding
import ru.tk4dmitriy.screens.airfares.di.AirfaresComponentHolder
import ru.tk4dmitriy.screens.airfares.ui.AirfaresViewModel
import ru.tk4dmitriy.screens.airfares.ui.adapter.GlobalAdapter
import ru.tk4dmitriy.screens.airfares.ui.adapter.offers_tickets.OfferTicketDelegateItem
import ru.tk4dmitriy.screens.airfares.ui.adapter.offers_tickets.OffersTicketsDelegate
import ru.tk4dmitriy.screens.airfares.ui.utils.OfferTicketItemDecoration
import java.util.Date
import javax.inject.Inject

internal class OffersTicketsFragment : Fragment() {
    private lateinit var binding: FragmentOffersTicketsBinding

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

    private val returnTickerPickerListener = object : DatePickerHelper.DatePickerListener {
        override fun onDateSelected(date: Date) {
            binding.btnReturnTicket.text = date.formatDate(pattern = "d MMM', 'E")
            binding.btnReturnTicket.setCompoundDrawables(null, null, null, null)
        }
    }

    private val departureDatePickerListener = object : DatePickerHelper.DatePickerListener {
        override fun onDateSelected(date: Date) {
            binding.btnDepartureDate.text = date.formatDate(pattern = "d MMM', 'E")
        }
    }

    private val returnTicketClickListener = OnClickListener {
        DatePickerHelper.showDatePickerDialog(requireActivity(), returnTickerPickerListener)
    }

    private val departureDateClickListener = OnClickListener {
        DatePickerHelper.showDatePickerDialog(requireActivity(), departureDatePickerListener)
    }

    private val btnSeeAllTicketsClickListener = OnClickListener {
        openAllTicketsFragment()
    }

    private var departure = ""
    private var arrival = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AirfaresComponentHolder.getComponent().inject(this)
        if (savedInstanceState == null) viewModel.getOffersTickets()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOffersTicketsBinding.inflate(inflater, container,false)

        arguments?.let {
            departure = it.getString(DEPARTURE_KEY) ?: ""
            arrival = it.getString(ARRIVAL_KEY) ?: ""
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.apply {
            addDelegate(OffersTicketsDelegate())
        }
        binding.apply {
            searchRoute.departureEditText.setText(departure)
            searchRoute.arrivalEditText.setText(arrival)
            btnDepartureDate.text = getCurrentDate(pattern = "d MMM', 'E")
            btnReturnTicket.setOnClickListener(returnTicketClickListener)
            btnDepartureDate.setOnClickListener(departureDateClickListener)
            rvOffersTickets.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = this@OffersTicketsFragment.adapter
                addItemDecoration(OfferTicketItemDecoration(requireActivity()))
            }
            btnSeeAllTickets.setOnClickListener(btnSeeAllTicketsClickListener)
            binding.searchRoute.icBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
        offersTicketsLaunch()
    }

    private fun offersTicketsLaunch() {
        viewModel.offersTicketsState.onEach {
            adapter.submitList(it.map { offerTicket ->
                OfferTicketDelegateItem(id = offerTicket.id, value = offerTicket)
            })
        }.launchIn(lifecycleScope)
    }

    private fun openAllTicketsFragment() {
        val bundle = Bundle()
        bundle.putString(TicketsFragment.DEPARTURE_KEY, departure)
        bundle.putString(TicketsFragment.ARRIVAL_KEY, arrival)
        bundle.putString(TicketsFragment.DEPARTURE_DATE_KEY, binding.btnDepartureDate.text.toString())
        bundle.putString(TicketsFragment.NUMBER_PASSENGER_KEY, binding.btnPassengers.text.toString())
        val navController = findNavController()
        navController.navigate(viewModel.navigateToTicketsFragment(), bundle)
    }

    companion object {
        const val DEPARTURE_KEY = "DEPARTURE_KEY"
        const val ARRIVAL_KEY = "ARRIVAL_KEY"
    }
}