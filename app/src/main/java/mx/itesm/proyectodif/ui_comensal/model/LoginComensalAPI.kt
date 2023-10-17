package mx.itesm.proyectodif.ui_comensal.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginComensalAPI {
    @Headers("Content-Type: application/json")
    @POST("/login")
    fun login(@Body loginComensal: LoginComensal) : Call<RespuestaServidor>
    //fun login(@Body loginComensal: RequestBody) : Call<LoginResponse>
}