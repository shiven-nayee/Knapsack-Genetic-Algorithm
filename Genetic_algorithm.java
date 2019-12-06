public class Genetic_algorithm {
  Knapsack[] knapsacks;
  int size; // Keep track of size of mating pool
  int[] Values;
  int[] Weights;

  public Genetic_algorithm(int[] Values, int[] Weights) {
    knapsacks = new Knapsack[15];
    this.Values = Values;
    this.Weights = Weights;
    size = 0;
    
    // Create new knapsacks with randomized selection values in Knapsacks[]
    for(int i = 0; i < 15; i++) {
      Knapsack k = new Knapsack(Values, Weights);
      knapsacks[i] = k;
      size++;
    }
  }

  public Knapsack selectFirstFittest() {
    Knapsack greatestValue = null;
    for(int i = 0; i < size; i++) {
      int Value = knapsacks[i].getValue();
      int Weight = knapsacks[i].getWeight();
      if(greatestValue == null && Weight <= 30) {
        greatestValue = knapsacks[i];
      } 
      // If greatestValue is not empty
      // And the current Knapsack has greater value
      // And the weight is less than 30
      if(greatestValue != null && Value > greatestValue.getValue() && Weight <= 30) {
        greatestValue = knapsacks[i];
      }
    }
    return greatestValue;
  }

  public Knapsack selectSecondFittest(Knapsack firstFittest) {
    Knapsack greatestValue = null;
    for(int i = 0; i < size; i++) {
      int Value = knapsacks[i].getValue();
      int Weight = knapsacks[i].getWeight();
      boolean equalFirstFittest = firstFittest.equals(knapsacks[i]);
      if(!equalFirstFittest && greatestValue == null && Weight <= 30) {
        greatestValue = knapsacks[i];
      } 
      // If greatestValue is not empty
      // And the current Knapsack has greater value
      // And the weight is less than 30
      if(!equalFirstFittest && greatestValue != null && Value > greatestValue.getValue() && Weight <= 30) {
        greatestValue = knapsacks[i];
      }
    }
    return greatestValue;
  }

  // Remove the candidates with the Highest weights
  public void remove() {
    for(int i = size-1; i > 9; i--) {
      knapsacks[i] = null;
      size--;
    }
  }

  public void runAlgorithm() {
    System.out.println("Initial Knapsacks");
    sort();
    displaySelections();

    int count = 1;
    while(count <= 6) {
      System.out.println("Iteration: " + count);
      count++;
      // Sort the knapsacks from lowest to highest in term of weight
      sort();
  
      // Remove the 5 knapsacks with the highest weight
      remove();
      
      // Add a knapsack with one randomly mutated gene
      addMutation();
  
      System.out.println("Knapsacks after sort and removal and added mutation");
      displaySelections();
  
      // Creates four children
      for(int i = 0; i < 4; i++) {
        // Select the two fittest Knapsacks
        Knapsack fittest = selectFirstFittest();
        Knapsack secondFittest = selectSecondFittest(fittest);
    
        System.out.print("First fittest: ");
        fittest.displaySelections();
    
        System.out.print("Second fittest: ");
        secondFittest.displaySelections();
  
        createChild(fittest, secondFittest);
      }
  
      System.out.println("After adding 4 children: ");
      displaySelections();
    }


  }

  public void createChild(Knapsack fittest, Knapsack secondFittest) {
    int[] givenSelection = new int[10];
    int[] fittestSelection = fittest.getSelections();
    int[] secondFittestSelection = secondFittest.getSelections();
    for(int i = 0; i < 10; i++) {
      // If the two "genes" are the same the child inherits the same gene
      // Otherwise it randomly choose between 1 and 2
      if(fittestSelection[i] == secondFittestSelection[i]) {
        givenSelection[i] = fittestSelection[i];
      } else {
        int max = 100;
        int min = 1;
        int range = max - min + 1;
        int randomNum = (int)(Math.random() * range) + min;
      
        // If the number is below 50 it will be 0
        // If the number is above 50 it will be 1
        if(randomNum <= 50) {
          givenSelection[i] = 0;
        } else {
          givenSelection[i] = 1;
        }
      }
    }
    Knapsack temp = new Knapsack(this.Values, this.Weights, givenSelection);
    System.out.print("Child: ");
    temp.displaySelections();
    System.out.println("\n");
    knapsacks[size] = temp;
    size++;
  }
  
  // Selection sort
  public void sort() {
    for(int i = 0; i < size; i++) {
      for(int k = 0; k < size; k++) {
        if(knapsacks[i].getValue() > knapsacks[k].getValue()){
          if(knapsacks[k].getWeight() <= 30) {
            Knapsack temp = knapsacks[k];
            knapsacks[k] = knapsacks[i];
            knapsacks[i] = temp;
          }
        }
      }
    }
  }

  public void addMutation() {
    int max = 9;
    int min = 0;
    int range = max - min + 1;
    int randomNum = (int)(Math.random() * range) + min;
    Knapsack temp = new Knapsack(knapsacks[randomNum]);
    temp.changeGene(randomNum);
    knapsacks[size] = temp;
    size++;
  }

  public void displaySelections() {
    for(int i = 0; i < size; i++) {
      System.out.print(i+1 + " :");
      knapsacks[i].displaySelections();
    }
    System.out.println("\n");
  }
}