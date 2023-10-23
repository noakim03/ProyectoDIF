package mx.itesm.proyectodif.ui_responsable.model

/**
 * @author Noh Ah Kim Kwon
 *
 * AsistenciaAPI
 */
data class Asistencia(
    var idComensal: Int,
    var idCom: Int,
    var pago: Int,
    var lugar: String //para comer o para llevar
)
