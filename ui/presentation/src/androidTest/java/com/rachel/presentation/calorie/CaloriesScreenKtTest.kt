/*
 * Copyright 2021-2024 The Calorie App Systems, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rachel.presentation.calorie

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToIndex
import androidx.navigation.compose.rememberNavController
import com.rachel.domain.models.Calorie
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlin.test.Test
import org.junit.Before
import org.junit.Rule

@HiltAndroidTest
class CaloriesScreenKtTest {
    @get:Rule val composeTestRule = createComposeRule()

    @get:Rule var hiltRule = HiltAndroidRule(this)

    lateinit var caloriesViewModel: CaloriesViewModel

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testCaloriesListIsScrollable() {
        // Set up the test data and state
        val testList =
            listOf(
                Calorie(
                    name = "Apple",
                    calories = 10.0,
                    servingSizeGrams = 19.1,
                    fatTotalGrams = 18.0,
                    fatSaturatedGrams = 18.0,
                    proteinGrams = 18.0,
                    sodiumMilligrams = 18.0,
                    potassiumMilligrams = 18.0,
                    cholesterolMilligrams = 18.0,
                    carbohydratesTotalGrams = 18.0,
                    fiberGrams = 18.0,
                    sugarGrams = 18.0
                ),
                Calorie(
                    name = "Apple",
                    calories = 10.0,
                    servingSizeGrams = 19.1,
                    fatTotalGrams = 18.0,
                    fatSaturatedGrams = 18.0,
                    proteinGrams = 18.0,
                    sodiumMilligrams = 18.0,
                    potassiumMilligrams = 18.0,
                    cholesterolMilligrams = 18.0,
                    carbohydratesTotalGrams = 18.0,
                    fiberGrams = 18.0,
                    sugarGrams = 18.0
                ),
                Calorie(
                    name = "Apple",
                    calories = 10.0,
                    servingSizeGrams = 19.1,
                    fatTotalGrams = 18.0,
                    fatSaturatedGrams = 18.0,
                    proteinGrams = 18.0,
                    sodiumMilligrams = 18.0,
                    potassiumMilligrams = 18.0,
                    cholesterolMilligrams = 18.0,
                    carbohydratesTotalGrams = 18.0,
                    fiberGrams = 18.0,
                    sugarGrams = 18.0
                ),
                Calorie(
                    name = "Apple",
                    calories = 10.0,
                    servingSizeGrams = 19.1,
                    fatTotalGrams = 18.0,
                    fatSaturatedGrams = 18.0,
                    proteinGrams = 18.0,
                    sodiumMilligrams = 18.0,
                    potassiumMilligrams = 18.0,
                    cholesterolMilligrams = 18.0,
                    carbohydratesTotalGrams = 18.0,
                    fiberGrams = 18.0,
                    sugarGrams = 18.0
                ),
                Calorie(
                    name = "Apple",
                    calories = 10.0,
                    servingSizeGrams = 19.1,
                    fatTotalGrams = 18.0,
                    fatSaturatedGrams = 18.0,
                    proteinGrams = 18.0,
                    sodiumMilligrams = 18.0,
                    potassiumMilligrams = 18.0,
                    cholesterolMilligrams = 18.0,
                    carbohydratesTotalGrams = 18.0,
                    fiberGrams = 18.0,
                    sugarGrams = 18.0
                )
            )

        composeTestRule.setContent {
            CaloriesScreenContent(navController = rememberNavController(), viewModel = caloriesViewModel)
        }

        // Verify that the LazyColumn is scrollable
        composeTestRule
            .onNodeWithTag("CaloriesList", useUnmergedTree = true)
            .performScrollToIndex(testList.size - 1) // Scroll to the last item
            .assertExists()
    }

    @Test
    fun testSearchOutlinedTextFieldIsVisible() {
        // Set up the test data and state
        composeTestRule.setContent {
            CaloriesScreenContent(navController = rememberNavController(), viewModel = caloriesViewModel)
        }

        // Verify the presence and visibility of the OutlinedTextField and its components
        composeTestRule
            .onNodeWithTag("SearchTextField")
            .assertIsDisplayed()
            .assert(hasText("Search")) // Verify placeholder text

        composeTestRule
            .onNodeWithContentDescription("search food")
            .assertIsDisplayed()
            .assert(hasClickAction()) // Verify the search icon is clickable
    }
}
