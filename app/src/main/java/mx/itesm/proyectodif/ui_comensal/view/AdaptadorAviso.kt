package mx.itesm.proyectodif.ui_comensal.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.itesm.proyectodif.R
import mx.itesm.proyectodif.databinding.RenglonComedorBinding
import mx.itesm.proyectodif.databinding.RenglonNoticiaBinding
import mx.itesm.proyectodif.ui_comensal.model.Aviso
import mx.itesm.proyectodif.ui_comensal.model.EstadoComedor

/**
 * @author Noh Ah Kim Kwon
 *
 * Adaptador del RecyclerView
 */
class AdaptadorAviso(private val contexto: Context, var arrAviso: Array<Aviso>)
    : RecyclerView.Adapter<AdaptadorAviso.RenglonAviso>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RenglonAviso {
        // Binding (local)
        val binding = RenglonNoticiaBinding.inflate(LayoutInflater.from(contexto))
        return RenglonAviso(binding.root)
    }


    // El número de renglones
    override fun getItemCount(): Int {
        return arrAviso.size
    }

    // Posición del renglón
    override fun onBindViewHolder(holder: RenglonAviso, position: Int) {
        val aviso = arrAviso[position]
        holder.set(aviso)
    }


    class RenglonAviso(var vistaRenglon: View) : RecyclerView.ViewHolder(vistaRenglon) {
        fun set(aviso: Aviso) {
            vistaRenglon.findViewById<TextView>(R.id.tvAviso).text = aviso.aviso
            vistaRenglon.findViewById<TextView>(R.id.tvIdCom).text = "Comedor #"+aviso.idComedor
            vistaRenglon.findViewById<TextView>(R.id.tvFecha).text = aviso.fecha
        }
    }


}