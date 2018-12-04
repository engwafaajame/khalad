package com.emuniapp.myapplication;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

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
    public void ADD_STUDENT(final String student_id,final  String student_name){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "http://wasfat.club/api/add_student.php", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                State stateResponse = gson.fromJson(response.toString(),State.class);

                setChanged();
                notifyObservers(stateResponse);

                if(iReceiveData !=null){
                    iReceiveData.onDataReceived(stateResponse);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap();
                map.put("student_id",student_id);
                map.put("student_name",student_name);
                return map;
               // return super.getParams();
            }
        };

        MyApplication.getInstance().addToRequestQueue(jsonObjectRequest);
    }
    public void addstudent(final String student_id,final  String student_name) {
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST,
                "http://wasfat.club/api/add_student.php",  new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                State jsonResult = gson.fromJson(response.toString(), State.class);

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
                map.put("student_id",student_id);
                map.put("student_name",student_name);
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
