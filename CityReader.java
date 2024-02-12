import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CityReader {
    private static final int INFINITY = 99999;
    public PathFinder readCitiesAndCreatePathFinder(String filename) {
        List<String> citiesList = new ArrayList<>();
        int[][] adjacencyMatrix;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Read city names
            String line = reader.readLine();
            if (line != null) {
                String[] cities = line.split(",");
                citiesList.addAll(Arrays.asList(cities).subList(1, cities.length));
                int size = cities.length - 1;
                adjacencyMatrix = new int[size][size];
            } else {
                throw new IOException("CSV file is empty");
            }

            // Populate adjacency matrix
            int rowIndex = 0;
            while ((line = reader.readLine()) != null && rowIndex < citiesList.size()) {
                String[] values = line.split(",");
                for (int i = 1; i < values.length && i <= citiesList.size(); i++) {
                    adjacencyMatrix[rowIndex][i - 1] = values[i].equals("99999") ? INFINITY : Integer.parseInt(values[i]);
                }
                rowIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        String[] cities = citiesList.toArray(new String[0]);
        return new PathFinder(cities, adjacencyMatrix);
    }
}
