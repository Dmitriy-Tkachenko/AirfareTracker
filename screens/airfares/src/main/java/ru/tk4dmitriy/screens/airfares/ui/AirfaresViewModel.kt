package ru.tk4dmitriy.screens.airfares.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import ru.tk4dmitriy.core.utils.Utils.formatNumberByDigits
import ru.tk4dmitriy.feature.offers.api.GetOffersFeatureCase
import ru.tk4dmitriy.features.departure_place.api.GetDeparturePlaceFeatureCase
import ru.tk4dmitriy.features.departure_place.api.SaveDeparturePlaceFeatureCase
import ru.tk4dmitriy.screens.airfares.R
import ru.tk4dmitriy.screens.airfares.di.AirfaresComponentHolder
import ru.tk4dmitriy.screens.airfares.ui.models.OfferUi
import javax.inject.Inject

internal class AirfaresViewModel @Inject constructor(
    private val saveDeparturePlaceFeatureCase: SaveDeparturePlaceFeatureCase,
    private val getDeparturePlaceFeatureCase: GetDeparturePlaceFeatureCase,
    private val getOffersFeatureCase: GetOffersFeatureCase
) : ViewModel() {
    private val saveDeparturePlaceState: MutableSharedFlow<String> = MutableSharedFlow()
    private val _departurePlaceState: MutableStateFlow<String> = MutableStateFlow("")
    val departurePlaceState: StateFlow<String> = _departurePlaceState.asStateFlow()

    private val _offersState: MutableStateFlow<List<OfferUi>> = MutableStateFlow(emptyList())
    val offersState: StateFlow<List<OfferUi>> = _offersState.asStateFlow()

    init {
        performSaveDeparturePlace()
        getDeparturePlace()
        getOffers()
    }

    fun saveDeparturePlace(str: String) {
        viewModelScope.launch {
            saveDeparturePlaceState.emit(str)
        }
    }

    private fun getDeparturePlace() {
        viewModelScope.launch {
            getDeparturePlaceFeatureCase().apply {
                _departurePlaceState.emit(this)
            }
        }
    }

    private fun getOffers() {
        viewModelScope.launch {
            getOffersFeatureCase().map {
                OfferUi(
                    id = it.id,
                    title = it.title,
                    town = it.town,
                    price = it.price.formatNumberByDigits(),
                    imgPreview = it.imgPreview
                )
            }.apply {
                _offersState.emit(this)
            }
        }
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun performSaveDeparturePlace() {
        saveDeparturePlaceState
            .filter { it.isNotBlank() || it.isEmpty() }
            .debounce(timeoutMillis = 500L)
            .flatMapLatest {
                flow {
                    saveDeparturePlaceFeatureCase(it)
                    emit(it)
                }
            }.launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        AirfaresComponentHolder.reset()

    }
}