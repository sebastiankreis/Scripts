package edu.psu.cmpsc221.unittests;

import org.junit.Test;
import static org.junit.Assert.*;
import edu.psu.cmpsc221.*;
import java.util.ArrayList;

public class FlixNetProcessorTests
{
    
    //MAKE SURE DATABASE IS IN THE STATE ORIGINALLY GIVEN BY THE HOMEWORK
    //UPDATE FlixNetProcessor PARAMETERS TO LOCAL YOUR LOCAL INFORMATION
    @Test
    public void testRun()
    {
        //Create processor
        FlixNetProcessor processor = new FlixNetProcessor("jdbc:derby://localhost/FlixNetDB", "admin","password");

        //Add movie
        Movie addedMovie = new Movie(94358, "Some Random Movie", "Fantasy",
                2009);
        processor.addMovie(addedMovie);
        Movie savedMovie = processor.getMovie(94358);
        assertTrue(addedMovie.getTitle().equals(savedMovie.getTitle()));

        //Add customer
        Customer addedCustomer = new Customer(34651, "John A. Smith",
                new Address ("123 Elm Street", "My Apt", "Nowhere", "PA", "00000"),
                "5555551234");
        processor.addCustomer(addedCustomer);
        Customer savedCustomer = processor.getCustomer(34651);
        assertTrue(addedCustomer.getName().equals(savedCustomer.getName()));

        //get movie and customer, have customer rent movie
        Movie rentedMovie = processor.getMovie(11);
        Customer rentingCustomer = processor.getCustomer(1);
        processor.rentsMovie(rentingCustomer, rentedMovie);
        ArrayList<Movie> rentedMovies = processor.getMovieList(rentingCustomer);
        boolean foundMatch = false;
        for(int i = 0; i < rentedMovies.size(); i++)
            if(rentedMovies.get(i).getID() == rentedMovie.getID())
                foundMatch = true;
        assertTrue(foundMatch);

        //lookup a rating of a movie
        Movie ratingMovie = processor.getMovie(55);
        assertEquals(processor.getRating(ratingMovie), 3.0, 0.1);

        //get the popularity of some movie
        Movie popMovie = processor.getMovie(55);
        assertEquals(processor.getPopularity(popMovie, "PA"), 1);
    }
}
