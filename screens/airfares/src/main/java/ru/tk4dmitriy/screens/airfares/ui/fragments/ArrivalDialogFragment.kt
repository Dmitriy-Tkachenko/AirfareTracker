package ru.tk4dmitriy.screens.airfares.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.tk4dmitriy.screens.airfares.databinding.DialogFragmentArrivalBinding
import ru.tk4dmitriy.screens.airfares.di.AirfaresComponentHolder
import ru.tk4dmitriy.screens.airfares.ui.AirfaresViewModel
import javax.inject.Inject

internal class ArrivalDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: DialogFragmentArrivalBinding

    private var departure = ""

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(
            owner = this,
            factory = viewModelFactory
        )[AirfaresViewModel::class.java]
    }

    private val arrivalChangeListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
        override fun afterTextChanged(s: Editable?) {
            val navController = findNavController()
            val bundle = Bundle()
            bundle.putString(OffersTicketsFragment.DEPARTURE_KEY, departure)
            bundle.putString(OffersTicketsFragment.ARRIVAL_KEY, binding.searchRout2.arrivalEditText.text.toString())
            navController.navigate(viewModel.navigateToOffersTicketsFragment(), bundle)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,  ru.tk4dmitriy.core.res.R.style.BottomSheetDialogTheme)
        AirfaresComponentHolder.getComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogFragmentArrivalBinding.inflate(inflater, container, false)

        arguments?.let {
            departure = it.getString(DEPARTURE_KEY) ?: ""
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        binding.apply {
            searchRout2.departureEditText.setText(departure)
            searchRout2.arrivalEditText.addTextChangedListener(arrivalChangeListener)
            tvDifficultRout.setOnClickListener {
                navController.navigate(viewModel.navigateToDifficultRouteFragment())
            }
            tvCalendar.setOnClickListener {
                navController.navigate(viewModel.navigateToWeekendFragment())
            }
            tvHotTickets.setOnClickListener {
                navController.navigate(viewModel.navigateToHotTicketsFragment())
            }
            tvAnywhere.setOnClickListener {
                searchRout2.arrivalEditText.setText(binding.tvAnywhere.text)
            }
            llRecItem1.setOnClickListener {
                searchRout2.arrivalEditText.setText(binding.tvIstanbul.text)
            }
            llRecItem2.setOnClickListener {
                searchRout2.arrivalEditText.setText(binding.tvSochi.text)
            }
            llRecItem3.setOnClickListener {
                searchRout2.arrivalEditText.setText(binding.tvPhuket.text)
            }
        }
    }

    companion object {
        const val DEPARTURE_KEY = "DEPARTURE_KEY"
    }
}