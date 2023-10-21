package mx.itesm.proyectodif.ui_responsable.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.proyectodif.ui_comensal.model.Encuesta
import mx.itesm.proyectodif.ui_comensal.model.EncuestaAPI
import mx.itesm.proyectodif.ui_comensal.model.ListaComedor
import mx.itesm.proyectodif.ui_comensal.model.ListaComedorAPI
import mx.itesm.proyectodif.ui_comensal.model.RespuestaServidor
import mx.itesm.proyectodif.ui_responsable.model.Incidente
import mx.itesm.proyectodif.ui_responsable.model.IncidenteAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// VIEWMODEL
/**
 * @author Noh Ah Kim Kwon
 *
 */
class RegistroIncidenteVM : ViewModel() {

    // Variables liveData
    val incidenteEnviadaLiveData = MutableLiveData<Boolean>()


    // Retrofit y otras propiedades necesarias
    // Crea el objeto de implementa ListaServiciosAPI
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://54.197.177.119:8080") // Reemplaza con la URL de tu servidor
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val descargaAPI by lazy {
        retrofit.create(IncidenteAPI::class.java)
    }

    fun enviarIncidente(incidente: Incidente) {

        val apiService = retrofit.create(IncidenteAPI::class.java)

        // Realiza la solicitud POST
        val call = apiService.enviarIncidente(incidente)

        call.enqueue(object : Callback<RespuestaServidor> {
            override fun onResponse(call: Call<RespuestaServidor>, response: Response<RespuestaServidor>) {
                if (response.isSuccessful) {
                    // Procesa la respuesta exitosa del servidor aquí
                    println("API descargados: ${response.body()}")
                    // Procesa la respuesta exitosa del servidor aquí
                    incidenteEnviadaLiveData.postValue(true)

                } else {
                    // Maneja errores de solicitud aquí
                    println("Datos incorrectos: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<RespuestaServidor>, t: Throwable) {
                // Maneja errores de conexión u otros errores aquí
                println("ERROR ${t.localizedMessage}")
            }
        })
    }



}