package com.lihan.leagueoflegends.feature.champion.presentation.champion_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.lihan.leagueoflegends.feature.champion.domain.repository.ChampionDetailRepository
import com.lihan.leagueoflegends.feature.champion.presentation.champion_detail.mapper.toChampionInfoUI
import com.lihan.leagueoflegends.feature.core.domain.navigation.Routes
import com.lihan.leagueoflegends.feature.core.domain.util.Result
import com.lihan.leagueoflegends.feature.core.presentation.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChampionDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: ChampionDetailRepository
): ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _state = MutableStateFlow(ChampionDetailState())
    val state = _state.asStateFlow()
        .onStart {
            val name = savedStateHandle.toRoute<Routes.ChampionDetail>().championName
            if(name.isNotEmpty()){
                val localDB = repository.getDetailFromRoomDatabase(name)
                if (localDB == null){
                    viewModelScope.launch {
                        when(val result = repository.getDetail(name)){
                            is Result.Error -> {
                                _uiEvent.send(
                                    UiEvent.ErrorMessage(
                                        message = result.error.toString()
                                    )
                                )
                            }
                            is Result.Success -> {
                                val resultData = result.data
                                val data = resultData?.toChampionInfoUI()
                                _state.update {
                                    it.copy(
                                        championInfoUI = data
                                    )
                                }
                                result.data?.let {
                                    repository.fetchData(
                                        championInfo = it
                                    )
                                }
                            }
                        }
                    }
                }else{
                    val data = localDB.toChampionInfoUI()
                    _state.update {
                        it.copy(
                            championInfoUI = data
                        )
                    }
                }
            }else{
                //onBack
            }
            _state.update {
                it.copy(
                    isLoading = false
                )
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = _state.value
        )
}