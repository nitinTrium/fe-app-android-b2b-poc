package com.example.fe_app_android_b2c.views.Search

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fe_app_android_b2c.R
import com.example.fe_app_android_b2c.models.Search.Contact
import com.example.fe_app_android_b2c.utils.ContactManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

//val contactList = listOf(
//    Contact(1, "Test User", "123-456-7890", R.drawable.ic_launcher_background),
////    Contact(2, "Jane Smith", "987-654-3210", R.drawable.ic_launcher_background),
////    Contact(3, "Alex Johnson", "555-123-4567", R.drawable.ic_launcher_background),
////    Contact(4, "Celeb David", "4741-123-4567", R.drawable.ic_launcher_background),
////    Contact(5, "Rickey", "888-854-5147", R.drawable.ic_launcher_background),
////    Contact(6, "Cameron Green", "888-515-8754", R.drawable.ic_launcher_background),
////    Contact(7, "Adam Warner", "548-453-5545", R.drawable.ic_launcher_background)
//)

@Composable
fun SearchScreen(
    scope: CoroutineScope,
    drawerState: DrawerState,
    contactManager: ContactManager
){

    var search by remember {
        mutableStateOf("")
    }

    val focusRequester = FocusRequester()
    var textFieldLoaded by remember { mutableStateOf(false) }

    Log.d("LUSID", contactManager.extractContacts().toString())

    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp)
                .background(
                    colorResource(id = R.color.white),
                    shape = RoundedCornerShape(80.dp)
                )
                .border(
                    width = 1.dp,
                    colorResource(id = R.color.primary_light),
                    shape = RoundedCornerShape(80.dp)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
                Image(
                    painter = painterResource(id = R.drawable.person),
                    contentDescription = "person",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .size(30.dp)
                        .clip(CircleShape)
                        .clickable {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }
                )
                TextField(
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .onGloballyPositioned {
                            if (!textFieldLoaded) {
                                focusRequester.requestFocus() // IMPORTANT
                                textFieldLoaded = true // stop cyclic recompositions
                            }
                        },
                    value = search,
                    onValueChange = {it -> search = it },
                    shape = RoundedCornerShape(12.dp),
                    placeholder = {
                        Text(text = "Search friends and family")
                    },
                    colors = TextFieldDefaults.colors(
                        cursorColor = colorResource(id = R.color.purple_700),
                        unfocusedContainerColor = colorResource(id = R.color.white),
                        focusedContainerColor = colorResource(id = R.color.white),
                        unfocusedTextColor = colorResource(id = R.color.secondary),
                        focusedTextColor = colorResource(id = R.color.secondary_dark),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    maxLines = 1,
                    textStyle = MaterialTheme.typography.labelLarge,
                )
            }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp),
        ) {

            Text(
                modifier = Modifier.padding(top = 15.dp),
                text = "My Contacts",
                style = MaterialTheme.typography.titleMedium
            )
            LazyColumn() {
                items(contactManager.extractContacts()) { contact ->
                    ContactItem(contact = contact)
                }
            }
        }
    }
}

@Composable
fun ContactItem(contact: Contact) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
//            .clickable { onContactClick(contact) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Avatar(
            contact.name
        )
//        Image(
//            painter = painterResource(id = contact.imageRes),
//            contentDescription = null,
//            modifier = Modifier.size(48.dp)
//        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = contact.name, style = MaterialTheme.typography.titleLarge)
            Text(text = contact.phoneNumber, style = MaterialTheme.typography.titleSmall)
        }
    }
}

@Composable
fun Avatar(firstName: String, modifier: Modifier = Modifier, shape: Shape = CircleShape) {
    Surface(
        modifier = modifier.size(50.dp),
        shape = shape,
        color = Color.Blue,
        border = BorderStroke(2.dp, Color.Black)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = firstName.take(1), color = Color.White)
        }
    }
}