package mx.itesm.proyectodif.ui_responsable.model

import mx.itesm.proyectodif.ui_comensal.model.EstadoComedor
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface ComensalAPI {
    @GET("/getcomensales")
    fun descargarListaComensales(): Call<List<Comensal>>
}