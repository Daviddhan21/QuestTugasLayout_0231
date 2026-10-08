package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

data class PersonData(
    val name: String,
    val phone: String? = null,
    val location: String,
    val cardBgColor: Color,
    val isCursiveFont: Boolean = false
)

@Composable
fun MainScreen() {
    val personList = listOf(
        PersonData(
            name = stringResource(id = R.string.name_bambang),
            location = stringResource(id = R.string.loc_bambang),
            cardBgColor = colorResource(id = R.color.card_grey),
            isCursiveFont = true
        ),
        PersonData(
            name = stringResource(id = R.string.name_gibran),
            phone = stringResource(id = R.string.phone_gibran),
            location = stringResource(id = R.string.loc_gibran),
            cardBgColor = colorResource(id = R.color.card_purple)
        ),
        PersonData(
            name = stringResource(id = R.string.name_zhilal),
            phone = stringResource(id = R.string.phone_zhilal),
            location = stringResource(id = R.string.loc_zhilal),
            cardBgColor = colorResource(id = R.color.card_blue)
        ),
        PersonData(
            name = stringResource(id = R.string.name_alfian),
            phone = stringResource(id = R.string.phone_alfian),
            location = stringResource(id = R.string.loc_alfian),
            cardBgColor = colorResource(id = R.color.card_green)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(id = R.string.title_prodi),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.title_univ),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            personList.forEach { person ->
                CustomPersonCard(person = person)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        Text(
            text = stringResource(id = R.string.copyright),
            fontSize = 10.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}

@Composable
fun CustomPersonCard(person: PersonData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = person.cardBgColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_umy),
                contentDescription = "Logo UMY Left",
                modifier = Modifier.size(56.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = person.name,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = if (person.isCursiveFont) FontFamily.Cursive else FontFamily.Default,
                    textAlign = TextAlign.Center
                )

                person.phone?.let { phoneNum ->
                    Text(
                        text = phoneNum,
                        color = colorResource(id = R.color.phone_text_color),
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                }

                Text(
                    text = person.location,
                    color = colorResource(id = R.color.loc_text_color),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }

            Image(
                painter = painterResource(id = R.drawable.logo_umy),
                contentDescription = "Logo UMY Right",
                modifier = Modifier.size(56.dp)
            )
        }
    }
}