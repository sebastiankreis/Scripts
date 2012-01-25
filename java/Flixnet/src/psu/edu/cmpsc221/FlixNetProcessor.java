/*
 * @name  -> Dan Tracy
 * @ID    -> 927347146
 * @Group -> Me
 */

package psu.edu.cmpsc221;

import java.util.ArrayList;
import java.sql.*;
import java.util.HashMap;

public class FlixNetProcessor
{
    private String user;
    private String pass;
    private String URL;
    private ResultSet results;
    private Statement statement;
    
    private Connection con = null;
    /*
     * Creates a FlixNet processor object that is associated with the database
     * connection given by the passed URL, user name, and password parameters.
     * The FlixNetProcessor will be used to interact the Derby database.
     */
    public FlixNetProcessor(String mURL, String username, String password)
    {
        URL = mURL;
        user = username; // "admin";
        pass = password; //"password";
    }

    /*
     * Adds the customer given by the specified parameter to the database.
     */
    public void addCustomer(Customer c)
    {
        if( c == null){return;}
        PreparedStatement s = null;
        
        Address addr    = c.getAddress();
        String zip      = addr.getZip();
        String addr1    = addr.getLine1();
        String addr2    = addr.getLine2();
        String st       = addr.getState();
        String city     = addr.getCity();
        String cname    = c.getName();
        String phone    = c.getPhone();
        int id          = c.getID();
        
        try{
            openConnection();
            String query = "INSERT INTO Customer" +
                           "(cid,cname,address1,address2,city,st,zip,phone)" +
                           " VALUES " +
                           "(?, ?, ?, ?, ?, ?, ?, ?)";
            // This is to prevent the user from entering duplicate entries
            // in the database which would cause an error since the primary
            // key could potentially no longer be unique
            statement = con.createStatement();
            results = statement.executeQuery(
                    "SELECT cid FROM CUSTOMER WHERE cid = " + id
                    );

            if(results.next() != true){
                s = con.prepareStatement(query);
                s.clearParameters();
                s.setInt(1, id);
                s.setString(2, cname);
                s.setString(3, addr1);
                s.setString(4, addr2);
                s.setString(5, city);
                s.setString(6, st);
                s.setString(7, zip);
                s.setString(8, phone);
                s.executeUpdate();
            }
        
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnections();
            try{ 
                if( s != null ){s.close();}
                s = null;
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    /*
     * Adds the movie given by the specified parameter to the database.
     */
    public void addMovie(Movie m)
    {
        if(m == null){return;}
        PreparedStatement s = null;
        String genre = m.getGenre();
        String title = m.getTitle();
        int id = m.getID();
        int release = m.getYear();
        
        try{
            openConnection();
            //Format
            //(mid, title, genre, yr)
            String query = "INSERT INTO Movie " +
                           "(mid, title, genre, yr)" +
                           " VALUES (?, ?, ?, ?) ";
            // This is to prevent the user from entering duplicate entries
            // in the database which would cause an error since the primary
            // key could potentially no longer be unique
            statement = con.createStatement();
            results = statement.executeQuery(
                    "SELECT mid FROM MOVIE WHERE mid = " + id
                    );

            if(results.next() != true){
                s = con.prepareStatement(query);
                s.clearParameters();
                s.setInt(1, id);
                s.setString(2, title);
                s.setString(3, genre);
                s.setInt(4, release);
                s.executeUpdate();
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnections();
            try{
                if( s != null ){s.close();}
                s = null;
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    /*
     * Returns a new customer object based on the database entry for the given
     * customer ID.
     */
    public Customer getCustomer(int ID)
    {
        Customer cust = null;
        String query = "SELECT * " +
                       "FROM Customer " +
                       "WHERE cid = " + ID;
      
        try{
            openConnection();
            statement = con.createStatement();
            results = statement.executeQuery(query);
            if(results.next() == true){
                String name     = results.getString("CNAME").trim();
                String addr1    = results.getString("ADDRESS1").trim();
                String addr2;
                if(results.getString("ADDRESS2") == null){
                    addr2 = "";
                }else{addr2 = results.getString("ADDRESS2");}
                String city     = results.getString("CITY").trim();
                String state    = results.getString("ST").trim();
                String zip      = results.getString("ZIP").trim();
                String phone    = results.getString("PHONE").trim();

                Address address = new Address(addr1,addr2,city,state,zip);
                cust = new Customer(ID, name, address, phone);
            }
           
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnections();
        }
        
        return cust;
    }

    /*
     * Returns a new movie object based on the database entry for the given
     * movie ID.
     */
    public Movie getMovie(int ID)
    {
        Movie tempMovie = null;
        String title = null;
        String genre = null;
        int year = 0;
        String query = "SELECT title, genre, yr " +
                       "FROM Movie " +
                       "WHERE mid = "+ ID;


        try{
            openConnection();
            statement = con.createStatement();
            results = statement.executeQuery(query);

            while(results.next()){
                title = results.getString("TITLE").trim();
                genre = results.getString("GENRE").trim();
                year  = results.getInt("YR");
                tempMovie = new Movie(ID, title, genre, year);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnections();
        }
        return tempMovie;
    }

    /*
     * Returns the average rating for the given movie m using the stored
     *database connection.
     */
    public double getRating(Movie m)
    {
        if(m == null){return 0;}
        float average = 0;
        int ID = m.getID();

        try{
            openConnection();
            statement = con.createStatement();
            results = statement.executeQuery(
                    "SELECT AVG (rating) " +
                    "FROM Rents " +
                    "WHERE mid = " + ID);

            while(results.next()){
                average = results.getInt(1);
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnections();
        }
        return average;
    }

    /*
     * Returns the number of customers located in the provided state that
     * have rented the specified movie.
     */
    public int getPopularity(Movie m, String state)
    {
        if(m == null){ return 0; }
        if(state == null || state.equals("")){return 0;}
       
        int id = m.getID();
        int popularity = 0;

        //Escape the string and capitalize it
        state = "\'" + state.toUpperCase() + "\'"; 
        String query = "SELECT COUNT (DISTINCT R.cid) " +
                "FROM Rents R, Customer Cu " +
                "WHERE R.CID = Cu.CID AND R.MID = " + id +
                "AND Cu.ST = "+ state;
        try{

            openConnection();
            statement = con.createStatement();
            results = statement.executeQuery(query);
            while(results.next()){
                popularity = results.getInt(1);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnections();
        }

        return popularity;
    }

    /*
     * Invalidates FlixNetProcessor object so that it no longer accepts any new
     * requests. This should close any open database connections.
     */
    public void invalidate()
    {
        closeConnections();
        closeDBConnection();
    }

    /*
     * Returns a list of all rented movies for the given customer c.
     */
    public ArrayList<Movie> getMovieList(Customer c)
    {
        //if no movie is provided then return an empty list
        if (c == null){return new ArrayList<Movie>(0);}

        int id = c.getID();
        ArrayList<Movie> list = new ArrayList<Movie>();

        String query = "SELECT DISTINCT * " +
                       "FROM MOVIE M, RENTS R " +
                       "WHERE M.MID = R.MID AND R.CID = " + id;
        try{

            openConnection();
            statement = con.createStatement();
            results = statement.executeQuery(query);

            while( results.next() ){
                String title = results.getString("TITLE");
                String genre = results.getString("GENRE");
                int mId = results.getInt("MID");
                int year = results.getInt("YR");
                list.add( new Movie(mId, title, genre, year));
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnections();
        }
        return list;
    }

    /*
     * Adds to the database information that Customer c rented Movie m.  No
     * rating information is recorded.
     */
    public void rentsMovie(Customer c, Movie m)
    {
        int cId = c.getID();
        int mId = m.getID();
        PreparedStatement s = null;
        String query = "INSERT INTO Rents (cid, mid) " +
                       "VALUES ( ?, ?)";
        
        try{

            openConnection();
            statement = con.createStatement();
            results = statement.executeQuery(
                    "SELECT cid, mid FROM Rents " +
                    "WHERE " +
                    "cid = " + cId + " AND " +
                    "mid = " + mId
                    );

            if(results.next() != true){
                s = con.prepareStatement(query);
                s.clearParameters();
                s.setInt(1, cId);
                s.setInt(2, mId);
                s.executeUpdate();
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{ 
                if( s!= null){s.close();}
                s = null;
            }
            catch(SQLException e){e.printStackTrace();}
            closeConnections();
        }
    }

    public ArrayList<Movie> getMovieDB(String arg){
        // The arg string is actually a customized query
        // the function will return the result set of the
        // query as an array list of movie objects
        if(arg == null){arg = "mid";}
        ArrayList<Movie> list = new ArrayList<Movie>(0);
        String query = " SELECT  * " +
                       " FROM MOVIE " +
                       " ORDER BY " + arg + " ASC";
        try{
            openConnection();
            statement = con.createStatement();
            results = statement.executeQuery(query);

            while( results.next() ){
                String title = results.getString("TITLE").trim();
                String genre = results.getString("GENRE").trim();
                int mId = results.getInt("MID");
                int year = results.getInt("YR");
                list.add( new Movie(mId, title, genre, year));
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnections();
        }

        return list;
    }

    public ArrayList<Customer> getCustDB(String arg){
    //Similar to the getMovieDB except this returns customers
        if( arg == null){arg = "cid";}

        ArrayList<Customer> list = new ArrayList<Customer>(0);

        String query = "SELECT * " +
                       "FROM CUSTOMER " +
                       "ORDER BY " + arg + " ASC";

        try{
            openConnection();
            statement = con.createStatement();
            results = statement.executeQuery(query);

            while( results.next() ){
                int id          = results.getInt("CID");
                String name     = results.getString("CNAME").trim();
                String addr1    = results.getString("ADDRESS1").trim();
                String addr2;
                if(results.getString("ADDRESS2") == null){addr2 = "";}
                else{addr2 = results.getString("ADDRESS2").trim();}
                String city     = results.getString("CITY").trim();
                String state    = results.getString("ST").trim();
                String zip      = results.getString("ZIP").trim();
                String phone    = results.getString("PHONE").trim();
                list.add( 
                        new Customer(id,name,
                             new Address(addr1,addr2,city,state,zip), phone));
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnections();
        }

        return list;
    }

    public ArrayList<String> getRentalDB(){
        ArrayList<String> list = new ArrayList<String>();
        String sp = "\t";
        String query = "select distinct r.cid, r.mid, m.title, cname " +
                       "from Customer cu, Rents r, Movie m " +
                       "where cu.CID = r.CID " +
                       "and m.MID = r.MID";
        try{
            openConnection();
            statement = con.createStatement();
            results = statement.executeQuery(query);

            while( results.next() ){
                int cid = results.getInt("CID");
                int mid = results.getInt("MID");
                String title = results.getString("TITLE").trim();
                String name  = results.getString("CNAME").trim();
                list.add(cid + sp + mid + sp + title + sp + name );
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnections();
        }

        return list;
    }
    
    public HashMap<Integer, String> getPopByState(String state){
        if(state == null){state = "AK";}
        HashMap<Integer,String> temp = new HashMap();
        String query = "SELECT DISTINCT m.title, avg(r.RATING) " +
                       "FROM Rents r, Movie m , Customer cu " +
                       "WHERE cu.CID = r.CID AND " +
                       "cu.ST = \'" + state +"\' AND " +
                       "m.MID = r.MID " +
                       "GROUP BY m.TITLE ";
        
        try{
            openConnection();
            statement = con.createStatement();
            results = statement.executeQuery(query);
            
            while(results.next()){
                int rating = results.getInt("2");
                String title = results.getString("TITLE");
                System.out.println(title +"\t" + rating);
                temp.put(rating, title);
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnections();
        }
        
        return temp;
    }
    
    private void openConnection() throws SQLException{
        //if the connection is not null then theres an open connection
        if(con != null){return;}        
        con = DriverManager.getConnection(URL, user, pass);
    }

    private void closeDBConnection(){
        try{
            if(con!=null){
                con.close();
                con = null;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    private void closeConnections(){
        try{
            if(statement != null){
                statement.close();
                statement = null;
            }
            if(results != null){
                results.close();
                results = null;
            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
