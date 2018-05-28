package ba.fit.api.is_klinika;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ba.fit.api.is_klinika.area_autentifikacija.LoginActivity.LoginActivity;
import ba.fit.api.is_klinika.area_pacijent.pregledRezervacija;
import ba.fit.api.is_klinika.helper.Sesija;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Sesija.getLogiraniKorisnik()==null){
            startActivity(new Intent(this, LoginActivity.class));
        }
        else
        {
            startActivity(new Intent(this,pregledRezervacija.class));
        }
    }
}
