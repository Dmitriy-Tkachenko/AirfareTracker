package ru.tk4dmitriy.screens.closer.api

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

interface CloserStarter {
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