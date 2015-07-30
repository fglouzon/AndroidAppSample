package net.glouz.myapp.model.models;

import android.util.Log;

import net.glouz.myapp.R;
import net.glouz.myapp.SampleApplication;
import net.glouz.myapp.commons.utils.ModelUtils;

import java.io.IOException;

/**
 * @author glouzonf
 */
public class BaseModel {

    @Override
    public String toString() {
        try {
            return ModelUtils.getInstance().getObjectMapper().writeValueAsString(this);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(getClass().getSimpleName(), SampleApplication.getInstance().getString(R.string.error_deserializing_model));
        }

        return super.toString();
    }
}
