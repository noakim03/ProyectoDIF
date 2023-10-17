package mx.itesm.proyectodif.ui_comensal.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import mx.itesm.proyectodif.MainActivity
import mx.itesm.proyectodif.ui_comensal.viewmodel.CalificarServicioVM
import mx.itesm.proyectodif.R
import mx.itesm.proyectodif.databinding.ActivityMenuResponsableBinding
import mx.itesm.proyectodif.databinding.FragmentCalificarServicioBinding
import mx.itesm.proyectodif.databinding.FragmentInicioBinding
import mx.itesm.proyectodif.ui_comensal.model.Encuesta
import mx.itesm.proyectodif.ui_comensal.model.ListaComedor
import mx.itesm.proyectodif.ui_comensal.viewmodel.InicioVM

// VISTA
/**
 * @author Noh Ah Kim Kwon
 *
 * Controla la vista del fragmento calificarServicio
 */
class CalificarServicioFrag : Fragment() {

    /*
    companion object {
        fun newInstance() = CalificarServicioFrag()
    }*/

    private lateinit var binding: FragmentCalificarServicioBinding
    private lateinit var viewModel: CalificarServicioVM
    //private val viewModel: CalificarServicioVM by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Esconder el menú de navegación
        (requireActivity() as MainActivity).setBottomNavigationVisibility(View.GONE)

        viewModel = ViewModelProvider(this).get(CalificarServicioVM::class.java)

        binding = FragmentCalificarServicioBinding.inflate(inflater, container, false)
        return binding.root

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
        // Configura tus elementos de interfaz de usuario, como Spinner, RatingBars, EditText y botón
        // Cuando se presione el botón de envío:
        binding.btnEnviar.setOnClickListener {
            val selectedComedor = binding.spComedor.selectedItem as ListaComedor
            val idCom = selectedComedor.idCom
            val calidad = binding.rbCalidad.rating.toInt()
            val higiene = binding.rbHigiene.rating.toInt()
            val servicio = binding.rbServicio.rating.toInt()
            val comentario = binding.etComentarios.text.toString()



            val encuesta = Encuesta(idCom, calidad, higiene, servicio, comentario)
            viewModel.enviarEncuesta(encuesta)


        }
        viewModel.encuestaEnviadaLiveData.observe(viewLifecycleOwner) { encuestaEnviada ->
            if (encuestaEnviada) {
                mostrarAlertaExito()
            }
        }
    }

    private fun mostrarAlertaExito() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Registrado con éxito")
        builder.setMessage("Gracias por completar la encuesta.")

        builder.setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CalificarServicioVM::class.java)
        // TODO: Use the ViewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        // Aparece el menú de navegación
        (requireActivity() as MainActivity).setBottomNavigationVisibility(View.VISIBLE)
    }


}