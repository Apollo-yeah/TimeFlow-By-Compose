package com.apollo.timeflow.bycompose.compose.screenAdaptation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apollo.timeflow.bycompose.Device
import com.apollo.timeflow.bycompose.defaultFontFamily
import com.apollo.timeflow.bycompose.getFontSize
import com.apollo.timeflow.bycompose.compose.component.TimeCard
import com.apollo.timeflow.bycompose.viewmodel.MainViewModel

@Preview(
    widthDp = 872,
    heightDp = 411,
)
@Composable
fun CardLargeLandSpace(
    deviceTypes: Device = Device.Phone(),
    leftOnClick: () -> Unit = {},
    rightOnClick: () -> Unit = {},
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.weight(10f)) {
            Row(
                modifier = Modifier
                    .background(Color.Black),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    TimeCard(
                        deviceTypes = deviceTypes,
                        clickable = leftOnClick,
                        isShowTimeFormat = viewModel.timeFormatRecordDataStoreFlow.collectAsState(
                            initial = false
                        ),
                        amOrPm = viewModel.amOrPm,
                        leftNumber = viewModel.hourLeftNumberState,
                        rightNumber = viewModel.hourRightNumberState,
                    )
                }
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    TimeCard(
                        deviceTypes = deviceTypes,
                        clickable = rightOnClick,
                        isShowTimeFormat = remember { mutableStateOf(false) },
                        amOrPm = null,
                        leftNumber = viewModel.minuteLeftNumberState,
                        rightNumber = viewModel.minuteRightNumberState,
                    )
                }
            }
        }

        if (viewModel.isDateShowDataStoreFlow.collectAsState(initial = false).value) {
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier.wrapContentHeight()
            ) {
                Text(
                    viewModel.currentDate.collectAsState(initial = "").value,
                    color = Color.White,
                    fontSize = getFontSize(deviceTypes),
                    modifier = Modifier.padding(bottom = 10.dp),
                    fontFamily = defaultFontFamily,
                )
            }
        }

    }
}