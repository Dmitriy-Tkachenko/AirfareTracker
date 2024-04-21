package ru.tk4dmitriy.screens.hotels.api

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

interface HotelsStarter {
    fun getTag(): String
    fun addFragment(
        @IdRes containerViewId: Int,
        transaction: FragmentTransaction,
    )
    fun showOrAddFragment(
        @IdRes containerViewId: Int,
        fragmentManager: FragmentManager,
        transaction: FragmentTransaction,
    )
}