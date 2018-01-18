package utils;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/7/2018.
 */
public class PassengerSet {

    private int adultCount;
    private int childCount;
    private int babyCount;

    public PassengerSet(int adultCount, int childCount, int babyCount) {
        this.adultCount = adultCount;
        this.childCount = childCount;
        this.babyCount = babyCount;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public int getChildCount() {
        return childCount;
    }

    public int getBabyCount() {
        return babyCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PassengerSet that = (PassengerSet) o;

        if (adultCount != that.adultCount) return false;
        if (childCount != that.childCount) return false;
        return babyCount == that.babyCount;
    }

    @Override
    public int hashCode() {
        int result = adultCount;
        result = 31 * result + childCount;
        result = 31 * result + babyCount;
        return result;
    }

    @Override
    public String toString() {
        return "PassengerSet{" +
                "adultCount=" + adultCount +
                ", childCount=" + childCount +
                ", babyCount=" + babyCount +
                '}';
    }
}
