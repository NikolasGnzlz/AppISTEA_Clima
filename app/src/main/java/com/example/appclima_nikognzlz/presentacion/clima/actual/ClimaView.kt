package com.example.appclima_nikognzlz.presentacion.clima.actual

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.example.appclima_nikognzlz.R
import com.example.appclima_nikognzlz.ui.theme.AppClima_NikoGnzlzTheme



@Composable
fun ClimaView(
    modifier: Modifier = Modifier,
    state : ClimaEstado,
    onAction: (ClimaIntencion)->Unit
) {
    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        onAction(ClimaIntencion.actualizarClima)
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(state) {
            is ClimaEstado.Error -> ErrorView(mensaje = state.mensaje)
            is ClimaEstado.Exitoso -> {
                val imageRes = when (state.principal) {
                    "Clear" -> R.drawable.sunny
                    "Clouds" -> R.drawable.cloudy
                    "Rain" -> R.drawable.rainy
                    "Storm" -> R.drawable.storm
                    else -> R.drawable.noresults
                }
            ClimaViewExitoso(
                ciudad = state.ciudad,
                temperatura = state.temperatura,
                principal = state.principal,
                descripcion = state.descripcion,
                st = state.st,
                imageRes = imageRes
            )
        }
            ClimaEstado.Vacio -> LoadingView()
            ClimaEstado.Cargando -> EmptyView()
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
fun ClimaViewExitoso(
    ciudad: String,
    temperatura: Double,
    principal: String,
    descripcion: String,
    st: Double,
    imageRes: Int
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Clima: $principal",
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = ciudad, style = MaterialTheme.typography.titleMedium)
        Text(text = "${temperatura}°", style = MaterialTheme.typography.titleLarge)
        Text(text = principal, style = MaterialTheme.typography.bodyMedium)
        Text(text = descripcion, style = MaterialTheme.typography.bodyMedium)
        Text(text = "Sensación Térmica: ${st}°", style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun EmptyView(){
    Text(text = "No hay nada que mostrar")
}

@Composable
fun LoadingView(){
    Image(
        painter = painterResource(id = R.drawable.loading),
        contentDescription = "Cargando",
        modifier = Modifier.size(94.dp),
        colorFilter = ColorFilter.tint(color = androidx.compose.ui.graphics.Color.Gray)
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = "")
}

@Composable
fun ErrorView(mensaje: String){
    Text(text = mensaje)
}


@Preview(showBackground = true)
@Composable
fun ClimaPreviewVacio() {
    AppClima_NikoGnzlzTheme {
        ClimaView(state = ClimaEstado.Vacio, onAction = {})
    }
}

@Preview(showBackground = true)
@Composable
fun ClimaPreviewError() {
    AppClima_NikoGnzlzTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(id = R.drawable.noresults),
                contentDescription = "Sin resultados",
                modifier = Modifier.size(64.dp),
                colorFilter = ColorFilter.tint(color = androidx.compose.ui.graphics.Color.Gray)
            )
            Spacer(modifier = Modifier.height(8.dp))
            ClimaView(state = ClimaEstado.Error("ERROR!"), onAction = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClimaPreviewExitoso() {
    AppClima_NikoGnzlzTheme {
        ClimaView(
            state = ClimaEstado.Exitoso(
                ciudad = "Buenos Aires",
                temperatura = 25.0,
                principal = "Clouds",
                descripcion = "Nubes dispersas",
                st = 27.0
            ),
            onAction = {}
        )
    }
}
