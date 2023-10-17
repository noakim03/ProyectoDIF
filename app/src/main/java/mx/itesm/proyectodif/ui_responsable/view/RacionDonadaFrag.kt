package mx.itesm.proyectodif.ui_responsable.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import mx.itesm.proyectodif.R
import mx.itesm.proyectodif.databinding.FragmentRacionDonadaBinding
import mx.itesm.proyectodif.ui_comensal.model.ListaComedor
import mx.itesm.proyectodif.ui_responsable.model.RacionDon
import mx.itesm.proyectodif.ui_responsable.viewmodel.RacionDonadaVM

// VISTA
/**
 * @author Noh Ah Kim Kwon
 *
 * Controla la vista del fragmento racionDonada
 */
class RacionDonadaFrag : Fragment() {

    /*companion object {
        fun newInstance() = RacionDonadaFrag()
    }*/

    private lateinit var binding: FragmentRacionDonadaBinding

    private lateinit var viewModel: RacionDonadaVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(RacionDonadaVM::class.java)

        binding = FragmentRacionDonadaBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RacionDonadaVM::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()
        viewModel.descargaListaComedor()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrarEventos()
        registrarObservables()
    }
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
        binding.btnDonar.setOnClickListener {
            val selectedComedor = binding.spComedor.selectedItem as ListaComedor
            val idCom = selectedComedor.idCom
            val idComensal = binding.etIDCom.text.toString().toInt()

            val donar = RacionDon(idCom, idComensal)
            viewModel.enviarRacionDonada(donar)

            //val accion = RacionDonadaFragDirections.actionRacionDonadaFrag2ToRegistradoFrag()
            //findNavController().navigate(accion)
        }
        viewModel.enviadaLiveData.observe(viewLifecycleOwner) { racionDonada ->
            if (racionDonada) {
                // Pasar a la pantalla "Registrado"
                //Toast.makeText(context, "Registrado", Toast.LENGTH_SHORT).show()
                //val accion = RacionDonadaFragDirections.actionRacionDonadaFrag2ToRegistradoFrag2()
                //findNavController().navigate(accion)
                mostrarAlertaExito()
            }
        }
    }

    private fun mostrarAlertaExito() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Donar Ración")
        builder.setMessage("Registrado con éxito")

        builder.setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }

}
