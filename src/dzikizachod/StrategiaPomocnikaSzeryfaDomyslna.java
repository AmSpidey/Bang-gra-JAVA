package dzikizachod;

import java.util.Collections;
import java.util.List;

public class StrategiaPomocnikaSzeryfaDomyslna
        extends StrategiaPomocnikaSzeryfa {

    public boolean strza³(Gracz gracz, List<Gracz> gracze,
            PulaAkcji pulaAkcji) {

        if (!gracz.getWylosowaneAkcje().contains(Akcja.STRZEL))
            return false;

        List<Gracz> wZasiegu = gracz.zbierzZasieg(gracze);

        if (!wZasiegu.isEmpty() && szeryfWZasiegu(wZasiegu)) {
            int i = znajdzSzeryfa(wZasiegu);
            wZasiegu.remove(i); // TU COS SIE JEBIE//
        }

        if (!wZasiegu.isEmpty()) {

            Collections.shuffle(wZasiegu);

            gracz.getDoWykonania().add(new Ruch(Akcja.STRZEL, wZasiegu.get(0)));
            gracz.getWylosowaneAkcje().remove(Akcja.STRZEL);
            return true;
        }

        return false;
    }

}
