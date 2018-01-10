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
}
