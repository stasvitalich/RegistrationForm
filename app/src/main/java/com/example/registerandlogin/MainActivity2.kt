package com.example.registerandlogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import android.util.Patterns
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.registerandlogin.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    lateinit var bindingClass: ActivityMain2Binding

    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        bindingClass = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        emailFocusListener()
        passwordFocusListener()
        phoneFocusListener()
        nameFocusListener()
    }

    private fun emailFocusListener() {
        bindingClass.emailEditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                bindingClass.emailContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = bindingClass.emailEditText.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return getString(R.string.InvalidEmail)
        }
        return null
    }

    private fun passwordFocusListener() {
        bindingClass.passwordEditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                bindingClass.passwordContainer.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String? {
        val passwordText = bindingClass.passwordEditText.text.toString()

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


    private fun phoneFocusListener() {
        bindingClass.phoneEditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                bindingClass.phoneContainer.helperText = validPhone()
            }
        }
    }

    private fun validPhone(): String? {
        val phoneText = bindingClass.phoneEditText.text.toString()

        if (phoneText.isEmpty()) {
            return getString(R.string.EmptyField)
        }
        if (!phoneText.matches(".*[0-9].*".toRegex())) {
            return getString(R.string.OnlyDigitsInPhone)
        }
        if (phoneText.length != 11) {
            return getString(R.string.PhoneNumberDigit)
        }
        if (phoneText.matches(".*[*#()/+.,;@!?<>%-].*".toRegex())) {
            return getString(R.string.CharachtersInPhone)
        }
        return null
    }


    private fun nameFocusListener() {
        bindingClass.nameEditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                bindingClass.nameContainer.helperText = validName()
            }
        }
    }

    private fun validName(): String? {
        val nameText = bindingClass.nameEditText.text.toString()

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


    fun onClickSubmitAndReturn(view: View) {
        submitForm()
        val userName = bindingClass.nameEditText.text
        val userPassword = bindingClass.passwordEditText.text
        val i = Intent()
        i.putExtra("name", "$userName")
        i.putExtra("yourpassword", "$userPassword")
        setResult(RESULT_OK, i)
    }

    private fun submitForm() {

        bindingClass.emailContainer.helperText = validEmail()
        bindingClass.passwordContainer.helperText = validPassword()
        bindingClass.phoneContainer.helperText = validPhone()
        bindingClass.nameContainer.helperText = validName()

        val validEmail = bindingClass.emailContainer.helperText == null
        val validPassword = bindingClass.passwordContainer.helperText == null
        val validPhone = bindingClass.phoneContainer.helperText == null
        val validName = bindingClass.nameContainer.helperText == null

        if (validEmail && validPassword && validPhone && validName) {
            resetForm()
        } else invalidForm()
    }

    private fun invalidForm() {
        var message = ""
        if (bindingClass.emailContainer.helperText != null) {
            message += "\n\n${(getString(R.string.email))}: " + bindingClass.emailContainer.helperText
        }
        if (bindingClass.passwordContainer.helperText != null) {
            message += "\n\n${(getString(R.string.password))}: " + bindingClass.passwordContainer.helperText
        }
        if (bindingClass.phoneContainer.helperText != null) {
            message += "\n\n${(getString(R.string.phone))}: " + bindingClass.phoneContainer.helperText
        }
        if (bindingClass.nameContainer.helperText != null) {
            message += "\n\n${(getString(R.string.name))}: " + bindingClass.nameContainer.helperText
        }

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.InvlidForm))
            .setMessage(message)
            .setPositiveButton(getString(R.string.Continue)) { _, _ ->
                //do nothing
            }
            .show()
    }

    private fun resetForm() {
        var message = "\n${(getString(R.string.email))}: " + bindingClass.emailEditText.text
        message += "\n${(getString(R.string.password))}: " + bindingClass.passwordEditText.text
        message += "\n${(getString(R.string.phone))}: " + bindingClass.phoneEditText.text
        message += "\n${(getString(R.string.name))}: " + bindingClass.nameEditText.text

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.FormSub))
            .setMessage(message)
            .setPositiveButton(getString(R.string.Save)) { _, _ ->
                bindingClass.emailEditText.text = null
                bindingClass.passwordEditText.text = null
                bindingClass.phoneEditText.text = null
                bindingClass.nameEditText.text = null
                finish()

                bindingClass.emailContainer.helperText = getString(R.string.required)
                bindingClass.passwordContainer.helperText = getString(R.string.required)
                bindingClass.phoneContainer.helperText = getString(R.string.required)
                bindingClass.nameContainer.helperText = getString(R.string.required)
            }
            .show()
    }

    fun onClickShowPhoto(view: View) {
        bindingClass.galeryimage.visibility = View.INVISIBLE
        bindingClass.imageButton.visibility = View.INVISIBLE
    }
}