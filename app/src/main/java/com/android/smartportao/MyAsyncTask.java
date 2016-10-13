package com.android.smartportao;


import android.os.AsyncTask;

/**
 * Created by JAIME on 12/10/2016.
 */
public class MyAsyncTask extends AsyncTask {
    public AsyncResponse delegate = null;

    @Override
    protected Object doInBackground(Object[] objects) {
        return null;
    }

    // you may separate this or combined to caller class.
    public interface AsyncResponse {
        void processFinish(String output);
    }



    public MyAsyncTask(AsyncResponse delegate){
        this.delegate = delegate;
    }


    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }
}