package com.example.compose_to_do_list


import android.graphics.Paint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun CustomComponent(
    canvas_size: Dp = 300.dp,
    indicator_value: Int = 0,
    max_Indicator_value: Int = 100,
    strokewidth: Float = 74f,
    background_indicator_color: Color = Color(119, 50, 50, 255)
) {

    var allowedIndicatorValue by remember {
        mutableStateOf(max_Indicator_value)
    }
    val btext by animateIntAsState(
        targetValue = allowedIndicatorValue,
        animationSpec = tween(1500)
        )
    allowedIndicatorValue =
        if (indicator_value <= max_Indicator_value) indicator_value else max_Indicator_value

    var animateindicatorvalue by remember {
        mutableStateOf(0f)
    }
    LaunchedEffect(key1 = allowedIndicatorValue, block = {
        animateindicatorvalue=allowedIndicatorValue.toFloat()
    })

    var percentage = (animateindicatorvalue / max_Indicator_value) * 100

    val sweepangle by animateFloatAsState(
        targetValue = (2.4 * percentage).toFloat(), animationSpec = tween(1500)
    )

     Column(
        Modifier
            .size(canvas_size)
            .background(Color(153, 142, 142, 255))
            .drawBehind {
                val componentsize = size / 1.5f
                backgroundIndicator(componentsize, background_indicator_color, strokewidth)
                foregroundIndicator(componentsize, sweepangle = sweepangle)
            },horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        embeddedElements(bigTExt = btext)
    }
}

fun DrawScope.backgroundIndicator(
    componensize: Size,
    indicator_color: Color,
    indicator_Stroke_width: Float
) {
    drawArc(
        size = componensize,
        color = indicator_color,
        startAngle = 150f,
        sweepAngle = 240f,
        useCenter = false,
        style = Stroke(
            width = indicator_Stroke_width,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componensize.width) / 2f,
            y = (size.height - componensize.width) / 2f
        )
    )
}

fun DrawScope.foregroundIndicator(
    componentsize: Size,
    startangle: Float = 150f,
    sweepangle: Float = 240f,
    color: Color = Color.Black,
    strokeWidth: Float = 74f
) {
    drawArc(
        color = color,
        sweepAngle = sweepangle,
        startAngle = startangle,
        size = componentsize,
        useCenter = false,
        style = Stroke(
            width = strokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentsize.width) / 2f,
            y = (size.height - componentsize.width) / 2f
        )

    )
}

@Composable
fun embeddedElements(
    bigTExt:Int,
    bigTextSuffix:String="GB",
    smallText:String="Remaining",
){
    Text(text = smallText,
        textAlign = TextAlign.Center
    )
    Text(
        text = "$bigTExt ${bigTextSuffix.take(2)}",
        textAlign = TextAlign.Center
    )
}

@Composable
@Preview
fun CustomComponentPreview() {
    CustomComponent(strokewidth = 74f, background_indicator_color = Color.White)
}