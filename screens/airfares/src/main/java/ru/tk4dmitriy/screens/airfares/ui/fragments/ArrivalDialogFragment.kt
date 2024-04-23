package ru.tk4dmitriy.screens.airfares.ui.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.tk4dmitriy.screens.airfares.databinding.DialogFragmentArrivalBinding


internal class ArrivalDialogFragment : BottomSheetDialogFragment() {
    interface Callback {
        fun clickOnDifficultRout()
        fun clickOnWeekend()
        fun clickOnHotTickets()
        fun arrivalIntroduced(arrival: String)
    }

    private lateinit var callback: Callback
    private lateinit var binding: DialogFragmentArrivalBinding

    private var departure = ""

    private val arrivalChangeListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
        override fun afterTextChanged(s: Editable?) {
            if (!s.isNullOrEmpty()) callback.arrivalIntroduced(s.toString())
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = requireParentFragment() as Callback
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,  ru.tk4dmitriy.core.res.R.style.BottomSheetDialogTheme)
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
        binding.apply {
            searchRout2.departureEditText.setText(departure)
            searchRout2.arrivalEditText.addTextChangedListener(arrivalChangeListener)

            tvDifficultRout.setOnClickListener {
                callback.clickOnDifficultRout()
            }
            tvCalendar.setOnClickListener {
                callback.clickOnWeekend()
            }
            tvHotTickets.setOnClickListener {
                callback.clickOnHotTickets()
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
        private const val DEPARTURE_KEY = "DEPARTURE_KEY"
        fun newInstance(departure: String): ArrivalDialogFragment {
            val args = Bundle().apply {
                putString(DEPARTURE_KEY, departure)
            }
            val fragment = ArrivalDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }
}