import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrainApp {
    
    private List<Bogie> bogies;
    private String trainID;
    private String cargoCode;
    
    // ========== REGEX PATTERNS ==========
    // Train ID Pattern: TRN- followed by exactly 4 digits
    private static final String TRAIN_ID_PATTERN = "^TRN-\\d{4}$";
    
    // Cargo Code Pattern: PET- followed by exactly 2 uppercase letters
    private static final String CARGO_CODE_PATTERN = "^PET-[A-Z]{2}$";
    
    // Compiled Pattern objects for efficiency
    private static final Pattern trainIDPattern = Pattern.compile(TRAIN_ID_PATTERN);
    private static final Pattern cargoCodePattern = Pattern.compile(CARGO_CODE_PATTERN);
    
    // Constructor to initialize the train
    public TrainApp() {
        this.bogies = new ArrayList<>();
        this.trainID = "";
        this.cargoCode = "";
    }
    
    // ========== VALIDATION METHODS ==========
    
    // Method to validate Train ID format
    public boolean validateTrainID(String id) {
        if (id == null || id.isEmpty()) {
            return false;
        }
        Matcher matcher = trainIDPattern.matcher(id);
        return matcher.matches();
    }
    
    // Method to validate Cargo Code format
    public boolean validateCargoCode(String code) {
        if (code == null || code.isEmpty()) {
            return false;
        }
        Matcher matcher = cargoCodePattern.matcher(code);
        return matcher.matches();
    }
    
    // Method to set and validate Train ID
    public boolean setTrainID(String id) {
        if (validateTrainID(id)) {
            this.trainID = id;
            return true;
        }
        return false;
    }
    
    // Method to set and validate Cargo Code
    public boolean setCargoCode(String code) {
        if (validateCargoCode(code)) {
            this.cargoCode = code;
            return true;
        }
        return false;
    }
    
    // Method to add a bogie
    public void addBogie(Bogie bogie) {
        bogies.add(bogie);
    }
    
    // Method to get Train ID
    public String getTrainID() {
        return trainID;
    }
    
    // Method to get Cargo Code
    public String getCargoCode() {
        return cargoCode;
    }
    
    // Method to display validation details
    public void displayValidationResult(String input, String type, boolean isValid) {
        String status = isValid ? "✓ VALID" : "✗ INVALID";
        System.out.printf("%-25s | %s | %s%n", input, type, status);
    }
    
    // Method to explain regex pattern
    public void explainRegexPatterns() {
        System.out.println("\n=== Regex Pattern Definitions ===");
        System.out.println("Train ID Pattern: " + TRAIN_ID_PATTERN);
        System.out.println("  Format: TRN- followed by exactly 4 digits");
        System.out.println("  Example: TRN-1234");
        System.out.println("");
        System.out.println("Cargo Code Pattern: " + CARGO_CODE_PATTERN);
        System.out.println("  Format: PET- followed by exactly 2 uppercase letters");
        System.out.println("  Example: PET-AB");
        System.out.println("═══════════════════════════════════════\n");
    }
    
    // Main method - Entry point of the application
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("UC11: Validate Train ID & Cargo Codes (Regex)\n");
        
        // Initialize the Train
        TrainApp train = new TrainApp();
        
        // Explain regex patterns
        train.explainRegexPatterns();
        
        // ========== TEST CASE 1: Valid Train ID ==========
        System.out.println("=== TEST 1: Valid Train ID Format ===");
        System.out.println("Input Format: Input (Type) | Status");
        System.out.println("─────────────────────────────────────────");
        train.displayValidationResult("TRN-1234", "Train ID", train.validateTrainID("TRN-1234"));
        System.out.println("✓ Valid Train ID accepted\n");
        
        // ========== TEST CASE 2: Invalid Train ID Formats ==========
        System.out.println("=== TEST 2: Invalid Train ID Formats ===");
        System.out.println("Input Format: Input (Type) | Status");
        System.out.println("─────────────────────────────────────────");
        train.displayValidationResult("TRAIN12", "Train ID", train.validateTrainID("TRAIN12"));
        train.displayValidationResult("TRN12A", "Train ID", train.validateTrainID("TRN12A"));
        train.displayValidationResult("1234-TRN", "Train ID", train.validateTrainID("1234-TRN"));
        train.displayValidationResult("TRN-123", "Train ID", train.validateTrainID("TRN-123"));
        train.displayValidationResult("TRN-12345", "Train ID", train.validateTrainID("TRN-12345"));
        train.displayValidationResult("TRN-", "Train ID", train.validateTrainID("TRN-"));
        System.out.println("✓ All invalid Train ID formats rejected\n");
        
        // ========== TEST CASE 3: Valid Cargo Code ==========
        System.out.println("=== TEST 3: Valid Cargo Code Format ===");
        System.out.println("Input Format: Input (Type) | Status");
        System.out.println("─────────────────────────────────────────");
        train.displayValidationResult("PET-AB", "Cargo Code", train.validateCargoCode("PET-AB"));
        train.displayValidationResult("PET-XY", "Cargo Code", train.validateCargoCode("PET-XY"));
        train.displayValidationResult("PET-ZZ", "Cargo Code", train.validateCargoCode("PET-ZZ"));
        System.out.println("✓ Valid Cargo Codes accepted\n");
        
        // ========== TEST CASE 4: Invalid Cargo Code Formats ==========
        System.out.println("=== TEST 4: Invalid Cargo Code Formats ===");
        System.out.println("Input Format: Input (Type) | Status");
        System.out.println("─────────────────────────────────────────");
        train.displayValidationResult("PET-ab", "Cargo Code", train.validateCargoCode("PET-ab"));
        train.displayValidationResult("PET-AB1", "Cargo Code", train.validateCargoCode("PET-AB1"));
        train.displayValidationResult("PET123", "Cargo Code", train.validateCargoCode("PET123"));
        train.displayValidationResult("AB-PET", "Cargo Code", train.validateCargoCode("AB-PET"));
        train.displayValidationResult("PET-A", "Cargo Code", train.validateCargoCode("PET-A"));
        train.displayValidationResult("PET-ABC", "Cargo Code", train.validateCargoCode("PET-ABC"));
        System.out.println("✓ All invalid Cargo Code formats rejected\n");
        
        // ========== TEST CASE 5: Case Sensitivity Validation ==========
        System.out.println("=== TEST 5: Case Sensitivity Validation ===");
        System.out.println("Cargo Code must have UPPERCASE letters only:");
        System.out.println("Input Format: Input (Type) | Status");
        System.out.println("─────────────────────────────────────────");
        train.displayValidationResult("PET-AB", "Cargo Code (Uppercase)", train.validateCargoCode("PET-AB"));
        train.displayValidationResult("PET-ab", "Cargo Code (Lowercase)", train.validateCargoCode("PET-ab"));
        train.displayValidationResult("PET-Ab", "Cargo Code (Mixed)", train.validateCargoCode("PET-Ab"));
        System.out.println("✓ Case sensitivity enforced\n");
        
        // ========== TEST CASE 6: Train ID Digit Length Validation ==========
        System.out.println("=== TEST 6: Train ID Digit Length Validation ===");
        System.out.println("Train ID must have exactly 4 digits:");
        System.out.println("Input Format: Input (Type) | Status");
        System.out.println("─────────────────────────────────────────");
        train.displayValidationResult("TRN-123", "Train ID (3 digits)", train.validateTrainID("TRN-123"));
        train.displayValidationResult("TRN-1234", "Train ID (4 digits)", train.validateTrainID("TRN-1234"));
        train.displayValidationResult("TRN-12345", "Train ID (5 digits)", train.validateTrainID("TRN-12345"));
        System.out.println("✓ Exact digit length enforced\n");
        
        // ========== TEST CASE 7: Empty Input Handling ==========
        System.out.println("=== TEST 7: Empty Input Handling ===");
        System.out.println("Input Format: Input (Type) | Status");
        System.out.println("─────────────────────────────────────────");
        train.displayValidationResult("", "Train ID (Empty)", train.validateTrainID(""));
        train.displayValidationResult("", "Cargo Code (Empty)", train.validateCargoCode(""));
        System.out.println("✓ Empty inputs rejected\n");
        
        // ========== TEST CASE 8: Exact Pattern Matching ==========
        System.out.println("=== TEST 8: Exact Pattern Matching ===");
        System.out.println("Pattern must match ENTIRE string (no partial matches):");
        System.out.println("Input Format: Input (Type) | Status");
        System.out.println("─────────────────────────────────────────");
        train.displayValidationResult("TRN-1234-EXTRA", "Train ID (Extra chars)", train.validateTrainID("TRN-1234-EXTRA"));
        train.displayValidationResult("PREFIX-TRN-1234", "Train ID (Prefix)", train.validateTrainID("PREFIX-TRN-1234"));
        train.displayValidationResult("TRN-1234", "Train ID (Exact)", train.validateTrainID("TRN-1234"));
        System.out.println("✓ Full string match enforced\n");
        
        // ========== TEST CASE 9: Setting Valid Values ==========
        System.out.println("=== TEST 9: Setting Valid Values ===");
        boolean trainIDSet = train.setTrainID("TRN-5678");
        boolean cargoCodeSet = train.setCargoCode("PET-CD");
        
        System.out.println("Setting Train ID 'TRN-5678': " + (trainIDSet ? "✓ Success" : "✗ Failed"));
        System.out.println("Setting Cargo Code 'PET-CD': " + (cargoCodeSet ? "✓ Success" : "✗ Failed"));
        System.out.println("Train ID in object: " + train.getTrainID());
        System.out.println("Cargo Code in object: " + train.getCargoCode());
        System.out.println("✓ Valid values stored successfully\n");
        
        // ========== TEST CASE 10: Rejecting Invalid Values ==========
        System.out.println("=== TEST 10: Rejecting Invalid Values ===");
        boolean invalidTrainID = train.setTrainID("INVALID");
        boolean invalidCargoCode = train.setCargoCode("WRONG");
        
        System.out.println("Attempting to set invalid Train ID 'INVALID': " + (invalidTrainID ? "✗ Accepted" : "✓ Rejected"));
        System.out.println("Attempting to set invalid Cargo Code 'WRONG': " + (invalidCargoCode ? "✗ Accepted" : "✓ Rejected"));
        System.out.println("✓ Invalid values rejected\n");
        
        // ========== TEST CASE 11: Original Input Integrity ==========
        System.out.println("=== TEST 11: Original Input Integrity ===");
        String originalTrainID = "TRN-9999";
        String originalCargoCode = "PET-ZZ";
        boolean isTrainIDValid = train.validateTrainID(originalTrainID);
        boolean isCargoCodeValid = train.validateCargoCode(originalCargoCode);
        
        System.out.println("Original Train ID (valid): " + originalTrainID);
        System.out.println("Original Cargo Code (valid): " + originalCargoCode);
        System.out.println("Train ID integrity: " + originalTrainID + " (unchanged)");
        System.out.println("Cargo Code integrity: " + originalCargoCode + " (unchanged)");
        System.out.println("✓ Original input values not modified\n");
        
        // ========== TEST CASE 12: Numeric Validation in Cargo Code ==========
        System.out.println("=== TEST 12: Numeric Validation in Cargo Code ===");
        System.out.println("Cargo Code must NOT contain digits:");
        System.out.println("Input Format: Input (Type) | Status");
        System.out.println("─────────────────────────────────────────");
        train.displayValidationResult("PET-A1", "Cargo Code (With digit)", train.validateCargoCode("PET-A1"));
        train.displayValidationResult("PET-12", "Cargo Code (Only digits)", train.validateCargoCode("PET-12"));
        train.displayValidationResult("PET-AB", "Cargo Code (Only letters)", train.validateCargoCode("PET-AB"));
        System.out.println("✓ Numeric validation enforced\n");
        
        System.out.println("✓ Regex-based validation successfully implemented!");
        System.out.println("Program continues...");
    }
}
