package mx.itesm.proyectodif

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.pdfview.PDFView
import mx.itesm.proyectodif.databinding.ActivityMainBinding
import mx.itesm.proyectodif.databinding.ActivityPdfactivityBinding

/**
 * @author Noh Ah Kim Kwon
 *
 * Controla la vista del pdf activity
 */
class PDFActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPdfactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdfactivity)

        binding = ActivityPdfactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pdfView: PDFView = findViewById(R.id.vistaPDF)

        // Ruta del archivo PDF en la carpeta de activos
        val pdfPath = "Aviso_privacidad.pdf"

        // Cargar el archivo PDF desde la carpeta de activos
        pdfView.fromAsset(pdfPath).show()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }


}