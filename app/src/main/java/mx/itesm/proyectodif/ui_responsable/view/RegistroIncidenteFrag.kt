package mx.itesm.proyectodif.ui_responsable.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import mx.itesm.proyectodif.databinding.FragmentRegistroIncidenciasBinding
import mx.itesm.proyectodif.ui_comensal.viewmodel.CalificarServicioVM
import mx.itesm.proyectodif.ui_responsable.model.Incidente
import mx.itesm.proyectodif.ui_responsable.viewmodel.RegistroIncidenteVM

// VISTA
/**
 * @author Noh Ah Kim Kwon
 *
 * Controla la vista del fragmento registroIncidencias
 */
class RegistroIncidenteFrag : Fragment() {

    /*companion object {
        fun newInstance() = RegistroIncidenciasFrag()
    }*/
    private lateinit var binding: FragmentRegistroIncidenciasBinding

    private lateinit var viewModel: RegistroIncidenteVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(RegistroIncidenteVM::class.java)

        binding = FragmentRegistroIncidenciasBinding.inflate(layoutInflater)
        return binding.root
        //return inflater.inflate(R.layout.fragment_registro_incidencias, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegistroIncidenteVM::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrarEventos()
    }

    private fun registrarEventos() {
        binding.btnEnviar.setOnClickListener {
            val usuario = requireActivity().intent.getStringExtra("ID_RESP")
            val idRes = usuario!!.toInt()
            //tvIDResp.text = "Mi ID: $usuario"  // Cambiar texto del tvID
            println("$idRes reportó un incidente")
            //idRes, incidente
            val incidente = binding.etIncidente.text.toString()

            val reportarIncidente = Incidente(idRes, incidente)

            // viewmodel
            viewModel.enviarIncidente(reportarIncidente)


        }
        viewModel.incidenteEnviadaLiveData.observe(viewLifecycleOwner) { incidenteEnviada ->
            if (incidenteEnviada) {
                // Pasar a la pantalla "Registrado"
                val accion = RegistroIncidenteFragDirections.actionRegistroIncidenciasFrag2ToRegistradoFrag()
                findNavController().navigate(accion)
            }
        }
    }

}