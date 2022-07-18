package br.com.pochilt.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.pochilt.domain.GetUserNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getUserNameUseCase: GetUserNameUseCase): ViewModel() {

    private val _screen = MutableSharedFlow<Screen>()
    val screen : SharedFlow<Screen> = _screen

    fun init() = viewModelScope.launch {
        _screen.emit(Screen.UpdateTitle(getUserNameUseCase.getUserName()))
    }

    sealed class Screen {
        data class UpdateTitle(val title: String) : Screen()
    }
}