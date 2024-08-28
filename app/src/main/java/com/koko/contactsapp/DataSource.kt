package com.koko.contactsapp

import com.koko.contactsapp.ui.theme.Contact

class DataSource {
   fun getContactList():List<Contact>{
       val contacts = mutableListOf<Contact>()
       contacts.add(Contact("auntie",R.drawable.auntie,"+20101234578"))
       contacts.add(Contact("brother",R.drawable.brother,"+201123456789"))
       contacts.add(Contact("daughter",R.drawable.daughter,"+201234567890"))
       contacts.add(Contact("friend_1",R.drawable.friend_1,"+201345678901"))
       contacts.add(Contact("friend_2",R.drawable.friend_2,"+201456789012"))
       contacts.add(Contact("grandfather",R.drawable.grandfather,"+201567890123"))
       contacts.add(Contact("granny",R.drawable.granny,"+201678901234"))
       contacts.add(Contact("neigbour",R.drawable.neigbour,"+201789012345"))
       contacts.add(Contact("sister",R.drawable.sister,"+201890123456"))
       contacts.add(Contact("son",R.drawable.son,"+201901234567"))
       contacts.add(Contact("uncle",R.drawable.uncle,"+20101234579"))
       return contacts
   }
}