package mx.itesm.proyectodif.ui_responsable.model

import mx.itesm.proyectodif.ui_comensal.model.RespuestaServidor
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * @author Noh Ah Kim Kwon
 *
 * API para registrar una ración donada
 */
interface RacionDonAPI {
    @Headers("Content-Type: application/json")
    @POST("/raciondon")
    fun enviarRacionDon(@Body racionDon: RacionDon) : Call<RespuestaServidor>
}