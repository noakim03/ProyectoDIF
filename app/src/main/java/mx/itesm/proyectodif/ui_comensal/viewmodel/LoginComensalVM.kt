package mx.itesm.proyectodif.ui_comensal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.proyectodif.ui_comensal.model.LoginComensalAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginComensalVM : ViewModel() {


    /*fun verificarCredenciales(usuario: String, password: String): MutableLiveData<String> {
        val resultadoLogin = MutableLiveData<String>()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://54.197.177.119:8080/") // Reemplaza con la URL de tu servidor
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(LoginComensalAPI::class.java)
        val usuarioData = Comensal(usuario, password)

        val call = apiService.login(usuarioData)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody == "Información correcta") {
                        loginResultLiveData.postValue("Información correcta")
                    } else {
                        loginResultLiveData.postValue("Usuario o Contraseña incorrectos.")
                    }
                } else {
                    loginResultLiveData.postValue("Error de servidor")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                loginResultLiveData.postValue("Error de conexión: ${t.message}")
            }
        })

        return resultadoLogin
    }     */

}