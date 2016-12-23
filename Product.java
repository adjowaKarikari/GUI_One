/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;


/**
 *
 * @author adjowa
 */
public abstract class Product 
{
    private String productID;
    private String name;
    private String year;
    private String price;
    /**
     * 
     * @param name this function takes in the name for either books or electronics
     * @param productID this function takes in the product ID for either books
     * or electronics
     * @param year this functions takes in the year for either books or electronics
     * for either books or electronics
     * @param price this function takes in the price for either books or electronics
     */
    public Product(String name,String productID,String year,String price) throws Exception
    {
        this.name = name;
        this.productID = productID;
        this.year = year;
        this.price = price;
        
        if(name.length() != 0) //exception for name
        {
            this.name = name;
        }
        else
        {
            throw new Exception("No name");
        }
        if(productID.length() == 6)//exception for product id
        {        
            this.productID = productID;
        }
        else
        {
            throw new Exception("No proID");
        }
        if(year.length() != 0)///exception for year
        {
            try
            {
                int yearInt = Integer.parseInt(year);
                if((yearInt < 1000)||(yearInt>9999))
                {
                    throw new Exception("Year in incorrect range");
                }
                else
                {
                    this.year = year;
                }
            }
            catch(NumberFormatException e)
            {
                throw new Exception("No year");
            }
        }
        else
        {
            throw new Exception("No year");
        }
        if(price.length() != 0)//exception for price
        {
            try
            {
                int priceInt = Integer.parseInt(price);
                if(priceInt <= 0)
                {
                    throw new Exception("No Price");
                }
                else
                {
                    this.price = price;
                }
            }
            catch(NumberFormatException e)
            {
                throw new Exception("No price");
            }
        }
        else
        {
            throw new Exception("No price");
        }
    }//end of product
    public String getName()
    {
        return this.name;
    }//end of getName
    public String getProductID()
    {
        return this.productID;
    }//end of getProductID
    public String getYear()
    {
        return this.year;
    }//end of getYear
    public String getPrice()
    {
        return this.price;
    }//end of getPrice
    public String toString()
    {
        return this.name + this.productID + this.year + this.price;
    }//end of toString
    public Product(Product p) throws Exception //copy constructor
    {
        this(p.name,p.price,p.productID,p.year);
    }
    
    
}
