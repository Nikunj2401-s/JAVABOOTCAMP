package WEEK6.assignmentproblems;

class Tool {
    private String privateField = "Private: Only in Tool";
    protected String protectedField = "Protected: Accessible in child";
    public String publicField = "Public: Accessible everywhere";

    public String getPrivateField() {
        return privateField;
    }
}

class Hammer extends Tool {
    void showAccess() {
        // System.out.println(privateField); // ❌ not accessible
        System.out.println(getPrivateField()); // ✅ access via getter
        System.out.println(protectedField);    // ✅ accessible
        System.out.println(publicField);       // ✅ accessible
    }
}

public class ToolAcces {
    public static void main(String[] args) {
        Hammer h = new Hammer();
        h.showAccess();
    }
}
