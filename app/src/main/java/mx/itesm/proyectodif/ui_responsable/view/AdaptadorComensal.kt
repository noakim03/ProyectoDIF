package mx.itesm.proyectodif.ui_responsable.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.itesm.proyectodif.R
import mx.itesm.proyectodif.databinding.RenglonComedorBinding
import mx.itesm.proyectodif.databinding.RenglonComensalBinding
import mx.itesm.proyectodif.ui_comensal.model.EstadoComedor
import mx.itesm.proyectodif.ui_responsable.model.Comensal
import java.util.Locale


class AdaptadorComensal(private val contexto: Context, var arrComensal: Array<Comensal>)
    : RecyclerView.Adapter<AdaptadorComensal.RenglonComensal>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RenglonComensal {
        // Binding (local)
        val binding = RenglonComensalBinding.inflate(LayoutInflater.from(contexto))
        return RenglonComensal(binding.root)
    }


    // El número de renglones
    override fun getItemCount(): Int {
        return arrComensal.size
    }

    // Posición del renglón
    override fun onBindViewHolder(holder: RenglonComensal, position: Int) {
        val comensal = arrComensal[position]
        holder.set(comensal)
    }

    class RenglonComensal(var vistaRenglon: View) : RecyclerView.ViewHolder(vistaRenglon) {
        @SuppressLint("SetTextI18n")
        fun set(comensal: Comensal) {
            vistaRenglon.findViewById<TextView>(R.id.tvNumID).text = comensal.idComensal.toString()
            vistaRenglon.findViewById<TextView>(R.id.tvNombre).text = comensal.nombre + " " + comensal.apellido

        }
    }

    private var comensalesFiltrados: List<Comensal> = arrComensal.toList()

    fun filter(text: String?) {
        val input = text.orEmpty().lowercase(Locale.getDefault())
        comensalesFiltrados = if (input.isEmpty()) {
            arrComensal.toList()
        } else {
            arrComensal.filter { comensal ->
                val idComensal = comensal.idComensal.toString().lowercase(Locale.getDefault())
                val nombre = comensal.nombre.lowercase(Locale.getDefault())
                val apellido = comensal.apellido.lowercase(Locale.getDefault())

                idComensal.contains(input) ||
                        nombre.contains(input) ||
                        apellido.contains(input)
            }
        }
        notifyDataSetChanged() // Notifica los cambios en el adaptador


        Log.d("AdaptadorComensal", "Filtro aplicado, tamaño de la lista: ${comensalesFiltrados.size}")
    }

    // Para filtrar en recyclerview
    fun filterList(filterList: ArrayList<Comensal>) {
        // below line is to add our filtered
        // list in our course array list.
        comensalesFiltrados = filterList
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged()
    }

    fun updateComensal(arrComensal: List<Comensal>){
        this.arrComensal = arrComensal.toTypedArray()
        notifyDataSetChanged()
    }
}