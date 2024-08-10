package com.example.fe_app_android_b2c

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Groups3
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Groups3
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Payments
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
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fe_app_android_b2c.api.RegisterAPI
import com.example.fe_app_android_b2c.ui.components.NavBar
import com.example.fe_app_android_b2c.ui.components.NavDrawer
import com.example.fe_app_android_b2c.ui.components.TopBar
import com.example.fe_app_android_b2c.ui.theme.Feappandroidb2cTheme
import com.example.fe_app_android_b2c.utils.ContactManager
import com.example.fe_app_android_b2c.utils.TokenManager
import com.example.fe_app_android_b2c.viewModel.ProfileViewModel
import com.example.fe_app_android_b2c.views.Circle.CircleScreen
import com.example.fe_app_android_b2c.views.Circle.PendingRequestsScreen
import com.example.fe_app_android_b2c.views.Home.HomeScreen
import com.example.fe_app_android_b2c.views.Login.MobileNumberScreen
import com.example.fe_app_android_b2c.views.Login.VerifyCodeScreen
import com.example.fe_app_android_b2c.views.RealCard.RealCardScreen
import com.example.fe_app_android_b2c.views.Search.SearchScreen
import com.example.fe_app_android_b2c.views.Vcn.CreateVcnScreen
import com.example.fe_app_android_b2c.views.Vcn.VcnScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var registerAPI: RegisterAPI

    @Inject
    lateinit var tokenManager: TokenManager

    @Inject
    lateinit var contactManager: ContactManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        installSplashScreen()
//
        GlobalScope.launch {
//            val resquestObj = SendRegisterCodeRequest("91", "9166853618")
//            val response = registerAPI.sendRegisterCode(resquestObj)
//            Log.d("LUSID", response.body().toString())

            // Check if the permission has been granted

            Log.d("LUSID", "app_start")
        }
        setContent {
//            window.statusBarColor = getColor(R.color.transparent)
            Feappandroidb2cTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigator(tokenManager, contactManager)
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
)


@Composable
fun Navigator(
    tokenManager: TokenManager,
    contactManager: ContactManager
) {
    val navController = rememberNavController()

//    val loginViewModel: LoginViewModel = hiltViewModel()
    val profileViewModel: ProfileViewModel = hiltViewModel()
//    val realCardViewModel: RealCardViewModel = hiltViewModel()
//    val vcnViewModel: VcnViewModel = hiltViewModel()

    var startDestination: String = "login"
    if(tokenManager.getAccessToken() != null){
        startDestination = "home"
    }

    NavHost(navController = navController, startDestination = startDestination){
//        composable(route = "mobileNumberScreen"){
//            MobileNumberScreen(onClick = {
//                Log.d("LUSID", it);
//                navController.navigate("verifyCodeScreen/${it}")
//            })
//        }
//        composable(route = "verifyCodeScreen/{mobile}", arguments = listOf(
//            navArgument("mobile"){ type = NavType.StringType }
//        )){
//            val number = it.arguments!!.getString("mobile")
//            VerifyCodeScreen(mobile = number!!, onClick = {
//                navController.navigate("basicDetailsScreen")
//            })
//        }
//        composable(route = "basicDetailsScreen"){
//            BasicDetailsScreen()
//        }

        composable(route="login"){
            MobileNumberScreen(onClick = {
                Log.d("LUSID", it);
                navController.navigate("verifyCodeScreen/${it}")
            })
        }
        composable(route = "verifyCodeScreen/{Nmobile}", arguments = listOf(
            navArgument("mobile"){ type = NavType.StringType }
        )){
            val number = it.arguments!!.getString("mobile")
            VerifyCodeScreen(mobile = number!!, onClick = {
                navController.navigate("home")
            })
        }

        composable(route="home"){
            val protectedNavController = rememberNavController();
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()

            val items = listOf<NavItemState>(
                NavItemState("Home", "home", Icons.Filled.Home, Icons.Outlined.Home),
                NavItemState("VCN", "vcn", Icons.Filled.CreditCard, Icons.Outlined.CreditCard),
                NavItemState("Real Card", "real-card", Icons.Filled.Payments, Icons.Outlined.Payments),
                NavItemState("Circle",  "circle", Icons.Filled.Groups3, Icons.Outlined.Groups3)
            )

            ModalNavigationDrawer(
                drawerState = drawerState,
                gesturesEnabled = true,
                drawerContent = {
                    NavDrawer(
                        tokenManager,
                        navController,
                        protectedNavController,
//                        profileViewModel
                    )
                }
            ) {
                Scaffold (
                    topBar = {
                        if (showTopBottomBar(currentRoute(protectedNavController))) {
                            TopBar(scope, drawerState,
                                onSearch = {
                                    protectedNavController.navigate("search")
                                })
                        } },
                    bottomBar = {
                        if (showTopBottomBar(currentRoute(protectedNavController))) {
                            NavBar(items, protectedNavController)
                        }
                    }
                ){innerPadding ->
                    Column (
                        modifier = Modifier
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.spacedBy(16.dp)

                    ){
                        NavHost(navController = protectedNavController,
                                startDestination = "home"){
                            composable("home"){
                                HomeScreen(
                                    createVcnBtn = {
                                        protectedNavController.navigate("vcn/create")
                                    }
                                )
                            }
                            composable("vcn"){
                                VcnScreen(){
                                    protectedNavController.navigate("vcn/create")
                                }
                            }
                            composable("vcn/create"){
                                CreateVcnScreen(onClose = {
                                    protectedNavController.popBackStack()
                                })
                            }
                            composable("real-card"){
                                RealCardScreen()
                            }
                            composable("circle"){
                                CircleScreen()
                            }
                            composable("pending-requests"){
                                PendingRequestsScreen()
                            }
                            composable("search"){
                                SearchScreen(scope,drawerState, contactManager)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
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
        "real-card" -> true;
        "circle" -> true;
        else -> false;
    }

    return show;
}