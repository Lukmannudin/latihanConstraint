package com.dsc.latihanconstraint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnSignIn.setOnClickListener {
            val username: String = edtUsername.text.toString()
            val password: String = edtPassword.text.toString()
            
            if (username == "lukman" && password == "123"){
                Toast.makeText(this,"YEAYYYY",Toast.LENGTH_SHORT).show()
            } else if (username == "lukman" && password != "123"){
                Toast.makeText(this,"Salah Password!",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,"Salah keduanya cuy!",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
