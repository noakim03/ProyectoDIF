package mx.itesm.proyectodif.ui_comensal.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import mx.itesm.proyectodif.LoginResponsableActivity
import mx.itesm.proyectodif.databinding.FragmentInformacionBinding
import mx.itesm.proyectodif.ui_comensal.viewmodel.InfoVM
// VISTA
/**
 * @author Noh Ah Kim Kwon
 *
 * Controla la vista del fragmento navegacion_info
 */
class InfoFrag : Fragment() {

    // View binding
    private lateinit var binding: FragmentInformacionBinding

    //private var _binding: FragmentInformacionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    //private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val infoViewModel =
            ViewModelProvider(this).get(InfoVM::class.java)

        binding = FragmentInformacionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //(requireActivity() as AppCompatActivity).supportActionBar?.hide()
        val usuario = requireActivity().intent.getStringExtra("ID_COMENSAL")
        binding.tvID.text = "ID: $usuario"  // Cambiar texto del tvID

        /*val textView: TextView = binding.textNotifications
        infoViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            binding.tvID.text = it.getString("USUARIO")
        }

        registrarEventos()
    }

    private fun registrarEventos() {
        binding.btnPrivacidad.setOnClickListener {

        }
        binding.btnContacto.setOnClickListener {
            val accion = InfoFragDirections.actionNavegacionInfoToDatoContactoFrag2()
            findNavController().navigate(accion)
        }
        binding.btnCalificarServicio.setOnClickListener {
            val accion = InfoFragDirections.actionNavegacionInfoToCalificarServicioFrag()
            findNavController().navigate(accion)
        }
        binding.btnCerrarSesion.setOnClickListener {
            confirmarLogout()
        }
    }

    private fun cerrarSesion() {
        //findNavController().navigate(R.id.loginResponsableActivity)
        // Redirigir al usuario a la pantalla de inicio de sesión.
        val intent = Intent(requireContext(), LoginResponsableActivity::class.java)
        startActivity(intent)
        requireActivity().finish() // Opcional: Cierra la actividad actual para que el usuario no pueda volver atrás.
        // Posiblemente no he cerrado la sesión
    }

    private fun confirmarLogout() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("¿Esta seguro que quiere cerrar sesión?")
            .setTitle("Cerrar Sesión")

        // Agregar un botón para confirmar
        builder.setPositiveButton("Sí") { _, _ ->
            // Si el usuario confirma, llama a la función para cerrar sesión.
            cerrarSesion()
        }

        // Agregar un botón para cancelar
        builder.setNegativeButton("Cancelar") { dialog, _ ->
            // Si el usuario cancela, cierra el cuadro de diálogo.
            dialog.dismiss()
        }

        // Crea y muestra el cuadro de diálogo de confirmación.
        val dialog = builder.create()
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //_binding = null

        //(requireActivity() as AppCompatActivity).supportActionBar?.show()

    }
}