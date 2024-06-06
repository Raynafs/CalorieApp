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

package com.rachel.presentation.nutrients

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.BreakfastDining
import androidx.compose.material.icons.rounded.Egg
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.material.icons.rounded.SetMeal
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rachel.presentation.R

@ExperimentalMaterial3Api
@Composable
private fun NutrientsScreenContent(
    navController: NavController,
    viewModel: NutrientsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "navigate back")
                    }
                },
                title = {}
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            when (uiState) {
                NutrientsUiState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val painter = painterResource(id = R.drawable.interneterror)
                        Image(
                            modifier = Modifier.width(250.dp).height(250.dp),
                            painter = painter,
                            contentDescription = "sad child"
                        )

                        Text(
                            text = stringResource(id = R.string.ooops),
                            fontSize = TextUnit(24f, TextUnitType.Sp)
                        )
                        Text(modifier = Modifier.padding(vertical = 16.dp), text = "Couldn't load details")

                        Button(onClick = { viewModel.fetch() }) { Text(text = "retry") }
                    }
                }
                NutrientsUiState.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is NutrientsUiState.Success -> {
                    val calorie = (uiState as NutrientsUiState.Success).nutrient

                    Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
                        Column(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text =
                                    calorie.name.split(" ").joinToString(" ") { s ->
                                        s.replaceFirstChar { char -> char.uppercase() }
                                    },
                                fontSize = MaterialTheme.typography.displaySmall.fontSize,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                            Text(
                                text = "${calorie.servingSizeGrams}g serving",
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            NutrientDetails(
                                modifier = Modifier.weight(1f),
                                borderStroke = BorderStroke(0.4.dp, Color.Red),
                                icon = {
                                    Icon(
                                        imageVector = Icons.Rounded.LocalFireDepartment,
                                        contentDescription = "",
                                        tint = Color.Red
                                    )
                                },
                                title = "Calories",
                                value = "${calorie.calories}kcal"
                            )

                            NutrientDetails(
                                modifier = Modifier.weight(1f),
                                borderStroke = BorderStroke(0.4.dp, Color.Blue),
                                icon = {
                                    Icon(
                                        imageVector = Icons.Rounded.BreakfastDining,
                                        contentDescription = "",
                                        tint = Color.Blue
                                    )
                                },
                                title = "Carbs",
                                value = "${calorie.carbohydratesTotalGrams}g"
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            NutrientDetails(
                                modifier = Modifier.weight(1f),
                                borderStroke = BorderStroke(0.4.dp, Color(0xFFDDE015)),
                                icon = {
                                    Icon(
                                        imageVector = Icons.Rounded.SetMeal,
                                        contentDescription = "",
                                        tint = Color(0xFFDDE015)
                                    )
                                },
                                title = "Fat",
                                value = "${calorie.fatTotalGrams}g"
                            )

                            NutrientDetails(
                                modifier = Modifier.weight(1f),
                                borderStroke = BorderStroke(0.4.dp, Color(0xFF3DDC97)),
                                icon = {
                                    Icon(
                                        imageVector = Icons.Rounded.Egg,
                                        contentDescription = "",
                                        tint = Color(0xFF3DDC97)
                                    )
                                },
                                title = "Protein",
                                value = "${calorie.proteinGrams}g"
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutrientsScreen(navController: NavController) {
    NutrientsScreenContent(navController = navController)
}
