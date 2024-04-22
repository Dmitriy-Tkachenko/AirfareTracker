package ru.tk4dmitriy.screens.airfares.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.tk4dmitriy.screens.airfares.R
import ru.tk4dmitriy.screens.airfares.databinding.FragmentAirfaresBinding
import ru.tk4dmitriy.screens.airfares.di.AirfaresComponentHolder
import ru.tk4dmitriy.screens.airfares.ui.adapter.OffersAdapter
import ru.tk4dmitriy.screens.airfares.ui.adapter.OffersDelegate
import ru.tk4dmitriy.screens.airfares.ui.adapter.OfferDelegateItem
import ru.tk4dmitriy.screens.airfares.ui.utils.OfferItemDecoration
import javax.inject.Inject

class AirfaresFragment : Fragment(R.layout.fragment_airfares) {
    private lateinit var binding: FragmentAirfaresBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(
            owner = this,
            factory = viewModelFactory
        )[AirfaresViewModel::class.java]
    }

    private val departurePlaceChangeListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
        override fun afterTextChanged(s: Editable?) {
            viewModel.saveDeparturePlace(s.toString())
        }
    }
    private val offersAdapter: OffersAdapter by lazy(LazyThreadSafetyMode.NONE) {
        OffersAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AirfaresComponentHolder.getComponent().inject(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAirfaresBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchRout.departureEditText.addTextChangedListener(departurePlaceChangeListener)
        binding.searchRout.arrivalEditText.setOnClickListener {

        }
        offersAdapter.apply {
            addDelegate(OffersDelegate())
        }
        binding.rvOffers.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = offersAdapter
            addItemDecoration(OfferItemDecoration())
        }
        departurePlaceStateLaunch()
        offersStateLaunch()
    }

    private fun departurePlaceStateLaunch() {
        viewModel.departurePlaceState.onEach {
            binding.searchRout.departureEditText.setText(it)
        }.launchIn(lifecycleScope)
    }

    private fun offersStateLaunch() {
        viewModel.offersState.onEach {
            offersAdapter.submitList(it.map { offer ->
                OfferDelegateItem(id = offer.id, value = offer)
            })
        }.launchIn(lifecycleScope)
    }
}