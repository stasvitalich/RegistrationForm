package com.example.registerandlogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import com.example.registerandlogin.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {

    lateinit var bindingClass: ActivityMain3Binding


    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        bindingClass = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        nameFocusListener()
        passwordFocusListener()

        var name = intent.getStringExtra("NAME")
        var password = intent.getStringExtra("PASSWORD")

        var userName = bindingClass.editName.text
        var userPassword = bindingClass.editPassword.text

        bindingClass.loginButton2.setOnClickListener {
            if (userName.toString() == name && userPassword.toString() == password){
                    val intent = Intent(this, MainActivity4::class.java)
                    startActivity(intent)
            }
            else if (userName.toString() == name && userPassword.toString() != password){
                bindingClass.wrongForm.text = getString(R.string.WrongPassword)
                bindingClass.wrongForm.visibility = View.VISIBLE

            }
            else if (userName.toString() != name && userPassword.toString() == password){
                bindingClass.wrongForm.text = getString(R.string.WrongUser)
                bindingClass.wrongForm.visibility = View.VISIBLE
            }
            else if (userName.toString() != name && userPassword.toString() != password){
                bindingClass.wrongForm.text = getString(R.string.WrongData)
                bindingClass.wrongForm.visibility = View.VISIBLE
            }


        }
    }

    private fun nameFocusListener() {
        bindingClass.editName.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                bindingClass.nameContainerLogin.helperText = validName()
            }
        }
    }

    private fun validName(): String? {
        val nameText = bindingClass.editName.text.toString()

        if (nameText.isEmpty()) {
            return getString(R.string.EmptyField)
        }
        if (nameText.matches(".*[1-9].*".toRegex())) {
            return getString(R.string.DigitsInName)
        }
        if (nameText.matches(".*[*#()/+.,;@!?<>%-].*".toRegex())) {
            return getString(R.string.CharachtersInPhone)
        }
        return null
    }


    private fun passwordFocusListener() {
        bindingClass.editPassword.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                bindingClass.passwordContainerLogin.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String? {
        val passwordText = bindingClass.editPassword.text.toString()

        if (passwordText.isEmpty()) {
            return getString(R.string.EmptyField)
        }
        if (passwordText.length < 8) {
            return getString(R.string.MinimumCharacters)
        }
        if (!passwordText.matches(".*[A-Z].*".toRegex())) {
            return getString(R.string.CapitalCharacter)
        }
        if (!passwordText.matches(".*[a-z].*".toRegex())) {
            return getString(R.string.LowerCase)
        }
        if (!passwordText.matches(".*[0-9].*".toRegex())) {
            return getString(R.string.Digit)
        }
        if (!passwordText.matches(".*[@#!?<>+)(%-].*".toRegex())) {
            return getString(R.string.SpecialCharacters)
        }
        return null
    }
}
