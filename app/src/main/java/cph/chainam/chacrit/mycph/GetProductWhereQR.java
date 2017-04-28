package cph.chainam.chacrit.mycph;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by masterUNG on 4/28/2017 AD.
 */

public class GetProductWhereQR extends AsyncTask<String, Void, String>{

    private Context context;


    public GetProductWhereQR(Context context) {
        this.context = context;
    }


    @Override
    protected String doInBackground(String... potition) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add(potition[0], potition[1])
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(potition[2]).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();


        } catch (Exception e) {
            Log.d("28AprilV1", "e doin ==> " + e.toString());
            return null;
        }


    }
}   // Main Class