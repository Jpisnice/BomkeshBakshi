package com.example.detective

import androidx.appcompat.app.AppCompatActivity
import android.Manifest
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val ImageViewmain: ImageView by lazy { findViewById(R.id.imageView) }
    private val READ_EXTERNAL_STORAGE_PERMISSION_CODE = 123
    private val PICK_IMAGE_REQUEST = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val detect: Button? = findViewById(R.id.detect)
        val pick: Button? = findViewById(R.id.click)

        val iv1: ImageView = findViewById(R.id.imageView1)
        val iv2: ImageView = findViewById(R.id.imageView2)
        val iv3: ImageView = findViewById(R.id.imageView3)

        detect?.setOnClickListener {
            val currentDrawable = ImageViewmain.drawable

            // Check if it's a BitmapDrawable
            if (currentDrawable is BitmapDrawable) {
                val bitmap: Bitmap = currentDrawable.bitmap

                // Now you can use the 'bitmap' variable for further processing
                // For example, you can pass it to a function for image processing
                processImage(bitmap)
            }
        }
        pick?.setOnClickListener(){
            pickImageFromGallery()
        }

        ImageViewmain.setOnClickListener(){
            ImageViewmain.setImageResource(R.drawable.ic_launcher_background)
        }

        iv1.setOnClickListener(){
            ImageViewmain.setImageResource(R.drawable.tomato)

        }

        iv2.setOnClickListener(){
            ImageViewmain.setImageResource(R.drawable.cheese)
        }

        iv3.setOnClickListener(){
            ImageViewmain.setImageResource(R.drawable.salad)

        }

    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                READ_EXTERNAL_STORAGE_PERMISSION_CODE
            )
        }
    }

    private fun processImage(bitmap: Bitmap) {
        Toast.makeText(applicationContext, "Function is Working", Toast.LENGTH_LONG).show()
    }

    private fun pickImageFromGallery() {
        // Create an Intent to pick an image from the gallery
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            // Get the selected image URI
            val imageUri = data.data

            // Decode the URI into a bitmap
            val bitmap: Bitmap? = try {
                BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri!!))
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }

            // Check if the ImageView is not null before setting the bitmap
            if (bitmap != null) {
                ImageViewmain.setImageBitmap(bitmap)
            } else {
                Toast.makeText(this, "Error loading image", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == READ_EXTERNAL_STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, you can proceed with the image picking
            } else {
                Toast.makeText(
                    this,
                    "Permission denied. Cannot pick images without permission.",
                    Toast.LENGTH_SHORT
                ).show()
                finish() // You might want to finish the activity or handle this situation accordingly
            }
        }
    }

}