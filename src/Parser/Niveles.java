package Parser;

public class Niveles {
	
    protected String[] misNiveles = new String[3]; 

    public Niveles() {
        this.misNiveles[0] = "src/Parser/nivel_1.txt";
        this.misNiveles[1] = "src/Parser/nivel_2.txt";
        this.misNiveles[2] = "src/Parser/nivel_3.txt";
    }

    public String getNivel(int i) {
        return this.misNiveles[i];
    }
}