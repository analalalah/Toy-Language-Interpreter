package DataTypes;

/**
 * Created by vladc on 07.11.2016.
 */
public class MyPair<F, S> {
    private F first;
    private S second;

    public MyPair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    @Override
    public String toString() {
//        return "(" + first.toString() + ", " + second.toString() + ")";
        return first.toString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof MyPair)) return false;

        MyPair<F, S> otherPair = (MyPair<F, S>)other;
        return otherPair.getFirst().equals(this.first) &&
                otherPair.getSecond().equals(this.second);
    }
}
