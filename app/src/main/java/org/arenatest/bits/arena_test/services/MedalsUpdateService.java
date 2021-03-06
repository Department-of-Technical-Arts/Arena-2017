package org.arenatest.bits.arena_test.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import org.arenatest.bits.arena_test.api.ApiClient;
import org.arenatest.bits.arena_test.api.ApiInterface;
import org.arenatest.bits.arena_test.app.Constants;
import org.arenatest.bits.arena_test.database.DBHelper;
import org.arenatest.bits.arena_test.sets.MedalData;
import org.arenatest.bits.arena_test.sets.MedalSet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tejeshwar on 6/1/17.
 */

public class MedalsUpdateService extends IntentService {

    final String TAG = "MedalsUpdateService";
    public MedalsUpdateService() {
        super("MedalsUpdateService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final Call<MedalData> medalDataCall = apiService.getResults(Constants.RESULTS_TAG,0);
        medalDataCall.enqueue(new Callback<MedalData>() {
            @Override
            public void onResponse(Call<MedalData> call, Response<MedalData> response) {
                DBHelper helper = new DBHelper(getApplicationContext());
                helper.deleteMedalsTable();
                List<MedalSet> medalSets = response.body().getData();
                Log.e(TAG,medalSets.size()+"");
                for (int i = 0 ; i<medalSets.size();i++){
                    helper.addMedalsRow(medalSets.get(i).getCollege(),medalSets.get(i).getGold(),medalSets.get(i).getSilver(),medalSets.get(i).getBronze());
//                    Log.e(TAG,success+"");
                }
            }

            @Override
            public void onFailure(Call<MedalData> call, Throwable t) {
                Log.e(TAG,"Check your internet connection");
            }
        });
    }
}
