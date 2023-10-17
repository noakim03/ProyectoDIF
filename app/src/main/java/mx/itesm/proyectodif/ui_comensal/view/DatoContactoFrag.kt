package mx.itesm.proyectodif.ui_comensal.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.proyectodif.MainActivity
import mx.itesm.proyectodif.R

// VISTA
/**
 * @author Noh Ah Kim Kwon
 *
 * Controla la vista del fragmento datoContacto
 */
class DatoContactoFrag : Fragment() {

    companion object {
        fun newInstance() = DatoContactoFrag()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Esconder el menú de navegación
        (requireActivity() as MainActivity).setBottomNavigationVisibility(View.GONE)

        return inflater.inflate(R.layout.fragment_dato_contacto, container, false)
    }


    override fun onDestroy() {
        super.onDestroy()
        // Aparece el menú de navegación
        (requireActivity() as MainActivity).setBottomNavigationVisibility(View.VISIBLE)
    }
}