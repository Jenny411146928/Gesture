package tw.edu.pu.csim.gesture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import tw.edu.pu.csim.gesture.ui.theme.GestureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GestureTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    //Greeting("Android")
                    //PointerEvents()
                    Tap()
                }
            }
        }
    }
}

@Composable
fun Tap() {
    var msg by remember { mutableStateOf("TAP相關手勢實例") }
    var offset1 by remember { mutableStateOf(Offset.Zero) }
    var offset2 by remember { mutableStateOf(Offset.Zero) }
    var PU = arrayListOf(R.drawable.pu0, R.drawable.pu1,
        R.drawable.pu2, R.drawable.pu3,
        R.drawable.pu4, R.drawable.pu5)
    var Number by remember { mutableStateOf(0) }


    Column {
        Text(text = msg)

        Image(
            painter = painterResource(id = PU[Number]),
            contentDescription = "靜宜美",
            modifier = Modifier
                .fillMaxSize()
                /*.pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {msg = "後觸發onTap(短按)"},
                        onDoubleTap = {msg = "雙擊"},
                        onLongPress = {msg = "長按"},
                        onPress = {msg = "先觸發onPress(按下)"}
                    )}*/
                .pointerInput(Unit) {
                    detectDragGesturesAfterLongPress(
                        onDrag = { change, dragAmount -> offset2+=dragAmount},
                        onDragStart = {
                            offset1 = it
                            offset2 = it},
                        onDragEnd = {if (offset2.x >= offset1.x){
                            msg = "長按後向右拖曳"
                            Number ++
                            if (Number>5){Number=0}
                        }
                        else{
                            msg = "長按後向左拖曳"
                            Number --
                            if (Number<0){Number=5}
                        }
                        }
                    )
                }




        )
    }
}

