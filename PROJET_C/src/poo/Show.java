package poo;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("Show")
public class Show extends Evenement implements Searchable {

    @XStreamAsAttribute
    private String title;

    @XStreamAsAttribute
    private String date;

    @XStreamAsAttribute
    private double price;

    @XStreamImplicit(itemFieldName = "artist")
    private List<Artist> artists;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public Show(String title, LocalDateTime dateEvent, double price, List<Artist> artists) {
        super(title, dateEvent);
        if (dateEvent.getDayOfWeek() == DayOfWeek.SUNDAY) {
            throw new InvalidDateException("Shows cannot be scheduled on Sundays.");
        }
        this.title = title;
        this.date = dateEvent.format(DATE_FORMATTER);
        this.price = price;
        this.artists = artists != null ? artists : new ArrayList<>();
    }

    public Show(String title, LocalDateTime dateEvent) {
        this(title, dateEvent, 0.0, new ArrayList<>());
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public List<Artist> getArtists() { return artists; }
    public void setArtists(List<Artist> artists) { this.artists = artists; }

    @Override
    public List<Artist> findBy(String s) {
        return artists.stream()
                      .filter(artist -> artist.getStyle().name().equalsIgnoreCase(s))
                      .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Show Details:\n");
        sb.append("Title: ").append(title).append("\n");
        sb.append("Date: ").append(date).append("\n");
        sb.append("Price: ").append(price).append(" EUR\n");
        sb.append("Artists:\n");

        // Group artists by style
        var artistsByStyle = artists.stream()
                                    .collect(Collectors.groupingBy(Artist::getStyle));

        for (var entry : artistsByStyle.entrySet()) {
            sb.append(" - ").append(entry.getKey().name()).append(" (")
              .append(entry.getValue().size()).append(" artiste(s))\n");
        }

        return sb.toString();
    }
}
