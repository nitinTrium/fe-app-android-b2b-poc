package com.example.fe_app_android_b2c.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.fe_app_android_b2c.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val urbanistFont = GoogleFont( "Urbanist" )

val urbanistFontFamily = FontFamily(
   Font(
       googleFont = urbanistFont,
       fontProvider = provider
   )
)

// Set of Material typography styles to start with
val Typography = Typography()