package com.comida.repartidog.ui.vista

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.comida.repartidog.MenuActivity
import com.comida.repartidog.R
import com.comida.repartidog.ui.adaptador
import com.comida.repartidog.ui.comida.ListaComida
import com.comida.repartidog.ui.usuario.pedido
import com.comida.repartidog.ui.usuario.sesiones
import com.comida.repartidog.ui.usuario.user
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_vista_comida.*

class VistaComidaActivity : AppCompatActivity() {

    private lateinit var imagenProducto: ImageView
    private lateinit var nombreP: TextView
    private lateinit var descripcion: TextView
    private lateinit var precio: TextView
    private lateinit var precioEnv: TextView
    private var totalF: Int? = null


    private lateinit var listaE: ListaComida

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_comida)

        var bundlee = intent.extras

        //var correo = ""


       // Toast.makeText(this, varible(correo), Toast.LENGTH_LONG).show()


//        cantidad.setText("1")



        btnPedir.setOnClickListener {
            if (cantidad.text.toString() == "0" || cantidad.text.isEmpty()) {
                Toast.makeText(
                    this,
                    "FAVOR DE INTRODUCIR LA CANTIDAD CORRECTAMENTE",
                    Toast.LENGTH_LONG
                ).show()
            } else {

            totalF = ((Integer.parseInt(cantidad.text.toString()))*(Integer.parseInt(precio.text.toString())))+Integer.parseInt(precioEnv.text.toString())
            total.setText(totalF.toString())
                basicReadWrite()

                onBackPressed()
                  //startActivity(Intent(this@VistaComidaActivity, MenuActivity::class.java))

            }
        }
        initViews()
        initValues()
    }

    fun initViews() {
        imagenProducto = findViewById(R.id.imgProductoPedido)
        nombreP = findViewById(R.id.comidaPedido)
        descripcion = findViewById(R.id.descripcionPedido)
        precio = findViewById(R.id.precioPedido)
        precioEnv = findViewById(R.id.precioEnvioPedido)
    }

    fun initValues() {

        listaE = (intent.extras?.getSerializable("comida") as ListaComida?)!!
        listaE.getImagen()?.let { imagenProducto.setImageResource(it) }
        nombreP.setText(listaE.getNombre_comida())
        descripcion.setText(listaE.getDescripcion())
        precio.setText(listaE.getPrecio())
        precioEnv.setText(listaE.getPrecio_envio())

    }

    //    XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    private fun write(
        nombre: String,
        cantidad: String,
        email: String,
        descripcion: String,
        precio: String,
        precioenv: String,
        total: String
    ) {
        var mDatos = FirebaseDatabase.getInstance().getReference()
        mDatos.child("Pedidos").push().setValue(
            pedido(
                nombre,
                cantidad,
                descripcion,
                precio,
                precioenv,
                email,
                total
            )
        )
    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
    private fun basicReadWrite(correo: String) {

        val database = Firebase.database
        val myRef = database.getReference("Usuario")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val usuarios = ArrayList<user>()
                for (postSnaphot in dataSnapshot.children) {
                    if (postSnaphot.child("email").getValue().toString() == correo) {
                        val nombre = postSnaphot.child("username").getValue().toString()
                        val email = postSnaphot.child("email").getValue().toString()
                        val apellido = postSnaphot.child("apellido").getValue().toString()

                        usuarios.add(user(nombre, email, apellido))
                    }
                }

//                recy.layoutManager = LinearLayoutManager(this@VistaComidaActivity)
//                recy.adapter = adaptador(usuarios)
                var f = findViewById<RecyclerView>(R.id.recy)
                f?.layoutManager = LinearLayoutManager(applicationContext)
                f?.adapter = adaptador(usuarios)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(MenuActivity.TAG, "Failed to read value.", error.toException())
            }
        })
    }


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

                   // b=email
                   println("yyyyyyyyyyyyyyyyyyyyyyyyyy"+email)
                    varible(email)
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
   private fun varible (a:String){
       println("eta aquiiiiiiiiiiiiiiiiiiiid"+a)
       var totalF = ((Integer.parseInt(cantidad.text.toString()))*(Integer.parseInt(precio.text.toString())))+Integer.parseInt(precioEnv.text.toString())
       write(
           comidaPedido.text.toString(),
           cantidad.text.toString(),
           a.toString(),
           descripcion.text.toString(),
           precioPedido.text.toString(),
           precioEnvioPedido.text.toString(),
           totalF.toString()
       )

       basicReadWrite(a)
    }

}