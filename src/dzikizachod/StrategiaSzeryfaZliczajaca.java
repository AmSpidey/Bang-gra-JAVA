package dzikizachod;

import java.util.Collections;
import java.util.List;

public class StrategiaSzeryfaZliczajaca extends StrategiaSzeryfa {

    @Override
    public boolean strza³(Gracz gracz, List<Gracz> gracze,
            PulaAkcji pulaAkcji) {

        if (!gracz.getWylosowaneAkcje().contains(Akcja.STRZEL))
            return false;

        List<Gracz> zasieg = gracz.zbierzZasieg(gracze);

        // Usuwamy z naszych celow szeryfa i ludzi, na ktorych
        // nie padly nasze podejrzenia.

        for (int i = 0; i <= zasieg.size() - 1; i++) {
            if (zasieg.get(i).getPozycja().equals("Szeryf")
                    || (!zabilWiecejPomocnikow(zasieg.get(i))
                            && !podejrzany(zasieg.get(i)))) {
                zasieg.remove(i);

                i--;
            }
        }

        if (zasieg.isEmpty())
            return false;

        Collections.shuffle(zasieg);

        gracz.getWylosowaneAkcje().remove(Akcja.STRZEL);
        gracz.getDoWykonania().add(new Ruch(Akcja.STRZEL, zasieg.get(0)));
        return true;

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
