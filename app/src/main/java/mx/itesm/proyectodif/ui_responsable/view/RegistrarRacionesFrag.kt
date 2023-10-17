package mx.itesm.proyectodif.ui_responsable.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import mx.itesm.proyectodif.R
import mx.itesm.proyectodif.databinding.FragmentRegistrarRacionesBinding
import mx.itesm.proyectodif.ui_comensal.model.ListaComedor
import mx.itesm.proyectodif.ui_responsable.model.Asistencia
import mx.itesm.proyectodif.ui_responsable.viewmodel.RegistrarRacionesVM
import mx.itesm.proyectodif.ui_responsable.viewmodel.RegistroRacionesConQRVM

// VISTA
/**
 * @author Noh Ah Kim Kwon
 *
 * Controla la vista del fragmento registrarRaciones
 */
class RegistrarRacionesFrag : Fragment() {

    /*companion object {
        fun newInstance() = RegistrarRacionesFrag()
    }*/

    private lateinit var binding: FragmentRegistrarRacionesBinding

    private lateinit var viewModel: RegistrarRacionesVM

    private lateinit var radioGroup: RadioGroup



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrarRacionesBinding.inflate(layoutInflater)
        
        // viewModel
        viewModel = ViewModelProvider(this).get(RegistrarRacionesVM::class.java)
        
        return binding.root
        //return inflater.inflate(R.layout.fragment_registrar_raciones, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegistrarRacionesVM::class.java)
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
        binding.btnRegistrarRaciones.setOnClickListener {
            val idComensal = binding.tvIdDesignado.text.toString()
            val selectedComedor = binding.spComedor.selectedItem as ListaComedor
            val idCom = selectedComedor.idCom
            val pago = binding.etPago.text.toString()

            // Verificar si se ha seleccionado un RadioButton
            val lugarRadioGroup = binding.rgPedido
            val selectedRadioButtonId = lugarRadioGroup.checkedRadioButtonId
            val lugar: String = when (selectedRadioButtonId) {
                R.id.btnComer -> "Para Comer"
                R.id.btnLlevar -> "Para Llevar"
                else -> ""
            }

            // Verificar si todos los campos están llenos
            if (idCom != 0 && pago.isNotEmpty() && lugar.isNotEmpty()) {
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
        builder.setTitle("Raciones para no registrados")
        builder.setMessage("Registrado con éxito")

        builder.setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }

}