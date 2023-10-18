package mx.itesm.proyectodif.ui_comensal.model

import com.google.gson.annotations.SerializedName

data class Aviso(
    @SerializedName("IDComedor")
    var idComedor: String,
    @SerializedName("Aviso")
    var aviso: String,
    @SerializedName("Fecha")
    var fecha: String
)
