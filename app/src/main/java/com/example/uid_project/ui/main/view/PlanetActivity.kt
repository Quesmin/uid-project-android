package com.example.uid_project.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.uid_project.R
import com.example.uid_project.data.api.ApiService
import com.example.uid_project.ui.base.ViewModelFactory
import com.example.uid_project.ui.main.adapters.MainListAdapter
import com.example.uid_project.ui.main.adapters.model.MainListItemPlanet
import com.example.uid_project.ui.main.viewmodel.MainViewModel

class PlanetActivity: AppCompatActivity() {

    //UI
    private lateinit var imageView: ImageView;
    private lateinit var name: TextView;
    private lateinit var population: TextView;
    private lateinit var terrain: TextView;
    private lateinit var gravity: TextView;
    private lateinit var diameter: TextView;
    private lateinit var climate: TextView;
    private lateinit var residentsButton: Button


    //UI-Data
    private lateinit var residentsArray: Array<String>
    private val mainList = mutableListOf<MainListItemPlanet>() as ArrayList<MainListItemPlanet>


    //BL
    private lateinit var viewModel: MainViewModel
    private lateinit var mainListAdapter: MainListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planet)

//        setupViewModel();
        setupUI();
//        setupObservers()

//
//        mainListAdapter = MainListAdapter(this, mainList)
//        mainListView.adapter = mainListAdapter
//        fetchInitialPlanetsList();


//        setListeners();
    }

    private fun initializeViews() {
        name = findViewById(R.id.planetNameTextValue);
        population = findViewById(R.id.planetPopulationTextValue);
        terrain = findViewById(R.id.planetTerrainTextValue);
        gravity = findViewById(R.id.planetGravityTextValue);
        diameter = findViewById(R.id.planetDiameterTextValue);
        climate = findViewById(R.id.planetClimateTextValue);
        residentsButton = findViewById(R.id.planetResidentsButton)
        imageView = findViewById(R.id.planetActivityImageView)

        val newName = intent.getStringExtra("name")
        val newClimate = intent.getStringExtra("climate")
        val newDiameter = intent.getStringExtra("diameter")
        val newTerrain = intent.getStringExtra("terrain")
        val newGravity = intent.getStringExtra("gravity")
        val newPopulation = intent.getStringExtra("population")
        val newResidents = intent.getStringArrayExtra("residents")
        val newImageId = intent.getIntExtra("imageId", 0)
        val currentDrawable = ContextCompat.getDrawable(this, newImageId)

        name.text = newName
        climate.text = newClimate
        diameter.text = newDiameter
        terrain.text = newTerrain
        gravity.text = newGravity
        population.text = newPopulation
        imageView.setImageDrawable(currentDrawable)
        if (newResidents != null) {
            residentsArray = newResidents
        }

    }

    private fun setupUI() {

        initializeViews();

//        recyclerView.layoutManager = LinearLayoutManager(this)
//        adapter = MainAdapter(arrayListOf())
//        recyclerView.addItemDecoration(
//            DividerItemDecoration(
//                recyclerView.context,
//                (recyclerView.layoutManager as LinearLayoutManager).orientation
//            )
//        )
//        recyclerView.adapter = adapter

//        mainListAdapter = MainListAdapter(arrayListOf())
//        mainListView.adapter = mainListAdapter

        residentsButton.setOnClickListener {
            val intent = Intent(this, ResidentsActivity::class.java)
            intent.putExtra("residents", residentsArray)
            startActivity(intent)
        }
    }



}