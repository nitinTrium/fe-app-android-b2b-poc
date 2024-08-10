package com.example.fe_app_android_b2c.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

import android.content.ContentResolver
import android.provider.ContactsContract
import com.example.fe_app_android_b2c.models.Search.Contact

class ContactManager @Inject constructor(
    @ApplicationContext val context: Context
){
    fun extractContacts(): List<Contact> {
            val contacts = mutableListOf<Contact>()
            val contentResolver: ContentResolver = context.contentResolver
            val cursor = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                null
            )

            cursor?.use { cursor ->
                if (cursor.moveToFirst()) {
                    do {
                        val contactId =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                        val name =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))

                        val phoneCursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            arrayOf(contactId),
                            null
                        )

                        phoneCursor?.use { phoneCursor ->
                            if (phoneCursor.moveToFirst()) {
                                val phoneNumber =
                                    phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                                contacts.add(Contact(contactId, name, phoneNumber))
                            }
                        }
                    } while (cursor.moveToNext())
                }
            }
            return contacts
        }
}