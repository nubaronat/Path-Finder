import java.util.*;

public class PathFinder {
    private final String[] cities;
    private final int[][] adjacencyMatrix;
    public static final int INFINITY = 99999;

    public int[][] getAdjacencyMatrix(){
        return adjacencyMatrix;
    }
    public String[] getCities(){
        return cities;
    }
    public PathFinder(String[] cities, int[][] adjacencyMatrix) {
        this.cities = cities;
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public List<String> findShortestPathBFS(String sourceCity, String destinationCity) {
        int sourceIndex = Arrays.asList(cities).indexOf(sourceCity);
        int destinationIndex = Arrays.asList(cities).indexOf(destinationCity);

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[cities.length];
        int[] parent = new int[cities.length];

        Arrays.fill(parent, -1);
        visited[sourceIndex] = true;
        queue.add(sourceIndex);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int neighbor = 0; neighbor < cities.length; neighbor++) {
                if (!visited[neighbor] && adjacencyMatrix[current][neighbor] != INFINITY) {
                    visited[neighbor] = true;
                    parent[neighbor] = current;
                    queue.add(neighbor);

                    if (neighbor == destinationIndex) {
                        return constructPath(parent, sourceIndex, destinationIndex);
                    }
                }
            }
        }

        return Collections.emptyList();
    }

    public List<String> findShortestPathDFS(String sourceCity, String destinationCity) {
        int sourceIndex = Arrays.asList(cities).indexOf(sourceCity);
        int destinationIndex = Arrays.asList(cities).indexOf(destinationCity);

        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[cities.length];
        int[] parent = new int[cities.length];

        Arrays.fill(parent, -1);
        visited[sourceIndex] = true;
        stack.push(sourceIndex);

        while (!stack.isEmpty()) {
            int current = stack.pop();

            for (int neighbor = 0; neighbor < cities.length; neighbor++) {
                if (!visited[neighbor] && adjacencyMatrix[current][neighbor] != INFINITY) {
                    visited[neighbor] = true;
                    parent[neighbor] = current;
                    stack.push(neighbor);

                    if (neighbor == destinationIndex) {
                        return constructPath(parent, sourceIndex, destinationIndex);
                    }
                }
            }
        }

        return Collections.emptyList();
    }

    private List<String> constructPath(int[] parent, int source, int destination) {
        List<String> path = new ArrayList<>();
        int current = destination;

        while (current != -1) {
            path.add(cities[current]);
            current = parent[current];
        }

        Collections.reverse(path);
        return path;
    }

    public static int calculateDistance(List<String> path, int[][] adjacencyMatrix, String[] cities) {
        int distance = 0;

        for (int i = 0; i < path.size() - 1; i++) {
            int currentCityIndex = Arrays.asList(cities).indexOf(path.get(i));
            int nextCityIndex = Arrays.asList(cities).indexOf(path.get(i + 1));
            distance += adjacencyMatrix[currentCityIndex][nextCityIndex];
        }

        return distance;
    }

}
