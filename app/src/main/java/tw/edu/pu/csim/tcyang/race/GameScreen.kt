package tw.edu.pu.csim.tcyang.race

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameScreen(message: String, gameViewModel: GameViewModel) {
    val imageBitmap = ImageBitmap.imageResource(R.drawable.horse0)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        gameViewModel.MoveCircle(dragAmount.x, dragAmount.y)
                    }
                }
        ) {
            // 繪製圓形
            drawCircle(
                color = Color.Red,
                radius = 100f,
                center = Offset(x = gameViewModel.circleX, y = gameViewModel.circleY)
            )
            drawImage(
                image = imageBitmap,
                dstOffset = IntOffset(0, 100),
                dstSize = IntSize(200, 200)
            )

        }
        Text("e04")
        // 資訊顯示區
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Text(
                text = message + gameViewModel.score,
                fontSize = 20.sp,
                color = Color.Black
            )
            Text(
                text = "螢幕: ${gameViewModel.screenWidthPx.toInt()} × ${gameViewModel.screenHeightPx.toInt()}",
                fontSize = 16.sp,
                color = Color.Black
            )
        }

        // 按鈕區
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Button(
                onClick = { gameViewModel.StartGame() },
                enabled = !gameViewModel.gameRunning,
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text(text = "遊戲開始")
            }

            Button(
                onClick = { gameViewModel.StopGame() },
                enabled = gameViewModel.gameRunning
            ) {
                Text(text = "停止")
            }
        }
    }
}