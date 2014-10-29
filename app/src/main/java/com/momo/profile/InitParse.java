package com.momo.profile;

/**
 * Created by daniel-home on 28/10/14.
 */

import android.app.Application;
import com.parse.Parse;

public class InitParse extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


    }


    public void init() {
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "fpHnJtvttEKiKs2FmZ6UPMrVxPjD4KMdnRj3jgIi", "w3KrbARBlNEJg0gNhD0FwiJBvcoDokDloLYQMtDz");
    }


}
