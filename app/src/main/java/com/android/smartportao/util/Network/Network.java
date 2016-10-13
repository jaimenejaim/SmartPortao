package com.android.smartportao.util.Network;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;

import org.apache.http.Header;
import org.json.JSONObject;

/**
 * Created by JAIME on 08/10/2016.
 */
public class Network {

    private static final String QUERY_OPEN_CLOSE_DOOR_SOCIAL = "http://192.168.0.128/in02/";
    private static final String QUERY_OPEN_CLOSE_DOOR_MAIN = "http://192.168.0.128/in04/";
    private static final String QUERY_GET_STATUS_DOOR_MAIN = "http://192.168.0.128/in02/state.json";
    private static final String QUERY_GET_STATUS_DOOR_SOCIAL = "http://192.168.0.128/in04/state.json";

    private IASyncFetchListener fetchListener = null;
    public AsyncHttpClient client;
    private PersistentCookieStore myCookieStore;
    private Context context;

    public Network(Context context) {
        this.context = context;
        client = new AsyncHttpClient();
        myCookieStore = new PersistentCookieStore(this.context);
    }

    public void setListener(IASyncFetchListener listener) {
        this.fetchListener = listener;
    }


    //IF BOOLEAN TYPE EQUALS FALSE, EXECUTE QUERY DOOR MAIN
    //IF BOOLEAN TYPE EQUALS TRUE, EXECUTE QUERY DOOR SOCIAL
    public void setActionDoor(String status,boolean type){
        if(myCookieStore == null) {
            myCookieStore = new PersistentCookieStore(this.context);
        }
        String query;
        if(!type){
            query = QUERY_OPEN_CLOSE_DOOR_MAIN;
        }else{
            query = QUERY_OPEN_CLOSE_DOOR_SOCIAL;
        }
        client.setCookieStore(myCookieStore);
        client.get(query+status+".json", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                if (fetchListener != null) {
                    fetchListener.onComplete(jsonObject);
                }
            }


            @Override
            public void onFailure(Throwable e, JSONObject errorResponse) {

                if(errorResponse != null) {
                    fetchListener.onError(errorResponse);
                }else{
                    fetchListener.onError(e);
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject errorResponse) {
                if(errorResponse != null) {
                    fetchListener.onError(errorResponse);
                }else{
                    fetchListener.onError(e);
                }
            }
        });
    }

    //IF BOOLEAN TYPE EQUALS FALSE, EXECUTE QUERY DOOR MAIN
    //IF BOOLEAN TYPE EQUALS TRUE, EXECUTE QUERY DOOR SOCIAL
    public void getStateDoor(boolean type){
        if(myCookieStore == null) {
            myCookieStore = new PersistentCookieStore(this.context);
        }
        String query;
        if(!type){
            query = QUERY_GET_STATUS_DOOR_MAIN;
        }else{
            query = QUERY_GET_STATUS_DOOR_SOCIAL;
        }
        client.setCookieStore(myCookieStore);
        client.get(query, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                if (fetchListener != null) {
                    fetchListener.onComplete(jsonObject);
                }
            }


            @Override
            public void onFailure(Throwable e, JSONObject errorResponse) {

                if(errorResponse != null) {
                    fetchListener.onError(errorResponse);
                }else{
                    fetchListener.onError(e);
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject errorResponse) {
                if(errorResponse != null) {
                    fetchListener.onError(errorResponse);
                }else{
                    fetchListener.onError(e);
                }
            }
        });
    }




}
