package com.example.detective

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val detect: Button? = findViewById(R.id.detect)
        val ImageView: ImageView = findViewById(R.id.imageView)

        val iv1: ImageView = findViewById(R.id.imageView1)
        val iv2: ImageView = findViewById(R.id.imageView2)
        val iv3: ImageView = findViewById(R.id.imageView3)

        detect?.setOnClickListener {
            Toast.makeText(applicationContext,"Working Button",Toast.LENGTH_SHORT).show()
            val currentDrawable = ImageView.drawable

            // Check if it's a BitmapDrawable
            if (currentDrawable is BitmapDrawable) {
                val bitmap: Bitmap = currentDrawable.bitmap

                // Now you can use the 'bitmap' variable for further processing
                // For example, you can pass it to a function for image processing
                processImage(bitmap)
            }
        }

        ImageView.setOnClickListener(){
            ImageView.setImageResource(R.drawable.ic_launcher_background)
        }

        iv1.setOnClickListener(){
            ImageView.setImageResource(R.drawable.tomato)

        }

        iv2.setOnClickListener(){
            ImageView.setImageResource(R.drawable.cheese)
        }

        iv3.setOnClickListener(){
            ImageView.setImageResource(R.drawable.salad)

        }





    }

    private fun processImage(bitmap: Bitmap) {
        Toast.makeText(applicationContext,"Function is Working",Toast.LENGTH_LONG).show()
    }
}