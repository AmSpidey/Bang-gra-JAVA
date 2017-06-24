package dzikizachod;

import java.util.ArrayList;
import java.util.List;

public abstract class StrategiaPomocnikaSzeryfa extends Strategia {

    public boolean leczenie(Gracz gracz, List<Gracz> gracze,
            PulaAkcji pulaAkcji) {

        int pozycjaSzeryfa = znajdzSzeryfa(gracze);

        List<Gracz> wZasiegu = zbierzSasiadow(gracz, gracze);

        if (gracz.getWylosowaneAkcje().contains(Akcja.ULECZ)
                && szeryfWZasiegu(wZasiegu)
                && gracze.get(pozycjaSzeryfa).ranny()) {

            gracz.getDoWykonania()
                    .add(new Ruch(Akcja.ULECZ, gracze.get(pozycjaSzeryfa)));
            gracz.getWylosowaneAkcje().remove(Akcja.ULECZ);
            return true;

        }

        else if (gracz.getWylosowaneAkcje().contains(Akcja.ULECZ)
                && gracz.ranny()) {

            gracz.getDoWykonania().add(new Ruch(Akcja.ULECZ, gracz));
            gracz.getWylosowaneAkcje().remove(Akcja.ULECZ);
            return true;

        }
        return false;
    }

    public List<Gracz> zbierzSasiadow(Gracz gracz, List<Gracz> gracze) {
        List<Gracz> wZasiêgu = new ArrayList<Gracz>();

        int j = gracz.numerGracza(gracze);

        if (j == gracze.size() - 1) {
            wZasiêgu.add(gracze.get(0));
        }

        else {
            wZasiêgu.add(gracze.get(j + 1));
        }

        if (j == 0) {
            wZasiêgu.add(gracze.get(gracze.size() - 1));
        }

        else {
            wZasiêgu.add(gracze.get(j - 1));
        }

        return wZasiêgu;
    }

    public boolean dynamit(Gracz gracz, List<Gracz> gracze,
            PulaAkcji pulaAkcji) {

        if (!gracz.getWylosowaneAkcje().contains(Akcja.DYNAMIT))
            return false;

        List<Gracz> doSzeryfa = doSzeryfa(gracze, gracz);

        if (doSzeryfa.isEmpty() || doSzeryfa.size() < 3)
            return false;

        else {
            int podejrzani = 0;
            int normalni = 0;

            for (int i = 0; i <= doSzeryfa.size() - 1; i++) {
                if (zabilWiecejPomocnikow(doSzeryfa.get(i))
                        || podejrzany(doSzeryfa.get(i))) {
                    podejrzani++;

                } else
                    normalni++;
            }

            if (podejrzani > 2 * (normalni + podejrzani) / 3) {

                gracz.getDoWykonania().add(new Ruch(Akcja.DYNAMIT,
                        gracze.get(gracz.numerNastepnego(gracze))));
                gracz.getWylosowaneAkcje().remove(Akcja.DYNAMIT);

                return true;
            }
            return false;
        }

    }

    public List<Gracz> doSzeryfa(List<Gracz> gracze, Gracz gracz) {

        List<Gracz> doZwrocenia = new ArrayList<Gracz>();
        int i = gracz.numerGracza(gracze);

        while (!gracze.get(i).getPozycja().equals("Szeryf")) {
            i++;
            if (i > gracze.size() - 1)
                i = 0;

            doZwrocenia.add(gracze.get(i));

        }

        return doZwrocenia;
    }

    public boolean zabilWiecejPomocnikow(Gracz gracz) {

        int pomocnicy = 0;
        int bandyci = 0;

        for (int i = 0; i <= gracz.getZamordowani().size() - 1; i++) {
            if (gracz.getZamordowani().get(i).getPozycja()
                    .equals("PomocnikSzeryfa")) {
                pomocnicy++;
            } else
                bandyci++;
        }

        if (pomocnicy > bandyci)
            return true;

        return false;
    }

    public boolean podejrzany(Gracz gracz) {

        if (gracz.getPodejrzany() > 0) {
            return true;

        }

        return false;

    }

}
