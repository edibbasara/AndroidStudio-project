package ba.fit.api.is_klinika.helper;

import android.app.Application;

import java.io.Serializable;

public interface MyRunnable<T> extends Serializable{
    void run(T result);
}
