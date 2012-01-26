/*
 * @name  -> Dan Tracy
 * @ID    -> 927347146
 * @Group -> Me
 */
package edu.psu.cmpsc221;

public class Customer
{
    private int custID;
    private String custName;
    private Address custAddr;
    private String phoneNum;

    public Customer(int ID, String name, Address addr, String phone){
        custID = ID;
        custName = name;
        custAddr = addr;
        phoneNum = phone;
    }

    public int getID(){
        return custID;
    }

    public String getName(){
        return custName.trim();
    }

    public Address getAddress(){
        return custAddr;
    }

    public String getPhone(){
        return phoneNum.trim();
    }

    public String print(){
        String sp = "\t";
        return(custID + sp + custName  + sp
                + custAddr.printAddr() + sp + phoneNum);
    }

    @Override
    public int hashCode() {
        return this.custID;
    }

    @Override
    public boolean equals(Object c){
        if( c == null || c.getClass() != this.getClass())
            return false;
        return this.equals((Customer) c);
    }
    public boolean equals(Customer c){
        if( !c.getAddress().equals(this.getAddress() ) ){return false;}
        if( c.getID() != this.getID() ){return false;}
        if( !c.getName().equals( this.getName() ) )   {return false;}
        if( !c.getPhone().equals( this.getPhone() )) {return false;}
        return true;
    }
}
