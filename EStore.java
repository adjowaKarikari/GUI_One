/***************************************
 * Adjowa Karkari(0914271)             *
 * CIS 2430 A3                         *
 * November 30 2016                    *
 **************************************/
package a3;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.border.TitledBorder;

/**
 * @author adjowa
 * {@code mainWindow This method sets up the inital JFrame for the GUI}
 * {@code mainMenu This method sets up the inital JPanel for the mainMenu with
 * options-> add,search,quit and a welcome message to the user}
 * {@code addWindow This method sets up the JPanel for adding items to Books with
 * all the required fields plus the add and reset buttons}
 * {@code elecWindow This method sets up the JPanel for adding items to Electronics with all
 * the required fields plus the add and reset buttons}
 * {@code addBook This function adds all the items from the previously entered Books fields to 
 * the constructor for the Books class, then adds the constructor to the arrayList, then adds the
 * items to the hashMap}
 * {@code addElec This function adds all the items from the previously entered Electronics fields to the
 * constructor for the Electronics class, then adds the constructor to the arrayList, then adds the
 * items to the hashMap}
 * {@code searchWin This function sets up the JPanel for the searchWindow with all the required fields
 * such as name, start and end year, and productID, along with the add and reset buttons}
 * {@code searchField This function checks to see what fields the user has filled out in order
 * to call the correct functions to implement the specific search}
 * {@code addHash This function adds all the names to the hashMap with an index related to the hashMap}
 * {@code searchHash This function implements the hashMap search by name keywords as parameters and returns all the
 * values associated with the keywords}
 * {@code time This function searches for the year and returns all products with the specific year}
 * {@code productID This function searches for just the productID and returns all related products}
 * {@code productIDYear This function searches for both productID and Year and returns all related products}
 * {@code writeWindow This function creates a JPanel for the quit option in order to take in a file name
 * as input and return that name to a function that writes to the file}
 * {@code write This function writes to the user specified file}
 * {@code resetFieldsBooks This function sets all the books fields to empty}
 * {@code resetFieldsElec This function sets all the electronic fields to empty}
 * {@code resetSearch This function sets all the search fields to empty}
 * {@code getProduct This function checks to see if the user picked books or electronics on the books panel}
 * {@code getProduct2 This function checks to see if the user picked books or electonics on the electronic panel}
 * {@code addListener This functions adds an actionlistener to the add Book panel}
 * {@code elecListener This function adds an actionListener to the Electronic panel}
 * {@code searchListener This functions adds an actionListener to the search panel}
 * {@code exitListener This function adds an actionListneer to the exit panel}
 * {@code bookError This function makes sure that the input entered in the books fields are valid}
 * {@code elecError This function makes sure that the input entered in the electronics fields are valid}
 * {@code readFile This function reads the file from the beginning}
 */
public class EStore extends JFrame
{
    /***************************************
    *          Variables                   *
    ****************************************/
    private static JFrame main;
    private static JMenu mainMenu;
    private static JMenuItem adds,search,quit;
    private static JLabel title,enter,bProductID,bName,bPrice,bYear,bAuthor,bPublisher,
                          eName,ePrice,eYear,eMaker,eProductID,
                          sProID,sName,sStartYear,sEndYear,prompt;
    private static JMenuBar menuBar;   
    private static JPanel mainPanel,addBook,searchArea,addElec,outerField,field,buttonField,fileWrite,inner;
    private static JTextArea area;
    private static JComboBox options,options2;
    private static JButton add,reset,searc,Enter;
    private static JTextField bProField,bNameField,bPriceField,bYearField,bAuthorField,
                              bPubField,eProField,eNameField,ePriceField,
                              eYearField,eMakerField,promptField,
                              sProField,sNameField,sStartField,sEndField;
    private static TitledBorder titled;
    
    ArrayList<Product>getProduct = new ArrayList<>();
    HashMap<String,int[]> map = new HashMap();
    
    Books checkBook;
    Electronics checkElec;

    /*************************************************
     *                 Fonts                         *
     *************************************************/
    Font font = new Font("Arial",Font.BOLD,40);
    Font fontTwo = new Font("Times New Romans",Font.PLAIN,12);
    Font fontThree = new Font("Times New Romans",Font.BOLD,20);
    
    protected EStore() throws Exception
    {
        super();
        this.checkElec = new Electronics("name","888888","1998","12000","maker"); //dummy constructor
        this.checkBook = new Books("name","999999","1998","author","publisher","199999"); //dummy constructor
        mainMenu();  
        mainWindow();
    }//end of EStore
    
    /*****************************************************
     *                Main Window                        *
     *****************************************************/
    private void mainWindow()
    {
        main = new JFrame("E-Store Search");
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize(680,580);
        main.setLocationRelativeTo(null);
        main.setResizable(false);
        main.getContentPane().setBackground(Color.white);
        main.setJMenuBar(menuBar);
        main.add(mainPanel);
        main.setVisible(true);
     }//end of mainWindow
    private void mainMenu()
    {
        /* Main Panel */
        mainPanel = new JPanel();
        addBook = new JPanel();
        fileWrite = new JPanel();
        searchArea = new JPanel();
        addElec = new JPanel();
        
        mainPanel.setLayout(new GridLayout(3,1));
        mainPanel.setBackground(Color.white);
      
        title = new JLabel("Welcome to E-Store",JLabel.CENTER);
        title.setFont(font);
        enter = new JLabel("Choose a command from the commands menu above for adding a product,searching a product,or quitting the program.");
       
        /* Menu Bar */
        menuBar = new JMenuBar();
        mainMenu = new JMenu("Command");
        menuBar.add(mainMenu);
        adds = new JMenuItem("Add");
        search = new JMenuItem("Search");
        quit = new JMenuItem("Quit");
        mainMenu.add(adds);
        mainMenu.add(search);
        mainMenu.add(quit);
        
        /* Action Listeners */
        adds.addActionListener(new addListener());
        quit.addActionListener(new exitListener());
        search.addActionListener(new searchListener());

        mainPanel.add(title);
        mainPanel.add(enter);
    }//end of mainMenu  
    
    /****************************************************
     *                Add Window                        *
     ****************************************************/
    public void addWindow()
    {
        /* Main Panel */
        addBook = new JPanel();
        addBook.setSize(200, 200);
        GridLayout lay= new GridLayout(2,1);
        addBook.setLayout(lay);
        addBook.setBackground(Color.white);  
        addBook.removeAll();
        /* Top Panel */
        outerField = new JPanel();
        GridLayout l = new GridLayout(1,3);
        outerField.setLayout(l);
        outerField.setVisible(true);
        
        /* Add Fields */
        bProductID = new JLabel("Product ID:",JLabel.CENTER);
        bProField = new JTextField(6);
        bName = new JLabel("Name:",JLabel.CENTER);
        bNameField = new JTextField(20);
        bPrice = new JLabel("Price:",JLabel.CENTER);
        bPriceField = new JTextField("");
        bYear = new JLabel("Year:",JLabel.CENTER);
        bYearField = new JTextField("");
        bAuthor = new JLabel("Author:",JLabel.CENTER);
        bAuthorField = new JTextField("");
        bPublisher = new JLabel("Publisher:",JLabel.CENTER);
        bPubField = new JTextField("");
        
        /* Field Panel */
        field = new JPanel();
        field.setBackground(Color.white);
        field.setLayout(new BoxLayout(field, BoxLayout.PAGE_AXIS));
        field.add(bProductID);
        field.add(bProField);
        field.add(bName);
        field.add(bNameField);
        field.add(bPrice);
        field.add(bPriceField);
        field.add(bYear);
        field.add(bYearField);
        field.add(bAuthor);
        field.add(bAuthorField);
        field.add(bPublisher);
        field.add(bPubField);
        outerField.add(field);
        field.setVisible(true);
        
        /* Combo Box */
        buttonField = new JPanel();
        FlowLayout lp = new FlowLayout();
        String [] option = {"Electronics","Books"};
        options = new JComboBox(option);
       
        /*  Combo Box Listener */
        options.addActionListener (new ActionListener () 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                getProduct(options.getSelectedIndex()); 
            }//end of actionPerformed
        });//end of actionListener
        
        /* Buttons field */
        add = new JButton("add");
        reset = new JButton("Reset");
        add.addActionListener(new addListener());
        reset.addActionListener(new addListener());
        buttonField.add(add);
        buttonField.add(reset);
        buttonField.add(options);
        buttonField.setBackground(Color.white);
        outerField.add(buttonField);
        addBook.add(outerField);
        buttonField.setVisible(true);
        
        /* Message Area */
        area = new JTextArea(10, 50);	
        titled = new TitledBorder("Adding Books");		
	area.setLineWrap(true);						
	area.setEditable(false);		
	JScrollPane scrollText = new JScrollPane(area);		
	scrollText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);		
        addBook.add(scrollText);
        addBook.setBorder(titled);
        
        main.add(addBook);
        addBook.setVisible(true);
        mainPanel.setVisible(false);
        searchArea.setVisible(false);
        fileWrite.setVisible(false);
        addElec.setVisible(false);
    }//End of add window
    public void elecWindow()
    {
        /* Main Panel */
        addElec = new JPanel();
        addElec.setSize(200, 200);
        GridLayout lay= new GridLayout(2,1);
        addElec.setLayout(lay);
        addElec.setBackground(Color.white);
        
        addElec.removeAll();
        
        add = new JButton("Add");
        add.addActionListener(new elecListener());
        reset = new JButton("Reset");
        reset.addActionListener(new elecListener());
        
        /* Top Panel */
        outerField = new JPanel();
        GridLayout l = new GridLayout(1,3);
        outerField.setLayout(l);
        
        /* Add Fields */
        eProductID = new JLabel("Product ID:",JLabel.CENTER);
        eProField = new JTextField("");
        eName = new JLabel("Name:",JLabel.CENTER);
        eNameField = new JTextField("");
        ePrice = new JLabel("Price:",JLabel.CENTER);
        ePriceField = new JTextField("");
        eYear = new JLabel("Year:",JLabel.CENTER);
        eYearField = new JTextField("");
        eMaker = new JLabel("Maker:",JLabel.CENTER);
        eMakerField = new JTextField("");
        
        /* Field Panel */
        field = new JPanel();
        field.setBackground(Color.white);
        field.setLayout(new BoxLayout(field, BoxLayout.PAGE_AXIS));
        field.add(eProductID);
        field.add(eProField);
        field.add(eName);
        field.add(eNameField);
        field.add(ePrice);
        field.add(ePriceField);
        field.add(eYear);
        field.add(eYearField);
        field.add(eMaker);
        field.add(eMakerField);
        outerField.add(field);
        
        /* Buttons Field */
        buttonField = new JPanel();
        FlowLayout lp = new FlowLayout();
        String [] option2 = {"Electronics","Books"};
        
        /* ComboBox Listener */
        options2 = new JComboBox(option2);
        options2.addActionListener (new ActionListener () 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                getProduct2(options2.getSelectedIndex());
            }//end of actionPerformed
        });//end of addactionListener
        
        buttonField.add(add);
        buttonField.add(reset);
        buttonField.add(options);
        buttonField.setBackground(Color.white);
        outerField.add(buttonField);
        addElec.add(outerField);
        
        /* Message Area */
        area = new JTextArea(10, 50);	
        titled = new TitledBorder("Adding Electronics");		
	area.setLineWrap(true);						
	area.setEditable(false);		
	JScrollPane scrollText = new JScrollPane(area);		
	scrollText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);		
        addElec.add(scrollText);
        addElec.setBorder(titled);
              
        main.add(addElec);
        addElec.setVisible(true);
        mainPanel.setVisible(false);
        searchArea.setVisible(false);
        fileWrite.setVisible(false);
        addBook.setVisible(false);
    }//end of elecWindow
    public void addToBook() throws Exception
    {
        Books getBook = new Books(bNameField.getText(),bProField.getText(),bYearField.getText(),bAuthorField.getText(),bPubField.getText(),bPriceField.getText()); 
        getProduct.add(getBook); //adds to the arrayList
        addHash(); //populates hashMap
    }//end of addToBook
    public void bookError()
    {
        if((bProField.getText().length() == 0)||(bProField.getText().length()>6))
        {
            area.setText("Product ID is not equal to 6\n");
        }
        if(bNameField.getText().length() == 0)
        {
            area.setText("No name has been entered\n");
        }
        if(bYearField.getText().length() == 0)
        {
            area.append("No year has been entered\n");
        }
        if(bPriceField.getText().length() == 0)
        {
            area.append("No price has been entered\n");
        }
    }//end of bookError
    public void elecError()
    {
        if((eProField.getText().length() == 0)||(bProField.getText().length()>6))
        {
            area.setText("Product ID is not equal to 6\n");
        }
        if(eNameField.getText().length() == 0)
        {
            area.setText("No name has been entered\n");
        }
        if(eYearField.getText().length() == 0)
        {
            area.append("No year has been entered\n");
        }
        if(ePriceField.getText().length() == 0)
        {
            area.append("No price has been entered\n");
        }
    }//end of elecError
    public void addToElec() throws Exception
    {
        Electronics getElec = new Electronics(eNameField.getText(),eProField.getText(),eYearField.getText(),ePriceField.getText(),eMakerField.getText());
        getProduct.add(getElec); //adds to the arraylist
        addHash();//adds to the hashMap
    }//end of addToElec
    
    /***************************************************
     *           Search Window                         *
     ***************************************************/
    public void searchWin()
    {
        /* Main Panel */
        searchArea = new JPanel();
        searchArea.setSize(200, 200);
        GridLayout lay= new GridLayout(2,1);
        searchArea.setLayout(lay);
        searchArea.setBackground(Color.white);
        searchArea.removeAll();
        searc = new JButton("Search");
        reset = new JButton("Reset");  
        searc.addActionListener(new searchListener());
        reset.addActionListener(new searchListener());

        /* Top Panel */
        outerField = new JPanel();
        GridLayout l = new GridLayout(1,3);
        outerField.setLayout(l);
        
        /* Search Fields */
        sProID = new JLabel("Product ID:",JLabel.CENTER);
        sProField = new JTextField("");
        sProField.setEditable(true);
        sName = new JLabel("Name Keywords:",JLabel.CENTER);
        sNameField = new JTextField("");
        sStartYear = new JLabel("Start Year:",JLabel.CENTER);
        sStartField = new JTextField("");
        sEndYear = new JLabel("End Year:",JLabel.CENTER);
        sEndField = new JTextField("");
        
        /* Search panel */
        field = new JPanel();
        field.setBackground(Color.white);
        field.setLayout(new BoxLayout(field, BoxLayout.PAGE_AXIS));
        field.add(sProID);
        field.add(sProField);
        field.add(sName);
        field.add(sNameField);
        field.add(sStartYear);
        field.add(sStartField);
        field.add(sEndYear);
        field.add(sEndField);
        outerField.add(field);
        
        /* Button Field */
        buttonField = new JPanel();
        FlowLayout lp = new FlowLayout();
        buttonField.add(searc);
        buttonField.add(reset);
        buttonField.setBackground(Color.white);
        outerField.add(buttonField);
        searchArea.add(outerField);
        
        /* Message Area */
        area = new JTextArea(10, 50);	
	titled = new TitledBorder("Searching products");		
	area.setLineWrap(true);						
	area.setEditable(false);		
	JScrollPane scrollText = new JScrollPane(area);		
	scrollText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);		
        searchArea.add(scrollText);
        searchArea.setBorder(titled);
      
        searchArea.setVisible(true);
        main.add(searchArea);
        mainPanel.setVisible(false);
        addBook.setVisible(false);
        fileWrite.setVisible(false);
        addElec.setVisible(false);
    }//end of searchWin
    public void searchField()
    {
        if((sProField.getText().length() == 0) && (sNameField.getText().length() == 0) && (sStartField.getText().length() == 0) 
            && (sEndField.getText().length() == 0)) //no search fields
        {
            for(Product get: getProduct)
            {  
                area.append(get.toString());
                area.setFont(fontTwo);
            }
        }//end of if
        if((sProField.getText().length() == 0) && (sStartField.getText().length() == 0) //name search
            && (sEndField.getText().length() == 0))
        {
            searchHash(sNameField.getText());
        }//end of if
        if((sNameField.getText().length() == 0) && (sStartField.getText().length() == 0) //search product ID
            && (sEndField.getText().length() == 0))
        {
            productID(sProField.getText());   
        }//end of if
        if(sNameField.getText().length() == 0) //search product ID and time
        {
            proIDTime(sProField.getText(),sStartField.getText(),sEndField.getText());
        }
        if((sNameField.getText().length() == 0) && (sProField.getText().length() == 0)) //search year
        {
            time(sStartField.getText(),sEndField.getText());
        }
    }//end of searchField
    public void addHash()
    {
        int values[];
        for(Product get: getProduct)
        {
            String name[] = get.getName().split("\\s");//splits name
            for(int i = 0; i<name.length; i++)//iterates through the list length
            {
                if(map.containsKey(name[i].toLowerCase()))//checks if the map already contiains the name key
                {
                    int misMatches = 0;
                    values = map.get(name[i].toLowerCase()); 
                    for(int k: values)
                    {
                        if(k != getProduct.indexOf(get))
                        {
                            misMatches++;//increments when k != to the index of the product
                        }
                    }//end of for               
                    if(misMatches == values.length)
                    {
                        int k;
                        int newValues[] = new int[values.length + 1];
                        for(k = 0; k < values.length; k++)
                        {
                            newValues[k] = values[k];//copies the value of alues into newValues                
                        }//end of for
                        newValues[k] = getProduct.indexOf(get);
                        map.put(name[i].toLowerCase(), newValues);
                    }//end of misMatches
                }//end of map.contains
                else
                {
                    int add[] = {getProduct.indexOf(get)};
                    map.put(name[i].toLowerCase(), add);//if its not already on the list add it
                }//end of else
            }//end of for
        }//end of for product get product
    }//end of addToHash
    public void searchHash(String name)
    {
        int [] finalResults = null;
        int not = 0;
        String [] spil = name.split(" ");
        for(int i = 0; i<spil.length; i++)
        {
            if(map.containsKey(spil[i]))
            {
                int [] newResult = map.get(spil[i]);
                if(i == 0)
                {
                    finalResults = newResult;
                }
                else
                {
                    for(int k = 0; k<newResult.length; k++)
                    {
                        for(int p = 0; p<finalResults.length; p++)
                        {
                            not = 0;
                            if(finalResults[p]!= newResult[k])
                            {
                                not++;
                            }
                        }
                        if(not == finalResults.length)
                        {
                            finalResults[finalResults.length]=k;
                        }
                    }//end of for
                }//end of else
            }//end of if
        }
        if(finalResults == null)
        {
            System.out.println("\nNo search items");
        }
        else
        {
            for(int i = 0; i<finalResults.length; i++)
            {
                area.append("\nFound: " + getProduct.get(finalResults[i]).toString());
            }//end of for
        }//end of else
    }//end of searchHash
    public void time(String startTime,String endTime)
    {       
        for(Product get: getProduct)
        {  
            if(get.getClass() == checkBook.getClass())
            {
                checkBook = (Books) get;
                if((startTime.equals(checkBook.getYear())) || (endTime.equals(checkBook.getYear())))//looks for both proID and time in books
                {
                    area.append("\nMatch found in Books\n" + get.toString());
                }
           }//end of if
           if(get.getClass() == checkElec.getClass())
           {
               checkElec = (Electronics) get;
               if(startTime.equals(checkElec.getYear()) || (endTime.equals(checkBook.getYear())))//looks for both proID and time in electronics
               {
                    area.append("\nMatch found in Electronics:\n" + get.toString());
               }
           }//end of if 
       }//end of for
    }//end of time
    public void productID(String productID)
    {
        for(Product get: getProduct)
        {  
            if(get.getClass() == checkBook.getClass())
            {
                checkBook = (Books) get;
                if(productID.equals(checkBook.getProductID()))
                {
                   area.append("\nMatch found in Books: " + get.toString());  
                }
            }//end of if
            if(get.getClass() == checkElec.getClass())
            {
                checkElec = (Electronics) get;
                if(productID.equals(checkElec.getProductID()))
                {
                    area.append("\nMatch found in Electronics: " + get.toString());       
                }
            }//end of if
       }//end of for
    }//end of productID
    public void proIDTime(String productID, String startTime,String endTime)
    {
        for(Product get: getProduct)
        {  
            if(get.getClass() == checkBook.getClass())
            {
                checkBook = (Books) get;
                if(productID.equals(checkBook.getProductID()) && (startTime.equals(checkBook.getYear())) && (endTime.equals(checkBook.getYear())))//looks for both proID and time in books
                {
                    area.append("Match found in Books\n" + get.toString());
                }
           }//end of if
           if(get.getClass() == checkElec.getClass())
           {
               checkElec = (Electronics) get;
               if(productID.equals(checkElec.getProductID()) && startTime.equals(checkElec.getYear()) && (endTime.equals(checkBook.getYear())))//looks for both proID and time in electronics
               {
                    area.append("Match found in Electronics:\n" + get.toString());
               }
           }//end of if 
       }//end of for
    }//end of proIDTime
    
    /***************************************************
     *              Exit Window                        *
     ***************************************************/
    public void writeWindow()
    {
        fileWrite = new JPanel(new GridLayout(3,3));
        inner = new JPanel(new FlowLayout());
        inner.setBackground(Color.white);
        
        fileWrite.setBackground(Color.white);
        prompt = new JLabel("Enter the file you wish to write to here",JLabel.CENTER);
        prompt.setFont(fontThree);
        Enter = new JButton("Enter");
        Enter.addActionListener(new exitListener());
        promptField = new JTextField("");
        inner.add(Enter);
        fileWrite.add(prompt);
        fileWrite.add(promptField);
        fileWrite.add(inner);
        
        fileWrite.setVisible(true);
        mainPanel.setVisible(false);
        searchArea.setVisible(false);
        addBook.setVisible(false);
        addElec.setVisible(false);
        main.add(fileWrite);
    }//end of writeWindow    
    public void write()
    {
        String file = promptField.getText();
        System.out.println(file);
        java.io.File files = new java.io.File(file);     
        try 
        {
            PrintWriter fileWriter = new PrintWriter(file);
            for (Product get : getProduct) 
            {
                if (get.getClass() == checkBook.getClass())
                {
                    checkBook = (Books) get;
                    fileWriter.println("type = \"book\"");
                    fileWriter.println("productID = \"" + checkBook.getProductID() + "\"");
                    fileWriter.println("name = \"" + checkBook.getName() + "\"");
                    fileWriter.println("price = \"" + checkBook.getPrice() + "\"");
                    fileWriter.println("year = \"" + checkBook.getYear() + "\"");
                    fileWriter.println("authors = \"" + checkBook.getAuthor() + "\"");
                    fileWriter.println("publisher = \"" + checkBook.getPublisher() + "\"");
                    fileWriter.println();
                    fileWriter.flush();
                }//end of if
                if(get.getClass() == checkElec.getClass())
                {
                    checkElec = (Electronics) get;
                    fileWriter.println("type = \"Electronics\"");
                    fileWriter.println("productID = \"" + checkElec.getProductID() + "\"");
                    fileWriter.println("name = \"" + checkElec.getName() + "\"");
                    fileWriter.println("price = \"" + checkElec.getPrice() + "\"");
                    fileWriter.println("year = \"" + checkElec.getYear() + "\"");
                    fileWriter.println("maker = \"" + checkElec.getMaker() + "\"");
                    fileWriter.println();
                    fileWriter.flush();
                }//end of if
            }//end of for
            fileWriter.close();//closes file
        }//end of try
        catch(FileNotFoundException e)
        {
            System.err.format("File does not exist\n");
        }//end of catch
    }//end of write
    
    /****************************************************
     *                 Misc                             *
     ****************************************************/
    public void resetFieldsBooks()
    {
        bNameField.setText("");
        bProField.setText("");
        bNameField.setText("");
        bYearField.setText("");
        bAuthorField.setText("");
        bPubField.setText("");
        bPriceField.setText("");  
        area.setText("");
    }//end of resetFieldsBooks
    public void resetFieldsElec()
    {
        eNameField.setText("");
        eProField.setText("");
        eMakerField.setText("");
        eYearField.setText("");
        ePriceField.setText("");
        area.setText("");
    }//end of resetFieldElec
    public void resetSearch()
    {
        sProField.setText("");
        sNameField.setText("");
        sEndField.setText("");
        sStartField.setText("");
        area.setText("");
    }//end of resetSearch
    public void getProduct(int n)
    {
        if(n == 0)
        {
            System.out.println("ELECTRONICS");
            elecWindow();
        }
        else if(n == 1)
        {
            System.out.println("BOOKS");
            addWindow();
        }
    }//end of getProduct
    public void getProduct2(int n)
    {
        if(n == 0)
        {
            System.out.println("ELECTRONICS");
            elecWindow();
        }
        else if(n == 1)
        {
           System.out.println("BOOKS");
           addWindow();
        }
    }//end of getProduct2
    
    /***********************************************************
     *                 Action Listeners                        *
     ***********************************************************/
    public class addListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == adds)
            {
               addWindow();
            }//end of adds         
            if(e.getActionCommand().equals("add"))
            {
                try {
                    addToBook();
                } catch (Exception ex) {
                    area.setText(ex.getMessage());
                }
            bookError();
            }//end of add 
            if(e.getActionCommand().equals("Reset"))
            {
              resetFieldsBooks();
            }//end of reset
        }//end of actionPerformmed        
    }//addListener
    public class elecListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == adds)
            {
                addWindow();
            }//end of adds
            if(e.getSource() == search)
            {
                searchWin();
            }
            if(e.getSource() == quit)
            {
                writeWindow();
            }
            if(e.getActionCommand().equals("Add"))
            {
                try {
                    addToElec();
                } catch (Exception ex) {
                     area.setText(ex.getMessage());
                }
                elecError();
            }//end of Add
            if(e.getActionCommand().equals("Reset"))
            {
              resetFieldsElec();
            }//end of reset 
        }//end of actionPerformed        
    }//end of elecListener
    public class searchListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == search)
            {
                searchWin();
            }//end of search  
            if(e.getActionCommand().equals("Search"))
            {
                searchField();
            }//end Search
            if(e.getActionCommand().equals("Reset"))
            {
                resetSearch();
            }//end of Reset
        }//end of actionPerformed        
    }//end of searchListener
    public class exitListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == quit)
            {
                writeWindow();
            }//end of quit   
            if(e.getActionCommand().equals("Enter"))
            {
                write();
              JOptionPane.showMessageDialog(main, "Thank you for using E-Store");
              System.exit(0);
            }//end of enter
        }//end of actionPerformed        
    }//end of exitListener
    
    /**********************************************************
     *                     File Stuff                         *
     **********************************************************/
    public void readFile(String fileName) throws Exception 
    {
        String type = "";
        String productID = "";
        String author = "";
        String maker = "";
        String publisher = "";
        String name = "";
        String year = "";
        String price = "";
        try
        {
            for(String lines : Files.readAllLines(Paths.get(fileName)))//reads the file line
            {
                if(lines.contains("type"))
                {
                    if(lines.contains("\"book\""))
                    {
                       type = "book";  
                    }
                    else if(lines.contains("\"electronics\""))
                    {
                       type = "electronics";
                    }
                }//end of if
                else if(lines.contains("productID"))
                {
                    Pattern p = Pattern.compile("\"([^\"]*)\"");
                    Matcher m = p.matcher(lines);
                    while(m.find())
                    {
                        productID = m.group(1);
                    }
                }//end of else if
                else if(lines.contains("name"))
                {
                    Pattern p = Pattern.compile("\"([^\"]*)\"");
                    Matcher m = p.matcher(lines);
                    while(m.find())
                    {
                        name = m.group(1);
                    }
                }//end of else if
                else if(lines.contains("price"))
                {
                    Pattern p = Pattern.compile("\"([^\"]*)\"");
                    Matcher m = p.matcher(lines);
                    while(m.find())
                    {
                        price = m.group(1);
                    }
                }//end of else if
                else if(lines.contains("year"))
                {
                    Pattern p = Pattern.compile("\"([^\"]*)\"");
                    Matcher m = p.matcher(lines);
                    while(m.find())
                    {
                        year = m.group(1);
                    }
                }//end of else if
               else if(lines.contains("authors"))
               {
                   Pattern p = Pattern.compile("\"([^\"]*)\"");
                   Matcher m = p.matcher(lines);
                   while(m.find())
                   {
                       author = m.group(1);
                   }
               }//end of else if
               else if(lines.contains("publisher"))
               {
                   Pattern p = Pattern.compile("\"([^\"]*)\"");
                   Matcher m = p.matcher(lines);
                   while(m.find())
                   {
                       publisher = m.group(1);
                   }
               }//end of else if
               else if(lines.contains("maker"))
               {
                   Pattern p = Pattern.compile("\"([^\"]*)\"");
                   Matcher m = p.matcher(lines);
                   while(m.find())
                   {
                       maker = m.group(1);
                   }
               }//end of else if
               else if (lines.isEmpty())
               {
                   if(type.equalsIgnoreCase("book"))
                   {
                        Books addBook = new Books(name,productID,year,author,publisher,price);
                        getProduct.add(addBook);
                   }//end of if
                   else if(type.equalsIgnoreCase("electronics"))
                   {
                        Electronics addElec = new Electronics(name,productID,year,price,maker);
                        getProduct.add(addElec);
                   }//end of else if
              }//end of else if
           }//end of for lines
       }//end of try
       catch(IOException e)
       {
           System.err.println("File could not be opened. Exiting program.");
           System.exit(0);
       }//end of IOException e
 }//end of E-Store
}

