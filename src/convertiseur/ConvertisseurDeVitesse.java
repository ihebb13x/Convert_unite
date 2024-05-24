public class ConvertisseurDeVitesse {
    public static double kphEnMph(double kph) {
        return kph * 0.621371;
    }

    public static double mphEnKph(double mph) {
        return mph / 0.621371;
    }
}
