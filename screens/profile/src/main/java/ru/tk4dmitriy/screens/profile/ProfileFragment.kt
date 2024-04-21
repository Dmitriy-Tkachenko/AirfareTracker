package ru.tk4dmitriy.screens.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.tk4dmitriy.screens.profile.databinding.FragmentProfileBinding
import ru.tk4dmitriy.screens.profile.di.ProfileComponentHolder

class ProfileFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProfileComponentHolder.getComponent().inject(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentProfileBinding.inflate(inflater, container, false).root
    }

    override fun onPause() {
        super.onPause()
        if (requireActivity().isFinishing)
            ProfileComponentHolder.reset()
    }
}