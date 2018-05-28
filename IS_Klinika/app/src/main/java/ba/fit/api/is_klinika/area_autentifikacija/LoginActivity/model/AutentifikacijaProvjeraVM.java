package ba.fit.api.is_klinika.area_autentifikacija.LoginActivity.model;

import java.util.Date;
import java.util.List;

public class AutentifikacijaProvjeraVM {
    //Korisnici
    public int Id ;
    public boolean Valid ;
    public String ImePrezime;
    public String KorisnickoIme ;
    public String Adresa ;
    public String Email ;
    public Date DatumRodjenja ;

    //Pacijent
    public String AktivacijskiHash ;
    public boolean IsPotvrdjen ;
    public String PacOpstinaPrebivalistaNaziv ;
    public String KlinikaPac ;

    //Rezervacije
    public class RezervacijeInfo
    {
        public int Id ;
        public boolean Valid ;
        public Date DatumRezervacije ;
        public Date DatumPregleda ;
        public int PregledId ;
        public int PacijentId ;
        public String PacijentNaziv ;
        public int ZaposlenikId ;
        public String ZaposlenikNaziv ;
        public int TerminId ;
        public String Termin;
        public boolean Zavrsen ;
    }

    public List<RezervacijeInfo> RezervacijaList ;
}
