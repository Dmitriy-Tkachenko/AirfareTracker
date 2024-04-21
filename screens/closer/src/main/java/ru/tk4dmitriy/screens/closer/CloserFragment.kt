package ru.tk4dmitriy.screens.closer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.tk4dmitriy.screens.closer.databinding.FragmentCloserBinding
import ru.tk4dmitriy.screens.closer.di.CloserComponentHolder

internal class CloserFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CloserComponentHolder.getComponent().inject(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCloserBinding.inflate(inflater, container, false).root
    }

    override fun onPause() {
        super.onPause()
        if (requireActivity().isFinishing)
            CloserComponentHolder.reset()
    }
}