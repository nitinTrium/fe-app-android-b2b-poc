package com.example.fe_app_android_b2b_poc

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fe_app_android_b2b_poc.ui.components.NavBar
import com.example.fe_app_android_b2b_poc.ui.components.NavDrawer
import com.example.fe_app_android_b2b_poc.ui.components.TopBar
import com.example.fe_app_android_b2b_poc.ui.theme.Feappandroidb2bpocTheme
import com.example.fe_app_android_b2b_poc.utils.TokenManager
import com.example.fe_app_android_b2b_poc.views.expense.CreateExpenseScreen
import com.example.fe_app_android_b2b_poc.views.expense.ExpenseDetailsScreen
import com.example.fe_app_android_b2b_poc.views.expense.ExpenseScreen
import com.example.fe_app_android_b2b_poc.views.home.HomeScreen
import com.example.fe_app_android_b2b_poc.views.login.LoginScreen
import com.example.fe_app_android_b2b_poc.views.notification.NotificationScreen
import com.example.fe_app_android_b2b_poc.views.profile.ProfileScreen
import com.example.fe_app_android_b2b_poc.views.scanToPay.ScanToPayScreen
import com.example.fe_app_android_b2b_poc.views.vcn.CreateVcnScreen
import com.example.fe_app_android_b2b_poc.views.vcn.VcnDetailsScreen
import com.example.fe_app_android_b2b_poc.views.vcn.VcnScreen

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        if(!hasRequiredPermission()){
            ActivityCompat.requestPermissions(
                this, CAMERAX_PERMISSIONS, 0
            )
        }

        setContent {
            Feappandroidb2bpocTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigator(
                        tokenManager,
                        applicationContext
                    )
                }
            }
        }
    }

    private fun hasRequiredPermission () : Boolean {
        return CAMERAX_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                applicationContext,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }
    
    companion object {
        private val CAMERAX_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        )
    }
}



@Composable
fun Navigator(
    tokenManager: TokenManager,
    applicationContext: Context,
) {

    val navController = rememberNavController()

    var startDestination: String = "auth"
    if(tokenManager.getAccessToken() != null){
        startDestination = "private"
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable("auth"){
            LoginScreen(){
                navController.navigate("private")
            }
        }
        composable("private"){
            ProtectedNavigator(
                tokenManager,
                navController,
                applicationContext
            )
        }
    }
}

@Composable
fun ProtectedNavigator(
    tokenManager: TokenManager,
    publicNavController: NavHostController,
    applicationContext: Context
){
    val protectedNavController = rememberNavController()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val items = listOf<NavItemState>(
        NavItemState("Home", "home", Icons.Filled.Home, Icons.Outlined.Home, 0),
        NavItemState("Cards", "vcn", Icons.Filled.CreditCard, Icons.Outlined.CreditCard, 1),
        NavItemState("", "scan", Icons.Filled.QrCodeScanner, Icons.Outlined.QrCodeScanner, 2),
        NavItemState("Expense", "expense", Icons.Filled.ShoppingCart, Icons.Outlined.ShoppingCart, 3),
        NavItemState("Account", "profile", Icons.Filled.Person, Icons.Outlined.Person, 4),
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            NavDrawer(
                tokenManager,
                publicNavController,
                protectedNavController
            )
        }
    ){
        Scaffold (
            topBar = {
                if (showTopBottomBar(CurrentRoute(protectedNavController))) {
                    TopBar(
                        scope,
                        drawerState,
                        items,
                        protectedNavController)
                } },
            bottomBar = {
                if (showTopBottomBar(CurrentRoute(protectedNavController))) {
                    NavBar(items, protectedNavController)
                }
            }
        ){innerPadding ->
            Column (
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){
                NavHost(
                    navController = protectedNavController,
                    startDestination = "home"
                ){
                    composable("home"){
                        HomeScreen(
                            createVcnBtn = {
                                protectedNavController.navigate("create-vcn")
                            },
                            createExpenseBtn = {
                                protectedNavController.navigate("create-expense")
                            }
                        )
                    }
                    composable("vcn"){
                        VcnScreen(){
                            protectedNavController.navigate("vcn/${it}")
                        }
                    }
                    composable("vcn/{id}"){
                        VcnDetailsScreen(it.arguments?.getString("id").toString() ?: ""){
                            protectedNavController.popBackStack()
                        }
                    }
                    composable("create-vcn"){
                        CreateVcnScreen(){
                            protectedNavController.navigate("vcn")
                        }
                    }
                    composable("scan"){
                        ScanToPayScreen(
                            applicationContext = applicationContext
                        )
                    }
                    composable("expense"){
                        ExpenseScreen(){
                            protectedNavController.navigate("expense/${it}")
                        }
                    }
                    composable("expense/{id}"){
                        ExpenseDetailsScreen(it.arguments?.getString("id").toString() ?: ""){
                            protectedNavController.popBackStack()
                        }
                    }
                    composable("create-expense"){
                        CreateExpenseScreen(){
                            protectedNavController.navigate("expense")
                        }
                    }
                    composable("profile"){
                        ProfileScreen()
                    }
                    composable("notification"){
                        NotificationScreen(){
                            protectedNavController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}

data class NavItemState(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val position: Int
)

@Composable
fun CurrentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

fun showTopBottomBar(
    route: String?
): Boolean{
    var show = true;

    show = when (route) {
        "home" -> true;
        "vcn" -> true;
        "scan" -> false;
        "expense" -> true;
        "profile" -> true;
        else -> false;
    }

    return show;
}