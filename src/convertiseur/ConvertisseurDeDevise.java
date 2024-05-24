 public class ConvertisseurDeDevise {
    public static double tndEnEur(double tnd) {
        // Conversion de TND en EUR
        return tnd * 0.313654; // Taux de conversion actuel
    }

    public static double eurEnTnd(double eur) {
        // Conversion d'EUR en TND
        return eur / 0.313654; // Taux de conversion actuel
    }

    public static double tndEnUsd(double tnd) {
        // Conversion de TND en USD
        return tnd * 0.352897; // Taux de conversion actuel
    }

    public static double usdEnTnd(double usd) {
        // Conversion d'USD en TND
        return usd / 0.352897; // Taux de conversion actuel
    }

    public static double eurEnUsd(double eur) {
        // Conversion d'EUR en USD
        return eur * 1.12315; // Taux de conversion actuel
    }

    public static double usdEnEur(double usd) {
        // Conversion d'USD en EUR
        return usd / 1.12315; // Taux de conversion actuel
    }
}
