import org.junit.jupiter.api.Test;

public class JavaHomeTask {

    @Test
    public void firstTaskJavaCode() {
        Double apartmentCost = 70.000;
        int creditTerm = 15;
        int percentagePerAnnum = 25;
        int daysAYear = 365;

        Double percentageTotal = apartmentCost * percentagePerAnnum / 100 * creditTerm * daysAYear / daysAYear;

        Double finalCreditTotal = apartmentCost + percentageTotal;

        System.out.println("Percentage total per 15 years is " + percentageTotal + " but Final credit total is " + finalCreditTotal);
    }
}