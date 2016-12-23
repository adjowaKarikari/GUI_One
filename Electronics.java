/*
  {@inheritDoc}
 * {@code maker This class gets the value of the maker for electronics or no value at all}
 */
package a3;

/**
 *
 * @author adjowa
 */
public class Electronics extends Product
{
    private String maker;
    
    public Electronics(String name, String productID, String year, String price, String maker) throws Exception
    {
        super(name,productID,year,price);
        this.maker = maker;
    }//end of electronics
    public String getMaker()
    {
        return this.maker;
    }//end of getMaker
    @Override
    public String toString()
    {
        return this.getName() + "\n" + this.getPrice() + "\n" + this.getProductID() + "\n" + this.getYear() + "\n" + this.maker;
    }//end of toString
    
}
