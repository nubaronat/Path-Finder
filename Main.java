import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CityReader cityReader = new CityReader();
        PathFinder pathFinder = cityReader.readCitiesAndCreatePathFinder("Turkish cities.csv");

        Scanner input = new Scanner(System.in);


        String sourceCity, destinationCity;

        while( true)
        {
            System.out.println("Enter source city:");
            sourceCity = input.next();

            if( Arrays.asList(pathFinder.getCities()).contains(sourceCity)) break;
            else{
                System.out.println("There is no such a city in the file");
            }
        }
        while( true)
        {
            System.out.println("Enter destination city:");
            destinationCity = input.next();

            if( Arrays.asList(pathFinder.getCities()).contains(destinationCity)) break;
            else{
                System.out.println("There is no such a city in the file");
            }
        }


        List<String> citiesPathBFS = pathFinder.findShortestPathBFS(sourceCity, destinationCity);
        List<String> citiesPathDFS = pathFinder.findShortestPathDFS(sourceCity, destinationCity);

        // Display results
        displayShortestPath("BFS", citiesPathBFS, pathFinder);
        displayShortestPath("DFS", citiesPathDFS, pathFinder);
    }

    private static void displayShortestPath(String algorithm, List<String> path, PathFinder pathFinder) {
        int distance = PathFinder.calculateDistance(path, pathFinder.getAdjacencyMatrix(), pathFinder.getCities());
        System.out.println("Shortest path using " + algorithm + ": " + path + ", Distance: " + distance);
    }
}
