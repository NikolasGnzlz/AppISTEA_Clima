package com.example.appclima_nikognzlz

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.appclima_nikognzlz.presentacion.ciudades.CiudadesPage
import com.example.appclima_nikognzlz.presentacion.clima.ClimaPage
import com.example.appclima_nikognzlz.router.Ruta
import com.example.appclima_nikognzlz.ui.theme.AppClima_NikoGnzlzTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage() {
    val navHostController = rememberNavController()
    
    val backgroundColor = Color(0xFFD7BDE2)
    val appBarColor = Color(0xFF8A2BE2)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = backgroundColor,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "AppClima_Niko",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = appBarColor,
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        NavHost(
            navController = navHostController,
            startDestination = Ruta.Ciudades.id,
            modifier = Modifier.padding(padding)
        ) {
            composable(
                route = Ruta.Ciudades.id
            ) {
                CiudadesPage(navHostController)
            }
            composable(
                route = "clima?lat={lat}&lon={lon}&nombre={nombre}",
                arguments = listOf(
                    navArgument("lat") { type = NavType.FloatType },
                    navArgument("lon") { type = NavType.FloatType },
                    navArgument("nombre") { type = NavType.StringType }
                )
            ) {
                val lat = it.arguments?.getFloat("lat") ?: 0.0f
                val lon = it.arguments?.getFloat("lon") ?: 0.0f
                val nombre = it.arguments?.getString("nombre") ?: ""
                ClimaPage(navHostController, lat = lat, lon = lon, nombre = nombre)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun MainPagePreview() {
    AppClima_NikoGnzlzTheme {
        MainPage()
    }
}
