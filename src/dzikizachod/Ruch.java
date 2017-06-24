package dzikizachod;

import java.util.List;

public class Ruch {

    private Akcja akcja;
    private Gracz cel;

    public Ruch(Akcja akcja, Gracz cel) {
        this.akcja = akcja;
        this.cel = cel;
    }

    public Akcja getAkcja() {
        return akcja;
    }

    public Gracz getCel() {
        return cel;
    }

    public String wypiszRuch(Gracz gracz, List<Gracz> gracze) {
        if (gracz != cel) {
            int j = cel.numerGracza(gracze) + 1;
            return ("    " + akcja + " " + j + " ");
        } else {
            return ("    " + akcja);
        }
    }

}
