package ba.fit.api.is_klinika;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

public class MyApp extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
