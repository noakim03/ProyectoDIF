package mx.itesm.proyectodif.ui_comensal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.proyectodif.ui_comensal.model.Aviso
import mx.itesm.proyectodif.ui_comensal.model.AvisoAPI
import mx.itesm.proyectodif.ui_comensal.model.EstadoComedor
import mx.itesm.proyectodif.ui_comensal.model.EstadoComedorAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InfoVM : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text


}