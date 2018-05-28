package ba.fit.api.is_klinika.area_autentifikacija.LoginActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ba.fit.api.is_klinika.R;
import ba.fit.api.is_klinika.area_autentifikacija.LoginActivity.model.AutentifikacijaApi;
import ba.fit.api.is_klinika.area_autentifikacija.LoginActivity.model.AutentifikacijaProvjeraVM;
import ba.fit.api.is_klinika.area_pacijent.pregledRezervacija;
import ba.fit.api.is_klinika.helper.MyRunnable;
import ba.fit.api.is_klinika.helper.Sesija;

public class LoginActivity extends AppCompatActivity {

    private EditText password;
    private EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        password = (EditText)findViewById(R.id.txtPassword);
        username = (EditText)findViewById(R.id.txtUsername);

        final Button btnLogin =(Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_btnLoginOnClick();
            }
        });
    }

    private void do_btnLoginOnClick() {
        AutentifikacijaApi.Provjera(LoginActivity.this, username.getText().toString(), password.getText().toString(), new MyRunnable<AutentifikacijaProvjeraVM>() {
            @Override
            public void run(AutentifikacijaProvjeraVM result) {
                if(result == null)
                {
                    Toast.makeText(LoginActivity.this,"Pogresan username ili password",Toast.LENGTH_LONG).show();
                }
                else{
                    Sesija.setLogiraniKorisnik(result);
                    Intent intent = new Intent(LoginActivity.this, pregledRezervacija.class);
                    startActivity(intent);
                }
            }
        });

    }
}
