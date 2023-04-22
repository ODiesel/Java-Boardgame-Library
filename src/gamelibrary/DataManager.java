package gamelibrary;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/**
 * The class Data manager
 */
public class DataManager {
    private final GameList primaryGameList;


    /**
     *
     * It is a constructor for DataManager
     *
     */
    public DataManager() {
        this.primaryGameList = new GameList("Primary");
    }


    /**
     *
     * Load game data xml
     *
     * @param GAMEDATAXML  the  GAMED ATAXM L.
     */
    public void loadGameDataXml(String GAMEDATAXML) {
        try {
            /* This code segment sets up a new DOM parser, and makes sure the current
                version of Java supports a DOM parser.
             */
            XMLParserUtility myParser = new XMLParserUtility(GAMEDATAXML);
            ArrayList<Game> myGameList = myParser.retrieveGameList();
            if (myGameList != null) {
                primaryGameList.setGameList(myGameList);
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


    /**
     *
     * Gets the primary game list
     *
     * @return the primary game list
     */
    public GameList getPrimaryGameList() {
        return this.primaryGameList;
    }
}
