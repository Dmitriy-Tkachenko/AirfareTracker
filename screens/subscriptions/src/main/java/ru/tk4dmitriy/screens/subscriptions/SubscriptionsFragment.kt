package ru.tk4dmitriy.screens.subscriptions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.tk4dmitriy.screens.subscriptions.databinding.FragmentSubscriptionsBinding
import ru.tk4dmitriy.screens.subscriptions.di.SubscriptionsComponentHolder

class SubscriptionsFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SubscriptionsComponentHolder.getComponent().inject(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSubscriptionsBinding.inflate(inflater, container, false).root
    }

    override fun onPause() {
        super.onPause()
        if (requireActivity().isFinishing)
            SubscriptionsComponentHolder.reset()
    }
}