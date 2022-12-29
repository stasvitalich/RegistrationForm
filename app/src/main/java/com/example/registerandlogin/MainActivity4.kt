package com.example.registerandlogin

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.example.registerandlogin.databinding.ActivityMain4Binding

class MainActivity4 : AppCompatActivity() {

    lateinit var bindingClass: ActivityMain4Binding


    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        bindingClass = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        var mpbuttonsound = MediaPlayer()
        mpbuttonsound.setDataSource(
            this,
            Uri.parse("android.resource://" + this.packageName + "/" + R.raw.anastasia)
        )
        mpbuttonsound.prepare()
        mpbuttonsound.start()

        bindingClass.buttonWindow.setOnClickListener {
            bindingClass.imageParis.visibility = View.VISIBLE
            bindingClass.secretButton.visibility = View.VISIBLE
        }

        bindingClass.secretButton.setOnClickListener {
            bindingClass.secretMessageText.visibility = View.VISIBLE
            bindingClass.backgroundText.visibility = View.VISIBLE
        }
    }
}



