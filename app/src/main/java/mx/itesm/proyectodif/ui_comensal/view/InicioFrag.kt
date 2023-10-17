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
import mx.itesm.proyectodif.R
import mx.itesm.proyectodif.databinding.FragmentInicioBinding
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

        binding = FragmentInicioBinding.inflate(inflater, container, false)
        val root: View = binding.root

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

    override fun onDestroyView() {
        super.onDestroyView()
        //binding = null
    }
}