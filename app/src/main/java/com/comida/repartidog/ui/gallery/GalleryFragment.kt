package com.comida.repartidog.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.comida.repartidog.MenuActivity
import com.comida.repartidog.R
import com.comida.repartidog.ui.adaptadorPedido
import com.comida.repartidog.ui.usuario.pedido
import com.comida.repartidog.ui.usuario.sesiones
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class GalleryFragment : Fragment() {

  private lateinit var galleryViewModel: GalleryViewModel
  companion object {
    private const val TAG = "KotlinActivity"
  }

  private lateinit var appBarConfiguration: AppBarConfiguration
  private  lateinit var database: FirebaseDatabase
  private lateinit var reference: DatabaseReference
  private var b:String =""

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_gallery, container, false)


//    val fragment = GalleryFragment()
    // val bundle = Bundle()
//    bundle.putString("image", "mesa")
//    fragment.setArguments(bundle)
    //val texto: String? = requireArguments().getString("email" )


    basicReadWrite()









    return view
  }


  // mostrar el historial de pedidos del susuario

  private fun mostrarHistorial(correo: String) {

    val database = Firebase.database
    val myRefr = database.getReference("Pedidos")

    myRefr.addValueEventListener(object : ValueEventListener {
      override fun onDataChange(dataSnapshot: DataSnapshot) {
        val usuarioss = ArrayList<pedido>()

        for (postSnaphot in dataSnapshot.children) {

          if (postSnaphot.child("email").getValue().toString() == correo) {

            //Toast.makeText(context, "aiuda", Toast.LENGTH_SHORT).show()
            val nombre = postSnaphot.child("nombre").getValue().toString()
            val cantidad = postSnaphot.child("cantidad").getValue().toString()
            val descripcion = postSnaphot.child("descripcion").getValue().toString()
            val precio = postSnaphot.child("precio").getValue().toString()
            val precioenvio = postSnaphot.child("precioenvio").getValue().toString()
            val email = postSnaphot.child("email").getValue().toString()
            val total = postSnaphot.child("total").getValue().toString()
            //var total = ((Integer.parseInt(cantidad))*(Integer.parseInt(precio)))+Integer.parseInt(precioenvio)

            usuarioss.add(pedido(nombre, cantidad, descripcion, precio, precioenvio, email,total))
            //email
          }
        }
        var re = view?.findViewById<RecyclerView>(R.id.recycler)
       re?.layoutManager = LinearLayoutManager(context)
        re?.adapter = adaptadorPedido(usuarioss)
      }

      override fun onCancelled(error: DatabaseError) {
        // Failed to read value
        Log.w(MenuActivity.TAG, "Failed to read value.", error.toException())
      }
    })
  }
  ///



  /// sacar el ultimo
  private fun basicReadWrite(){
    val database = Firebase.database
    val myRef = database.getReference("Sesiones").endAt("email").limitToLast(1)
    myRef.addValueEventListener(object : ValueEventListener {

      override fun onDataChange(dataSnapshot: DataSnapshot) {
        val usuarioss = ArrayList<sesiones>()

        for (postSnaphot in dataSnapshot.children) {
          // if(postSnaphot.child("email").getValue().toString()==correo) {
          // val nombre = postSnaphot.child("username").getValue().toString()
          val email = postSnaphot.child("email").getValue().toString()
          //val apellido = postSnaphot.child("apellido").getValue().toString()
          println("hhhhhhhhhhhhhhhhhd"+email)
          b=email
          println("yyyyyyyyyyyyyyyyyyyyyyyyyy"+b)
          mostrarHistorial(b)
         //usuarioss.add(sesiones(email))
          //Toast.makeText(context,b+"ddd", Toast.LENGTH_SHORT).show()
          // }
        }
        //reli(usuarioss)
      }

      override fun onCancelled(error: DatabaseError) {
        // Failed to read value
        Log.w(MenuActivity.TAG, "Failed to read value.", error.toException())
      }
    })


  }


}