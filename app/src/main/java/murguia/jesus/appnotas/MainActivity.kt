package murguia.jesus.appnotas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*

class MainActivity : AppCompatActivity() {
    var notas=ArrayList<Nota>()
    lateinit var adapter: NotasAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        adapter= NotasAdapter(this,notas)
        lv_notas.adapter=adapter

        btn_add.setOnClickListener(){
            var intent= Intent(this,AgregarNotas::class.java)
            startActivityForResult(intent,123)
        }

        leerNotas()
    }

    fun cargarNotas(){
        notas.add(Nota("nota 1","contenido1"))
        notas.add(Nota("nota 2","contenido2"))
        notas.add(Nota("nota 3","contenido3"))
        notas.add(Nota("nota 4","contenido4"))
    }

    fun leerNotas(){
        notas.clear()
        var carpeta = File(ubicacion().absolutePath)

        if (carpeta.exists()){
             var archivos=carpeta.listFiles()
            if(archivos!=null){
                for(archivo in archivos){
                    leerArchivo(archivo)
                }
            }
        }
    }

    fun leerArchivo(archivo:File){
        val fis = FileInputStream(archivo)
        val di = DataInputStream(fis)
        val br = BufferedReader(InputStreamReader(di))
        var strLine: String? = br.readLine()
        var myData = ""
        while (strLine != null ) {
            myData = myData + strLine
            strLine = br.readLine()
        }
        br.close()
        di.close()
        fis.close()

        var nombre = archivo.name.substring(0, archivo.name.length-4)
        var nota = Nota(nombre,myData)
        notas.add(nota)
        }


    private fun ubicacion():File{
        var carpeta= File(getExternalFilesDir(null),"notas")
        if(!carpeta.exists()){
            carpeta.mkdir()
        }
        return carpeta
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==123){
            leerNotas()
            adapter.notifyDataSetChanged()
        }
    }

}