package com.comida.repartidog.ui.comida

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.comida.repartidog.R
import com.comida.repartidog.ui.vista.VistaComidaActivity

class AdaptadorComida (list: List<ListaComida>) : RecyclerView.Adapter<AdaptadorComida.Holder>() {
    private val list: List<ListaComida>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorComida.Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.lista_comida, parent, false)
        return AdaptadorComida.Holder(view)
    }

    override fun onBindViewHolder(holder: AdaptadorComida.Holder, position: Int) {
        val comida: ListaComida = list[position]

        Glide.with(holder.imagen).load(comida.getImagen()).into(holder.imagen)
        holder.nombreComida.setText(comida.getNombre_comida())
        holder.tiempo.setText(comida.getTiempo_envio())
       holder.precio.setText(comida.getPrecio())
        holder.precio_envio.setText(comida.getPrecio_envio())

        holder.itemView.setOnClickListener {
            var intent: Intent = Intent(holder.itemView.context, VistaComidaActivity::class.java)
            intent.putExtra("comida", comida)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }

    class Holder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val imagen: ImageView
        val nombreComida: TextView
        val tiempo: TextView
        val precio: TextView
        val precio_envio: TextView


        init {
            imagen = itemview.findViewById(R.id.imgProducto)
            nombreComida = itemview.findViewById(R.id.comida)
            tiempo = itemview.findViewById(R.id.tiempo_envio)
            precio = itemview.findViewById(R.id.precio)
            precio_envio = itemview.findViewById(R.id.costo_envio)

        }
    }

    init {
        this.list = list
    }
}