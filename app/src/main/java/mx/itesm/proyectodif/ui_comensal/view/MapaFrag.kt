package mx.itesm.proyectodif.ui_comensal.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import mx.itesm.proyectodif.R
import mx.itesm.proyectodif.databinding.FragmentMapaBinding
import mx.itesm.proyectodif.ui_comensal.viewmodel.CalificarServicioVM
import mx.itesm.proyectodif.ui_comensal.viewmodel.MapaVM

class MapaFrag : Fragment() {

    private var _binding: FragmentMapaBinding? = null
    private lateinit var viewModel: MapaVM
    private var adaptadorComedor: AdaptadorComedor? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mapaViewModel =
            ViewModelProvider(this).get(MapaVM::class.java)

        viewModel = ViewModelProvider(this).get(MapaVM::class.java)

        _binding = FragmentMapaBinding.inflate(inflater, container, false)
        val root: View = binding.root


        //val view = inflater.inflate(R.layout.fragment_mapa, container, false)
        val webView = root.findViewById<WebView>(R.id.mapView)
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.loadUrl("https://www.google.com/maps/d/viewer?mid=16p4XUJ3OiJqezpHleTAKGHy4Ti_8rYc&ll=19.575418665693352%2C-99.24592264703536&z=13")

        configurarAdaptador()

        /*val textView: TextView = binding.textDashboard
        mapaViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    override fun onStart() {
        super.onStart()
        viewModel.descargarDatosEstadoComedor()

    }

    private fun configurarAdaptador() {
        // configuración de layoutmanager
        //val layout = LinearLayoutManager(this)
        val layout = GridLayoutManager(context, 1)
        //layout.orientation = LinearLayoutManager.HORIZONTAL
        binding.rvComedores.layoutManager = layout

        viewModel.estadoComedor.observe(viewLifecycleOwner){ lista ->
            val arrComedor = lista.toTypedArray()
            adaptadorComedor = AdaptadorComedor(requireContext(), arrComedor)
            binding.rvComedores.adapter = adaptadorComedor
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}