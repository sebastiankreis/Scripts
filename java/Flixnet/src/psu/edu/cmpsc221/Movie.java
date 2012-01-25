/*
 * @name  -> Dan Tracy
 * @ID    -> 927347146
 * @Group -> Me
 */
package psu.edu.cmpsc221;

public class Movie
{
    private int id;
    private String title;
    private String genre;
    private int release;
    /*
     * Creates a new movie with the ID, title, genre, and release date given.
     */

    public Movie(int ID, String mTitle, String mGenre, int mRelease)
    {
        id = ID;
        title = mTitle;
        genre = mGenre;
        release = mRelease;
    }

    public int getID()
    {
        return id;
    }

    public String getTitle()
    {
        return title.trim();
    }

    public String getGenre()
    {
        return genre.trim();
    }

    public int getYear()
    {
        return release;
    }

    public boolean equals(Movie m){
        if(
            !this.getGenre().equals(m.getGenre()) &&
             this.getID()   != m.getID()          &&
            !this.getTitle().equals(m.getTitle()) &&
             this.getYear() != m.getYear()
             ){
            return false;
        }

        return true;
    }
}