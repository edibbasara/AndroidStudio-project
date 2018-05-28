package ba.fit.api.is_klinika.helper;

import android.view.View;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class F {
    public static <T> T findView(View v,int i,Class<T> type){
        final View viewById = v.findViewById(i);
        return (T) viewById;
    }

    public static String Date_dd_MM_YYYY(Date DatumPregleda){
        if(DatumPregleda==null)
            return "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(DatumPregleda);
    }

    public static String Decimal_0_00(float value) {
        if(value == 0)
            return "";
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(value);
    }
}
