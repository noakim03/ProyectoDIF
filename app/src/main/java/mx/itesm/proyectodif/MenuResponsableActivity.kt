package mx.itesm.proyectodif

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.gms.common.moduleinstall.InstallStatusListener
import com.google.android.gms.common.moduleinstall.ModuleInstall
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest
import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate
import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate.InstallState.STATE_CANCELED
import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate.InstallState.STATE_FAILED
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import mx.itesm.proyectodif.databinding.ActivityMenuResponsableBinding

class MenuResponsableActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuResponsableBinding

    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_menu_responsable)
        binding = ActivityMenuResponsableBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //https://developer.android.com/guide/navigation/integrations/ui?hl=es-419
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        //binding = ActivityMenuResponsableBinding.inflate(layoutInflater)
        //setContentView(binding.root)

        //supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,
        //    InicioResponsableFrag()
        //).commit()

        //registrarEventos()
        //replaceFragment(InicioResponsableFrag())

    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun registrarEventos() {
        /*binding.btnAgregarQR.setOnClickListener {
            /*val menu = InicioResponsableFrag()
            val fragment : Fragment? =

            supportFragmentManager.findFragmentByTag(InicioResponsableFrag::class.java.simpleName)

            if (fragment !is InicioResponsableFrag){
                supportFragmentManager.beginTransaction()
                    .add(menu, InicioResponsableFrag::class.java.simpleName)
                    .commit()
            }*/
            replaceFragment(RegistrarRacionesFrag())

        }*/

    }
/*
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }*/
}