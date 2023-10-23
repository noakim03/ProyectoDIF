package mx.itesm.proyectodif.ui_responsable.model

import mx.itesm.proyectodif.ui_comensal.model.RespuestaServidor
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * @author Noh Ah Kim Kwon
 *
 * API para registrar la asistencia de un comensal
 */
interface AsistenciaAPI {
    @Headers("Content-Type: application/json")
    @POST("/asistencia")
    fun registrarAsistencia(@Body asistencia: Asistencia) : Call<RespuestaServidor>
}