/*
 * Adjowa Karikari(0914271)
 * Novemember 30 2016
 * CIS 2430 A4
 */
package a3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adjowa
 */
public class A3 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        try 
        {
            EStore store = new EStore();
            if(args[1] == null)
            {
                System.err.println("Proper usage is: java EStoreSearch<filename.txt>");
            }
            else
            {
                String fileName = args[2];
                store.readFile(fileName);//pass file name to read
            }
        } 
        catch (Exception ex) 
        {
            System.out.println("Error");
        }
    }
}
