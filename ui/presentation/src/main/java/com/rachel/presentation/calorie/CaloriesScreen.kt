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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rachel.presentation.R
import com.rachel.presentation.navigation.Screens

@Composable
fun CaloriesScreenContent(
    navController: NavController,
    viewModel: CaloriesViewModel = hiltViewModel()
) {
    val error by viewModel.hasError.collectAsState(initial = null)
    val isLoading by viewModel.isLoading.collectAsState(initial = false)
    val isEmpty by viewModel.isEmpty.collectAsState(initial = false)

    val query by viewModel.query.collectAsState(initial = "")
    val uiState by viewModel.state.collectAsState()
    val list by viewModel.calories.collectAsState(initial = listOf())

    Scaffold(
        topBar = {
            Column(
                modifier =
                    Modifier.background(
                            shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        .padding(vertical = 20.dp, horizontal = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.hello),
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.find_food),
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(24.dp))
                OutlinedTextField(
                    modifier =
                        Modifier.fillMaxWidth()
                            .height(65.dp)
                            .padding(vertical = 8.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .background(MaterialTheme.colorScheme.primary),
                    singleLine = true,
                    placeholder = { Text(text = stringResource(id = R.string.search), fontSize = 16.sp) },
                    value = query,
                    onValueChange = { viewModel.updateQuery(it) },
                    trailingIcon = {
                        IconButton(
                            enabled = query.isNotBlank() or (uiState !is CaloriesUiState.Loading),
                            onClick = { viewModel.search() }
                        ) {
                            Icon(imageVector = Icons.Rounded.Search, contentDescription = "search food")
                        }
                    },
                    shape = RoundedCornerShape(30.dp),
                    colors =
                        TextFieldDefaults.colors(
                            disabledTextColor = Color.Transparent,
                            focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                            unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                            cursorColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        )
                )
            }
        },
        snackbarHost = {
            if (error != null) {
                Snackbar(modifier = Modifier.padding(8.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = error?.uppercase() ?: "")
                        Spacer(modifier = Modifier)

                        Button(onClick = { viewModel.hideError() }) {
                            Text(text = stringResource(id = R.string.retry))
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier =
                Modifier.padding(innerPadding)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onSecondary)
        ) {
            AnimatedVisibility(
                visible = isLoading,
                enter = slideInVertically(),
                exit = slideOutVertically()
            ) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
            AnimatedVisibility(
                visible = isEmpty,
                enter = slideInVertically(),
                exit = slideOutVertically()
            ) {
                Row(
                    modifier =
                        Modifier.background(MaterialTheme.colorScheme.primary).fillMaxWidth().padding(16.dp)
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "No items found for $query",
                        color = Color.Black
                    )
                    Icon(
                        modifier = Modifier.clickable { viewModel.hideEmpty() },
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "remove empty error",
                        tint = Color.Black
                    )
                }
            }

            Column(modifier = Modifier.weight(1f)) {
                when (uiState) {
                    is CaloriesUiState.Error -> {
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
                                fontSize = TextUnit(24f, TextUnitType.Sp),
                                color = Color.Black
                            )
                            Text(
                                modifier = Modifier.padding(vertical = 16.dp),
                                text = (uiState as CaloriesUiState.Error).message,
                                color = Color.Black
                            )

                            Button(onClick = { viewModel.search() }) { Text(text = "retry") }
                        }
                    }
                    CaloriesUiState.Empty -> {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val painter = painterResource(id = R.drawable.search)
                            Image(
                                modifier = Modifier.width(250.dp).height(250.dp),
                                painter = painter,
                                contentDescription = "Image of Error"
                            )

                            Text(text = stringResource(id = R.string.no_items), color = Color.Black)
                        }
                    }
                    CaloriesUiState.Loading -> {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    CaloriesUiState.Success -> {
                        LazyColumn {
                            items(items = list) {
                                CalorieItem(calorie = it) {
                                    navController.navigate(Screens.Nutrients.route.plus("/${it.name}"))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CaloriesScreen(navController: NavController) {
    CaloriesScreenContent(navController = navController)
}

@Preview
@Composable
fun CaloriesScreenPreview() {
    val context = LocalContext.current
    CaloriesScreen(navController = NavController(context = context))
}
