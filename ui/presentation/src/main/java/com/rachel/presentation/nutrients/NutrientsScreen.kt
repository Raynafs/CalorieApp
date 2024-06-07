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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.BakeryDining
import androidx.compose.material.icons.rounded.Blender
import androidx.compose.material.icons.rounded.BreakfastDining
import androidx.compose.material.icons.rounded.Coffee
import androidx.compose.material.icons.rounded.Cookie
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
                                color = Color.Black
                            )
                            Text(
                                text = "${calorie.servingSizeGrams}g serving",
                                color = Color.Black
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            NutrientDetails(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.onBackground, RoundedCornerShape(16.dp)),
                                borderStroke = BorderStroke(0.4.dp, Color.Black),
                                icon = {
                                    Icon(

                                        imageVector = Icons.Rounded.BakeryDining,
                                        contentDescription = "",
                                    )
                                },
                                title = "Calories",
                                value = "${calorie.calories}kcal"
                            )

                            NutrientDetails(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.onPrimaryContainer,RoundedCornerShape(16.dp)),
                                borderStroke = BorderStroke(0.4.dp, Color.Black),
                                icon = {
                                    Icon(
                                        imageVector = Icons.Rounded.Blender,
                                        contentDescription = "",
                                    )
                                },
                                title = "Cholesterol",
                                value = "${calorie.cholesterolMilligrams}mg"
                            )
                        }
                        Spacer(Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            NutrientDetails(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.onPrimary,
                                        RoundedCornerShape(16.dp)
                                    ),
                                borderStroke = BorderStroke(0.4.dp, Color.Black),
                                icon = {
                                    Icon(
                                        imageVector = Icons.Rounded.Coffee,
                                        contentDescription = "",
                                    )
                                },
                                title = "Fat",
                                value = "${calorie.fatTotalGrams}g"
                            )

                            NutrientDetails(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.onTertiary,
                                        RoundedCornerShape(16.dp)),

                                borderStroke = BorderStroke(0.4.dp, Color.Black),
                                icon = {
                                    Icon(
                                        imageVector = Icons.Rounded.Cookie,
                                        contentDescription = "",
                                    )
                                },
                                title = "Sugar",
                                value = "${calorie.sugarGrams}g"
                            )
                        }
                        Spacer(Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ){
                            NutrientDetails(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.onTertiaryContainer,RoundedCornerShape(16.dp)),

                                borderStroke = BorderStroke(0.4.dp, Color.Black),
                                icon = {
                                    Icon(
                                        imageVector = Icons.Rounded.BreakfastDining,
                                        contentDescription = "",
                                    )
                                },
                                title = "Protein",
                                value = "${calorie.proteinGrams}g"
                            )

                            NutrientDetails(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.onBackground,RoundedCornerShape(16.dp)),

                                borderStroke = BorderStroke(0.4.dp, Color.Black),
                                icon = {
                                    Icon(
                                        imageVector = Icons.Rounded.SetMeal,
                                        contentDescription = "",
                                    )
                                },
                                title = "Carbohydrates",
                                value = "${calorie. carbohydratesTotalGrams}g"
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
