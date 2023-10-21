package mx.itesm.proyectodif.ui_responsable.view

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.google.zxing.integration.android.IntentIntegrator
import mx.itesm.proyectodif.R
import mx.itesm.proyectodif.databinding.FragmentInformacionBinding
import mx.itesm.proyectodif.databinding.FragmentInicioBinding
import mx.itesm.proyectodif.databinding.FragmentInicioResponsableBinding
import mx.itesm.proyectodif.databinding.FragmentRegistroRacionesConQRBinding
import mx.itesm.proyectodif.ui_comensal.model.Encuesta
import mx.itesm.proyectodif.ui_comensal.model.ListaComedor
import mx.itesm.proyectodif.ui_comensal.viewmodel.CalificarServicioVM
import mx.itesm.proyectodif.ui_responsable.model.Asistencia
import mx.itesm.proyectodif.ui_responsable.viewmodel.RegistroRacionesConQRVM
// VISTA
/**
 * @author Noh Ah Kim Kwon
 *
 * Controla la vista del fragmento registrar raciones con qr
 */
class RegistroRacionesConQRFrag : Fragment() {

    /*companion object {
        fun newInstance() = RegistroRacionesConQRFrag()
    }*/

    private lateinit var binding: FragmentRegistroRacionesConQRBinding

    private lateinit var viewModel: RegistroRacionesConQRVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistroRacionesConQRBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //(requireActivity() as AppCompatActivity).supportActionBar?.hide()

        // Recuperar el valor scannedText del argumento
        // Obtener el argumento "scannedText" del Bundle de argumentos
        val args = RegistroRacionesConQRFragArgs.fromBundle(requireArguments())
        val scannedText = args.scannedText

        // Establece el texto del TextView con el valor del scannedText y mostrar el valor en un TextView
        binding.tvIdComensal.text = scannedText

        // viewModel
        viewModel = ViewModelProvider(this).get(RegistroRacionesConQRVM::class.java)


        return root

        //return root
        //return inflater.inflate(R.layout.fragment_registro_raciones_con_q_r, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegistroRacionesConQRVM::class.java)
        // TODO: Use the ViewModel
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
        binding.btnRegistrarRaciones.setOnClickListener {
            val idComensal = binding.tvIdComensal.text.toString()
            val selectedComedor = binding.spComedor.selectedItem as ListaComedor
            val idCom = selectedComedor.idCom
            val pago = binding.etPago.text.toString()

            // Obtén el valor seleccionado del RadioGroup
            val tipoPedido = binding.rgPedido

            // Obtén el valor seleccionado del RadioGroup
            val btnComer = binding.btnComer
            val btnLlevar = binding.btnLlevar

            // Verificar si se ha seleccionado un RadioButton
            val lugarRadioGroup = binding.rgPedido
            val selectedRadioButtonId = lugarRadioGroup.checkedRadioButtonId
            val lugar: String = when (selectedRadioButtonId) {
                R.id.btnComer -> "Para Comer"
                R.id.btnLlevar -> "Para Llevar"
                else -> ""
            }

            // Verificar si todos los campos están llenos
            if (idCom != 0 && pago.isNotEmpty() && lugar.isNotEmpty()) {
                // Todos los campos están llenos, puedes enviar la información
                val asistencia = Asistencia(idComensal.toInt(), idCom, pago.toInt(), lugar)
                viewModel.registroRacionQR(asistencia)
            } else {
                // Alguno de los campos está vacío
                Toast.makeText(context, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }

        }
        viewModel.enviadaLiveData.observe(viewLifecycleOwner) { encuestaEnviada ->
            if (encuestaEnviada) {
                Toast.makeText(context, "Enviado", Toast.LENGTH_SHORT).show()
                mostrarAlertaExito()
            }
        }

        // Registrar otro QR
        binding.btnOtroQR.setOnClickListener {
            startQRScanner()
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

                binding.tvIdComensal.text = scannedText

                // Obtén una referencia al TextView en el fragmento
                //val miFragmento = childFragmentManager.findFragmentById(R.id.registroRacionesConQRFrag) as RegistroRacionesConQRFrag
                //miFragmento.actualizarResultadoEscaneo(scannedText)

            }
        } else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun mostrarAlertaExito() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Registro de raciones con QR")
        builder.setMessage("Registrado con éxito")

        builder.setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }


}