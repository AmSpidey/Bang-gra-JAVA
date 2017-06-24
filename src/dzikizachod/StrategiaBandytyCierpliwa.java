package dzikizachod;

import java.util.List;

public class StrategiaBandytyCierpliwa extends StrategiaBandyty {
    boolean zabitoBandyte;

    @Override
    public boolean strza³(Gracz gracz, List<Gracz> gracze,
            PulaAkcji pulaAkcji) {

        if (!gracz.getWylosowaneAkcje().contains(Akcja.STRZEL))
            return false;
        List<Gracz> zasieg = gracz.zbierzZasieg(gracze);

        if (szeryfWZasiegu(zasieg)) {

            gracz.getDoWykonania().add(
                    new Ruch(Akcja.STRZEL, gracze.get(znajdzSzeryfa(gracze))));
            gracz.getWylosowaneAkcje().remove(Akcja.STRZEL);

            return true;

        }

        return false;

    }

}
