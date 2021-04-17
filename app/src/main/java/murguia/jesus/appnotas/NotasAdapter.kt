package murguia.jesus.appnotas

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.nota_layout.view.*

class NotasAdapter: BaseAdapter{
    var notas= ArrayList<Nota>()
    var contexto: Context? =null
    constructor(contexto: Context, notas: ArrayList<Nota>){
        this.contexto=contexto
        this.notas=notas
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var nota=notas[p0]
        var inflador=contexto!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var vista=inflador.inflate(R.layout.nota_layout, null)
        vista.tv_Nombre.setText(nota.titulo)
        vista.tv_contenido.setText(nota.contenido)



        return vista
    }

    override fun getItem(p0: Int): Any {
        return notas[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return notas.size
    }
}