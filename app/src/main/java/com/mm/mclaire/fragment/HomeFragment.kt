package com.mm.mclaire.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mm.mclaire.activity.MealActivity
import com.mm.mclaire.adapters.CategoriesAdapter
import com.mm.mclaire.adapters.PopularMealsAdapter
import com.mm.mclaire.databinding.FragmentHomeBinding
import com.mm.mclaire.pojo.MealsByCategory
import com.mm.mclaire.pojo.Meal
import com.mm.mclaire.viewModel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvm: HomeViewModel
    private lateinit var randomMeal: Meal

    var popularItemsAdapter=PopularMealsAdapter()
    var categoryAdapter=CategoriesAdapter()

    companion object {
        const val MEAL_ID = "com.mm.mclaire.fragment.idMeal"
        const val MEAL_NAME = "com.mm.mclaire.fragment.nameMeal"
        const val MEAL_THUMB = "com.mm.mclaire.fragment.thumbMeal"
    }

    //fragment instantiated & is in CREATED state
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // homeMvvm = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        homeMvvm = ViewModelProviders.of(this)[HomeViewModel::class.java]
    }

    //inflate the fragments' layout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    //Bind specific views to properties by calling findViewById()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeMvvm.getRandomMeal()
        observeRandomMeal()
        onRandomMealClick()

        homeMvvm.getPopularMeals()
        observePopularMealsLiveData()
        onPopularItemClick()

        prepareCategoriesRecyclerView()
        homeMvvm.getCategories()
        observeCategoryLiveData()
    }

    //observeRandomMeal() preferred approach
    private fun observeRandomMeal() {
        homeMvvm.observeRandomMealLiveData().observe(viewLifecycleOwner ){ meal ->
            Glide.with(this@HomeFragment)
                .load(meal!!.strMealThumb)
                .into(binding.imgRandomMeal)
            this.randomMeal = meal
        }
    }

    private fun onRandomMealClick() {
        binding.randomMealCard.setOnClickListener() {
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_ID, randomMeal.idMeal)
            intent.putExtra(MEAL_NAME, randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB, randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observePopularMealsLiveData() {
        homeMvvm.observePopularMealsLiveData().observe(viewLifecycleOwner
        ) { mealList->
            //set mealList to adapter
            binding.recViewMealsPopular.apply {
                layoutManager=LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
                adapter=popularItemsAdapter

                //mealList in the lambda is a list but our function 'setMeals' in adapter takes an ArrayList --hence the need to cast
                popularItemsAdapter.setMeals(mealList= mealList as ArrayList<MealsByCategory>)
            }
        }
    }

    private fun onPopularItemClick() {
        popularItemsAdapter.onItemClick =  {meal ->
            val intent=Intent(activity,MealActivity::class.java)
            intent.putExtra(MEAL_ID, meal.idMeal)
            intent.putExtra(MEAL_NAME, meal.strMeal)
            intent.putExtra(MEAL_THUMB, meal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun prepareCategoriesRecyclerView() {

        binding.recViewCategory.apply {
            categoryAdapter= CategoriesAdapter()
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false
            )
        }
        binding.recViewCategory.adapter = categoryAdapter;
    }
    //LIVEDATA
    /**
    -Data which we fetch from server can be LiveData
    -We sent it to our HomeFragment where it can be observed
    -Changes in data that is in the server/updates that take place will be observed and will be reflected
    automatically on the activity
    -We convert data to livedata and add observer to it
    -Using livedata with view-models ensures that livedata-objects updates the UI in the viewmodel
    thus the lifecycleOwners/UI Controllers will only be responsible of displaying data
    -Here WE ARE ADDING AN OBSERVER TO THE LIVEDATA
    -We attach observer object to Livedata using Observer fn
    -Observer fn takes 2 params:
    1. LIFECYCLE OWNER
    2. OBSERVER Interface
     */
    private fun observeCategoryLiveData() {
        homeMvvm.observeCategoryLiveData().observe(viewLifecycleOwner, Observer { categories->
            categoryAdapter.setCategoryList(categories)
        })
    }
}

// .................................................................................................
// categories.forEach{category ->
//               Log.d("CATEGORY TEST", category.strCategory)}

//..................................................................................................

//observeRandomMeal() Version 1

    //listen to randomMealLiveData in this function
    /**
    private fun observeRandomMeal() {
        homeMvvm.observeRandomMealLiveData().observe(viewLifecycleOwner, object : Observer<Meal>{
            override fun onChanged(t: Meal?) {
                        Glide.with(this@HomeFragment)
                            .load(t!!.strMealThumb)
                            .into(binding.imgRandomMeal)
            }
        })
    }**/

    //observeRandomMeal() Version 2
/**
    private fun observeRandomMeal() {
    homeMvvm.observeRandomMealLiveData().observe(viewLifecycleOwner,
    { meal ->
    Glide.with(this@HomeFragment)
    .load(meal!!.strMealThumb)
    .into(binding.imgRandomMeal)
    this.randomMeal=meal
    })
    }

**/
