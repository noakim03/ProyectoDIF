package mx.itesm.proyectodif.ui_comensal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CodigoQRVM : ViewModel() {

    // TODO: Implement the ViewModel
    val usuarioText = MutableLiveData<String>()

    fun setUsuarioText(text: String) {
        usuarioText.value = text
    }

    fun getUsuarioText(): LiveData<String> {
        return usuarioText
    }
}