package mx.itesm.proyectodif

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import mx.itesm.proyectodif.databinding.ActivityMainBinding
import mx.itesm.proyectodif.ui_comensal.view.InfoFrag
import mx.itesm.proyectodif.ui_comensal.view.InicioFrag
import mx.itesm.proyectodif.ui_comensal.viewmodel.CodigoQRVM

/**
 * @author Noh Ah Kim Kwon
 *
 * Controla la vista del navegacion_inicio(fragmento inicio),
 * navegacion_mapa(fragmento mapa), navegacion_info(fragmento info)
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // para esconder la barra de aplicación
        // supportActionBar?.hide()

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navegacion_inicio, R.id.navegacion_mapa, R.id.navegacion_info
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }

    fun setBottomNavigationVisibility(visibility: Int) {
        binding.navView.visibility = visibility
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp()
                || super.onSupportNavigateUp()
    }


}