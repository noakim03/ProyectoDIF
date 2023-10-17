package mx.itesm.proyectodif.ui_comensal.model

import retrofit2.Call
import retrofit2.http.GET

interface ListaComedorAPI {
    @GET("/getcomedores")                  // Endpoint (servicio)
    fun descargarListaComedor(): Call<List<ListaComedor>>    //<tipo de dato que quiero descargar>

}