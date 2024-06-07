package com.rachel.presentation.calorie

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.testing.TestNavHostController
import com.rachel.presentation.navigation.Screens
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.*
import org.junit.Rule
import kotlin.test.Test

@HiltAndroidTest
class CaloriesScreenKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

}