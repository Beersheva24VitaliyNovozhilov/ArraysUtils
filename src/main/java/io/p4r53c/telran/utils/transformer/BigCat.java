package io.p4r53c.telran.utils.transformer;

public class BigCat {

    private int weight;
    private String name;
    private String kind;

    public BigCat(int weight, String name, String kind) {
        this.weight = weight;
        this.name = name;
        this.kind = kind;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return String.format("BigCat{weight=%d, name='%s', kind='%s'}", weight, name, kind);
    }
}
