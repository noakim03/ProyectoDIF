package mx.itesm.proyectodif.ui_responsable.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.proyectodif.ui_comensal.model.EstadoComedor
import mx.itesm.proyectodif.ui_comensal.model.EstadoComedorAPI
import mx.itesm.proyectodif.ui_comensal.model.ListaComedor
import mx.itesm.proyectodif.ui_comensal.model.ListaComedorAPI
import mx.itesm.proyectodif.ui_comensal.model.RespuestaServidor
import mx.itesm.proyectodif.ui_responsable.model.Asistencia
import mx.itesm.proyectodif.ui_responsable.model.AsistenciaAPI
import mx.itesm.proyectodif.ui_responsable.model.Comensal
import mx.itesm.proyectodif.ui_responsable.model.ComensalAPI
import mx.itesm.proyectodif.ui_responsable.model.RacionDonAPI
import mx.itesm.proyectodif.ui_responsable.model.RespuestaServidorComensal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Noh Ah Kim Kwon
 *
 */
class PedidoParaVM : ViewModel() {
    // TODO: Implement the ViewModel

    // Variables liveData
    val listaComensales = MutableLiveData<List<Comensal>>()
    val listaComedores = MutableLiveData<List<ListaComedor>>()
    val enviadaLiveData = MutableLiveData<Boolean>()

    //val estadoComedor = MutableLiveData<List<EstadoComedor>>()

    // El objeto retrofit, que se conecta a la red
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://54.197.177.119:8080")
            .addConverterFactory(GsonConverterFactory.create())     // JSON
            .build()
    } // pospone la creación

    // Crea el objeto de implementa ListaServiciosAPI
    private val descargaAPI by lazy {
        retrofit.create(ComensalAPI::class.java)
    }

    fun registroRacion(asistencia: Asistencia) {

        val apiService = retrofit.create(AsistenciaAPI::class.java)

        // Realiza la solicitud POST
        val call = apiService.registrarAsistencia(asistencia)

        call.enqueue(object : Callback<RespuestaServidor> {
            override fun onResponse(call: Call<RespuestaServidor>, response: Response<RespuestaServidor>) {
                if (response.isSuccessful) {
                    // Procesa la respuesta exitosa del servidor aquí
                    println("API descargados: ${response.body()}")
                    // Procesa la respuesta exitosa del servidor aquí
                    enviadaLiveData.postValue(true)

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


    // Interfase para la vista
    fun descargarDatosComensales() {
        // Crea un objeto que REALIZARÁ la descarga
        val call = descargaAPI.descargarListaComensales()
        // Inicia la descarga de manera ASÍNCRONA
        call.enqueue(object: Callback<List<Comensal>> {
            override fun onResponse(
                call: Call<List<Comensal>>,
                response: Response<List<Comensal>>
            ) {
                if (response.isSuccessful) {
                    println("API descargados: ${response.body()}")

                    // Avisar a la vista que hay nuevos datos
                    listaComensales.value = response.body()

                } else {
                    println("Datos incorrectos: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<Comensal>>, t: Throwable) {
                println("ERROR ${t.localizedMessage}")
            }

        })
    }

    // Crea el objeto de implementa ListaServiciosAPI
    private val descarga2API by lazy {
        retrofit.create(ListaComedorAPI::class.java)
    }

    // Interface para la vista
    fun descargaListaComedor() {
        // Crea un objeto que REALIZARÁ la descarga
        val call = descarga2API.descargarListaComedor()
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