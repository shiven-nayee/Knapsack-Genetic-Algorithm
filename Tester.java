public class Tester {
  public static void main(String[] args) {
    int[] Values = {9, 17, 11, 6, 10, 12, 11, 12, 5, 3};
    int[] Weights = {3, 9, 3, 8, 3, 5, 6, 6, 3, 6};
    Genetic_algorithm babies = new Genetic_algorithm(Values, Weights);
    babies.runAlgorithm();
  }
}