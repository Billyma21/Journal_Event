package poo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("artist")
public class Artist {

    private String pseudo;
    private String firstname;
    private String lastname;
    private Style style;

    public Artist(String pseudo, String firstname, String lastname, Style style) {
        this.pseudo = pseudo;
        this.firstname = firstname;
        this.lastname = lastname;
        this.style = style;
    }

    // Getters et Setters
    public String getPseudo() { return pseudo; }
    public void setPseudo(String pseudo) { this.pseudo = pseudo; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public Style getStyle() { return style; }
    public void setStyle(Style style) { this.style = style; }
}
