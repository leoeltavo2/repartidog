package com.comida.repartidog.ui


//import com.bumptech.glide.Glide
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.comida.repartidog.R
import com.comida.repartidog.ui.usuario.pedido


class adaptadorPedido(list: List<pedido>) : RecyclerView.Adapter<adaptadorPedido.ViewHolder>() {

    private val list: List<pedido>



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.pedidos, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: adaptadorPedido.ViewHolder, position: Int) {
        holder.nombre.text= list[position].nombre.toString()
        holder.cantidad.text= list[position].cantidad.toString()
//        holder.email.text= list[position].email.toString()
        holder.descripcion.text= list[position].descripcion.toString()
       holder.precio.text= list[position].precio.toString()
   holder.precioenvio.text= list[position].precioenvio.toString()
    holder.total.text= list[position].total.toString()


    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
         var nombre: TextView
//     var email: TextView
       var cantidad: TextView
      var descripcion: TextView
     var precio: TextView
     var precioenvio:TextView
       var total:TextView


        init {
            nombre=itemview.findViewById(R.id.nombre)
//           email=itemview.findViewById(R.id.text_emai)
          cantidad=itemview.findViewById(R.id.cantidadd)
            descripcion=itemview.findViewById(R.id.descrpcion)
           precio=itemview.findViewById(R.id.precioPro)
            precioenvio=itemview.findViewById(R.id.precioenio)
           total=itemview.findViewById(R.id.total)


        }
    }


    init {
        this.list = list
    }




}