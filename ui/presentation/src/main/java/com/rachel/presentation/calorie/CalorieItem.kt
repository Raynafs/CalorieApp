package  com.rachel.presentation.calorie

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rachel.data.models.Calorie

@Composable
fun CalorieItem(calorie: Calorie, block: () -> Unit) {

    Spacer(modifier = Modifier.height(8.dp))
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        border = BorderStroke(0.4.dp, Color.Red)
    ) {
        Column(
            modifier = Modifier
                .clickable { block.invoke() }
                .background(MaterialTheme.colorScheme.surface)
                .padding(24.dp),
        ) {
            Text(
                text = calorie.name.replaceFirstChar { it.uppercase() },
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(text = "\uD83D\uDCAA ${calorie.calories.toInt()} kcal")

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            ) {

                    Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "${calorie.proteinGrams.toInt()} g",
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Protein",
                        fontSize = MaterialTheme.typography.bodySmall.fontSize
                    )
                }



                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "${calorie.carbohydratesTotalGrams.toInt()} g",
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Carbs",
                        fontSize = MaterialTheme.typography.bodySmall.fontSize
                    )
                }


                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "${calorie.fatTotalGrams.toInt()} g",
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Fat",
                        fontSize = MaterialTheme.typography.bodySmall.fontSize
                    )
                }
            }

        }
    }

}

@Preview
@Composable
fun CalorieItemPreview() {
    CalorieItem(
        calorie = Calorie(
            "rice",
            10.0,
            10.0,
            10.0,
            10.0,
            10.0,
            10.0,
            10.0,
            10.0,
            10.0,
            10.0,
            10.0,
        )
    ) {

    }
}