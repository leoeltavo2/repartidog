package com.comida.repartidog.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.comida.repartidog.R
import com.comida.repartidog.ui.comida.AdaptadorComida
import com.comida.repartidog.ui.comida.ListaComida


class HomeFragment : Fragment() {

  private var recyclerView: RecyclerView? = null
  private lateinit var adapter1: AdaptadorComida

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_home, container, false)

    val imageList = ArrayList<SlideModel>()

    imageList.add(SlideModel(R.drawable.camaron, "Parrillada de camarones a la barbacoa"))
    imageList.add(SlideModel(R.drawable.espaguetti, "Espaguetti con queso"))
    imageList.add(SlideModel(R.drawable.tacos_pastor, "Tacos al pastor"))
    imageList.add(SlideModel(R.drawable.filete, "Filete de res"))
    imageList.add(SlideModel(R.drawable.hamburguesa, "Hamburguesa"))
    imageList.add(SlideModel(R.drawable.pollo_bbq, "Pollo BBQ"))

    val imageSlider:ImageSlider = view.findViewById(R.id.slider)
    imageSlider.setImageList(imageList)

//    RECYCLERVIEW
    val list = ArrayList<ListaComida>()
    recyclerView = view.findViewById(R.id.recycler_productos)
    recyclerView?.setLayoutManager(LinearLayoutManager(context))

  list.add(ListaComida(R.drawable.fiekele,"Filete de Res","35","17","30-35 min", "Filete de Res al puro estilo Michoácan"))
  list.add(ListaComida(R.drawable.salmon_plancha,"Salmon a la Plancha","120","20","20-35 min", "mmmmmmmm salmoncito"))
  list.add(ListaComida(R.drawable.enchiladas_verdes,"Enchiladas Verdes","80","25","10-25 min", "la exquisites de las enchiladas"))
  list.add(ListaComida(R.drawable.filete_pechuga,"Filete de Pechuga de Pollo","75","15","10-25 min", "Los mejores cortes y con el mejor sabor"))
  list.add(ListaComida(R.drawable.kebab,"Kebab","35","10","10-15 min", "Lo mejor de lo mejor"))
  list.add(ListaComida(R.drawable.pollo_frito,"Pollo Frito","70","16","20-25 min", "Crujiente pariente"))
  list.add(ListaComida(R.drawable.pastel_galleta,"Pastel de Galleta","35","12","10-15 min", "El pastel que comen los dioses"))
  list.add(ListaComida(R.drawable.zopes,"Zopes","25","15","10-15 min", "Quieres unos zopes? te los damos"))

    adapter1 = AdaptadorComida(list)
    recyclerView?.adapter = adapter1
//    FIN RECYCLERVIEW
    return view
  }

}
