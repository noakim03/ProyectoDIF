package mx.itesm.proyectodif.ui_responsable.model

import mx.itesm.proyectodif.ui_comensal.model.RespuestaServidor
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginResponsableAPI {
    @Headers("Content-Type: application/json")
    @POST("/loginres")
    fun loginres(@Body loginResponsable: LoginResponsable) : Call<RespuestaServidor>
}