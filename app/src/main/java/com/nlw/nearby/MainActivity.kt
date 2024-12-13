package com.nlw.nearby

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.nlw.nearby.data.model.Market
import com.nlw.nearby.ui.screen.home.HomeScreen
import com.nlw.nearby.ui.screen.home.HomeViewModel
import com.nlw.nearby.ui.screen.market_details.MarketDetailsScreen
import com.nlw.nearby.ui.screen.splash.SplashScreen
import com.nlw.nearby.ui.screen.splash.WelcomeScreen
import com.nlw.nearby.ui.route.Home
import com.nlw.nearby.ui.route.QRCodeScanner
import com.nlw.nearby.ui.route.Splash
import com.nlw.nearby.ui.route.Welcome
import com.nlw.nearby.ui.screen.market_details.MarketDetailsUiEvent
import com.nlw.nearby.ui.screen.market_details.MarketDetailsViewModel
import com.nlw.nearby.ui.screen.qrcode_scanner.QRCodeScannerScreen
import com.nlw.nearby.ui.theme.NearbyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NearbyTheme {
                val navController = rememberNavController()

                val homeViewModel by viewModels<HomeViewModel>()
                val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()

                val marketDetailsViewModel by viewModels<MarketDetailsViewModel>()
                val marketDetailsUiState by marketDetailsViewModel.uiState.collectAsStateWithLifecycle()
                NavHost(
                    navController = navController,
                    startDestination = Splash
                ) {
                    composable<Splash> {
                        SplashScreen(
                            modifier = Modifier.fillMaxSize(),
                            onNavigateToWelcome = {
                                navController.navigate(Welcome)
                            }
                        )
                    }
                    composable<Welcome> {
                        WelcomeScreen(onNavigateToHome = {
                            navController.navigate(Home)
                        })
                    }
                    composable<Home> {
                        HomeScreen(
                            onNavigateToMarketDetails = { selectedMarket ->
                                navController.navigate(selectedMarket)
                            },
                            uiState = homeUiState,
                            onEvent = homeViewModel::OnEvent
                        )
                    }
                    composable<Market> {
                        val selectedMarket = it.toRoute<Market>()

                        MarketDetailsScreen(
                            market = selectedMarket,
                            uiState = marketDetailsUiState,
                            onEvent = marketDetailsViewModel::onEvent,
                            onNavigateToQrCodeScanner = {
                                navController.navigate(QRCodeScanner)
                            },
                            onNavigateBack = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable<QRCodeScanner> {
                        QRCodeScannerScreen(
                            onCompletedScan = { qrCodeContent ->
                                if (qrCodeContent.isNotEmpty()) {
                                    marketDetailsViewModel.onEvent(
                                        MarketDetailsUiEvent.OnFetchCoupon(
                                            qrCodeContent = qrCodeContent
                                        )
                                    )
                                }
                                navController.popBackStack()
                            })
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NearbyTheme {

    }
}