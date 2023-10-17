package mx.itesm.proyectodif.ui_comensal.model

import retrofit2.Call
import retrofit2.http.GET

interface EstadoComedorAPI {
    @GET("/getcomedores")                  // Endpoint (servicio)
    fun descargarListaEstadoComedor(): Call<List<EstadoComedor>>    //<tipo de dato que quiero descargar>

}