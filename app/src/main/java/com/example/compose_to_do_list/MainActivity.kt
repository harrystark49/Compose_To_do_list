package com.example.compose_to_do_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.Placeholder
import com.example.compose_to_do_list.ui.theme.Compose_To_do_listTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_To_do_listTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
//                    Column (
//                        Modifier
//                            .fillMaxSize(),
//                        verticalArrangement = Arrangement.Center,
//                        horizontalAlignment = Alignment.CenterHorizontally
//                            ){
//                        CustomComponent(strokewidth =74f, background_indicator_color = Color.Black)
//                        Spacer(modifier = Modifier
//                            .width(20.dp))
//
//                        var indicatorvalue by remember {
//                            mutableStateOf(0)
//                        }
//                        TextField(value = indicatorvalue.toString(), onValueChange ={
//                            indicatorvalue=it.toInt()
//                        } )
//
//
//                    }


                    Column() {
                        var value by remember {
                            mutableStateOf(0)
                        }
                        CustomComponent(indicator_value = value )

                        TextField(value = value.toString(), onValueChange = {
                            value=if(it.isNotEmpty()){
                                it.toInt()
                            }else{
                                0
                            }
                        },keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ))
                    }

                }
            }
        }
    }
}
@Composable
fun Greeting() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        var name by remember {
            mutableStateOf("")
        }
        var maxChar=10
        OutlinedTextField(label = {
            Text("Name")
        },placeholder = {
            Text("Enter Text")
        },value = name, onValueChange = {
            if(it.length<=maxChar) name = it

        },)
    }

}

@Composable
fun custom(){

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    Compose_To_do_listTheme {
        CustomComponent(strokewidth = 74f, background_indicator_color = Color.Black)
    }
}