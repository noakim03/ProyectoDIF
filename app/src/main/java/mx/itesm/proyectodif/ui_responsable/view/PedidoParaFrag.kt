package mx.itesm.proyectodif.ui_responsable.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import mx.itesm.proyectodif.R
import mx.itesm.proyectodif.databinding.FragmentPedidoParaBinding
import mx.itesm.proyectodif.ui_comensal.model.EstadoComedor
import mx.itesm.proyectodif.ui_comensal.model.ListaComedor
import mx.itesm.proyectodif.ui_responsable.model.Asistencia
import mx.itesm.proyectodif.ui_responsable.model.Comensal
import mx.itesm.proyectodif.ui_responsable.viewmodel.PedidoParaVM
import java.util.Locale


// VISTA
/**
 * @author Noh Ah Kim Kwon
 *
 * Controla la vista del fragmento PedidoPara
 */
class PedidoParaFrag : Fragment() {

    /*companion object {
        fun newInstance() = PedidoParaFrag()
    }*/

    private lateinit var binding: FragmentPedidoParaBinding
    private lateinit var viewModel: PedidoParaVM
    private var adaptadorComensal: AdaptadorComensal? = null

    private var listaComensal = ArrayList<Comensal>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_pedido_para, container, false)

        viewModel = ViewModelProvider(this).get(PedidoParaVM::class.java)
        /*binding.etFiltro.addTextChangedListener {userFilter ->
            val listaFiltrado =
                listaComensal.filter { datoComensal -> datoComensal.nombre.lowercase().contains(userFilter.toString().lowercase()) }
            adaptadorComensal?.updateComensal(listaFiltrado)
        }*/

        binding = FragmentPedidoParaBinding.inflate(layoutInflater)
        val root: View = binding.root


        return root
    }


    override fun onStart() {
        super.onStart()
        viewModel.descargarDatosComensales()
        viewModel.descargaListaComedor()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PedidoParaVM::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar el SearchView
        val searchView = view.findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String ): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                //filter(newText)
                return true
            }
        })
        //binding.rvBuscar.adapter = adaptadorComensal

        registrarEventos()
        configurarAdaptador()
        registrarObservables()

    }

    // creating a variable for array list and context.
    private val comensalArrayList: ArrayList<Comensal>? = null
/*
    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredlist = ArrayList<Comensal>()

        // running a for loop to compare elements.
        if (comensalArrayList != null) {
            for (item in comensalArrayList) {
                // checking if the entered string matched with any item of our recycler view.
                if (item.nombre.lowercase(Locale.ROOT).contains(text.lowercase(Locale.getDefault()))) {
                    // if the item is matched we are
                    // adding it to our filtered list.
                    filteredlist.add(item)
                }
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(context, "Dato no encontrado", Toast.LENGTH_SHORT).show()
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adaptadorComensal?.filterList(filteredlist)
        }
    }*/


    private fun registrarObservables() {
        viewModel.listaComedores.observe(viewLifecycleOwner){ lista ->
            val arrServicios =  lista.toTypedArray()
            binding.spComedor.adapter = ArrayAdapter(requireContext(),
                R.layout.spinner_items_customed, arrServicios)
            // Apagar el progress bar
            // binding.pbDescarga.visibility = View.INVISIBLE
        }
    }


    private fun registrarEventos() {
        binding.btnRegistrarPara.setOnClickListener {
            //val accion = PedidoParaFragDirections.actionPedidoParaFragToRegistradoFrag()
            //findNavController().navigate(accion)
            val idComensal = binding.etNumID.text.toString()
            val selectedComedor = binding.spComedor.selectedItem as ListaComedor
            val idCom = selectedComedor.idCom
            val pago = binding.etPago.text.toString()
            val lugar = "Para Llevar"

            // Verificar si todos los campos están llenos
            if (idComensal != "" && pago.isNotEmpty()) {
                // Todos los campos están llenos, puedes enviar la información
                val asistencia = Asistencia(idComensal.toInt(), idCom, pago.toInt(), lugar)
                viewModel.registroRacion(asistencia)
            } else {
                // Alguno de los campos está vacío
                Toast.makeText(context, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }


            //val accion = RegistrarRacionesFragDirections.actionRegistrarRacionesFrag2ToRegistradoFrag()
            //findNavController().navigate(accion)
        }
        viewModel.enviadaLiveData.observe(viewLifecycleOwner) { encuestaEnviada ->
            if (encuestaEnviada) {
                Toast.makeText(context, "Enviado", Toast.LENGTH_SHORT).show()
                mostrarAlertaExito()
            }
        }
    }

    private fun mostrarAlertaExito() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Llevar raciones para otros")
        builder.setMessage("Registrado con éxito")

        builder.setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }


    private fun configurarAdaptador() {
        // configuración de layoutmanager
        //val layout = LinearLayoutManager(this)
        val layout = GridLayoutManager(context, 1)
        //layout.orientation = LinearLayoutManager.HORIZONTAL
        binding.rvBuscar.layoutManager = layout

        viewModel.listaComensales.observe(viewLifecycleOwner){ lista ->
            val arrComensal = lista.toTypedArray()
            adaptadorComensal = AdaptadorComensal(requireContext(), arrComensal)
            binding.rvBuscar.adapter = adaptadorComensal
        }
    }

}