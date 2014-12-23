package pl.golinski.piotr.speedmeter.model;

import com.google.gson.Gson;

/**
 * Created by xxx on 23.12.14.
 */
public abstract class PGModel {
    protected Gson mGson = new Gson();

    public String getObjectAsJSON(){
        return mGson.toJson(this);
    }
}
