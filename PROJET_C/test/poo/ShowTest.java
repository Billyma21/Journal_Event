package poo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

class ShowTest {

    private Artist artistReggae;
    private Artist artistRock;
    private Show show;

    @BeforeEach
    void setUp() {
        artistReggae = new Artist("The King of Reggae", "Bob", "Marley", Style.REGGAE);
        artistRock = new Artist("King of Rock", "John", "Doe", Style.ROCK);
        show = new Show("Concert Rasta", LocalDateTime.of(2023, 9, 4, 20, 0), 50.0, 
                         Arrays.asList(artistReggae, artistRock));
    }

    @Test
    void testConstructorWithPrice() {
        Show show = new Show("Concert Rasta", LocalDateTime.of(2023, 9, 4, 20, 0), 50.0, 
                             Arrays.asList(artistReggae, artistRock));
        Assertions.assertEquals("Concert Rasta", show.getTitle());
        Assertions.assertEquals("2023-09-04T20:00:00", show.getDate()); // Corriger le format de la date
        Assertions.assertEquals(50.0, show.getPrice());
        Assertions.assertEquals(2, show.getArtists().size());
    }

    @Test
    void testConstructorWithoutPrice() {
        Show show = new Show("Fête de la musique", LocalDateTime.of(2023, 6, 21, 18, 30));
        Assertions.assertEquals("Fête de la musique", show.getTitle());
        Assertions.assertEquals("2023-06-21T18:30:00", show.getDate()); // Corriger le format de la date
        Assertions.assertEquals(0.0, show.getPrice());
        Assertions.assertTrue(show.getArtists().isEmpty());
    }

    @Test
    void testConstructorWithSundayDate() {
        Assertions.assertThrows(InvalidDateException.class, () -> {
            new Show("Invalid Show", LocalDateTime.of(2023, 9, 3, 20, 0), 50.0, 
                     Arrays.asList(artistReggae, artistRock)); // 3 Septembre 2023 est un dimanche
        });
    }

    @Test
    void testFindBy() {
        List<Artist> result = show.findBy("REGGAE");
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("The King of Reggae", result.get(0).getPseudo());
    }

    @Test
    void testToString() {
        String expected = "Show Details:\n" +
                          "Title: Concert Rasta\n" +
                          "Date: 2023-09-04T20:00:00\n" + // Corriger le format de la date
                          "Price: 50.0 EUR\n" +
                          "Artists:\n" +
                          " - REGGAE (1 artiste(s))\n" +
                          " - ROCK (1 artiste(s))\n"; // Le nombre d'artistes pour chaque style

        Assertions.assertEquals(expected, show.toString());
    }
}
