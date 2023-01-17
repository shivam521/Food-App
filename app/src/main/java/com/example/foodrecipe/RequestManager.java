package com.example.foodrecipe;

import android.content.Context;

import com.example.foodrecipe.Listeners.InstructionsListener;
import com.example.foodrecipe.Listeners.RandomRecipeResponseListener;
import com.example.foodrecipe.Listeners.RecipeDetailsListener;
import com.example.foodrecipe.Listeners.SimilarRecipeListener;
import com.example.foodrecipe.Models.InstructionsResponse;
import com.example.foodrecipe.Models.RandomRecipeApiResponse;
import com.example.foodrecipe.Models.RecipeDetailsResponse;
import com.example.foodrecipe.Models.SimilarRecipeResponse;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {



    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getRandomRecipes(RandomRecipeResponseListener listener, List<String> tags){

        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
        Call<RandomRecipeApiResponse> call = callRandomRecipes.callRandomRecipe(context.getString(R.string.api_key),"10", tags);

        call.enqueue(new Callback<RandomRecipeApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }

                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipeApiResponse> call, Throwable t) {

                listener.didError(t.getMessage());
            }
        });
    }

    public void getRecipeDetails(RecipeDetailsListener listener, int id){
        CallRecipeDertails callRecipeDertails = retrofit.create(CallRecipeDertails.class);
        Call<RecipeDetailsResponse> call = callRecipeDertails.callRecipeDetails(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeDetailsResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailsResponse> call, Response<RecipeDetailsResponse> response) {

                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }

                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailsResponse> call, Throwable t) {

                listener.didError(t.getMessage());
            }
        });
    }

    public void getSimilarRecipes(SimilarRecipeListener listener, int id){

        CallsimilarRecipe callsimilarRecipe = retrofit.create(CallsimilarRecipe.class);
        Call<List<SimilarRecipeResponse>> call = callsimilarRecipe.callSimilarRecipe(id,"4", context.getString(R.string.api_key));
        call.enqueue(new Callback<List<SimilarRecipeResponse>>() {
            @Override
            public void onResponse(Call<List<SimilarRecipeResponse>> call, Response<List<SimilarRecipeResponse>> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;

                }

                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<SimilarRecipeResponse>> call, Throwable t) {

                listener.didError(t.getMessage());

            }
        });

    }

    public void getInstructions(InstructionsListener listener, int id){

        CallInstructions callInstructions = retrofit.create(CallInstructions.class);
        Call<List<InstructionsResponse>> call = callInstructions.callInstruction(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<List<InstructionsResponse>>() {
            @Override
            public void onResponse(Call<List<InstructionsResponse>> call, Response<List<InstructionsResponse>> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }

                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<InstructionsResponse>> call, Throwable t) {

                listener.didError(t.getMessage());
            }
        });
    }

    private interface CallRandomRecipes{
        @GET("recipes/random")

        Call<RandomRecipeApiResponse> callRandomRecipe(

                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("tags")List<String> tags
                );


    }


    private interface CallRecipeDertails{
        @GET("recipes/{id}/information")

        Call<RecipeDetailsResponse> callRecipeDetails(
            @Path("id") int id,
                    @Query("apiKey") String apiKey
        );
    }

    private interface CallsimilarRecipe{

        @GET("recipes/{id}/similar")

        Call<List<SimilarRecipeResponse>> callSimilarRecipe(

                @Path("id") int id,
                @Query("number") String number,
                @Query("apiKey") String apiKey

        );

    }

    private interface CallInstructions{

        @GET("recipes/{id}/analyzedInstructions")

        Call<List<InstructionsResponse>> callInstruction(

                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }
}
