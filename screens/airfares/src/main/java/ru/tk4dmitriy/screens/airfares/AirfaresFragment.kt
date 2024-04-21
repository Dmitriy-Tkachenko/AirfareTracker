package ru.tk4dmitriy.screens.airfares

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.tk4dmitriy.screens.airfares.databinding.FragmentAirfaresBinding
import ru.tk4dmitriy.screens.airfares.di.AirfaresComponentHolder

class AirfaresFragment : Fragment(R.layout.fragment_airfares) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AirfaresComponentHolder.getComponent().inject(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentAirfaresBinding.inflate(inflater, container, false).root
    }

    override fun onPause() {
        super.onPause()
        if (requireActivity().isFinishing)
            AirfaresComponentHolder.reset()
    }
}