package mx.itesm.proyectodif.ui_responsable.model

import com.google.gson.annotations.SerializedName

/**
 * @author Noh Ah Kim Kwon
 *
 * ComensalAPI
 */

data class Comensal(
    @SerializedName("IDComensal")
    var idComensal: Int,
    @SerializedName("Nombre")
    var nombre: String,
    @SerializedName("Apellido")
    var apellido: String
)