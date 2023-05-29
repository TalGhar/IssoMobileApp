package com.example.issomobileapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.issomobileapp.utils.FileUtils
import com.github.barteksc.pdfviewer.PDFView

class DeclarationViewActivity: AppCompatActivity() {

    private lateinit var pdfView: PDFView
    private lateinit var close: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_declaration)
        pdfView = findViewById(R.id.pdfView)
        close = findViewById(R.id.closeDeclaration)
        close.setOnClickListener { finish() }
        checkPdfAction(intent)
    }

    private fun showPdfFromAssets(pdfName: String) {
        pdfView.fromAsset(pdfName)
            .password(null)
            .defaultPage(0)
            .onPageError { page, _ ->
                Toast.makeText(
                    this@DeclarationViewActivity,
                    "Error at page: $page", Toast.LENGTH_LONG
                ).show()
            }
            .load()
    }

    private fun checkPdfAction(intent: Intent) {
        when (intent.getStringExtra("ViewType")) {
            "assets" -> {
                showPdfFromAssets(FileUtils.getPdfNameFromAssets())
            }
            "storage" -> {

            }
            "internet" -> {

            }
        }
    }

}