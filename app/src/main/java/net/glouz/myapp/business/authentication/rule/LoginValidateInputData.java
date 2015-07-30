package net.glouz.myapp.business.authentication.rule;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by glouzonf on 28/07/2015.
 */
public class LoginValidateInputData {

    private static final int MIN_CHARACTERS = 6;

    //without retro lambda
//    public static void validateT(BehaviorSubject<String> username, BehaviorSubject<String> password){
//        Observable.combineLatest(username, password, new Func2<String, String, Boolean[]>() {
//            @Override
//            public Boolean[] call(String s1, String s2) {
//                Boolean [] results = new Boolean[2];
//
//                if (s1 != null && !s1.equals("")){
//                    results[0] = true;
//                }
//
//                if (s2 != null && !s2.equals("")){
//                    results[1] = true;
//                }
//
//                return results;
//            }
//        }).subscribe(new Action1<Boolean[]>() {
//            @Override
//            public void call(Boolean[] aBoolean) {
////                pushMe.setEnabled(aBoolean);
//            }
//        });
//    }

    //with retro lambda
    //we check that the fields are not empty and have minimum 6 characters each.
    public static Observable validate(BehaviorSubject<String> username, BehaviorSubject<String> password) {
        return Observable.combineLatest(username, password, (s1, s2) -> {
            boolean[] results = new boolean[2];

            results[0] = checkData(s1);
            results[1] = checkData(s2);

            return results;
        });
    }

    private static boolean checkData(String data) {
        return data != null && !data.equals("") && data.length() > MIN_CHARACTERS;
    }
}
