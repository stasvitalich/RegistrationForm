package com.example.registerandlogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.registerandlogin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var bindingClass: ActivityMainBinding

    private var launcherRegister: ActivityResultLauncher<Intent>? = null

    override fun onCreate(s: Bundle?) {

        super.onCreate(s)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        var name = ""
        var password = ""

        launcherRegister =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == RESULT_OK) {
                    name = result.data?.getStringExtra("name").toString()
                    password = result.data?.getStringExtra("yourpassword").toString()

                    if (name != "template" && name?.length!! > 1) {
                        bindingClass.newAccountButton.visibility = View.INVISIBLE

                    }
                    bindingClass.textUser?.text = name


                    val newDimensionInPixels = 450
                    bindingClass.whiteBackgroundForButtons.layoutParams.height =
                        newDimensionInPixels
                    bindingClass.whiteBackgroundForButtons.requestLayout()

                    val newPositionInPixels = 800
                    bindingClass.exit.y = newPositionInPixels.toFloat()

                }

                bindingClass.loginButton.setOnClickListener {
                    //bindingClass.textPassword?.text = password
                    val intent = Intent(this, MainActivity3::class.java).also {
                        it.putExtra("PASSWORD", password)
                        it.putExtra("NAME", name)
                        startActivity(it)
                    }
                }


            }
    }

    fun onClickRegister(view: View) {
        launcherRegister?.launch(Intent(this, MainActivity2::class.java))
    }
}