package com.comida.repartidog.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
import com.comida.repartidog.R
import com.comida.repartidog.ui.adaptador.ViewHolder
import com.comida.repartidog.ui.usuario.user


class adaptador(list: ArrayList<user>) : RecyclerView.Adapter<ViewHolder>() {

    private val list: List<user>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.usuarios, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nombre.text= list[position].username.toString()
       // holder.apellido.text= list[position].apellido.toString()
        holder.email.text= list[position].email.toString()


    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
         var nombre: TextView
        // var apellido: TextView
         var email: TextView

        init {
            nombre=itemview.findViewById(R.id.nombree)
           // apellido=itemview.findViewById(R.id.nombre_usuario)
            email=itemview.findViewById(R.id.emaillll)


        }
    }


    init {
        this.list = list
    }


}