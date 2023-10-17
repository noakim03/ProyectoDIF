package mx.itesm.proyectodif.ui_responsable.model

import mx.itesm.proyectodif.ui_comensal.model.RespuestaServidor
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface IncidenteAPI {
    @Headers("Content-Type: application/json")
    @POST("/incidente")
    fun enviarIncidente(@Body incidente: Incidente) : Call<RespuestaServidor>
}