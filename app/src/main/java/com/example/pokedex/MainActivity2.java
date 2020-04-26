package com.example.pokedex;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends Activity {

    private static final String BASE_URL = "https://raw.githubusercontent.com";

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private android.content.SharedPreferences SharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokedex);

        SharedPreferences= getSharedPreferences("user_prefs", Context.MODE_PRIVATE);

        gson = new GsonBuilder()
                .setLenient()
                .create();
        List<Pokemon> pokemonList =dataExist();
        if(pokemonList !=null)
        {
            showList(pokemonList);
        }
        else  makeApiCall();

        //makeApiCall();


    }

    private List<Pokemon> dataExist()
    {
        String jsonPokemon= SharedPreferences.getString("jsonPokemonList", null);
        if (jsonPokemon==null){return null;}
        else{
            Type listType= new TypeToken<List<Pokemon>>(){}.getType();
            return gson.fromJson(jsonPokemon, listType);}

    }

    private void showList(List<Pokemon> PokemonList)
    {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        mAdapter = new ListAdapter(PokemonList);
        recyclerView.setAdapter(mAdapter);
    }


    private void makeApiCall()
    {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        IPokemonDex IPokemonDex = retrofit.create(IPokemonDex.class);

        Call<Pokedex> call = IPokemonDex.getPokemonResponse();
        call.enqueue(new Callback<Pokedex>() {
            @Override
            public void onResponse(Call<Pokedex> call, Response<Pokedex> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    List<Pokemon> pokemonList= response.body().getPokemon();/////////////
                    saveList(pokemonList);
                    showList(pokemonList);
                }
                else catchError();
            }

            @Override
            public void onFailure(Call<Pokedex> call, Throwable t) {
                catchError();
            }
        });

    }

    private void saveList(List<Pokemon> pokemonList) {
        String jsonString = gson.toJson(pokemonList);
        SharedPreferences
                .edit()
                .putString("jsonPokemonList", jsonString)
                .apply();
    }

    private void catchError()
    {
        Toast.makeText(getApplicationContext(), "ko", Toast.LENGTH_SHORT).show();
    }

}
