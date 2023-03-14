import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class DataManager {
    private final GameList primaryGameList;

    public DataManager() {
        this.primaryGameList = new GameList("Primary");
    }

    public void loadGameDataXml(String GAMEDATAXML) {
        try {
            /* This code segment sets up a new DOM parser, and makes sure the current
                version of Java supports a DOM parser.
             */
            XMLParserUtility myParser = new XMLParserUtility(GAMEDATAXML);
            ArrayList<Game> myGameList = myParser.retrieveGameList();
            if (myGameList != null) {
                primaryGameList.setGameList(myGameList);
            //    System.out.println("\nThere were " + myGameList.size() + " game objects found in the file.");
            //    for (Game g : myGameList) {
            //        System.out.println(g.toString());
            //    }
            }
            else {
                System.out.println("Nothing was retrieved from the file.");
            }

        } catch (FileNotFoundException e1) {
            System.err.println("Unable to open file: " + GAMEDATAXML);
            System.err.println("Exiting program.");
            System.exit(1);
        } catch (IOException e2) {
            System.err.println("Unable to parse the XML document contained in: " + GAMEDATAXML);
            System.err.println("Exiting program.");
            System.exit(2);
        }
    }
    public void loadConfigurationFile() {}
    public void saveConfigurationFile() {}
    public GameList getPrimaryGameList() {return this.primaryGameList;}
    public void printPrimaryGameList() {primaryGameList.printGameList();}
}
