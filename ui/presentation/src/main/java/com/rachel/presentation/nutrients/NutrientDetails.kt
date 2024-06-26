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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NutrientDetails(
    modifier: Modifier,
    borderStroke: BorderStroke,
    icon: @Composable () -> Unit,
    title: String,
    value: String
) {
    Card(modifier = modifier.width(150.dp).padding(4.dp), border = borderStroke) {
        Row(
            modifier =
                Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface).padding(24.dp)
        ) {
            icon()
        }
        Column(
            modifier =
                Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface).padding(16.dp)
        ) {
            Text(text = title)
            Text(text = value)
        }
    }
}
