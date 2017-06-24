package dzikizachod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StrategiaSzeryfaDomyslna extends StrategiaSzeryfa {

    public boolean strza�(Gracz gracz, List<Gracz> gracze,
            PulaAkcji pulaAkcji) {
        if (gracz.getWylosowaneAkcje().contains(Akcja.STRZEL)) {

            if (gracz.getAtakuj�cy().isEmpty()) {
                return atakujLosow�(gracz, gracze, pulaAkcji);
            }

            else {
                return atakujAtakuj�cych(gracz, gracze, pulaAkcji);
            }
        }

        return false;
    }

    private boolean atakujLosow�(Gracz gracz, List<Gracz> gracze,
            PulaAkcji pulaAkcji) {
        List<Gracz> wZasi�gu = gracz.zbierzZasieg(gracze);

        if (wZasi�gu.isEmpty())
            return false;

        else {
            Collections.shuffle(wZasi�gu);

            gracz.getWylosowaneAkcje().remove(Akcja.STRZEL);
            gracz.getDoWykonania().add(new Ruch(Akcja.STRZEL, wZasi�gu.get(0)));
            return true;

        }

    }

    private boolean atakujAtakuj�cych(Gracz gracz, List<Gracz> gracze,
            PulaAkcji pulaAkcji) {

        List<Gracz> wZasi�gu = gracz.zbierzZasieg(gracze);
        List<Gracz> atakuj�cyWZasi�gu = new ArrayList<Gracz>();

        for (int i = 0; i <= wZasi�gu.size() - 1; i++) {
            if (gracz.getAtakuj�cy().contains(wZasi�gu.get(i))) {
                atakuj�cyWZasi�gu.add(wZasi�gu.get(i));
            }
        }

        if (atakuj�cyWZasi�gu.isEmpty())
            return false;
        else {
            Collections.shuffle(atakuj�cyWZasi�gu);
            gracz.getWylosowaneAkcje().remove(Akcja.STRZEL);
            gracz.getDoWykonania()
                    .add(new Ruch(Akcja.STRZEL, atakuj�cyWZasi�gu.get(0)));
            return true;

        }
    }
}
