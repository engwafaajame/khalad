package com.emuniapp.myapplication;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Created by hp on 12/09/2017.
 */

public class VolleyRequests<T> extends Observable {
    interface IReceiveData<T> {
        void onDataReceived(T posts);
    }

    IReceiveData iReceiveData;



    public void addstudent(final String id,final  String name) {
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST,
                "http://wasfat.club/api/add_student.php",  new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                JsonResult jsonResult = gson.fromJson(response.toString(), JsonResult.class);

                setChanged();
                notifyObservers(jsonResult);

                if (iReceiveData != null) {
                    iReceiveData.onDataReceived(jsonResult);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap();
                map.put("id",id);
                map.put("name",name);
                return map;
            }
        };

        MyApplication.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    public VolleyRequests setIReceiveData(IReceiveData iReceiveData) {
        this.iReceiveData = iReceiveData;
        return this;
    }
}