package ba.fit.api.is_klinika.helper;

import android.content.SharedPreferences;

import ba.fit.api.is_klinika.MyApp;
import ba.fit.api.is_klinika.area_autentifikacija.LoginActivity.model.AutentifikacijaProvjeraVM;

public class Sesija {
    private static final String PREF_NAME ="DatotekaZaSharedPreference" ;
    private static final String KEY_LOGIRANIKORISNIK ="logiraniKorisnikJSON" ;



    public static void setLogiraniKorisnik(AutentifikacijaProvjeraVM logiraniKorisnik) {
        String str;
        if(logiraniKorisnik != null)
            str = MyGSON.build().toJson(logiraniKorisnik);
         else{
            str ="";
       }
         SharedPreferences setings = MyApp.getContext().getSharedPreferences(PREF_NAME,0);
         SharedPreferences.Editor editor = setings.edit();
         editor.putString(KEY_LOGIRANIKORISNIK,str);
         editor.commit();
    }

    public static AutentifikacijaProvjeraVM getLogiraniKorisnik() {
        SharedPreferences setings = MyApp.getContext().getSharedPreferences(PREF_NAME,0);
        String str = setings.getString(KEY_LOGIRANIKORISNIK,"");
        if(str.length() == 0)
            return null;
        return MyGSON.build().fromJson(str,AutentifikacijaProvjeraVM.class);
    }
}
