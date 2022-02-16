package com.example.tugasloginregiaster
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
class Login : AppCompatActivity() {

    //Untuk mnenginisialisasi properti database firebase
    private lateinit var auth :FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Mendeklarasikan suatu id yang ada pada file xml
        var etEmail = findViewById<EditText>(R.id.etEmail)
        var etPassword = findViewById<EditText>(R.id.etPassword)
        var btnRegister = findViewById<Button>(R.id.btnRegister)
        var btnLogin = findViewById<Button>(R.id.btnLogin)

        //Untuk memanggil button dan intent dan memulai activity ke halaman selanjutnya
        auth= FirebaseAuth.getInstance()
        btnRegister.setOnClickListener {
            Intent(this,RegisterActivity::class.java).also { startActivity(it) }
        }
        btnLogin.setOnClickListener {
            val email=etEmail.text.toString().trim()
            val password=etPassword.text.toString().trim()
            if (email.isEmpty()){
                etEmail.error="Email Harus Diisi"
                etEmail.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                etPassword.error="Password Harus Diisi"
                etPassword.requestFocus()
                return@setOnClickListener
            }
            register(email, password)
        }
    }

    //Memberikan syarat agar email dan password harus diisi agar bisa login dan masuk ke halaman intent yang dituju
    private fun register(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this, "Akun Berasil Dibuat",
                        Toast.LENGTH_SHORT).show()
                    Intent(this, RegisterActivity::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                                Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                }else{
                    Toast.makeText(this, it.exception?.message,
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}
