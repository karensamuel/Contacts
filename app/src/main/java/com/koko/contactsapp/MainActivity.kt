package com.koko.contactsapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.koko.contactsapp.ui.theme.Contact
import com.koko.contactsapp.ui.theme.ContactsAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactsAppTheme {
                ContactPage()
            }
        }
    }
}

@Composable
fun ContactPage(modifier: Modifier = Modifier) {

    // A surface container using the 'background' color from the theme
    Scaffold(
        topBar = { TopBar() },
    ) { innerPadding ->
        ContactList(
            contacts = DataSource().getContactList(),
            modifier = Modifier
                .padding(innerPadding)
                .padding(0.dp)
        )
    }

}

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    var context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = "Contancts App",
            textAlign = TextAlign.Start
        )
        Icon(
            painter = painterResource(id = R.drawable.home),
            contentDescription = "home",
            modifier = Modifier
                .padding(end = 8.dp)
                .clickable {
                    val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                        data = Uri.parse("tell:05555555")
                    }
                    context.startActivity(dialIntent)
                })
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContactPagePreview() {
    //val contact= Contact("karen",R.drawable.auntie,"00")
    ContactPage()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListItem(contact: Contact, modifier: Modifier = Modifier) {
    val clipboardManager = LocalClipboardManager.current

    var showCopy by remember { mutableStateOf(false) }
    var context = LocalContext.current
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RectangleShape, onClick = {
            val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${contact.number}")
            }
            context.startActivity(dialIntent)

        }

    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = contact.pic), contentDescription = "photo")
            Text(text = contact.name)

            Text(text = contact.number,
                modifier = modifier.clickable {
                    clipboardManager
                        .setText(annotatedString = AnnotatedString(contact.number))
                    Toast.makeText(context, "number copied", Toast.LENGTH_SHORT).show()

                })

        }
    }

}

@Composable
fun ContactList(contacts: List<Contact>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(0.dp),
        horizontalArrangement = Arrangement.spacedBy(0.dp), // No spacing horizontally
        verticalArrangement = Arrangement.spacedBy(0.dp),
        modifier = modifier
    ) {
        items(contacts) { contact ->
            ContactListItem(contact = contact)
        }
    }
}

