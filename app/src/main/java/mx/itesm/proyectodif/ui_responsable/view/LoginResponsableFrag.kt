package mx.itesm.proyectodif.ui_responsable.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.proyectodif.R
import mx.itesm.proyectodif.ui_responsable.viewmodel.LoginResponsableVM

// VISTA
/**
 * @author Noh Ah Kim Kwon
 *
 * Controla la vista del fragmento loginResponsable
 */
class LoginResponsableFrag : Fragment() {

    companion object {
        fun newInstance() = LoginResponsableFrag()
    }

    private lateinit var viewModel: LoginResponsableVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_responsable, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginResponsableVM::class.java)
        // TODO: Use the ViewModel
    }

}