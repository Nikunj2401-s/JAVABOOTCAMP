package WEEK5.assignmentproblms;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/*
 * Single-file Hospital Management System demo
 * - Immutable MedicalRecord
 * - Patient with multiple admission constructors (chaining)
 * - Doctor, Nurse, Administrator with role-based access
 * - HospitalSystem for admission and access validation
 *
 * Note: classes are package-private except Main (public) to fit "one file" requirement.
 */

// ---------------------- Immutable MedicalRecord ----------------------
final class MedicalRecord {
    private final String recordId;
    private final String patientDNA;
    private final String[] allergies;
    private final String[] medicalHistory;      // past diagnoses, surgeries, etc.
    private final LocalDate birthDate;
    private final String bloodType;

    // Simple HIPAA-style validations (illustrative)
    private static final Set<String> VALID_BLOOD_TYPES = Set.of(
            "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
    );

    public MedicalRecord(String recordId,
                         String patientDNA,
                         String[] allergies,
                         String[] medicalHistory,
                         LocalDate birthDate,
                         String bloodType) {
        if (recordId == null || recordId.isBlank()) throw new IllegalArgumentException("recordId required");
        if (patientDNA == null || patientDNA.length() < 10) // simple sanity check
            throw new IllegalArgumentException("Invalid patient DNA");
        if (birthDate == null || birthDate.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Invalid birthDate");
        if (bloodType == null || !VALID_BLOOD_TYPES.contains(bloodType))
            throw new IllegalArgumentException("Invalid blood type");

        // defensive copies for arrays
        this.recordId = recordId;
        this.patientDNA = patientDNA;
        this.allergies = (allergies == null) ? new String[0] : Arrays.copyOf(allergies, allergies.length);
        this.medicalHistory = (medicalHistory == null) ? new String[0] : Arrays.copyOf(medicalHistory, medicalHistory.length);
        this.birthDate = birthDate;
        this.bloodType = bloodType;
    }

    // Getters only (defensive copies where needed)
    public String getRecordId() { return recordId; }
    public String getPatientDNA() { return patientDNA; }
    public String[] getAllergies() { return Arrays.copyOf(allergies, allergies.length); }
    public String[] getMedicalHistory() { return Arrays.copyOf(medicalHistory, medicalHistory.length); }
    public LocalDate getBirthDate() { return birthDate; }
    public String getBloodType() { return bloodType; }

    // public final for safety (cannot be overridden)
    public final boolean isAllergicTo(String substance) {
        if (substance == null || substance.isBlank()) return false;
        for (String a : allergies) {
            if (substance.equalsIgnoreCase(a)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        // Avoid printing patient DNA — privacy protection. Include non-sensitive summary.
        return "MedicalRecord{id=" + recordId +
                ", birthDate=" + birthDate +
                ", bloodType=" + bloodType +
                ", allergies=" + Arrays.toString(allergies) +
                ", historyCount=" + medicalHistory.length +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MedicalRecord)) return false;
        MedicalRecord that = (MedicalRecord) o;
        return recordId.equals(that.recordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId);
    }
}

// ---------------------- Patient ----------------------
class Patient {
    // Protected health information (PHI) - immutable reference to medical record
    private final String patientId;
    private final MedicalRecord medicalRecord;

    // Modifiable personal data
    private String currentName;
    private String emergencyContact;
    private String insuranceInfo;

    // Current treatment info
    private int roomNumber;
    private String attendingPhysician;

    // Audit trail meta
    private final LocalDate admissionDate;
    private final List<String> auditTrail = new ArrayList<>();

    // Constructor 1: Emergency admission (minimal data) - generates temp ID and minimal medical record
    public Patient(String name, LocalDate birthDate) {
        // Create a minimal temporary MedicalRecord with minimal checks
        this.patientId = "TMP-" + UUID.randomUUID();
        this.medicalRecord = new MedicalRecord(
                "MR-" + UUID.randomUUID(),
                generateDummyDNA(),            // dummy DNA for emergency
                new String[0],
                new String[0],
                Objects.requireNonNull(birthDate),
                "O+"                           // default safe blood type is illustrative
        );
        this.currentName = Objects.requireNonNull(name);
        this.emergencyContact = "UNKNOWN";
        this.insuranceInfo = "UNKNOWN";
        this.roomNumber = -1;
        this.attendingPhysician = "Triage";
        this.admissionDate = LocalDate.now();
        recordAudit("Emergency admission created");
    }

    // Constructor 2: Standard admission (full patient information)
    public Patient(String patientId,
                   MedicalRecord medicalRecord,
                   String currentName,
                   String emergencyContact,
                   String insuranceInfo,
                   int roomNumber,
                   String attendingPhysician) {
        // chain to main constructor
        this(patientId, medicalRecord, currentName, emergencyContact, insuranceInfo, roomNumber, attendingPhysician, LocalDate.now());
    }

    // Main constructor used by others (transfer admission may call this)
    public Patient(String patientId,
                   MedicalRecord medicalRecord,
                   String currentName,
                   String emergencyContact,
                   String insuranceInfo,
                   int roomNumber,
                   String attendingPhysician,
                   LocalDate admissionDate) {
        if (patientId == null || patientId.isBlank()) throw new IllegalArgumentException("patientId required");
        if (medicalRecord == null) throw new IllegalArgumentException("medicalRecord required");
        if (currentName == null || currentName.isBlank()) throw new IllegalArgumentException("currentName required");
        if (roomNumber < -1) throw new IllegalArgumentException("Invalid room number");

        this.patientId = patientId;
        this.medicalRecord = medicalRecord;
        this.currentName = currentName;
        this.emergencyContact = (emergencyContact == null) ? "UNKNOWN" : emergencyContact;
        this.insuranceInfo = (insuranceInfo == null) ? "UNKNOWN" : insuranceInfo;
        this.roomNumber = roomNumber;
        this.attendingPhysician = (attendingPhysician == null) ? "Unassigned" : attendingPhysician;
        this.admissionDate = Objects.requireNonNull(admissionDate);
        recordAudit("Standard/Transfer admission created");
    }

    // Generate dummy DNA (emergency). In reality you'd not do this; here for demo only.
    private static String generateDummyDNA() {
        return "EMERGENCYDNA" + UUID.randomUUID().toString().replace("-", "").substring(0, 12);
    }

    // JavaBean getters/setters (validated where appropriate)
    public String getPatientId() { return patientId; }              // immutable id
    public MedicalRecord getMedicalRecord() { return medicalRecord; } // PHI - reference only

    public String getCurrentName() { return currentName; }
    public void setCurrentName(String currentName) {
        if (currentName == null || currentName.isBlank()) throw new IllegalArgumentException("Name required");
        this.currentName = currentName;
        recordAudit("Name updated");
    }

    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = (emergencyContact == null) ? "UNKNOWN" : emergencyContact;
        recordAudit("Emergency contact updated");
    }

    public String getInsuranceInfo() { return insuranceInfo; }
    public void setInsuranceInfo(String insuranceInfo) {
        this.insuranceInfo = (insuranceInfo == null) ? "UNKNOWN" : insuranceInfo;
        recordAudit("Insurance info updated");
    }

    public int getRoomNumber() { return roomNumber; }
    public void setRoomNumber(int roomNumber) {
        if (roomNumber < 0) throw new IllegalArgumentException("Room number must be >= 0");
        this.roomNumber = roomNumber;
        recordAudit("Room assigned: " + roomNumber);
    }

    public String getAttendingPhysician() { return attendingPhysician; }
    public void setAttendingPhysician(String attendingPhysician) {
        this.attendingPhysician = (attendingPhysician == null) ? "Unassigned" : attendingPhysician;
        recordAudit("Attending physician set: " + this.attendingPhysician);
    }

    // Package-private basic info for hospital staff (more detail but still limited)
    String getBasicInfo() {
        return String.format("ID=%s, Name=%s, Room=%s, Physician=%s", patientId, currentName,
                (roomNumber < 0 ? "NotAssigned" : roomNumber), attendingPhysician);
    }

    // Public non-sensitive info
    public String getPublicInfo() {
        return String.format("Name=%s, Room=%s", currentName, (roomNumber < 0 ? "NotAssigned" : roomNumber));
    }

    // Audit trail recorder (private)
    private void recordAudit(String action) {
        String entry = LocalDate.now().format(DateTimeFormatter.ISO_DATE) + " - " + action;
        auditTrail.add(entry);
    }

    // Expose audit trail only to trusted internal components via package-private method
    List<String> getAuditTrailInternal() {
        return new ArrayList<>(auditTrail);
    }

    @Override
    public String toString() {
        // Avoid printing PHI like DNA or full medical history.
        return "Patient{" +
                "patientId='" + patientId + '\'' +
                ", name='" + currentName + '\'' +
                ", room=" + (roomNumber < 0 ? "Unassigned" : roomNumber) +
                ", physician='" + attendingPhysician + '\'' +
                ", admissionDate=" + admissionDate +
                ", auditEntries=" + auditTrail.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return patientId.equals(patient.patientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId);
    }
}

// ---------------------- Medical Staff ----------------------
class Doctor {
    private final String licenseNumber;
    private final String specialty;
    private final Set<String> certifications;

    public Doctor(String licenseNumber, String specialty, Set<String> certifications) {
        if (licenseNumber == null || licenseNumber.isBlank()) throw new IllegalArgumentException("license required");
        if (specialty == null || specialty.isBlank()) throw new IllegalArgumentException("specialty required");
        this.licenseNumber = licenseNumber;
        this.specialty = specialty;
        this.certifications = (certifications == null) ? new HashSet<>() : new HashSet<>(certifications);
    }

    public String getLicenseNumber() { return licenseNumber; }
    public String getSpecialty() { return specialty; }
    public Set<String> getCertifications() { return Collections.unmodifiableSet(certifications); }

    @Override
    public String toString() {
        return "Doctor{license=" + licenseNumber + ", specialty=" + specialty + "}";
    }
}

class Nurse {
    private final String nurseId;
    private final String shift;
    private final List<String> qualifications;

    public Nurse(String nurseId, String shift, List<String> qualifications) {
        if (nurseId == null || nurseId.isBlank()) throw new IllegalArgumentException("nurseId required");
        if (shift == null || shift.isBlank()) throw new IllegalArgumentException("shift required");
        this.nurseId = nurseId;
        this.shift = shift;
        this.qualifications = (qualifications == null) ? new ArrayList<>() : new ArrayList<>(qualifications);
    }

    public String getNurseId() { return nurseId; }
    public String getShift() { return shift; }
    public List<String> getQualifications() { return Collections.unmodifiableList(qualifications); }

    @Override
    public String toString() {
        return "Nurse{id=" + nurseId + ", shift=" + shift + "}";
    }
}

class Administrator {
    private final String adminId;
    private final List<String> accessPermissions;

    public Administrator(String adminId, List<String> accessPermissions) {
        if (adminId == null || adminId.isBlank()) throw new IllegalArgumentException("adminId required");
        this.adminId = adminId;
        this.accessPermissions = (accessPermissions == null) ? new ArrayList<>() : new ArrayList<>(accessPermissions);
    }

    public String getAdminId() { return adminId; }
    public List<String> getAccessPermissions() { return Collections.unmodifiableList(accessPermissions); }

    @Override
    public String toString() {
        return "Admin{id=" + adminId + "}";
    }
}

// ---------------------- HospitalSystem ----------------------
class HospitalSystem {
    // patientRegistry stores Patient objects keyed by patientId
    private final Map<String, Object> patientRegistry = new HashMap<>(); // Object to allow future types
    private final String hospitalName;

    // policy constants
    static final String POLICY_MIN_STAFF_CERT = "MEDICALLY_CERTIFIED";
    static final int POLICY_MAX_ROOM = 1000;
    public static final String HOSPITAL_SYSTEM_VERSION = "1.0-PRIVACY";

    public HospitalSystem(String hospitalName) {
        this.hospitalName = (hospitalName == null || hospitalName.isBlank()) ? "Unnamed" : hospitalName;
    }

    // Admit patient: staff must be doctor or nurse or admin; do instanceof checks for validation
    public boolean admitPatient(Object patientObj, Object staffObj) {
        if (!(patientObj instanceof Patient)) throw new IllegalArgumentException("patientObj must be Patient");
        if (!validateStaffAccess(staffObj, patientObj)) {
            System.out.println("Access denied: staff lacks permission to admit patient.");
            return false;
        }
        Patient p = (Patient) patientObj;
        patientRegistry.put(p.getPatientId(), p);
        // package-private operation: internal assignment
        internalAssignRoomIfNeeded(p);
        System.out.println("Patient admitted: " + p.getPatientId());
        return true;
    }

    // Validate staff access to patient (private)
    private boolean validateStaffAccess(Object staffObj, Object patientObj) {
        if (staffObj instanceof Doctor) {
            Doctor d = (Doctor) staffObj;
            // simplistic check: doctor must hold at least one certification
            return !d.getCertifications().isEmpty();
        } else if (staffObj instanceof Nurse) {
            Nurse n = (Nurse) staffObj;
            // Nurse can admit if qualified list contains "Triage" or similar
            return n.getQualifications().contains("Triage");
        } else if (staffObj instanceof Administrator) {
            Administrator a = (Administrator) staffObj;
            // Admin must have explicit permission
            return a.getAccessPermissions().contains("ADMIT_PATIENT");
        }
        return false;
    }

    // Package-private: internal operation used by hospital staff components
    void internalAssignRoomIfNeeded(Patient patient) {
        if (patient.getRoomNumber() < 0) {
            int assigned = new Random().nextInt(POLICY_MAX_ROOM) + 1;
            try {
                patient.setRoomNumber(assigned);
            } catch (IllegalArgumentException ex) {
                // fallback
            }
        }
    }

    // Public: staff reading patient basic info - enforces role checks via instanceof
    public String fetchPatientBasicInfo(String patientId, Object staffObj) {
        Object obj = patientRegistry.get(patientId);
        if (obj == null) return "Patient not found";
        if (!(obj instanceof Patient)) return "Invalid patient record";
        Patient p = (Patient) obj;

        // Role-based partial visibility
        if (staffObj instanceof Doctor || staffObj instanceof Nurse || staffObj instanceof Administrator) {
            return p.getBasicInfo(); // staff get basic info
        } else {
            return p.getPublicInfo(); // others get only public info
        }
    }

    // Package-private: retrieve internal audit trail for authorized admin only
    List<String> retrieveAuditTrail(String patientId, Administrator admin) {
        if (admin == null || !admin.getAccessPermissions().contains("VIEW_AUDIT")) {
            throw new SecurityException("Not authorized");
        }
        Object obj = patientRegistry.get(patientId);
        if (obj == null || !(obj instanceof Patient)) return Collections.emptyList();
        Patient p = (Patient) obj;
        return p.getAuditTrailInternal();
    }

    // For demo/test purposes
    public void printRegistrySummary() {
        System.out.println("Hospital: " + hospitalName + " Registry size: " + patientRegistry.size());
        for (Object v : patientRegistry.values()) {
            System.out.println(" - " + v.toString());
        }
    }
}
public class HospitalDemo {
    public static void main(String[] args) {
        // Setup hospital system
        HospitalSystem hs = new HospitalSystem("Saint Mercy");

        // Create staff
        Doctor dr = new Doctor("LIC-12345", "Cardiology", Set.of("BoardCertified"));
        Nurse nurse = new Nurse("N-001", "Day", List.of("Triage", "IV"));
        Administrator admin = new Administrator("ADM-01", List.of("ADMIT_PATIENT", "VIEW_AUDIT"));

        // Create a full MedicalRecord (immutable)
        MedicalRecord mr = new MedicalRecord(
                "MR-" + UUID.randomUUID(),
                "ACTUALDNASTRING12345",
                new String[]{"Penicillin", "Peanuts"},
                new String[]{"Appendectomy-2015", "Hypertension"},
                LocalDate.of(1980, 5, 20),
                "A+"
        );

        // Standard admission (full info)
        Patient standardPatient = new Patient(
                "PAT-" + UUID.randomUUID(),
                mr,
                "Alice Johnson",
                "Mom: +1-555-0100",
                "InsureCorp Plan A",
                101,
                "Dr. Banner"
        );

        // Emergency admission (minimal)
        Patient emergencyPatient = new Patient("John Doe", LocalDate.of(1990, 1, 1));

        // Try admitting patients with different staff
        boolean admitted1 = hs.admitPatient(standardPatient, dr);      // expect true (doctor with cert)
        boolean admitted2 = hs.admitPatient(emergencyPatient, nurse);  // expect true (nurse with Triage)
        boolean admitted3 = hs.admitPatient(new Patient("Temp", LocalDate.of(2000,1,1)), new Administrator("X", List.of())); // fail

        System.out.println("Admitted standardPatient? " + admitted1);
        System.out.println("Admitted emergencyPatient? " + admitted2);
        System.out.println("Admitted third patient? " + admitted3);

        // Fetch info as different roles
        System.out.println("Doctor fetch basic info: " + hs.fetchPatientBasicInfo(standardPatient.getPatientId(), dr));
        System.out.println("Public fetch basic info: " + hs.fetchPatientBasicInfo(standardPatient.getPatientId(), new Object())); // anonymous

        // Admin retrieving audit trail
        try {
            List<String> audit = hs.retrieveAuditTrail(standardPatient.getPatientId(), admin);
            System.out.println("Audit entries for " + standardPatient.getPatientId() + ": " + audit);
        } catch (SecurityException se) {
            System.out.println("Admin not authorized to view audit");
        }

        // Print registry summary (internal hospital view - for staff)
        hs.printRegistrySummary();
    }
}
