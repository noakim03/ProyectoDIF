package mx.itesm.proyectodif.ui_responsable.model

import com.google.gson.annotations.SerializedName

data class Comensal(
    @SerializedName("IDComensal")
    var idComensal: Int,
    @SerializedName("Nombre")
    var nombre: String,
    @SerializedName("Apellido")
    var apellido: String
)