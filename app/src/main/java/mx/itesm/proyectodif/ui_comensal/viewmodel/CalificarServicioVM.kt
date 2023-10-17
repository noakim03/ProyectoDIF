package mx.itesm.proyectodif.ui_comensal.viewmodel

import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.proyectodif.ui_comensal.model.Encuesta
import mx.itesm.proyectodif.ui_comensal.model.EncuestaAPI
import mx.itesm.proyectodif.ui_comensal.model.ListaComedor
import mx.itesm.proyectodif.ui_comensal.model.ListaComedorAPI
import mx.itesm.proyectodif.ui_comensal.model.RespuestaServidor
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
class CalificarServicioVM : ViewModel() {

    // Variables liveData
    val listaComedores = MutableLiveData<List<ListaComedor>>()
    val encuestaEnviadaLiveData = MutableLiveData<Boolean>()


    // Retrofit y otras propiedades necesarias
    // Crea el objeto de implementa ListaServiciosAPI
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://54.197.177.119:8080") // Reemplaza con la URL de tu servidor
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val descargaAPI by lazy {
        retrofit.create(ListaComedorAPI::class.java)
    }

    fun enviarEncuesta(encuesta: Encuesta) {

        val apiService = retrofit.create(EncuestaAPI::class.java)

        // Realiza la solicitud POST
        val call = apiService.enviarEncuesta(encuesta)

        call.enqueue(object : Callback<RespuestaServidor> {
            override fun onResponse(call: Call<RespuestaServidor>, response: Response<RespuestaServidor>) {
                if (response.isSuccessful) {
                    // Procesa la respuesta exitosa del servidor aquí
                    //println("API descargados: ${response.body()}")
                    // Procesa la respuesta exitosa del servidor aquí
                    encuestaEnviadaLiveData.postValue(true)



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



    // Interface para la vista
    fun descargaListaComedor() {
        // Crea un objeto que REALIZARÁ la descarga
        val call = descargaAPI.descargarListaComedor()
        // Inicia la descarga de manera ASÍNCRONA
        call.enqueue(object: Callback<List<ListaComedor>> {
            override fun onResponse(
                call: Call<List<ListaComedor>>,
                response: Response<List<ListaComedor>>
            ) {
                if (response.isSuccessful) {
                    println("API descargados: ${response.body()}")
                    // Ejecuta después de 2 segundos
                    listaComedores.value = response.body()
                    //Handler().postDelayed({
                    //    // Avisar a la vista que hay nuevos datos
                    //    listaComedores.value = response.body()
                    //}, 2000)
                } else {
                    println("Datos incorrectos: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<ListaComedor>>, t: Throwable) {
                println("ERROR ${t.localizedMessage}")
            }

        })
    }
}