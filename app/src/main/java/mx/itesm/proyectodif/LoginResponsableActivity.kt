package mx.itesm.proyectodif

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import mx.itesm.proyectodif.databinding.ActivityLoginResponsableBinding
import mx.itesm.proyectodif.ui_responsable.model.LoginResponsableAPI
import mx.itesm.proyectodif.ui_comensal.model.RespuestaServidor
import mx.itesm.proyectodif.ui_responsable.model.LoginResponsable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginResponsableActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginResponsableBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginResponsableBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Escoder la barra superior
        supportActionBar?.hide()

        // Desenfocar EditText
        val rootView = findViewById<View>(android.R.id.content)
        rootView.setOnClickListener {
            // Realiza aquí la acción que desees, como desenfocar o cerrar el teclado
            binding.etResponsable.clearFocus()
            binding.etPasswordR.clearFocus()
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(rootView.windowToken, 0)
        }
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedInR", false)

        if (isLoggedIn) {
            val userID = sharedPreferences.getString("userID", "") // Recuperar el ID del usuario
            // El usuario no ha iniciado sesión previamente, redirige a la loginActivity
            val intent = Intent(this, MenuResponsableActivity::class.java)
            startActivity(intent)
            finish()
        }


        registrarEventos()

    }

    private fun registrarEventos() {
        binding.btnLoginR.setOnClickListener {
            //menuInicial()
            if (binding.etResponsable.text.toString().isEmpty() || binding.etPasswordR.text.toString().isEmpty()){
                Toast.makeText(this, "Ingrese los valores", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            postDataR(binding.etResponsable.text.toString(), binding.etPasswordR.text.toString())
        }
        binding.btnComensal.setOnClickListener {
            loginComensal()
        }
    }

    private fun postDataR(id: String, pass: String) {
        // Instancia de Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://54.197.177.119:8080") // Reemplaza con la URL de tu servidor
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Crear una instancia del servicio de Retrofit
        val apiService = retrofit.create(LoginResponsableAPI::class.java)

        // Crear una instancia de Usuario con los datos del usuario
        val usuarioData = LoginResponsable(id.toInt(), pass)
        //println(usuarioData)
        // Realizar la solicitud POST para verificar el usuario
        val call = apiService.loginres(usuarioData)
        call.enqueue(object : Callback<RespuestaServidor> {
            override fun onResponse(call: Call<RespuestaServidor>, response: Response<RespuestaServidor>) {
                // Este método se llama cuando recibimos una respuesta de nuestra API.
                Toast.makeText(this@LoginResponsableActivity, "Validando...", Toast.LENGTH_SHORT).show()
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

                        val i = Intent(this@LoginResponsableActivity, MenuResponsableActivity::class.java).apply {
                            putExtra("ID_RESP", id)
                        }
                        startActivity(i)

                        // Cuando el usuario inicia sesión con éxito
                        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("ID_RESP", id)
                        editor.putBoolean("isLoggedInR", true)
                        editor.apply()


                        finish() // Finaliza la actividad de inicio de sesión
                    }
                } else {
                    // Error en la respuesta del servidor
                    Toast.makeText(this@LoginResponsableActivity, "Usuario o Contraseña incorrectos", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<RespuestaServidor>, t: Throwable) {
                // Manejar fallos de conexión
                //Toast.makeText(this@LoginResponsableActivity, "Error de conexión: ${t.message}", Toast.LENGTH_LONG).show()
                Toast.makeText(this@LoginResponsableActivity, "Error de conexión", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun loginComensal() {
        // LoginResponsableActivity
        val i = Intent(this, LoginComensalActivity::class.java)
        startActivity(i)
    }




}