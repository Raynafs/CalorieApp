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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rachel.domain.models.Calorie


@Composable
fun CalorieItem(calorie: Calorie, block: () -> Unit) {
    Spacer(modifier = Modifier.height(8.dp))
    Card(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        border = BorderStroke(0.4.dp, Color.Red)
    ) {
        Column(
            modifier =
                Modifier.clickable { block.invoke() }
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(24.dp)
        ) {
            Text(
                text = calorie.name.replaceFirstChar { it.uppercase() },
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(text = "\uD83D\uDCAA ${calorie.calories.toInt()} kcal")

            Row(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "${calorie.proteinGrams.toInt()} g",
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(text = "Protein", fontSize = MaterialTheme.typography.bodySmall.fontSize)
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "${calorie.carbohydratesTotalGrams.toInt()} g",
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(text = "Carbs", fontSize = MaterialTheme.typography.bodySmall.fontSize)
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "${calorie.fatTotalGrams.toInt()} g",
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(text = "Fat", fontSize = MaterialTheme.typography.bodySmall.fontSize)
                }
            }
        }
    }
}

@Preview
@Composable
fun CalorieItemPreview() {
    CalorieItem(
        calorie = Calorie("rice", 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0)
    ) {}
}
