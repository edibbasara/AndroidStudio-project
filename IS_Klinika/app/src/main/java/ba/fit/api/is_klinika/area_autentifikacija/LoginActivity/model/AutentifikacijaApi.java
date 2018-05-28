package ba.fit.api.is_klinika.area_autentifikacija.LoginActivity.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;

import ba.fit.api.is_klinika.MyApp;
import ba.fit.api.is_klinika.helper.HttpManager;
import ba.fit.api.is_klinika.helper.MyRunnable;
import ba.fit.api.is_klinika.helper.url.MojRazultat;

public class AutentifikacijaApi {
    public static void Provjera(final Context context, final String username, final String password, final MyRunnable<AutentifikacijaProvjeraVM> onSuccess) {
        new AsyncTask<Void, Void, MojRazultat<AutentifikacijaProvjeraVM>>() {

            private ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                dialog = ProgressDialog.show(context,"Pristup podacima","u toku!!!");
                dialog.show();
            }

            @Override
            protected MojRazultat<AutentifikacijaProvjeraVM> doInBackground(Void... voids) {
                return HttpManager.get("Autentifikacija/Provjera",AutentifikacijaProvjeraVM.class,
                        new BasicNameValuePair("username",username),
                        new BasicNameValuePair("password",password));
            }

            @Override
            protected void onPostExecute(MojRazultat<AutentifikacijaProvjeraVM> result) {
                dialog.dismiss();
                if(result.IsError)
                {
                    Toast.makeText(context,"Greška u komunikaciji",Toast.LENGTH_LONG).show();
                }
                else{
                    onSuccess.run(result.value);
                }

            }
        }.execute();
    }
}
