package mx.itesm.proyectodif.ui_comensal.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface EncuestaAPI {
    @Headers("Content-Type: application/json")
    @POST("/encuesta")
    fun enviarEncuesta(@Body encuesta: Encuesta) : Call<RespuestaServidor>
}