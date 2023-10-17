package mx.itesm.proyectodif.ui_comensal.model

import com.google.gson.annotations.SerializedName

//ListaComedorAPI
data class EstadoComedor(
    @SerializedName("Estado")
    var estado: String,
    @SerializedName("Ubicacion")
    var ubicacion: String
)
