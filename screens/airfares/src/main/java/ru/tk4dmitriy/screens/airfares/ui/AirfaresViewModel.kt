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
import ru.tk4dmitriy.core.utils.Utils.calculateTravelTime
import ru.tk4dmitriy.core.utils.Utils.formatNumberByDigits
import ru.tk4dmitriy.core.utils.Utils.formatStringDateToTime
import ru.tk4dmitriy.feature.offers.api.GetOffersFeatureCase
import ru.tk4dmitriy.features.departure_place.api.GetDeparturePlaceFeatureCase
import ru.tk4dmitriy.features.departure_place.api.SaveDeparturePlaceFeatureCase
import ru.tk4dmitriy.features.offers_tickets.api.GetOffersTicketsFeatureCase
import ru.tk4dmitriy.features.tickets.api.GetTicketsFeatureCase
import ru.tk4dmitriy.screens.airfares.di.AirfaresComponentHolder
import ru.tk4dmitriy.screens.airfares.navigation.AirfaresNavigation
import ru.tk4dmitriy.screens.airfares.ui.models.OfferTicketUi
import ru.tk4dmitriy.screens.airfares.ui.models.OfferUi
import ru.tk4dmitriy.screens.airfares.ui.models.TicketUi
import javax.inject.Inject

internal class AirfaresViewModel @Inject constructor(
    private val saveDeparturePlaceFeatureCase: SaveDeparturePlaceFeatureCase,
    private val getDeparturePlaceFeatureCase: GetDeparturePlaceFeatureCase,
    private val getOffersFeatureCase: GetOffersFeatureCase,
    private val getOffersTicketsFeatureCase: GetOffersTicketsFeatureCase,
    private val getTicketsFeatureCase: GetTicketsFeatureCase,
    private val airfaresNavigation: AirfaresNavigation
) : ViewModel() {
    private val saveDeparturePlaceState: MutableSharedFlow<String> = MutableSharedFlow()
    private val _departurePlaceState: MutableStateFlow<String> = MutableStateFlow("")
    val departurePlaceState: StateFlow<String> = _departurePlaceState.asStateFlow()

    private val _offersState: MutableStateFlow<List<OfferUi>> = MutableStateFlow(emptyList())
    val offersState: StateFlow<List<OfferUi>> = _offersState.asStateFlow()

    private val _offersTicketsState: MutableStateFlow<List<OfferTicketUi>> = MutableStateFlow(emptyList())
    val offersTicketsState: StateFlow<List<OfferTicketUi>> = _offersTicketsState.asStateFlow()

    private val _ticketsState: MutableStateFlow<List<TicketUi>> = MutableStateFlow(emptyList())
    val ticketsState: StateFlow<List<TicketUi>> = _ticketsState.asStateFlow()


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

    fun getOffersTickets() {
        viewModelScope.launch {
            getOffersTicketsFeatureCase().map {
                OfferTicketUi(
                    id = it.id,
                    title = it.title,
                    timeRange = it.timeRange.joinToString("  "),
                    price = it.price.formatNumberByDigits()
                )
            }.apply {
                _offersTicketsState.emit(this)
            }
        }
    }

    fun getTickets() {
        viewModelScope.launch {
            getTicketsFeatureCase().map {
                val departureTime = it.departureDate.formatStringDateToTime()
                val arrivalTime = it.arrivalDate.formatStringDateToTime()
                TicketUi(
                    id = it.id,
                    badge = it.badge,
                    price = it.price.formatNumberByDigits(),
                    departureTime = departureTime,
                    departureAirport = it.departureAirport,
                    arrivalTime = arrivalTime,
                    arrivalAirport = it.arrivalAirport,
                    hasTransfer = it.hasTransfer,
                    travelTime = calculateTravelTime(departureTime, arrivalTime)
                )
            }.apply {
                _ticketsState.emit(this)
            }
        }
    }

    fun navigateToArrivalDialogFragment() =
        airfaresNavigation.toArrivalDialogFragment()

    fun navigateToDifficultRouteFragment() : Int =
        airfaresNavigation.toDifficultRouteFragment()

    fun navigateToWeekendFragment() : Int =
        airfaresNavigation.toWeekendFragment()

    fun navigateToHotTicketsFragment() : Int =
        airfaresNavigation.toHotTicketsFragment()

    fun navigateToOffersTicketsFragment() : Int =
        airfaresNavigation.toOffersTicketsFragment()

    fun navigateToTicketsFragment() : Int =
        airfaresNavigation.toTicketsFragment()

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
}