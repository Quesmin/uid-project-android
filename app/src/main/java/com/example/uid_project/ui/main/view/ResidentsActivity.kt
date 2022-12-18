package com.example.uid_project.ui.main.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uid_project.R
import com.example.uid_project.data.api.ApiService
import com.example.uid_project.ui.base.ViewModelFactory
import com.example.uid_project.ui.main.adapters.model.MainListItemPlanet
import com.example.uid_project.ui.main.adapters.model.ResidentListItemData
import com.example.uid_project.ui.main.adapters.ResidentsAdapter
import com.example.uid_project.ui.main.viewmodel.ResidentsViewModel
import com.example.uid_project.utils.Status


class ResidentsActivity : AppCompatActivity() {


    //UI
    private lateinit var residentsListView: RecyclerView;
    private lateinit var progressBar: ProgressBar;
    private lateinit var noResidentsLabel: TextView;


    //UI-Data
    private lateinit var residentsIdsArray: Array<Int>
    private val mainList = mutableListOf<MainListItemPlanet>() as ArrayList<MainListItemPlanet>


    //BL
    private lateinit var viewModel: ResidentsViewModel
    private lateinit var residentListAdapter: ResidentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_residents)

//        val residents = intent.getStringArrayExtra("residents");
        residentsIdsArray = intent.getStringArrayExtra("residents")
            ?.let { getIdArrayFromResidentsStringUrls(it) }!!

//        val parsed = residents?.get(0)?.split("/").

//        Log.e("s a tras: ", parsed.toString())



        setupViewModel();
        setupUI();
        setupObservers()

//
//        mainListAdapter = MainListAdapter(this, mainList)
//        mainListView.adapter = mainListAdapter
//        fetchInitialPlanetsList();


//        setListeners();
    }

    private fun getIdFromUrlStringResident(url: String): Int {
        val urlStringElements = url.split("/");
        val id = urlStringElements.get(urlStringElements.size - 2).toInt()

        return id
    }

    private fun getIdArrayFromResidentsStringUrls(urlArray: Array<String>): Array<Int>{

        var idArray: Array<Int> = arrayOf()
        urlArray.forEach {
            val parsedId = getIdFromUrlStringResident(it)
            idArray += parsedId
        }

        return idArray;
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

//        residentListAdapter = ResidentsAdapter(arrayListOf())
//        residentsListView.adapter = residentListAdapter

        residentsListView.layoutManager = LinearLayoutManager(this)
        residentListAdapter = ResidentsAdapter(arrayListOf())
        residentsListView.addItemDecoration(
            DividerItemDecoration(
                residentsListView.context,
                (residentsListView.layoutManager as LinearLayoutManager).orientation
            )
        )
        residentsListView.adapter = residentListAdapter
    }

//    @SuppressLint("CheckResult")
//    private fun fetchInitialPlanetsList() {
//        mainActivityLoader.visibility = View.VISIBLE;
//        mainListView.visibility = View.INVISIBLE
//
//        CoroutineScope(Dispatchers.IO).launch {
//
//            var call = ApiService.planetApi.getPlanetById(1)
//
//
//            for (i in START_PLANET_ID..END_PLANET_ID) {
//
////            var newMutableListRequest = requests.toMutableList()
////                newMutableListRequest.add(ApiService.planetApi.getPlanetById(i))
////
////            requests = newMutableListRequest.toTypedArray()
////            requests.add(ApiService.planetApi.getPlanetById(i))
//
//                var call = async { ApiService.planetApi.getPlanetById(i) }
//
//
//            }
//
//        }
//
//
////        var requests =
//
//
//
//        Observable.zip(requests) {
//            val planetsResponse = it as Array<*>
//            planetsResponse.forEach {
//                val planetResponse = it as PlanetData
//
//                val parsedDrawablePlanetName =
//                    planetResponse.name.replace("\\s".toRegex(), "_").lowercase();
//
//                val drawableId =
//                    resources.getIdentifier(parsedDrawablePlanetName, "drawable", packageName)
//
//                ActivityCompat.getDrawable(this@MainActivity, drawableId)?.let {
//                    MainListItemPlanet(
//                        planetResponse.name,
//                        planetResponse.population,
//                        it
//                    )
//                }?.let {
//                    mainList.add(it)
//
//                    Log.d("MainActivity", "s a adaugat un letz !!!!!!!!!!!!!!!" + it.toString())
//                }
//            }
//
//            mainActivityLoader.visibility = View.INVISIBLE;
//            mainListView.visibility = View.VISIBLE
//            mainListAdapter.notifyDataSetChanged()
//
//
//        }
//            .subscribe({
//                Log.d("MainActivity", "s-a completat fetchu")
//            }) {
//                Log.d("MainActivity", "s-a pisat fetchu: $it")
//            }
//
//
////        for (i in 1 .. MAX_PLANET_LIST){
////            ApiService.planetApi.getPlanetById(i).enqueue( object: Callback<GetPlanetWithIdResponse>
////            {
////                override fun onResponse (call: Call<GetPlanetWithIdResponse>, response: Response<GetPlanetWithIdResponse>)
////                {
////                    if ( !response.isSuccessful )
////                    {
////
////                        Log.d("MainActivity", "onListFetch: " + response.errorBody()!!.string())
////                    }
////                    else
////                    {
////                        // received data is available in as a Kotlin object of type User
////                        val planetResponse: GetPlanetWithIdResponse = response.body()!!
////
////                        val parsedDrawablePlanetName = planetResponse.name.replace("\\s".toRegex(), "_").lowercase();
////
////                        val drawableId = resources.getIdentifier(parsedDrawablePlanetName, "drawable", packageName)
////
////                        ActivityCompat.getDrawable(this@MainActivity, drawableId)?.let {
////                            MainListItemPlanet(
////                                planetResponse.name,
////                                planetResponse.population,
////                                it
////                            )
////                        }?.let {
////                            mainList.add(it)
////                            mainListAdapter.notifyDataSetChanged()
////                            Log.d("MainActivity", "s a adaugat un letz !!!!!!!!!!!!!!!"+ it.toString())
////                        }
////
////                    }
////
////                }
////                override fun onFailure( call: Call<GetPlanetWithIdResponse>, t: Throwable )
////                {
////                    // code to be executed on connection or processing failure
////                }
////            });
////
////        }
//    }

    private fun setupObservers() {

        Log.e(" id arr: ", residentsIdsArray.toString())
        viewModel.getResidentsFromIdArray(residentsIdsArray).observe(this, Observer {
            it?.let { resource ->
                Log.e("status ", resource.toString())
                when (resource.status) {
                    Status.SUCCESS -> {
                        residentsListView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        noResidentsLabel.visibility = View.GONE
                        resource.data?.let { residents ->
                            if (residents.size == 0) {
                                noResidentsLabel.visibility = View.VISIBLE
                                residentsListView.visibility = View.GONE
                            }
                            retrieveMainList(residents)
                        }
                    }
                    Status.ERROR -> {
                        residentsListView.visibility = View.GONE
                        progressBar.visibility = View.GONE
                        noResidentsLabel.visibility = View.VISIBLE
                        Log.e("error: ", it.message.toString())
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        residentsListView.visibility = View.GONE
                        noResidentsLabel.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(ApiService)
        )[ResidentsViewModel::class.java]
    }

    private fun retrieveMainList(residents: List<ResidentListItemData>) {
        residentListAdapter.apply {
            setResidents(residents as ArrayList<ResidentListItemData>)
            notifyDataSetChanged()
        }
    }

//    private fun setListeners() {
//        mainListView.setOnItemClickListener(this)
//    }

    private fun initializeViews() {
        residentsListView = findViewById(R.id.residentsListView);
        progressBar = findViewById(R.id.residentProgressBar)
        noResidentsLabel = findViewById(R.id.residentUnavailableDataLabel)
    }

//    private fun initializeBookingsList() {
//        mainList.clear()
//        for (index in resources.getStringArray(R.array.planet_names).indices) {
//            getDrawable(
//                resources.obtainTypedArray(R.array.planet_images).getResourceId(index, 0)
//            )?.let {
//                MainListItemPlanet(
//                    resources.getStringArray(R.array.planet_names)[index],
//                    resources.getStringArray(R.array.planet_population)[index],
//                    it
//                )
//            }?.let {
//                mainList.add(
//                    it
//                )
//            }
//        }
//    }

//    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
////        TODO("Not yet implemented")
//    }
}