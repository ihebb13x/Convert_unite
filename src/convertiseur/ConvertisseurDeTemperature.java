public class ConvertisseurDeTemperature {
    public static double celsiusEnFahrenheit(double celsius) {
        return (celsius * 9/5) + 32;
    }

    public static double fahrenheitEnCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5/9;
    }

    // Ajoutez d'autres méthodes de conversion au besoin
}
