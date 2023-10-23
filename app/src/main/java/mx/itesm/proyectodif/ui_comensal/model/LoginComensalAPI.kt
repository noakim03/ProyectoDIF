package mx.itesm.proyectodif.ui_comensal.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * @author Noh Ah Kim Kwon
 *
 * API para el login de un comensal
 */
interface LoginComensalAPI {
    @Headers("Content-Type: application/json")
    @POST("/login")
    fun login(@Body loginComensal: LoginComensal) : Call<RespuestaServidor>

}