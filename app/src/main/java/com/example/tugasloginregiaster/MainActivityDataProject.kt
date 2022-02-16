package com.example.jualbuku
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.tugasloginregiaster.Login
import com.example.tugasloginregiaster.R
import com.google.firebase.auth.FirebaseAuth
class Databuku : AppCompatActivity() {

//Untuk mnenginisialisasi properti database firebase
private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dataproject)
        auth= FirebaseAuth.getInstance()

        //Untuk mendeklarasikan id atau button yang ada pada xml
        var btnMasuk = findViewById<Button>(R.id.btnMasuk)
        var btnLogout = findViewById<Button>(R.id.btnLogout)

        //Untuk memanggil button dan intent dan memulai activity ke halaman selanjutnya
        btnMasuk.setOnClickListener {
            val intent = Intent(this, activityResultRegistry::class.java)
            startActivity(intent)
        }
        //Button Logout untuk menuju halaman login
        btnLogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}