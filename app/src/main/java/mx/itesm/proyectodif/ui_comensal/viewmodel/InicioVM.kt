package mx.itesm.proyectodif.ui_comensal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.proyectodif.ui_comensal.model.Aviso
import mx.itesm.proyectodif.ui_comensal.model.AvisoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InicioVM : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    // Variables liveData
    val listaAviso = MutableLiveData<List<Aviso>>()

    // El objeto retrofit, que se conecta a la red
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://54.197.177.119:8080")
            .addConverterFactory(GsonConverterFactory.create())     // JSON
            .build()
    } // pospone la creación

    // Crea el objeto de implementa ListaServiciosAPI
    private val descargaAPI by lazy {
        retrofit.create(AvisoAPI::class.java)
    }

    // Interfase para la vista
    fun descargarListaAviso() {
        // Crea un objeto que REALIZARÁ la descarga
        val call = descargaAPI.descargarListaAviso()
        // Inicia la descarga de manera ASÍNCRONA
        call.enqueue(object: Callback<List<Aviso>> {
            override fun onResponse(
                call: Call<List<Aviso>>,
                response: Response<List<Aviso>>
            ) {
                if (response.isSuccessful) {
                    println("API descargados: ${response.body()}")

                    // Avisar a la vista que hay nuevos datos
                    listaAviso.value = response.body()

                } else {
                    println("Datos incorrectos: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<Aviso>>, t: Throwable) {
                println("ERROR ${t.localizedMessage}")
            }

        })
    }
}