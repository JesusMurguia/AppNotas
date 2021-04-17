package murguia.jesus.appnotas

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_agregar_notas.*
import kotlinx.android.synthetic.main.nota_layout.*
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.util.jar.Manifest

class AgregarNotas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_notas)

        btn_guardar.setOnClickListener(){
            guardarNota()
        }
    }

    fun guardarNota(){
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),235)
        }else{
            guardar()
        }
    }

    fun guardar(){
        var titulo=editTextTextPersonName.text.toString()
        var contenido=editTextTextPersonName2.text.toString()
        if(titulo == "" || contenido== ""){
            Toast.makeText(this,"Campos vacios", Toast.LENGTH_SHORT).show()
        }else{
            try {
                var archivo=File(ubicacion(), "$titulo.txt")
                val fos=FileOutputStream(archivo)
                fos.write(contenido.toByteArray())
                fos.close()
                Toast.makeText(this,"Se guardo el archivo",Toast.LENGTH_SHORT).show()
            }catch (e: Exception){
                Toast.makeText(this,"No se guardo el archivo",Toast.LENGTH_SHORT).show()
            }
        }
        finish()
    }

    private fun ubicacion():String{
        var carpeta= File(getExternalFilesDir(null),"notas")
        if(!carpeta.exists()){
            carpeta.mkdir()
        }
        return carpeta.absolutePath
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            235 -> {
                if((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    guardar()
                }else{
                    Toast.makeText(this,"No se aceptaron los permisos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}