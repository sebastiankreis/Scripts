/*
 * @name  -> Dan Tracy
 * @ID    -> 927347146
 * @Group -> Me
 */
package edu.psu.cmpsc221;

public class Address
{
    private String addr1, addr2, custCity, custState, custZip;

    public Address(String line1, String line2, String city,
                   String state, String zip){
        addr1 = line1;
        if(line2==null){
            addr2="";
        }else{
            addr2 = line2;
        }
        custCity = city;
        custState = state;
        custZip = zip;
    }

    public String getLine1 (){
        return addr1.trim();
    }

    public String getLine2 (){
        return addr2.trim();
    }

    public String getCity (){
        return custCity.trim();
    }

    public String getState (){
        return custState.trim();
    }

    public String getZip (){
        return custZip.trim();
    }

    public String printAddr(){
        String sp = "\t";
        String temp = getLine1() + sp + sp +
                      getLine2() + sp + sp + sp +
                      getCity()  + sp + sp +
                      getState() + sp + sp +
                      getZip();
        return temp;
    }

    @Override
    public int hashCode() {
        return this.printAddr().hashCode();
    }

    @Override
    public boolean equals( Object a){
        if( a == null || a.getClass() != this.getClass() )
            return false;
        return this.equals((Address) a);

    }
    public boolean equals( Address a){
        if( !addr1.equals( a.getLine1() ))     {return false;}
        if( !addr2.equals( a.getLine2() ))     {return false;}
        if( !custCity.equals( a.getCity() ))   {return false;}
        if( !custState.equals( a.getState() )) {return false;}
        if( !custZip.equals( a.getZip() ))     {return false;}
        return true;
    }
}
