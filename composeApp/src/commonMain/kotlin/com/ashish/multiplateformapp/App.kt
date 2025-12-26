package com.ashish.multiplateformapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashish.multiplateformapp.model.Data
import com.ashish.multiplateformapp.state.Error
import com.ashish.multiplateformapp.state.Loading
import com.ashish.multiplateformapp.state.Success
import com.ashish.multiplateformapp.state.UIState
import com.ashish.multiplateformapp.state.Users
import com.ashish.multiplateformapp.viewModel.UserViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview


@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel = koinViewModel<UserViewModel>()
        var uiState by remember { viewModel.uiState }

        LaunchedEffect(Unit){
            viewModel.getData()
        }

        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(
                        vertical = innerPadding.calculateTopPadding(),
                        horizontal = 24.dp
                    )
            ) {
                when(uiState){
                    is Error -> Text(text = (uiState as Error).error)
                    Loading -> CircularProgressIndicator()
                    is Success -> Text(text = ((uiState as Success).apiResponse as Users).user.size.toString(),
                        style = TextStyle(
                            color = Color.Red,
                            fontSize = 20.sp
                        )
                    )
                }
            }
        }
    }
}