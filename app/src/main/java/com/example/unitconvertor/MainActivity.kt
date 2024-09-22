@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.unitconvertor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconvertor.ui.theme.UnitConvertorTheme
import java.time.format.TextStyle
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
fun UnitConvertor(){
    var inputValue by remember { mutableStateOf("")}
    var outputValue by remember { mutableStateOf("")}
    var inputUnit by remember { mutableStateOf("Meters")}
    var outputUnit by remember { mutableStateOf("Meters")}
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionConvertor = remember { mutableStateOf(1.0) }
    val oConversionConvertor = remember { mutableStateOf(1.0) }

    val customStyle = androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 16.sp,
        color = Color.Black

    )
    fun ConvertUnits() {
        //?: elvis Operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionConvertor.value*100/oConversionConvertor.value).roundToInt()/100.0
        outputValue = result.toString()
    }

    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        //Ui elements below each other
        Text(text = "Unit Converter",
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier= Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange ={
            inputValue = it
            ConvertUnits()
        },
            label = { Text(text = "Enter Value")}
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //Ui elements will be stacked next to each other
            //Input Box
           Box {
               //Input Button
                    Button(onClick = { iExpanded = true }) {
                        Text(text = inputUnit)
                        Icon(Icons.Default.ArrowDropDown,contentDescription = "Arrow Down")
                    }
               DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                   DropdownMenuItem(text = {
                       Text(text = "Centimeters")
                   }, onClick = { 
                       iExpanded = false
                       inputUnit =  "Centimeters"
                       conversionConvertor.value = .01
                       ConvertUnits()
                   })
                   DropdownMenuItem(text = {
                       Text(text = "Meters")
                   }, onClick = {
                       iExpanded = false
                       inputUnit =  "Meters"
                       conversionConvertor.value = 1.0
                       ConvertUnits()
                   })
                   DropdownMenuItem(text = {
                       Text(text = "Feet")
                   }, onClick = {
                       iExpanded = false
                       inputUnit =  "Feet"
                       conversionConvertor.value = .3048
                       ConvertUnits()
                   })
                   DropdownMenuItem(text = {
                       Text(text = "MiliMeters")
                   }, onClick = {
                       iExpanded = false
                       inputUnit =  "MiliMeters"
                       conversionConvertor.value = 0.001
                       ConvertUnits()
                   })
               }
           }
            Spacer(modifier = Modifier.width(16.dp))
            //Output Box
            Box{
                //Output Button
                Button(onClick = { oExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown,contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = {
                        Text(text = "Centimeters")
                    }, onClick = {
                        oExpanded = false
                        outputUnit =  "Centimeters"
                        oConversionConvertor.value = .01
                        ConvertUnits()
                    })
                    DropdownMenuItem(text = {
                        Text(text = "Meters")
                    }, onClick = {
                        oExpanded = false
                        outputUnit =  "Meters"
                        oConversionConvertor.value = 1.0
                        ConvertUnits()
                    })
                    DropdownMenuItem(text = {
                        Text(text = "Feet")
                    }, onClick = {
                        oExpanded = false
                        outputUnit =  "Feet"
                        oConversionConvertor.value = .3048
                        ConvertUnits()
                    })
                    DropdownMenuItem(text = {
                        Text(text = "MiliMeters")
                    }, onClick = {
                        oExpanded = false
                        outputUnit =  "MiliMeters"
                        oConversionConvertor.value = .001
                        ConvertUnits()
                    })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        //Result Text
        Text(text = "Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium

            )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConvertor()
}