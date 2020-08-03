package com.e.midterm_expenses

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_password.*

class Password : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)

        var passwordInput = findViewById(R.id.pwd) as EditText

        btnlogin.setOnClickListener {
            val userInput = passwordInput.text
            if(userInput.toString() == "1234") {
                var intentResult = Intent()
                intentResult.putExtra(
                    MainActivity.KEY_RES, "accept")

                setResult(Activity.RESULT_OK, intentResult)

                finish()
            } else {
                Toast.makeText(this@Password,"Wrong Password!", Toast.LENGTH_LONG).show()
            }
        }


    }
}
