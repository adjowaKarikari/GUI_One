/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

/**
 * {@inheritDoc}
 * {@code getAuthor This class gets the value of the author and stores it in the books constructor or it stores
 * null if nothing is entered}
 * {@code getPublisher This class gets the value of the publisher and stores it in the books constructor for Books or it stores
 * null if nothing is entered}
 * {@code toString This class gets everything related to books such as name,price,productID
 *  year,author, and publisher and returns the string}
 */
public class Books extends Product
{
    private String author;
    private String publisher;
    
    public Books(String name, String productID, String year, String author, String publisher, String price) throws Exception
    {
        super(name,productID,year,price);
        this.author = author;
        this.publisher = publisher;
    }//end of Books
    public String getAuthor()
    {
        return this.author;
    }//end of getAuthor
    public String getPublisher()
    {
        return this.publisher;
    }//end of getPublisher
    @Override
    public String toString()
    {
        return this.getName() + "\n" + this.getPrice() + "\n" + this.getProductID() + "\n" + this.getYear()+ "\n" + this.author + "\n"+ this.publisher;
    }//end of toString
    
    
}
