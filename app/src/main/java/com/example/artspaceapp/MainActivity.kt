package com.example.artspaceapp

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.ColorUtils
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme {
                val window = (LocalView.current.context as Activity).window
                var i by remember { mutableIntStateOf(0) }
                val bgColor = when (i) {
                    0 -> Color(0xffecf284)
                    1 -> Color(0xff17deee)
                    2 -> Color(0xffff4162)
                    else -> Color(0xff17deee)
                }

                SideEffect {
                    window.statusBarColor = bgColor.toArgb()
                    window.navigationBarColor = bgColor.toArgb()
                }

                val resourceId: IntArray = when (i) {
                    0 -> intArrayOf(
                        R.drawable.art_1,
                        R.string.art_title_1,
                        R.string.art_artist_name_1,
                        R.string.art_year_1
                    )

                    1 -> intArrayOf(
                        R.drawable.art_2,
                        R.string.art_title_2,
                        R.string.art_artist_name_2,
                        R.string.art_year_2
                    )

                    2 -> intArrayOf(
                        R.drawable.art_3,
                        R.string.art_title_3,
                        R.string.art_artist_name_3,
                        R.string.art_year_3
                    )

                    else -> intArrayOf(
                        R.drawable.art_1,
                        R.string.art_title_1,
                        R.string.art_artist_name_1,
                        R.string.art_year_1
                    )
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(bgColor)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(
                            alignment = Alignment.CenterVertically,
                            space = 18.dp
                        ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Surface(
                            shadowElevation = 8.dp,
                            color = bgColor,
                            modifier = Modifier
                                .weight(2f)
                                .padding(0.dp, 32.dp)
                        ) {
                            Image(
                                modifier = Modifier.padding(32.dp, 64.dp),
                                painter = painterResource(resourceId[0]),
                                contentDescription = ""
                            )
                        }
                        Surface(
                            shadowElevation = 4.dp, color = Color(0xffF5F5DC),
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    stringResource(resourceId[1]),
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Light
                                )
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        stringResource(resourceId[2]),
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(" (")
                                    Text(
                                        stringResource(resourceId[3]),
                                        fontStyle = FontStyle.Italic
                                    )
                                    Text(")")
                                }
                            }
                        }
                        Row(
                            verticalAlignment = Alignment.Bottom,
                            modifier = Modifier.weight(0.5f)
                        ) {
                            MyButton(
                                onClick = {
                                    i = if (i - 1 < 0) 2 else i - 1
                                },
                                bgColor = bgColor,
                                label = R.string.previous
                            )
                            MyButton(
                                onClick = {
                                    i = (1 + i) % 3
                                },
                                bgColor = bgColor,
                                label = R.string.next
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.MyButton(
    @StringRes label: Int,
    bgColor: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonColors(
            containerColor = Color(
                ColorUtils.blendARGB(
                    bgColor.toArgb(),
                    Color.DarkGray.toArgb(),
                    0.2f
                )
            ),
            contentColor = Color.DarkGray,
            disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor,
            disabledContainerColor = ButtonDefaults.buttonColors().disabledContainerColor
        ),
        modifier = Modifier
            .weight(1f)
            .padding(24.dp, 0.dp)
    ) {
        Text(stringResource(label))
    }
}