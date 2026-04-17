import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TrainApp {

    private List<Bogie> bogies;

    public TrainApp() {
        this.bogies = new ArrayList<>();
    }

    public void addBogie(Bogie bogie) {
        bogies.add(bogie);
    }

    public List<Bogie> getBogies() {
        return bogies;
    }

    public List<Bogie> filterByCapacityLoop(int minCapacity) {
        List<Bogie> filtered = new ArrayList<>();
        for (Bogie bogie : bogies) {
            if (bogie.getCapacity() > minCapacity) {
                filtered.add(bogie);
            }
        }
        return filtered;
    }

    public List<Bogie> filterByCapacityStream(int minCapacity) {
        return bogies.stream()
                .filter(bogie -> bogie.getCapacity() > minCapacity)
                .collect(Collectors.toList());
    }

    public static void printBogieList(String title, List<Bogie> bogieList) {
        System.out.println("\n" + title);
        System.out.println("ID       | Type            | Capacity");
        System.out.println("----------------------------------------");
        if (bogieList.isEmpty()) {
            System.out.println("No bogies match the filter.");
        } else {
            for (Bogie bogie : bogieList) {
                System.out.println(bogie);
            }
        }
        System.out.println("----------------------------------------");
        System.out.println("Count: " + bogieList.size() + "\n");
    }

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("UC13: Performance Comparison - Loops vs Streams\n");

        TrainApp sampleApp = new TrainApp();
        sampleApp.addBogie(new Bogie("PB001", "Sleeper", 72));
        sampleApp.addBogie(new Bogie("PB002", "AC Chair", 54));
        sampleApp.addBogie(new Bogie("PB003", "First Class", 95));
        sampleApp.addBogie(new Bogie("GB001", "Rectangular", 80));
        sampleApp.addBogie(new Bogie("GB002", "Cylindrical", 45));
        sampleApp.addBogie(new Bogie("PB004", "Sleeper", 60));
        sampleApp.addBogie(new Bogie("PB005", "AC Chair", 88));

        System.out.println("Sample bogie set prepared for capacity filtering (> 60):");
        printBogieList("All Bogies", sampleApp.getBogies());

        long startLoop = System.nanoTime();
        List<Bogie> loopResult = sampleApp.filterByCapacityLoop(60);
        long endLoop = System.nanoTime();
        long loopTime = endLoop - startLoop;

        long startStream = System.nanoTime();
        List<Bogie> streamResult = sampleApp.filterByCapacityStream(60);
        long endStream = System.nanoTime();
        long streamTime = endStream - startStream;

        printBogieList("Loop-based filter result", loopResult);
        printBogieList("Stream-based filter result", streamResult);

        System.out.println("=== Execution Time Measurement ===");
        System.out.println("Loop filtering elapsed time  : " + loopTime + " nanoseconds");
        System.out.println("Stream filtering elapsed time: " + streamTime + " nanoseconds");
        System.out.println("Loop and stream results match: " + (loopResult.size() == streamResult.size()));

        System.out.println("\n=== Large Dataset Benchmark ===");
        TrainApp largeApp = new TrainApp();
        for (int i = 1; i <= 100_000; i++) {
            String id = "BG" + String.format("%05d", i);
            String type = (i % 2 == 0) ? "Sleeper" : "AC Chair";
            int capacity = 30 + (i % 120);
            largeApp.addBogie(new Bogie(id, type, capacity));
        }

        long startLargeLoop = System.nanoTime();
        List<Bogie> largeLoopResult = largeApp.filterByCapacityLoop(60);
        long endLargeLoop = System.nanoTime();
        long largeLoopTime = endLargeLoop - startLargeLoop;

        long startLargeStream = System.nanoTime();
        List<Bogie> largeStreamResult = largeApp.filterByCapacityStream(60);
        long endLargeStream = System.nanoTime();
        long largeStreamTime = endLargeStream - startLargeStream;

        System.out.println("Large dataset size          : " + largeApp.getBogies().size());
        System.out.println("Filtered bogies (> 60) count: " + largeLoopResult.size());
        System.out.println("Loop elapsed time           : " + largeLoopTime + " nanoseconds");
        System.out.println("Stream elapsed time         : " + largeStreamTime + " nanoseconds");
        System.out.println("Large dataset results match : " + (largeLoopResult.size() == largeStreamResult.size()));

        System.out.println("\nProgram continues...");
    }
}
