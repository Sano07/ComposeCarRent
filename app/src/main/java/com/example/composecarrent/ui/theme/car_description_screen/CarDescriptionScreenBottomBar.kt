package com.example.composecarrent.ui.theme.car_description_screen

import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecarrent.R

@Composable
fun CarDescriptionScreenBottomBar(onStepBack: () -> Unit) {
    val colorWhiteBack = colorResource(id = R.color.white2)

    var isPressedRent by remember { mutableStateOf(false) }
    val scaleRent by animateFloatAsState(
        if (isPressedRent) 0.9f else 1f,
    )
    var isPressedBack by remember { mutableStateOf(false) }
    val scaleDelete by animateFloatAsState(
        if (isPressedBack) 0.9f else 1f,
    )

    Box {
        Row(modifier = Modifier.fillMaxWidth().background(colorWhiteBack).height(120.dp)) {
            Box(
                modifier = Modifier
                    .graphicsLayer(
                        scaleX = scaleDelete,
                        scaleY = scaleDelete
                    )
                    .pointerInteropFilter {
                        when (it.action) {
                            MotionEvent.ACTION_DOWN -> isPressedBack = true
                            MotionEvent.ACTION_UP -> {
                                isPressedBack = false
                            }

                            MotionEvent.ACTION_CANCEL -> isPressedBack = false
                        }
                        false
                    }
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(15.dp)
                        .height(60.dp),
                    onClick = {
                        onStepBack()
                        isPressedBack = false
                    },
                    colors = ButtonDefaults.buttonColors(Color.White),
                    border = BorderStroke(2.dp, Color.Black)
                ) {
                    Text(
                        text = "Back",
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                }
            }
            Box(
                modifier = Modifier
                    .graphicsLayer(
                        scaleX = scaleRent,
                        scaleY = scaleRent
                    )
                    .pointerInteropFilter {
                        when (it.action) {
                            MotionEvent.ACTION_DOWN -> isPressedRent = true
                            MotionEvent.ACTION_UP -> {
                                isPressedRent = false
                            }

                            MotionEvent.ACTION_CANCEL -> isPressedRent = false
                        }
                        false
                    }
            ) {
                Button(
                    onClick = {
                        // какая то логика для заказа машины
                        isPressedRent = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    border = BorderStroke(2.dp, Color.Black)
                ) {
                    Text(text = "Rent", color = Color.Black, fontSize = 18.sp)
                }
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
    }
}