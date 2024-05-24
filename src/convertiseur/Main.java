import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JComboBox<String> categorieComboBox;
    private JComboBox<String> conversionComboBox;
    private JTextField champDeSaisie;
    private JLabel etiquetteResultat;

    public Main() {
        setTitle("Convertisseur d'Unités");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.WHITE);

        // Style personnalisé de l'interface utilisateur
        Font font = new Font("Segoe UI", Font.PLAIN, 14);
        UIManager.put("Label.font", font);
        UIManager.put("ComboBox.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("Button.font", font);
        UIManager.put("Button.background", new Color(66, 165, 245));
        UIManager.put("Button.foreground", Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        JLabel etiquetteCategorie = new JLabel("Sélectionnez la Catégorie:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(etiquetteCategorie, gbc);

        categorieComboBox = new JComboBox<>(new String[]{"Longueur", "Poids", "Température", "Vitesse", "Volume", "Temps", "Devise"});
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(categorieComboBox, gbc);

        JLabel etiquetteConversion = new JLabel("Sélectionnez la Conversion:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(etiquetteConversion, gbc);

        conversionComboBox = new JComboBox<>();
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(conversionComboBox, gbc);

        JLabel etiquetteSaisie = new JLabel("Entrez la Valeur:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(etiquetteSaisie, gbc);

        champDeSaisie = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(champDeSaisie, gbc);

        JButton boutonConvertir = new JButton("Convertir");
        boutonConvertir.addActionListener(new ConvertButtonListener());
        boutonConvertir.setBackground(new Color(66, 165, 245)); // Set button background color
        boutonConvertir.setForeground(Color.WHITE); // Set button text color
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(boutonConvertir, gbc);

        etiquetteResultat = new JLabel("", SwingConstants.CENTER);
        etiquetteResultat.setFont(font.deriveFont(Font.BOLD)); // Set result label font to bold
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(etiquetteResultat, gbc);

        categorieComboBox.addActionListener(new CategoryComboBoxListener());
        updateConversionOptions();

        setVisible(true);
    }

    private void updateConversionOptions() {
        conversionComboBox.removeAllItems();
        String selectedCategory = (String) categorieComboBox.getSelectedItem();

        switch (selectedCategory) {
            case "Longueur":
                conversionComboBox.addItem("Mètres en Kilomètres");
                conversionComboBox.addItem("Kilomètres en Mètres");
                break;
            case "Poids":
                conversionComboBox.addItem("Grammes en Kilogrammes");
                conversionComboBox.addItem("Kilogrammes en Grammes");
                break;
            case "Température":
                conversionComboBox.addItem("Celsius en Fahrenheit");
                conversionComboBox.addItem("Fahrenheit en Celsius");
                break;
            case "Vitesse":
                conversionComboBox.addItem("Kilomètres par heure en Miles par heure");
                conversionComboBox.addItem("Miles par heure en Kilomètres par heure");
                break;
            case "Volume":
                conversionComboBox.addItem("Litres en Gallons");
                conversionComboBox.addItem("Gallons en Litres");
                break;
            case "Temps":
                conversionComboBox.addItem("Heures en Minutes");
                conversionComboBox.addItem("Minutes en Heures");
                break;
            case "Devise":
                conversionComboBox.addItem("TND en EUR");
                conversionComboBox.addItem("EUR en TND");
                conversionComboBox.addItem("TND en USD");
                conversionComboBox.addItem("USD en TND");
                conversionComboBox.addItem("EUR en USD");
                conversionComboBox.addItem("USD en EUR");
                break;
            default:
                break;
        }
    }

    private class CategoryComboBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateConversionOptions();
        }
    }

    private class ConvertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double inputValue = Double.parseDouble(champDeSaisie.getText());
                if (inputValue <= 0) {
                    throw new IllegalArgumentException("Veuillez entrer un nombre valide supérieur à 0.");
                }

                String selectedConversion = (String) conversionComboBox.getSelectedItem();
                double result = 0;

                switch (selectedConversion) {
                    case "Mètres en Kilomètres":
                        result = ConvertisseurDeLongueur.metresEnKilometres(inputValue);
                        break;
                    case "Kilomètres en Mètres":
                        result = ConvertisseurDeLongueur.kilometresEnMetres(inputValue);
                        break;
                    case "Grammes en Kilogrammes":
                        result = ConvertisseurDePoids.grammesEnKilogrammes(inputValue);
                        break;
                    case "Kilogrammes en Grammes":
                        result = ConvertisseurDePoids.kilogrammesEnGrammes(inputValue);
                        break;
                    case "Celsius en Fahrenheit":
                        result = ConvertisseurDeTemperature.celsiusEnFahrenheit(inputValue);
                        break;
                    case "Fahrenheit en Celsius":
                        result = ConvertisseurDeTemperature.fahrenheitEnCelsius(inputValue);
                        break;
                    case "Kilomètres par heure en Miles par heure":
                        result = ConvertisseurDeVitesse.kphEnMph(inputValue);
                        break;
                    case "Miles par heure en Kilomètres par heure":
                        result = ConvertisseurDeVitesse.mphEnKph(inputValue);
                        break;
                    case "Litres en Gallons":
                        result = ConvertisseurDeVolume.litresEnGallons(inputValue);
                        break;
                    case "Gallons en Litres":
                        result = ConvertisseurDeVolume.gallonsEnLitres(inputValue);
                        break;
                    case "TND en EUR":
                        result = ConvertisseurDeDevise.tndEnEur(inputValue);
                        break;
                    case "EUR en TND":
                        result = ConvertisseurDeDevise.eurEnTnd(inputValue);
                        break;
                    case "TND en USD":
                        result = ConvertisseurDeDevise.tndEnUsd(inputValue);
                        break;
                    case "USD en TND":
                        result = ConvertisseurDeDevise.usdEnTnd(inputValue);
                        break;
                    case "EUR en USD":
                        result = ConvertisseurDeDevise.eurEnUsd(inputValue);
                        break;
                    case "USD en EUR":
                        result = ConvertisseurDeDevise.usdEnEur(inputValue);
                        break;
                    case "Heures en Minutes":
                        result = ConvertisseurDeTemps.heuresEnMinutes(inputValue);
                        break;
                    case "Minutes en heures":
                        result = ConvertisseurDeTemps.minutesEnHeures(inputValue);
                        break;
                    default:
                        break;
                }

                // Format result to display with two decimal places
                String formattedResult = String.format("%.2f", result);
                etiquetteResultat.setText("Résultat: " + formattedResult);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(Main.this, "Veuillez entrer un nombre valide", "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(Main.this, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
