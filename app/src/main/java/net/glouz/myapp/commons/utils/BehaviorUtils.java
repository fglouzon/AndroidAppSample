package net.glouz.myapp.commons.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import rx.subjects.BehaviorSubject;

/**
 * Created by glouzonf on 30/07/2015.
 */
public class BehaviorUtils {

    public static BehaviorSubject<String> getBehaviorSubject (EditText editText){

        final BehaviorSubject behaviorSubject = BehaviorSubject.create(editText.getText().toString());
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                behaviorSubject.onNext(editable.toString());
            }
        });

        return behaviorSubject;
    }

}
