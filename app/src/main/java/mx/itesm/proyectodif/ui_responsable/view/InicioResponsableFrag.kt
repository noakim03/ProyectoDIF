package mx.itesm.proyectodif.ui_responsable.view

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.google.zxing.integration.android.IntentIntegrator
import mx.itesm.proyectodif.LoginComensalActivity
import mx.itesm.proyectodif.LoginResponsableActivity
import mx.itesm.proyectodif.R

import mx.itesm.proyectodif.databinding.FragmentInicioResponsableBinding
import mx.itesm.proyectodif.ui_responsable.viewmodel.InicioResponsableVM

// VISTA
/**
 * @author Noh Ah Kim Kwon
 *
 * Controla la vista del fragmento inicioResponsable
 */
class InicioResponsableFrag : Fragment() {

    /*companion object {
        fun newInstance() = InicioResponsableFrag()
    }*/

    private lateinit var binding: FragmentInicioResponsableBinding

    private lateinit var viewModel: InicioResponsableVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInicioResponsableBinding.inflate(layoutInflater)
        val root: View = binding.root

        //val view = inflater.inflate(R.layout.fragment_inicio_responsable, container, false)
        //val tvIDResp: TextView = root.findViewById(R.id.tvIDResp)
        val usuario = requireActivity().intent.getStringExtra("ID_RESP")
        //tvIDResp.text = "Mi ID: $usuario"  // Cambiar texto del tvID
        binding.tvIDResp.text = "Mi ID: $usuario"

        return root

        //return inflater.inflate(R.layout.fragment_inicio_responsable, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InicioResponsableVM::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrarEventos()
    }

    private fun registrarEventos() {
        binding.btnAgregarQR.setOnClickListener {
            Toast.makeText(context, "Escanear QR", Toast.LENGTH_SHORT).show()
            startQRScanner()
        }
        binding.btnRaciones.setOnClickListener {
            val accion = InicioResponsableFragDirections.actionInicioResponsableFragToRegistrarRacionesFrag2()
            findNavController().navigate(accion)
        }
        binding.btnRacionesPara.setOnClickListener {
            val accion = InicioResponsableFragDirections.actionInicioResponsableFragToPedidoParaFrag()
            findNavController().navigate(accion)
        }
        binding.btnRacionDonada.setOnClickListener {
            val accion = InicioResponsableFragDirections.actionInicioResponsableFragToRacionDonadaFrag2()
            findNavController().navigate(accion)
        }
        binding.btnIncidencias.setOnClickListener {
            val accion = InicioResponsableFragDirections.actionInicioResponsableFragToRegistroIncidenciasFrag2()
            findNavController().navigate(accion)
        }
        binding.btnLogOut.setOnClickListener(){
            confirmarLogout()
        }
    }

    private fun startQRScanner() {
        // Código para iniciar el escaneo de QR
        val integrator = IntentIntegrator.forSupportFragment(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Escanear código QR")
        integrator.setCameraId(0) // Usar la cámara trasera (0) por defecto
        integrator.setBeepEnabled(true) // Reproducir un sonido al escanear
        integrator.setOrientationLocked(true) // Bloquear la orientación de la cámara

        integrator.initiateScan()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                // Escaneo cancelado o sin resultados
                Toast.makeText(context, "Escaneo cancelado", Toast.LENGTH_SHORT).show()

            } else {
                // Aquí puedes usar el resultado del escaneo (result.contents)
                val scannedText = result.contents
                Toast.makeText(context, "Resultado del escaneo: $scannedText", Toast.LENGTH_SHORT).show()

                val accion = InicioResponsableFragDirections.actionInicioResponsableFragToRegistroRacionesConQRFrag(scannedText)
                findNavController().navigate(accion)

                // Obtén una referencia al TextView en el fragmento
                //val miFragmento = childFragmentManager.findFragmentById(R.id.registroRacionesConQRFrag) as RegistroRacionesConQRFrag
                //miFragmento.actualizarResultadoEscaneo(scannedText)

            }
        } else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
    private fun cerrarSesion() {
        //findNavController().navigate(R.id.loginResponsableActivity)
        // Redirigir al usuario a la pantalla de inicio de sesión.
        val intent = Intent(requireContext(), LoginComensalActivity::class.java)
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


}