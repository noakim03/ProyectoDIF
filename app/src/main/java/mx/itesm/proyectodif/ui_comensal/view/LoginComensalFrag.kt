package mx.itesm.proyectodif.ui_comensal.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.proyectodif.MainActivity
import mx.itesm.proyectodif.R
import mx.itesm.proyectodif.ui_comensal.viewmodel.LoginComensalVM
// VISTA
/**
 * @author Noh Ah Kim Kwon
 *
 * Controla la vista del fragmento loginComensal
 */
class LoginComensalFrag : Fragment() {

    companion object {
        fun newInstance() = LoginComensalFrag()
    }

    private lateinit var viewModel: LoginComensalVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_comensal, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginComensalVM::class.java)
        // TODO: Use the ViewModel
    }


}