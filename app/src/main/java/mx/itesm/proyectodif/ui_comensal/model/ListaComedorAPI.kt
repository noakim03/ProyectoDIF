package mx.itesm.proyectodif.ui_comensal.model

import retrofit2.Call
import retrofit2.http.GET

/**
 * @author Noh Ah Kim Kwon
 *
 * API para descargar la lista de los comedores (id, ubicación)
 */
interface ListaComedorAPI {
    @GET("/getcomedores")                  // Endpoint (servicio)
    fun descargarListaComedor(): Call<List<ListaComedor>>    //<tipo de dato que quiero descargar>

}