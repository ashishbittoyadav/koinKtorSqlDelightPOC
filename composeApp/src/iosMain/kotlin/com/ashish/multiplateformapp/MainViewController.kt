package com.ashish.multiplateformapp

import androidx.compose.ui.window.ComposeUIViewController
import com.ashish.multiplateformapp.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = { initKoin() }) { App() }