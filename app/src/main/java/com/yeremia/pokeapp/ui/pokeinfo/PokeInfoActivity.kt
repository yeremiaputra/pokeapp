package com.yeremia.pokeapp.ui.pokeinfo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yeremia.pokeapp.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_pokeinfo.*

class PokeInfoActivity : AppCompatActivity() {

    lateinit var viewModel: PokeInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokeinfo)

        viewModel = ViewModelProvider(this).get(PokeInfoViewModel::class.java)

        initUI()
    }

    private fun initUI(){
        val id = intent.extras?.get("id") as Int

        viewModel.getPokemonInfo(id)

        viewModel.pokemonInfo.observe(this, Observer { pokemon ->
            nameTextView.text = pokemon.name
            heightText.text = "height: ${pokemon.height/10.0}m"
            weightText.text = "weight: ${pokemon.weight/10.0}"
            typeText.text = "type: ${pokemon.natural_gift_type}"
            descriptionText.text = "description: ${pokemon.description}"
            Log.d("USERYERE","${pokemon.height}")
            Log.d("USERYERE","${pokemon.description}")
            Glide.with(this).load(pokemon.sprites.frontDefault).into(imageView)
        })
    }
}