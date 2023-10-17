package mx.itesm.proyectodif.ui_comensal.model

import com.google.gson.annotations.SerializedName

data class ListaComedor(
    @SerializedName("IDComedor")
    var idCom: Int,
    @SerializedName("Ubicacion")
    var ubicacion: String
)
{
    override fun toString(): String {
        return "$idCom- $ubicacion"   // regresa el contenido del campo
    }
}
