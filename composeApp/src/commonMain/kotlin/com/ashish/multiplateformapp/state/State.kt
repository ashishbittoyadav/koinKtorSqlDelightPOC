package com.ashish.multiplateformapp.state

import com.ashish.multiplateformapp.model.Data
import com.ashish.multiplateformapp.model.UserResponse

sealed interface UIState

data class Success(val apiResponse : UiData) : UIState
data class Error(val error : String) : UIState
object Loading : UIState

abstract class UiData

class Users(val user : List<Data> ): UiData()