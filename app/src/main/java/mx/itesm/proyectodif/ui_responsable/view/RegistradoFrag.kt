package mx.itesm.proyectodif.ui_responsable.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.proyectodif.databinding.FragmentRegistradoBinding
import androidx.navigation.fragment.findNavController
import mx.itesm.proyectodif.ui_responsable.viewmodel.RegistradoVM


// VISTA
/**
 * @author Noh Ah Kim Kwon
 *
 * Controla la vista del fragmento registrado
 */
class RegistradoFrag : Fragment() {

    /*companion object {
        fun newInstance() = RegistradoFrag()
    }*/

    private lateinit var binding: FragmentRegistradoBinding


    private lateinit var viewModel: RegistradoVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistradoBinding.inflate(layoutInflater)
        return binding.root
        //return inflater.inflate(R.layout.fragment_registrado, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegistradoVM::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrarEventos()
    }

    private fun registrarEventos() {
        binding.btnRegresarMenu.setOnClickListener {
            val accion = RegistradoFragDirections.actionRegistradoFragToInicioResponsableFrag()
            findNavController().navigate(accion)
        }
        binding.btnRegistrarOtro.setOnClickListener {
            //Regresa al fragmento anterior
            findNavController().navigateUp()

        }
    }

}