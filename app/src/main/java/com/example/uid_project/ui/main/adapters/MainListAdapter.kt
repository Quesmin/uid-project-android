package com.example.uid_project.ui.main.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.uid_project.R
import com.example.uid_project.data.models.PlanetResponseData
import com.example.uid_project.ui.main.adapters.model.MainListItemPlanet
import com.example.uid_project.ui.main.adapters.model.PlanetActivityData
import com.example.uid_project.ui.main.mappers.MainMapper
import com.example.uid_project.ui.main.view.PlanetActivity

class MainListAdapter(private val dataList: ArrayList<MainListItemPlanet>) : BaseAdapter() {

    override fun getCount(): Int {
        return dataList.size;
    }

    override fun getItem(position: Int): Any {
        return dataList.get(position);
    }

    override fun getItemId(position: Int): Long {
        return position.toLong();
    }

    override fun getView(position: Int, containerView: View?, viewGroupParent: ViewGroup): View {
        val currentContext = viewGroupParent.context
        val rowView = containerView ?: LayoutInflater.from(currentContext)
            .inflate(R.layout.main_list_item, viewGroupParent, false)

        val currentDataItem = dataList.get(position)


        val parsedDrawablePlanetName =
            currentDataItem.name.replace("\\s".toRegex(), "_").lowercase();
        val drawableId = currentContext.resources.getIdentifier(
            parsedDrawablePlanetName,
            "drawable",
            currentContext.packageName
        )
        val currentDrawable = ActivityCompat.getDrawable(currentContext, drawableId);
        val mainListItemFavButton = rowView.findViewById<Button>(R.id.mainListItemFavButton)
        val favButtonBackground =
            if (currentDataItem.isFavorite) ContextCompat.getDrawable(
                currentContext,
                R.drawable.fav_star_yellow
            ) else ContextCompat.getDrawable(
                currentContext,
                R.drawable.fav_star_black
            )


        rowView.background = currentDrawable;
        rowView.findViewById<TextView>(R.id.mainListItemTitle).text = currentDataItem.name;
        rowView.findViewById<TextView>(R.id.mainListItemPopulation).text =
            currentDataItem.population;
        mainListItemFavButton.background = favButtonBackground
        mainListItemFavButton.setOnClickListener {
            currentDataItem.isFavorite = !currentDataItem.isFavorite
            this.notifyDataSetChanged()
        }

        rowView.setOnClickListener {
            val newIntentDataItem =
                MainMapper.fromPlanetMainItemToPlanetActivityData(
                    currentDataItem,
                    drawableId
                )

            onItemClick(newIntentDataItem, currentContext);
        }

        return rowView
    }

    private fun onItemClick(planetActivityData: PlanetActivityData, currentContext: Context) {
        val intent = Intent(currentContext, PlanetActivity::class.java)
        intent.putExtra("name", planetActivityData.name)
        intent.putExtra("climate", planetActivityData.climate)
        intent.putExtra("diameter", planetActivityData.diameter)
        intent.putExtra("terrain", planetActivityData.terrain)
        intent.putExtra("gravity", planetActivityData.gravity)
        intent.putExtra("population", planetActivityData.population)
        intent.putExtra("residents", planetActivityData.residents.toTypedArray())
        intent.putExtra("imageId", planetActivityData.imageId)

        currentContext.startActivity(intent)

    }

    fun setPlanets(planets: List<MainListItemPlanet>) {
        this.dataList.apply {
            clear()
            addAll(planets)
        }
    }

}