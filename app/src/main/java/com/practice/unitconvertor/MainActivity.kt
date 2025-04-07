package com.practice.unitconvertor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practice.unitconvertor.ui.theme.UnitConvertorTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConvertorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConvertor()
                }
            }
        }
    }
}

@Composable
fun UnitConvertor() {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meter") }
    var outputUnit by remember { mutableStateOf("Meter") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.0) }
    val oConversionFactor = remember { mutableStateOf(1.0) }

    fun convertUnits() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100 / oConversionFactor.value).roundToInt() / 100
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Unit Converter",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = inputValue,
            onValueChange = { inputValue = it
                              convertUnits()
                            },
            label = { Text("Enter Value") }

        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box {
                FilledTonalButton(onClick = { iExpanded = true }) {
                    Text(inputUnit)
                    Icon(
                        Icons.Default.KeyboardArrowDown,
                        contentDescription = "Select"
                    )
                }
                DropdownMenu(
                    expanded = iExpanded,
                    onDismissRequest = { iExpanded = false }
                ){
                    DropdownMenuItem(
                        text = { Text(text = "Kilometer") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Kilometer"
                            conversionFactor.value = 1000.00
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meter") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meter"
                            conversionFactor.value = 1.00
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Centimeter") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeter"
                            conversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimeter") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Millimeter"
                            conversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box {
                FilledTonalButton(onClick = { oExpanded  = true }) {
                    Text(outputUnit)
                    Icon(
                        Icons.Default.KeyboardArrowDown,
                        contentDescription = "KeyboardArrowDown"
                    )
                }
                DropdownMenu(
                    expanded = oExpanded,
                    onDismissRequest = { oExpanded = false }
                ){
                    DropdownMenuItem(
                        text = { Text(text = "Kilometer") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Kilometer"
                            oConversionFactor.value = 1000.00
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meter") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Meter"
                            oConversionFactor.value = 1.00
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Centimeter") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centimeter"
                            oConversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimeter") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Millimeter"
                            oConversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                }

            }

        }
        Text("Result: $outputValue $outputUnit")

    }

}

@Preview(showBackground = true)
@Composable
fun UnitConverter_Preview() {
    UnitConvertorTheme {
        UnitConvertor()
    }
}
