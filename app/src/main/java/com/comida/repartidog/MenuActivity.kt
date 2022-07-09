package com.comida.repartidog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.comida.repartidog.ui.adaptador
import com.comida.repartidog.ui.gallery.GalleryFragment
import com.comida.repartidog.ui.mapa.MapaActivity
import com.comida.repartidog.ui.usuario.pedido
import com.comida.repartidog.ui.usuario.sesiones
import com.comida.repartidog.ui.usuario.user
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_regis.*
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.nav_header_main.*

class MenuActivity : AppCompatActivity() {
    companion object {
        const val TAG = "KotlinActivity"
    }
    private lateinit var appBarConfiguration: AppBarConfiguration
    private  lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)




        var bundlee = intent.extras

        var correo = bundlee?.getString("email")

//        val fragment = GalleryFragment()
//          val bundle = Bundle()
//          //bundle.putString( correo.toString())
//          fragment.setArguments(bundle)
//        val args = Bundle()
//        args.putString("image", correo)

        val intent = Intent(this, GalleryFragment::class.java).apply {
            putExtra("email", correo)
        }

//insert
      // write("Hamburgesa","1",correo.toString(), "Hamburguesa con tocino","45","15","60")
       // motrar uuario
       basicReadWrite(correo.toString())
      // meter sesion

        NewUser( correo.toString())


        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            startActivity(Intent(this@MenuActivity, MapaActivity::class.java))

        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                 R.id.nav_gallery, R.id.nav_home,R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        CAMBIAR EL NOMBRE, CORREO E IMAGEN
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        val headerView: View = navigationView.getHeaderView(0)
       // val navUsername: TextView = headerView.findViewById(R.id.nombre_usuario)
      //  val navUserEmail : TextView = headerView.findViewById(R.id.correo_usuario)

//        navUsername.text = nombre
      //  navUserEmail.text = correo

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        when (id) {
            R.id.cerrar -> {
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this@MenuActivity, MainActivity::class.java))
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
// inert pedidos
    private  fun write(nombre:String, cantidad:String, email:String, descripcion:String, precio:String, precioenv:String, total:String){
        var mDatos = FirebaseDatabase.getInstance().getReference()
        mDatos.child("Pedidos").push().setValue(pedido(nombre,cantidad, descripcion, precio,precioenv, email,total))
    //email
    }
    //insert datos a el ususario

    private  fun writeNewUser(name:String, apellido:String, email:String){
        var mDatos = FirebaseDatabase.getInstance().getReference()
        mDatos.child("Usuario").push().setValue(user(name,apellido, email))
    }

    //insert datos de sesiones

    private  fun NewUser( email:String){
        var mDatos = FirebaseDatabase.getInstance().getReference()
        mDatos.child("Sesiones").push().setValue(sesiones(email))
    }

    /// leer datos usuario
    private fun basicReadWrite(correo:String) {
        val database = Firebase.database
        val myRef = database.getReference("Usuario")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val usuarios = ArrayList<user>()

                for (postSnaphot in dataSnapshot.children){
                    if(postSnaphot.child("email").getValue().toString()==correo) {
                        val nombre = postSnaphot.child("username").getValue().toString()
                        val email = postSnaphot.child("email").getValue().toString()
                        val apellido = postSnaphot.child("apellido").getValue().toString()
                        usuarios.add(user(nombre, email, apellido))
                    }
                }
                var f = findViewById< RecyclerView>(R.id.recy)
                f?.layoutManager= LinearLayoutManager(applicationContext)
                f?.adapter= adaptador(usuarios)
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }
//mostrar



    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val builder = android.app.AlertDialog.Builder(this)
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            builder.setTitle("SALIR")

            builder.setMessage("¿DESEA SALIR DE LA APLICACIÓN?")
            builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, which -> finish()
            })

            builder.setNegativeButton("Cancelar", null)
            val dialog: android.app.AlertDialog = builder.create()
            dialog.show()


        }
            return super.onKeyDown(keyCode, event)
    }


}