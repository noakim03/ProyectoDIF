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
import mx.itesm.proyectodif.ui_comensal.model.EstadoComedor


class AdaptadorComedor(private val contexto: Context, var arrComedores: Array<EstadoComedor>)
    : RecyclerView.Adapter<AdaptadorComedor.RenglonComedor>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RenglonComedor {
        // Binding (local)
        val binding = RenglonComedorBinding.inflate(LayoutInflater.from(contexto))
        return RenglonComedor(binding.root)
    }


    // El número de renglones
    override fun getItemCount(): Int {
        return arrComedores.size
    }

    // Posición del renglón
    override fun onBindViewHolder(holder: RenglonComedor, position: Int) {
        val comedor = arrComedores[position]
        holder.set(comedor)
    }


    class RenglonComedor(var vistaRenglon: View) : RecyclerView.ViewHolder(vistaRenglon) {
        fun set(comedor: EstadoComedor) {
            vistaRenglon.findViewById<TextView>(R.id.tvEstado).text = comedor.estado
            vistaRenglon.findViewById<TextView>(R.id.tvDireccion).text = comedor.ubicacion

        }
    }
    private var arrComedoresOriginal: List<EstadoComedor> = arrComedores.toList()

}