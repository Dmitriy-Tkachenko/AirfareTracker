package ru.tk4dmitriy.core.utils.ui.delegateAdapter

interface DelegateItem {
    fun content(): Any
    fun id(): Number
    fun compareToOther(other: DelegateItem): Boolean
}