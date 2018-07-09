package ru.solodkov.javacoreprofessional;

public class Box<T extends Fruit> {
    T obj;
    int total = 0;

    public Box(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }

    float getWeight() {
        return total*obj.getWeight();
    }

    boolean compare(Box box) {
        return getWeight() == box.getWeight();
    }

    boolean emptyIn(Box box) {
        if (getObj() == box.getObj()) {
            box.add(total);
            total = 0;
            return true;
        } else
            return false;
    }

    void add(int number) {
        total += number;
    }
}