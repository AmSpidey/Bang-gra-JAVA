package dzikizachod;

public class Szeryf extends Gracz {

    public Szeryf() {
        this(new StrategiaSzeryfaDomyslna());

    }

    public Szeryf(Strategia strategia) {
        super();
        this.maksZycie = 5;
        this.strategia = strategia;
        this.aktZycie = 5;

    }

    public String getPozycja() {
        return "Szeryf";
    }

    @Override
    public Gracz klonuj() {
        return new Szeryf(strategia);
    }

}
