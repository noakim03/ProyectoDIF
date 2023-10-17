package mx.itesm.proyectodif

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.gson.GsonBuilder
import mx.itesm.proyectodif.databinding.ActivityLoginComensalBinding
import mx.itesm.proyectodif.ui_comensal.model.LoginComensal
import mx.itesm.proyectodif.ui_comensal.model.LoginComensalAPI
import mx.itesm.proyectodif.ui_comensal.model.RespuestaServidor
import mx.itesm.proyectodif.ui_comensal.viewmodel.LoginComensalVM
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginComensalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginComensalBinding
    private lateinit var viewModel: LoginComensalVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginComensalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Escoder la barra superior
        supportActionBar?.hide()

        // Inicializa el ViewModel
        viewModel = ViewModelProvider(this).get(LoginComensalVM::class.java)

        // Desenfocar EditText
        val rootView = findViewById<View>(android.R.id.content)
        rootView.setOnClickListener {
            // Realiza aquí la acción que desees, como desenfocar o cerrar el teclado
            binding.etUsuario.clearFocus()
            binding.etPassword.clearFocus()
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(rootView.windowToken, 0)
        }

        registrarEventos()
    }


    private fun registrarEventos() {
        binding.btnLoginC.setOnClickListener {
            if (binding.etUsuario.text.toString().isEmpty() || binding.etPassword.text.toString().isEmpty()) {
                Toast.makeText(this, "Ingrese los valores", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            postData(binding.etUsuario.text.toString(), binding.etPassword.text.toString())
            //menuInicial()
            /*
            val text = binding.etUsuario.text.toString()
            val myImageView: ImageView = findViewById(R.id.ivCodigoQR)

            val encoder = BarcodeEncoder()
            val bitmap = encoder.encodeBitmap(text, BarcodeFormat.QR_CODE, 400, 400)
            myImageView.setImageBitmap(bitmap)*/
        }
        binding.btnRegistrar.setOnClickListener {
            val url = Uri.parse("https://google.com")
            val intNavegador = Intent(Intent.ACTION_VIEW, url)
            startActivity(intNavegador)
        }
        binding.btnResponsable.setOnClickListener {
            loginResponsable()
        }
    }

    private fun postData(id: String, pass: String) {

        val usuario = binding.etUsuario.text.toString()
        val psw = binding.etPassword.text.toString()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        // Crear una instancia de Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://54.197.177.119:8080") // Reemplaza con la URL de tu servidor
            .addConverterFactory(GsonConverterFactory.create())
            .build()
/*
        /*// Crear JSONObject
        val jsonObject = JSONObject()
        jsonObject.put("id", usuario)
        jsonObject.put("pass", psw)

        val jsonObjectString = jsonObject.toString()*/
        val apiService = retrofit.create(LoginComensalAPI::class.java)

        try {
            val loginData = JSONObject()
            loginData.put("id", id.toInt())
            loginData.put("pass", password)

            val requestBody = RequestBody.create(MediaType.parse("application/json"), loginData.toString())

            val call = apiService.login(requestBody)
            call.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        // Autenticación exitosa
                        val responseMessage = response.body() // Puedes obtener la respuesta del servidor si es necesario
                        if (responseMessage!!.message == "Información correcta") {
                            // Autenticación exitosa, procede a la siguiente pantalla
                            Toast.makeText(this@LoginComensalActivity, "Credenciales válidas", Toast.LENGTH_LONG).show()
                        } else {
                            // La respuesta no es lo que se esperaba, maneja el error
                            Toast.makeText(this@LoginComensalActivity, "Usuario o Contraseña incorrectos.", Toast.LENGTH_LONG).show()
                        }
                        // Procede a la siguiente pantalla
                    } else {
                        // Autenticación falló, muestra un mensaje de error
                        // response.errorBody() puede contener detalles del error proporcionados por el servidor
                        // Error en la respuesta del servidor
                        Toast.makeText(this@LoginComensalActivity, "Error de servidor", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    // Ocurrió un error en la comunicación con el servidor
                }
            })
        } catch (e: JSONException) {
            // Manejar cualquier error al construir el JSONObject
        }*/


        // Crear una instancia del servicio de Retrofit
        val apiService = retrofit.create(LoginComensalAPI::class.java)

        // Crear una instancia de Usuario con los datos del usuario
        val usuarioData = LoginComensal(id.toInt(), pass)
        //println(usuarioData)
        // Realizar la solicitud POST para verificar el usuario
        val call = apiService.login(usuarioData)
        call.enqueue(object : Callback<RespuestaServidor> {
            override fun onResponse(call: Call<RespuestaServidor>, response: Response<RespuestaServidor>) {
                // Este método se llama cuando recibimos una respuesta de nuestra API.
                Toast.makeText(this@LoginComensalActivity, "Validando...", Toast.LENGTH_SHORT).show()
                //binding.etUsuario.setText("")
                //binding.etPassword.setText("")
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    //val id = loginResponse?.id
                    //val pass = loginResponse?.pass
                    // Maneja la respuesta exitosa, por ejemplo, muestra un mensaje
                    println(loginResponse)
                    if ((loginResponse != null) && (loginResponse.mensaje == "Información correcta")) {
                        // La autenticación fue exitosa, puedes proceder a la siguiente pantalla
                        // Credenciales válidas, inicia la actividad MainActivity
                        val i = Intent(this@LoginComensalActivity, MainActivity::class.java).apply {
                            putExtra("ID_COMENSAL", id)
                        }
                        startActivity(i)
                        finish() // Finaliza la actividad de inicio de sesión
                    }
                } else {
                    // Error en la respuesta del servidor
                    Toast.makeText(this@LoginComensalActivity, "Usuario o Contraseña incorrectos", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<RespuestaServidor>, t: Throwable) {
                // Manejar fallos de conexión
                //Toast.makeText(this@LoginComensalActivity, "Error de conexión: ${t.message}", Toast.LENGTH_LONG).show()
                Toast.makeText(this@LoginComensalActivity, "Error de conexión", Toast.LENGTH_LONG).show()
            }
        })

    }

    private fun menuInicial() {
        // MainActivity


        /*var usuario ="12345"
        var psw ="c"
        if (binding.etUsuario.text.toString() == usuario){
            if (binding.etPassword.text.toString() == psw){
                Toast.makeText(this,"Iniciando sesion...", Toast.LENGTH_LONG).show()
                val i = Intent(this, MainActivity::class.java).apply {
                    putExtra("ID_COMENSAL", usuario)
                }
                startActivity(i)

            } else {
                Toast.makeText(this,"Usuario o Contraseña incorrectos.", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this,"Complete los campos.", Toast.LENGTH_LONG).show()
        }*/

    }

    private fun loginResponsable() {
        // LoginResponsableActivity
        val i = Intent(this, LoginResponsableActivity::class.java)
        startActivity(i)
    }


}