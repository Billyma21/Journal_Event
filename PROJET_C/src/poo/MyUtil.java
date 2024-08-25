package poo;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyUtil {

    private static final XStream xstream = new XStream(new DomDriver());

    static {
        xstream.alias("Show", Show.class);
        xstream.alias("artist", Artist.class);
        xstream.useAttributeFor(Show.class, "title");
        xstream.useAttributeFor(Show.class, "date");
        xstream.useAttributeFor(Show.class, "price");
        xstream.allowTypes(new Class[]{Show.class, Artist.class});
    }

    public static void saveShowToFile(Show show, String filePath) {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            xstream.toXML(show, fos);
            System.out.println("Le show a été sauvegardé avec succès dans le fichier : " + filePath);
        } catch (IOException e) {
            System.err.println("Une erreur s'est produite lors de la sauvegarde du show : " + e.getMessage());
        }
    }

    public static Show loadShowFromFile(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Show show = (Show) xstream.fromXML(fis);
            System.out.println("Le show a été chargé avec succès depuis le fichier : " + filePath);
            return show;
        } catch (IOException e) {
            System.err.println("Une erreur s'est produite lors du chargement du show : " + e.getMessage());
            return null;
        }
    }
}
