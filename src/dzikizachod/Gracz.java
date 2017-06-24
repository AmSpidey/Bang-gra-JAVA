package dzikizachod;

import java.util.ArrayList;
import java.util.List;

public abstract class Gracz {
    protected int maksZycie;
    protected int aktZycie;

    private int zasiêg;

    private boolean zadynamitowany;

    protected Strategia strategia;

    private List<Akcja> wylosowaneAkcje;

    private List<Ruch> doWykonania;

    private List<Gracz> zamordowani;

    private int podejrzany;

    private List<Gracz> atakuj¹cy;

    Gracz() {

        this.zasiêg = 1;
        zadynamitowany = false;
        wylosowaneAkcje = new ArrayList<Akcja>();

        doWykonania = new ArrayList<Ruch>();
        zamordowani = new ArrayList<Gracz>();
        podejrzany = 0;
        atakuj¹cy = new ArrayList<Gracz>();
    }

    public int getMaksZycie() {
        return maksZycie;
    }

    public int getPodejrzany() {
        return podejrzany;
    }

    public void wykonajRuch(List<Gracz> gracze, PulaAkcji pulaAkcji) {

        Ruch zrób = doWykonania.get(0);

        this.zuzyjAkcje(zrób.getAkcja(), pulaAkcji);

        switch (zrób.getAkcja()) {
        case ULECZ:
            this.ulecz(zrób.getCel());
            break;
        case ZASIEG_PLUS_JEDEN:
            this.zwiekszZasieg(1);
            break;
        case ZASIEG_PLUS_DWA:
            this.zwiekszZasieg(2);
            break;
        case STRZEL:
            this.strza³(gracze, zrób.getCel(), pulaAkcji);
            break;
        case DYNAMIT:
            this.dynamit(gracze);
            break;
        }

    }

    private void dynamit(List<Gracz> gracze) {
        gracze.get(this.numerNastepnego(gracze)).zadynamitowany = true;

    }

    public int numerNastepnego(List<Gracz> gracze) {
        if (numerGracza(gracze) == gracze.size() - 1)
            return 0;
        else
            return numerGracza(gracze) + 1;
    }

    public List<Gracz> getZamordowani() {
        return zamordowani;
    }

    public void odbierzZycie(int ile) {
        this.aktZycie = this.aktZycie - ile;
        if (aktZycie < 0) {
            this.aktZycie = 0;
        }
    }

    public void umrzyj(List<Gracz> gracze, PulaAkcji pulaAkcji) {
        while (!wylosowaneAkcje.isEmpty()) {
            pulaAkcji.wrzucWykorzystanaAkcje(wylosowaneAkcje.remove(0));
        }

        gracze.remove(this);
    }

    public boolean martwy() {
        return aktZycie == 0;
    }

    public void losujAkcje(PulaAkcji pula) {
        if (pula.getListaAkcji().size() < 5 - wylosowaneAkcje.size()) {
            pula.wykorzystajZuzyte();
        }

        while (wylosowaneAkcje.size() < 5) {
            wylosowaneAkcje.add(pula.getListaAkcji().remove(0));
        }

    }

    public List<Gracz> zbierzZasieg(List<Gracz> gracze) {
        List<Gracz> wZasiêgu = new ArrayList<Gracz>();

        int j = numerGracza(gracze);
        for (int i = 0; i <= zasiêg; i++) {

            wZasiêgu.add(gracze.get(j));

            j++;
            j = j % gracze.size();
        }

        j = numerGracza(gracze);

        for (int i = 0; i <= zasiêg; i++) {

            wZasiêgu.add(gracze.get(j));

            j--;
            if (j < 0) {
                j = gracze.size() - 1;
            }
        }

        wZasiêgu.remove(this);
        wZasiêgu.remove(this);
        return wZasiêgu;
    }

    public void rozegrajTure(Tura tura, List<Gracz> gracze, List<Gracz> wszyscy,
            PulaAkcji pulaAkcji) {

        Komunikator komunikatorTuryGracza = new Komunikator();

        boolean aktualnyGraczMialDynamit = false;

        if (!martwy()) {

            if (zadynamitowany) {
                if (!oddynamituj(gracze)) {
                    odbierzZycie(3);
                    if (martwy()) {
                        umrzyj(gracze, pulaAkcji);
                    }
                }

                aktualnyGraczMialDynamit = true;

            }

            losujAkcje(pulaAkcji);

            komunikatorTuryGracza.wypiszGraczaZywego(wszyscy, this);

            if (aktualnyGraczMialDynamit) {
                komunikatorTuryGracza.wypiszDynamit(this, !zadynamitowany,
                        wszyscy);
            }
            System.out.println("  Ruchy: ");

            // Jeœli gracz nie jest martwy, wywo³uje strategiê, która
            // poda mu ruch, który mo¿e wykonaæ. Bêdzie to wykonywane tak d³ugo,
            // a¿ strategia nie bêdzie w stanie zasugerowaæ ruchu
            // lub skoñcz¹ siê wylosowane Akcje.

            if (!this.martwy()) {
                while (!this.wylosowaneAkcje.isEmpty()
                        && this.strategia.dajRuch(this, gracze, pulaAkcji)) {
                    while (!this.doWykonania.isEmpty()) {

                        System.out.println(this.doWykonania.get(0)
                                .wypiszRuch(this, wszyscy));

                        this.wykonajRuch(gracze, pulaAkcji);

                        if (!tura.sprawdzenieWyniku(wszyscy)
                                .equals(Wynik.BRAK_WYNIKU)) {

                            return;
                        }
                    }
                }
            } else {
                komunikatorTuryGracza.martwy();
            }

            if (!tura.sprawdzenieWyniku(wszyscy).equals(Wynik.BRAK_WYNIKU)) {

                return;
            }

        } else {
            komunikatorTuryGracza.wypiszGraczaMartwego(wszyscy, this);
        }

        komunikatorTuryGracza.wypiszStanPoRuchu(wszyscy);

    }

    private boolean oddynamituj(List<Gracz> gracze) {

        int wartosc = Kostka.wylosuj();

        if (wartosc == 1) {
            return false;
        } else {
            this.dynamit(gracze);
            this.zadynamitowany = false;

            return true;
        }

    }

    public int getAktZycie() {

        return aktZycie;
    }

    public int numerGracza(List<Gracz> gracze) {

        int i = 0;

        while (i <= gracze.size() - 1 && gracze.get(i) != this) {
            i++;
        }

        return i;
    }

    public void zwiekszZasieg(int i) {
        zasiêg += i;
    }

    public int getZasieg() {
        return zasiêg;
    }

    @Override
    public String toString() {

        if (this.martwy()) {
            return "X (" + this.getPozycja() + ") ";
        }

        else {
            return (this.getPozycja() + " (liczba ¿yæ: " + this.getAktZycie()
                    + ")");
        }
    }

    public List<Akcja> getWylosowaneAkcje() {
        return wylosowaneAkcje;
    }

    public void zuzyjAkcje(Akcja akcja, PulaAkcji pulaAkcji) {
        this.doWykonania.remove(0);
        if (!akcja.equals(Akcja.DYNAMIT))
            pulaAkcji.wrzucWykorzystanaAkcje(akcja);
    }

    public void dodajZamordowanego(Gracz gracz) {
        this.zamordowani.add(gracz);
    }

    public void dodajPodejrzanego(Gracz gracz) {
        atakuj¹cy.add(gracz);
    }

    public void wzrostPodejrzen() {
        this.podejrzany++;
    }

    public void strzelaj(Gracz gracz) {
        gracz.odbierzZycie(1);

    }

    public boolean ranny() {
        if (aktZycie < maksZycie)
            return true;
        return false;
    }

    public void strza³(List<Gracz> gracze, Gracz cel, PulaAkcji pulaAkcji) {
        this.strzelaj(cel);

        cel.dodajPodejrzanego(this);

        if (cel.martwy()) {

            if (cel.zadynamitowany) {
                cel.dynamit(gracze);
            }

            cel.umrzyj(gracze, pulaAkcji);

            this.dodajZamordowanego(cel);

        }
        if (cel.getPozycja().equals("Szeryf")) {
            cel.wzrostPodejrzen();

        }

    }

    public void ulecz(Gracz gracz) {
        assert gracz.ranny();
        gracz.aktZycie++;
    }

    public List<Gracz> getAtakuj¹cy() {
        return atakuj¹cy;
    }

    public List<Ruch> getDoWykonania() {
        return doWykonania;
    }

    public abstract String getPozycja();

    public abstract Gracz klonuj();

}
