package com.example.dgpopfilms

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class ProfileScreen : AppCompatActivity() {


    lateinit var permissaoParaAcesso: AcessoCamera
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private val imagem by lazy { findViewById<ImageView>(R.id.profilePhoto) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_screen)

        initViews()
    }

    private fun initViews() {
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {

                Snackbar.make(button, "obrigado", Snackbar.LENGTH_INDEFINITE)
                    .setAction("fechar")
                    {
                        Toast.makeText(this, "tudo certo!", Toast.LENGTH_LONG).show()
                    }
                    .show()
            }

        var titleName = findViewById<TextView>(R.id.titleName)
        var nomeSobrenome = findViewById<TextView>(R.id.nomeSobrenome_input)

        titleName = nomeSobrenome


        }

    override fun onStart() {
        super.onStart()

        if(perdirPermissaoAoUsuario()) {
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Alerta de utilização do sistema")
                .setMessage("Vá até as suas configurações para dar as permissões")
                .setPositiveButton("Obrigado!"){dialog, _ -> dialog.dismiss() }.show()
        }
    }

    private fun perdirPermissaoAoUsuario(): Boolean = sharedPreferencesHelper.pegarAlertaAoIniciar()

    private fun botaoClicado() {
        val botaoFoto = findViewById<FloatingActionButton>(R.id.botao_foto)

        botaoFoto.setOnClickListener {
            val permissao = Manifest.permission.CAMERA
            requerimentoDeCamera(permissao)
            abrirGaleria()
        }

    }

    private fun abrirGaleria(){
        val listaIntents = mutableListOf<Intent>()
        val tirarFotoDaIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val escolhaIntent = Intent()
        escolhaIntent.type = "image/*"
        escolhaIntent.action = Intent.ACTION_GET_CONTENT

        listaIntents.add(escolhaIntent)
        listaIntents.add(tirarFotoDaIntent)

        val galeriaIntent = Intent.createChooser(listaIntents[0], "Escolha como tirar a foto: ")
        galeriaIntent.putExtra(
            Intent.EXTRA_INITIAL_INTENTS,
            listaIntents.toTypedArray()
        )
        startActivityForResult(galeriaIntent, 200)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 200 && resultCode == Activity.RESULT_OK && intent?.data !=null){
            val foto = intent.data as Uri
            imagem.setImageURI(foto)
        } else if(intent?.extras != null) {
            val foto = intent.extras?.get("data") as Bitmap
            imagem.setImageBitmap(foto)
        }
    }

    private fun requerimentoDeCamera(permissao: String){
        permissaoParaAcesso.requerimentoDeCamera(permissao)
    }

    private fun requerimentoDeGaleria(){
        val permissoes =listOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
        permissaoParaAcesso.requerimentoDeGaleria(permissoes)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissaoParaAcesso.resultadoPedidoDePermissao(
            requestCode,
            permissions,
            grantResults
        )
    }

}
