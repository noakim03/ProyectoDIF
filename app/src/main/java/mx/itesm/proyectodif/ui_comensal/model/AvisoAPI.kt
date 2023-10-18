package mx.itesm.proyectodif.ui_comensal.model

import retrofit2.Call
import retrofit2.http.GET

interface AvisoAPI {
    @GET("/getaviso")                  // Endpoint (servicio)
    fun descargarListaAviso(): Call<List<Aviso>>    //<tipo de dato que quiero descargar>
}