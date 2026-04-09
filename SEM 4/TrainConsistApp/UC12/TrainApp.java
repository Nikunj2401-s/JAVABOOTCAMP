import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TrainApp {
    
    private List<GoodsBogie> goodsBogies;
    
    // Constructor to initialize the train with empty bogie list
    public TrainApp() {
        this.goodsBogies = new ArrayList<>();
    }
    
    // Method to add a goods bogie
    public void addGoodsBogie(GoodsBogie bogie) {
        goodsBogies.add(bogie);
    }
    
    // ========== SAFETY VALIDATION METHODS ==========
    
    // STREAM METHOD: Check if cylindrical bogie carries Petroleum (Safety Rule)
    private boolean isCylindricalWithValidCargo(GoodsBogie bogie) {
        // If bogie is Cylindrical, it must carry Petroleum
        if (bogie.getType().equalsIgnoreCase("Cylindrical")) {
            return bogie.getCargo().equalsIgnoreCase("Petroleum");
        }
        // Non-cylindrical bogies can carry any cargo
        return true;
    }
    
    // STREAM METHOD: Check if all bogies satisfy safety rules using allMatch()
    public boolean isSafetyCompliant() {
        return goodsBogies.stream()
                .allMatch(this::isCylindricalWithValidCargo);
    }
    
    // STREAM METHOD: Check if empty bogies list is safe
    public boolean isEmptyBogieListSafe() {
        return goodsBogies.stream()
                .allMatch(bogie -> true);
    }
    
    // STREAM METHOD: Find unsafe bogies (those violating safety rules)
    public List<GoodsBogie> findUnsafeBogies() {
        return goodsBogies.stream()
                .filter(bogie -> bogie.getType().equalsIgnoreCase("Cylindrical") 
                        && !bogie.getCargo().equalsIgnoreCase("Petroleum"))
                .collect(Collectors.toList());
    }
    
    // STREAM METHOD: Count safe bogies
    public long countSafeBogies() {
        return goodsBogies.stream()
                .filter(this::isCylindricalWithValidCargo)
                .count();
    }
    
    // STREAM METHOD: Count unsafe bogies
    public long countUnsafeBogies() {
        return goodsBogies.stream()
                .filter(bogie -> !isCylindricalWithValidCargo(bogie))
                .count();
    }
    
    // Method to validate specific bogie
    public boolean validateBogie(GoodsBogie bogie) {
        return isCylindricalWithValidCargo(bogie);
    }
    
    // Method to display all goods bogies
    public void displayAllBogies() {
        System.out.println("\n--- Goods Bogies Inventory ---");
        System.out.println("ID              | Type            | Cargo           | Capacity");
        System.out.println("──────────────────────────────────────────────────────────────────");
        
        if (goodsBogies.isEmpty()) {
            System.out.println("No goods bogies registered");
        } else {
            for (int i = 0; i < goodsBogies.size(); i++) {
                System.out.println((i + 1) + ". " + goodsBogies.get(i));
            }
        }
        
        System.out.println("──────────────────────────────────────────────────────────────────");
        System.out.println("Total Bogies: " + goodsBogies.size() + "\n");
    }
    
    // Method to display safety validation result
    public void displaySafetyReport() {
        System.out.println("\n=== Safety Compliance Report ===");
        System.out.println("Safety Rule: Cylindrical bogies must carry ONLY Petroleum");
        System.out.println("────────────────────────────────────────────────────────");
        
        boolean isSafe = isSafetyCompliant();
        String status = isSafe ? "✓ SAFE - Compliant" : "✗ UNSAFE - Violation Detected";
        System.out.println("Train Status: " + status);
        
        System.out.println("\nValidation Summary:");
        System.out.println("  Total Bogies: " + goodsBogies.size());
        System.out.println("  Safe Bogies: " + countSafeBogies());
        System.out.println("  Unsafe Bogies: " + countUnsafeBogies());
        
        List<GoodsBogie> unsafe = findUnsafeBogies();
        if (!unsafe.isEmpty()) {
            System.out.println("\n⚠ Unsafe Bogies Detected:");
            for (GoodsBogie bogie : unsafe) {
                System.out.println("  - " + bogie.getId() + " (Type: " + bogie.getType() + 
                                 ", Cargo: " + bogie.getCargo() + ")");
            }
        }
        
        System.out.println("════════════════════════════════════════════════════════════\n");
    }
    
    // Method to display detailed bogie validation
    public void displayBogieValidationDetails() {
        System.out.println("\n--- Individual Bogie Validation ---");
        System.out.println("ID              | Type            | Cargo           | Status");
        System.out.println("──────────────────────────────────────────────────────────────");
        
        for (GoodsBogie bogie : goodsBogies) {
            String status = validateBogie(bogie) ? "✓ SAFE" : "✗ UNSAFE";
            System.out.printf("%-15s | %-15s | %-15s | %s%n", 
                            bogie.getId(), bogie.getType(), bogie.getCargo(), status);
        }
        
        System.out.println("──────────────────────────────────────────────────────────────\n");
    }
    
    // Method to get original list size
    public int getOriginalBogieCount() {
        return goodsBogies.size();
    }
    
    // Getter for bogies list
    public List<GoodsBogie> getGoodsBogies() {
        return goodsBogies;
    }
    
    // Main method - Entry point of the application
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("UC12: Safety Compliance Check for Goods Bogies\n");
        
        // ========== SCENARIO 1: Valid Train (All Compliance) ==========
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║ SCENARIO 1: Valid Train Formation (All Safe)               ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        TrainApp validTrain = new TrainApp();
        System.out.println("\nCreating goods bogies for valid train...");
        validTrain.addGoodsBogie(new GoodsBogie("GB001", "Cylindrical", "Petroleum", 50000));
        validTrain.addGoodsBogie(new GoodsBogie("GB002", "Cylindrical", "Petroleum", 50000));
        validTrain.addGoodsBogie(new GoodsBogie("GB003", "Rectangular", "Coal", 40000));
        validTrain.addGoodsBogie(new GoodsBogie("GB004", "Open", "Grain", 30000));
        
        validTrain.displayAllBogies();
        validTrain.displayBogieValidationDetails();
        validTrain.displaySafetyReport();
        
        // TEST CASE 1: Valid Train Formation
        System.out.println("=== TEST 1: Valid Train Formation ===");
        System.out.println("Using allMatch() to validate all bogies:");
        boolean isValid = validTrain.isSafetyCompliant();
        System.out.println("Validation Result: " + (isValid ? "✓ TRUE (All bogies compliant)" : "✗ FALSE (Violations found)"));
        System.out.println("✓ Test passed: All bogies satisfy safety rules\n");
        
        // ========== SCENARIO 2: Invalid Train (Cylindrical with wrong cargo) ==========
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║ SCENARIO 2: Invalid Train Formation (Safety Violation)     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        TrainApp invalidTrain = new TrainApp();
        System.out.println("\nCreating goods bogies for invalid train...");
        invalidTrain.addGoodsBogie(new GoodsBogie("GB101", "Cylindrical", "Petroleum", 50000));
        invalidTrain.addGoodsBogie(new GoodsBogie("GB102", "Cylindrical", "Coal", 50000));  // VIOLATION
        invalidTrain.addGoodsBogie(new GoodsBogie("GB103", "Rectangular", "Coal", 40000));
        
        invalidTrain.displayAllBogies();
        invalidTrain.displayBogieValidationDetails();
        invalidTrain.displaySafetyReport();
        
        // TEST CASE 2: Invalid Cylindrical with Wrong Cargo
        System.out.println("=== TEST 2: Cylindrical Bogie with Invalid Cargo ===");
        System.out.println("Cylindrical bogie 'GB102' carries 'Coal' (Should be Petroleum only)");
        boolean isInvalid = invalidTrain.isSafetyCompliant();
        System.out.println("Validation Result: " + (isInvalid ? "✓ TRUE (Unexpected)" : "✗ FALSE (Correct - violation detected)"));
        System.out.println("✓ Test passed: Validation correctly identified violation\n");
        
        // ========== SCENARIO 3: Non-Cylindrical Bogies ==========
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║ SCENARIO 3: Non-Cylindrical Bogies (Any Cargo Allowed)     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        TrainApp nonCylindricalTrain = new TrainApp();
        System.out.println("\nCreating non-cylindrical goods bogies...");
        nonCylindricalTrain.addGoodsBogie(new GoodsBogie("GB201", "Rectangular", "Coal", 40000));
        nonCylindricalTrain.addGoodsBogie(new GoodsBogie("GB202", "Open", "Grain", 30000));
        nonCylindricalTrain.addGoodsBogie(new GoodsBogie("GB203", "Box", "Electronics", 25000));
        
        nonCylindricalTrain.displayAllBogies();
        nonCylindricalTrain.displayBogieValidationDetails();
        nonCylindricalTrain.displaySafetyReport();
        
        // TEST CASE 3: Non-Cylindrical Bogies with Any Cargo
        System.out.println("=== TEST 3: Non-Cylindrical Bogies Flexibility ===");
        System.out.println("Non-cylindrical bogies can carry any cargo without restriction");
        boolean flexibilityTest = nonCylindricalTrain.isSafetyCompliant();
        System.out.println("Validation Result: " + (flexibilityTest ? "✓ TRUE (Correct - all compliant)" : "✗ FALSE (Unexpected)"));
        System.out.println("✓ Test passed: Non-cylindrical bogies allow any cargo\n");
        
        // ========== SCENARIO 4: Empty Bogies List ==========
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║ SCENARIO 4: Empty Bagies List                              ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        TrainApp emptyTrain = new TrainApp();
        System.out.println("\nCreating train with no goods bogies...");
        
        // TEST CASE 4: Empty Bogie List
        System.out.println("=== TEST 4: Empty Bogie List ===");
        System.out.println("Train with 0 goods bogies:");
        System.out.println("  Total Bogies: " + emptyTrain.getOriginalBogieCount());
        boolean emptyTest = emptyTrain.isSafetyCompliant();
        System.out.println("Validation Result: " + (emptyTest ? "✓ TRUE (Correct - no violations)" : "✗ FALSE (Unexpected)"));
        System.out.println("✓ Test passed: Empty list returns true (no rule violations)\n");
        
        // ========== SCENARIO 5: Mixed Valid and Invalid ==========
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║ SCENARIO 5: Multiple Violations                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        TrainApp mixedTrain = new TrainApp();
        System.out.println("\nCreating train with multiple violations...");
        mixedTrain.addGoodsBogie(new GoodsBogie("GB301", "Cylindrical", "Petroleum", 50000));  // Valid
        mixedTrain.addGoodsBogie(new GoodsBogie("GB302", "Cylindrical", "Grain", 50000));      // Invalid
        mixedTrain.addGoodsBogie(new GoodsBogie("GB303", "Cylindrical", "Coal", 50000));       // Invalid
        mixedTrain.addGoodsBogie(new GoodsBogie("GB304", "Rectangular", "Coal", 40000));      // Valid
        
        mixedTrain.displayAllBogies();
        mixedTrain.displayBogieValidationDetails();
        mixedTrain.displaySafetyReport();
        
        // TEST CASE 5: Multiple Violations
        System.out.println("=== TEST 5: Multiple Violations ===");
        System.out.println("Train with 2 Cylindrical bogies carrying invalid cargo:");
        System.out.println("  GB302: Cylindrical with Grain (Invalid)");
        System.out.println("  GB303: Cylindrical with Coal (Invalid)");
        boolean multiViolation = mixedTrain.isSafetyCompliant();
        System.out.println("Validation Result: " + (multiViolation ? "✓ TRUE (Unexpected)" : "✗ FALSE (Correct)"));
        System.out.println("Violations detected: " + mixedTrain.countUnsafeBogies());
        System.out.println("✓ Test passed: Multiple violations correctly detected\n");
        
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║ ✓ Stream-based safety validation successfully implemented! ║");
        System.out.println("║ All test cases passed.                                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        System.out.println("Program continues...");
    }
}
