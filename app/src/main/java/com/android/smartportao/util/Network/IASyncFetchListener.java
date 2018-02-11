package com.android.smartportao.util.Network;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.EventListener;

/**
 * Created by Jaime on 1/20/16.
 */
public interface IASyncFetchListener extends EventListener {
    void onComplete(JSONObject jsonObject);
    void onComplete(JSONArray jsonArray);
    void onError(Throwable error);
    void onError(JSONObject errorResponse);
}
