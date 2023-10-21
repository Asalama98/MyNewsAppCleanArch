package com.example.mynewsappcleanarch.mealz.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<Event : ViewEvent, UiState : ViewState> : ViewModel() {

    private val initialState: UiState by lazy { setInitialState() }
    abstract fun setInitialState(): UiState

    private val _viewState: MutableState<UiState> = mutableStateOf(initialState)
    val viewState: State<UiState> = _viewState

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()

    private val _effect: Channel<ViewSideEffect> = Channel()
    val effect = _effect.receiveAsFlow()

    lateinit var lastEvent: Event
        private set


    init {
        subscribeToEvents()
    }

    private fun subscribeToEvents() {
        viewModelScope.launch {
            _event.collect {
                lastEvent = it
                handleEvents(it)
            }
        }
    }

    protected abstract fun handleEvents(event: Event)

    fun setEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }

    fun setState(reducer: UiState.() -> UiState) {
        val newState = viewState.value.reducer()
        _viewState.value = newState
    }

    fun setEffect(builder: () -> ViewSideEffect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

    fun <T> launchCoroutine(job: Job? = null, function: suspend (scope: CoroutineScope) -> T): Job {
        return viewModelScope.launch((job ?: Job()) ) {
            function(this)
        }
    }


}