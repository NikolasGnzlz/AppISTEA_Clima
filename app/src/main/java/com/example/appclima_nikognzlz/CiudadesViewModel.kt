import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appclima_nikognzlz.CiudadesIntent
import com.example.appclima_nikognzlz.EstadoCiudades
import kotlinx.coroutines.launch

class CiudadesViewModel : ViewModel() {
    private val _state = mutableStateOf(EstadoCiudades())
    val estado: State<EstadoCiudades> = _state

    fun handleIntent(intent: CiudadesIntent) {
        when (intent) {
            is CiudadesIntent.CargaCiudad -> cargaCiudad()
            is CiudadesIntent.SeleccionaCiudad -> seleccionaCiudad(intent.nombreCiudad)
            is CiudadesIntent.BuscaCiudadPorLocalicacion -> buscarCiudadPorLocalizacion()
        }
    }

    private fun cargaCiudad() {
        viewModelScope.launch {
            _state.value = _state.value.copy(cargando = true)
            // Simula la carga de datos o llámala desde un repositorio
            val ciudades = listOf("Buenos Aires", "Santiago", "Lima")
            _state.value = _state.value.copy(ciudades = ciudades, cargando = false)
        }
    }


    private fun seleccionaCiudad(nombreCiudad: String) {
        _state.value = _state.value.copy(ciudadSeleccionada = nombreCiudad)
    }

    private fun buscarCiudadPorLocalizacion() {
        viewModelScope.launch {
            // Simula la búsqueda de una ciudad por geolocalización
            _state.value = _state.value.copy(ciudadSeleccionada = "Localización Actual")
        }
    }
}
