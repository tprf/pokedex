package com.example.pokedex.modele;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IPokemonDex {

    @GET("/Biuni/PokemonGO-Pokedex/master/pokedex.json")
    Call<Pokedex> getPokemonResponse();

}
