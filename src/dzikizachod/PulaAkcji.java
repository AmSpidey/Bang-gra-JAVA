package dzikizachod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PulaAkcji {

    private List<Akcja> listaAkcji;
    private List<Akcja> wykorzystaneAkcje;

    PulaAkcji() {
        this.listaAkcji = new ArrayList<Akcja>();
        this.wykorzystaneAkcje = new ArrayList<Akcja>();
    }

    public void dodaj(Akcja akcja, int ile) {
        for (int i = 0; i <= ile - 1; i++) {
            getListaAkcji().add(akcja);
        }
    }

    public void tasowanie() {
        Collections.shuffle(getListaAkcji());
    }

    // void wrzucZuzyte(List<Akcja> zuzyteAkcje) {
    // wykorzystaneAkcje.addAll(zuzyteAkcje);
    // zuzyteAkcje.clear();
    // }

    public List<Akcja> getListaAkcji() {
        return listaAkcji;
    }

    public void wykorzystajZuzyte() {
        while (!wykorzystaneAkcje.isEmpty()) {
            listaAkcji.add(wykorzystaneAkcje.remove(0));
        }

    }

    public List<Akcja> getWykorzystaneAkcje() {
        return wykorzystaneAkcje;
    }

    public void wrzucWykorzystanaAkcje(Akcja akcja) {
        this.wykorzystaneAkcje.add(akcja);
    }

}
