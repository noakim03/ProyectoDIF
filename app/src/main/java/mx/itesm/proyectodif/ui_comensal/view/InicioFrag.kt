package mx.itesm.proyectodif.ui_comensal.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import mx.itesm.proyectodif.R
import mx.itesm.proyectodif.databinding.FragmentInicioBinding
import mx.itesm.proyectodif.ui_comensal.viewmodel.InfoVM
import mx.itesm.proyectodif.ui_comensal.viewmodel.InicioVM
// VISTA
/**
 * @author Noh Ah Kim Kwon
 *
 * Controla la vista del fragmento navegacion_inicio
 */
class InicioFrag : Fragment() {

    // View binding
    private lateinit var binding: FragmentInicioBinding
    private lateinit var viewModel: InicioVM
    private var adaptadorAviso: AdaptadorAviso? = null


    //private var _binding: FragmentInicioBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    //private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val inicioViewModel =
            ViewModelProvider(this).get(InicioVM::class.java)

        viewModel = ViewModelProvider(this).get(InicioVM::class.java)

        binding = FragmentInicioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        configurarAdaptador()
        /*val textView: TextView = binding.textHome
        inicioViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrarEventos()
    }

    private fun registrarEventos() {
        binding.btnCodigoQR.setOnClickListener {
            val accion = InicioFragDirections.actionNavegacionInicioToCodigoQRFrag()
            findNavController().navigate(accion)
        }
    }
    override fun onStart() {
        super.onStart()
        viewModel.descargarListaAviso()

    }
    private fun configurarAdaptador() {
        // configuración de layoutmanager
        //val layout = LinearLayoutManager(this)
        val layout = GridLayoutManager(context, 1)
        //layout.orientation = LinearLayoutManager.HORIZONTAL
        binding.rvNoticias.layoutManager = layout

        viewModel.listaAviso.observe(viewLifecycleOwner){ lista ->
            val arrAviso = lista.toTypedArray()
            adaptadorAviso = AdaptadorAviso(requireContext(), arrAviso)
            binding.rvNoticias.adapter = adaptadorAviso
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        //binding = null
    }
}