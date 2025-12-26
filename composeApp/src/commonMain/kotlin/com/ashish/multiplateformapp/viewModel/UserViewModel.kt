package com.ashish.multiplateformapp.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashish.db.UserEntity
import com.ashish.multiplateformapp.model.UserResponse
import com.ashish.multiplateformapp.repository.UserRepository
import com.ashish.multiplateformapp.repository.WebRepository
import com.ashish.multiplateformapp.state.Loading
import com.ashish.multiplateformapp.state.Success
import com.ashish.multiplateformapp.state.UIState
import com.ashish.multiplateformapp.state.Users
import kotlinx.coroutines.launch

class UserViewModel(private val webRepository: WebRepository,private val userRepository: UserRepository): ViewModel() {

    private var _uiState = mutableStateOf<UIState>(Loading)
    val uiState = _uiState

    fun getData(){
        viewModelScope.launch {
//            webRepository.hitUrl("https://jsondata.reactbd.com/api/users")
            userRepository.observeUsers().let {
                if(it.isNotEmpty()){
                    _uiState.value = Success(Users(it))
                }else{
                    webRepository.hitUrl("https://fakestoreapiserver.reactbd.org/api/users?page=1&perPage=10")
                        .also {
                            userRepository.saveUsers(it.data)
                            _uiState.value = Success(Users(it.data))
                        }
                }
            }
        }
    }

}