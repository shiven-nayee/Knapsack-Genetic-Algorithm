public class Knapsack {
  private Item[] items;
  private int[] selections;
  private int Value;
  private int Weight;
  
  public Knapsack(int[] Values, int[] Weights) {
    this.items = new Item[10];
    this.selections = new int[10];
    this.Value = 0;
    this.Weight = 0;

    // Intialize Arrays of items with given Values and Weights
    for(int i = 0; i < 10; i++) {
      items[i] = new Item(Values[i], Weights[i]);
    }

    // Intialize Array of Selection with a given Selection of either 0 or 1
    for(int i = 0; i < 10; i++) {
      // Generates a random number between 1 - 100
      int max = 100;
      int min = 1;
      int range = max - min + 1;
      int randomNum = (int)(Math.random() * range) + min;
      
      // If the number is below 50 it will be 0
      // If the number is above 50 it will be 1
      if(randomNum <= 50) {
        selections[i] = 0;
      } else {
        selections[i] = 1;
      }
    }

    // Set value and space
    for(int i = 0; i < 10; i++) {
      if(selections[i] == 1) {
        this.Value += items[i].getValue();
        this.Weight += items[i].getSpace();
      }
    }
  }
  
  // Copy constructor
  public Knapsack(Knapsack other) {
    this.items = other.getItems();
    this.selections = other.getSelections().clone();
    this.Value = 0;
    this.Weight = 0;
  }

  // Child Constructor
  public Knapsack(int[] Values, int[] Weights, int[] givenSelections) {
    this.items = new Item[10];
    this.selections = new int[10];
    this.Value = 0;
    this.Weight = 0;

    // Intialize Arrays of items with given Values and Weights
    for(int i = 0; i < 10; i++) {
      items[i] = new Item(Values[i], Weights[i]);
    }

    this.selections = givenSelections;

    // Set value and space
    for(int i = 0; i < 10; i++) {
      if(selections[i] == 1) {
        this.Value += items[i].getValue();
        this.Weight += items[i].getSpace();
      }
    }
  }

  public Item[] getItems() {
    return items;
  }

  public int[] getSelections() {
    return selections;
  }

  public int getValue() {
    return Value;
  }

  public int getWeight() {
    return Weight;
  }

  public void changeGene(int index) {
    if(selections[index] == 0) {
      selections[index] = 1;
    } else {
      selections[index] = 0;
    }
    // Recalculate the Value and Weight
    for(int i = 0; i < 10; i++) {
      if(this.selections[i] == 1) {
        this.Value += items[i].getValue();
        this.Weight += items[i].getSpace();
      }
    }
    System.out.println("Changed gene at index of: " + index);
  }

  public void displaySelections() {
    String str = "[";
    for(int i = 0; i < selections.length-1; i++) {
      str += selections[i] + ", ";
    }
    str += selections[selections.length-1] + "] ";
    str += "Value: " + getValue() + ", Weight: " + getWeight();
    System.out.println(str);
  }

}