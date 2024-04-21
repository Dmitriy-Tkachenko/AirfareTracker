package ru.tk4dmitriy.screens.hotels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.tk4dmitriy.screens.hotels.databinding.FragmentHotelsBinding
import ru.tk4dmitriy.screens.hotels.di.HotelsComponentHolder

internal class HotelsFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HotelsComponentHolder.getComponent().inject(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentHotelsBinding.inflate(inflater, container, false).root
    }

    override fun onPause() {
        super.onPause()
        if (requireActivity().isFinishing)
            HotelsComponentHolder.reset()
    }
}