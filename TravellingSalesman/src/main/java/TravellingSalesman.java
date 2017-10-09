
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



public class TravellingSalesman {

    static final int NUM_OF_LOCATIONS = 45;
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        ExcelReader excelReader = new ExcelReader();

            ArrayList<Location> locations = new ArrayList<>();
            
            double distances[][] = new double[NUM_OF_LOCATIONS][NUM_OF_LOCATIONS]; // 2d array holding distances between locations
            

            // read input file into arrayLists
            ArrayList<String> codes = excelReader.columnToArrayListAsString("input.xlsx", 0);
            ArrayList<Double> latitudes = excelReader.columnToArrayListAsDouble("input.xlsx", 1);
            ArrayList<Double> longitudes = excelReader.columnToArrayListAsDouble("input.xlsx", 2);
            ArrayList<String> names = excelReader.columnToArrayListAsString("input.xlsx", 3);
            ArrayList<String> locatedIns = excelReader.columnToArrayListAsString("input.xlsx", 4);            

            // build list of locations
            for (int i = 0; i < NUM_OF_LOCATIONS; i++) {
                Location tempLocation = new Location(codes.get(i), names.get(i), locatedIns.get(i), latitudes.get(i), longitudes.get(i));
                locations.add(tempLocation);
            }
            
            for (int i = 0; i < NUM_OF_LOCATIONS; i++) {
                for (int j = 0; j < NUM_OF_LOCATIONS; j++) {
                    distances[i][j] = Haversine.calc(locations.get(i).getLatitude(), locations.get(i).getLongitude(), 
                            locations.get(j).getLatitude(), locations.get(j).getLongitude());
                }
            }
            
            // test locations list
            for(Location location : locations) {
                System.out.println(location.getCode() + "\t" + location.getLatitude() + "\t" + location.getLongitude() + "\t" + 
                        location.getName() + "\t" + location.getLocatedIn());
            }
            
            // test distances
            for (int i = 0; i < NUM_OF_LOCATIONS; i++) {
                for (int j = 0; j < NUM_OF_LOCATIONS; j++) {
                    System.out.println(locations.get(i).getCode() + " to " + locations.get(j).getCode() + 
                            ": " + distances[i][j] + " km");
                }
            }
    }
    
}
