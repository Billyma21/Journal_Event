package poo;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Collections;

public class Application {
    public static void main(String[] args) {
        // Assurez-vous que le répertoire 'data' existe
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }

        // Créez l'artiste souhaité pour l'inclusion dans le fichier XML
        Artist artist = new Artist("The King of Reggae", "Bob", "Marley", Style.REGGAE);

        // Initialise correctement la date de l'événement
        LocalDateTime dateEvent = LocalDateTime.of(2023, 9, 4, 20, 0);

        // Créez le Show avec un seul artiste
        Show show = new Show("Concert Rasta", dateEvent, 50.0, Collections.singletonList(artist));

        // Sauvegarde du Show dans un fichier
        MyUtil.saveShowToFile(show, "data/show.xml");

        // Chargement du Show depuis le fichier et affichage
        Show loadedShow = MyUtil.loadShowFromFile("data/show.xml");

        System.out.println("Détails du show chargé :");
        if (loadedShow != null) {
            System.out.println(loadedShow);
        } else {
            System.out.println("Échec du chargement du show.");
        }
    }
}
