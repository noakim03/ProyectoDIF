package mx.itesm.proyectodif.ui_comensal.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import mx.itesm.proyectodif.MainActivity
import mx.itesm.proyectodif.R
import mx.itesm.proyectodif.databinding.FragmentCodigoQRBinding
import mx.itesm.proyectodif.ui_comensal.viewmodel.CodigoQRVM


// VISTA
/**
 * @author Noh Ah Kim Kwon
 *
 * Controla la vista del fragmento codigoQR
 */
class CodigoQRFrag : Fragment() {

    /*companion object {
        fun newInstance() = CodigoQRFrag()
    }*/

    private lateinit var binding: FragmentCodigoQRBinding
    private lateinit var viewModel: CodigoQRVM


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Esconder el menú de navegación
        (requireActivity() as MainActivity).setBottomNavigationVisibility(View.GONE)

        //return inflater.inflate(R.layout.fragment_codigo_q_r, container, false)

        binding = FragmentCodigoQRBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //val view = inflater.inflate(R.layout.fragment_codigo_q_r, container, false)
        val myImageView: ImageView = root.findViewById(R.id.ivCodigoQR)
        //val tvID: TextView = root.findViewById(R.id.tvID)

        val sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val usuario = sharedPreferences.getString("ID_COMENSAL", "")

        //val usuario = requireActivity().intent.getStringExtra("ID_COMENSAL")
        binding.tvID.text = "ID: $usuario"  // Cambiar texto del tvID
        //binding.tvID.text = "ID: $usuario"  // Cambiar texto del tvID
        val encoder = BarcodeEncoder()
        val bitmap = encoder.encodeBitmap(usuario, BarcodeFormat.QR_CODE, 500, 500)
        myImageView.setImageBitmap(bitmap)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CodigoQRVM::class.java)
        // TODO: Use the ViewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        // Aparece el menú de navegación
        (requireActivity() as MainActivity).setBottomNavigationVisibility(View.VISIBLE)

    }

}