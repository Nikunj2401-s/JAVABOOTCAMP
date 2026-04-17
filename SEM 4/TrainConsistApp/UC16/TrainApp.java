public class TrainApp {

    public static void bubbleSort(int[] capacities) {
        int n = capacities.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (capacities[j] > capacities[j + 1]) {
                    int temp = capacities[j];
                    capacities[j] = capacities[j + 1];
                    capacities[j + 1] = temp;
                }
            }
        }
    }

    public static void printCapacities(String title, int[] capacities) {
        System.out.println("\n" + title);
        System.out.print("Capacities: ");
        for (int capacity : capacities) {
            System.out.print(capacity + " ");
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("UC16: Sort Passenger Bogies by Capacity (Bubble Sort)\n");

        int[] capacities = {72, 56, 24, 70, 60};
        printCapacities("Original bogie capacities", capacities);

        bubbleSort(capacities);
        printCapacities("Sorted bogie capacities", capacities);

        System.out.println("=== Additional bubble sort checks ===");

        int[] alreadySorted = {24, 56, 60, 70, 72};
        printCapacities("Already sorted capacities", alreadySorted);
        bubbleSort(alreadySorted);
        printCapacities("After bubble sort (already sorted)", alreadySorted);

        int[] duplicates = {72, 56, 56, 24};
        printCapacities("Duplicate capacities", duplicates);
        bubbleSort(duplicates);
        printCapacities("After bubble sort (duplicates)", duplicates);

        int[] singleElement = {50};
        printCapacities("Single element array", singleElement);
        bubbleSort(singleElement);
        printCapacities("After bubble sort (single element)", singleElement);

        int[] allEqual = {40, 40, 40};
        printCapacities("All equal capacities", allEqual);
        bubbleSort(allEqual);
        printCapacities("After bubble sort (all equal)", allEqual);

        System.out.println("Program continues after sorting demonstration...");
    }
}
