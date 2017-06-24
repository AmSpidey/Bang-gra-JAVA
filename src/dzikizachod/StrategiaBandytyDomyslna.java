package dzikizachod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StrategiaBandytyDomyslna extends StrategiaBandyty {

    public boolean strza³(Gracz gracz, List<Gracz> gracze,
            PulaAkcji pulaAkcji) {

        if (!gracz.getWylosowaneAkcje().contains(Akcja.STRZEL))
            return false;
        List<Gracz> wZasiegu = przeciwnicyWZasiegu(gracz, gracze);

        if (!wZasiegu.isEmpty()) {

            if (szeryfWZasiegu(wZasiegu)) {

                gracz.getDoWykonania().add(new Ruch(Akcja.STRZEL,
                        gracze.get(znajdzSzeryfa(gracze))));
                gracz.getWylosowaneAkcje().remove(Akcja.STRZEL);

                return true;

            }
            Collections.shuffle(wZasiegu);

            gracz.getDoWykonania().add(new Ruch(Akcja.STRZEL, wZasiegu.get(0)));
            gracz.getWylosowaneAkcje().remove(Akcja.STRZEL);
            return true;
        }
        return false;
    }

    private List<Gracz> przeciwnicyWZasiegu(Gracz gracz, List<Gracz> gracze) {
        List<Gracz> doZwrocenia = new ArrayList<Gracz>();

        doZwrocenia = gracz.zbierzZasieg(gracze);

        for (int i = 0; i <= doZwrocenia.size() - 1; i++) {
            if (doZwrocenia.get(i).getPozycja().equals("Bandyta")) {
                doZwrocenia.remove(i);
                --i;
            }
        }

        return doZwrocenia;
    }

}
