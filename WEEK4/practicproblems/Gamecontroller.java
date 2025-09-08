package WEEK4.practicproblems;

import java.util.*;

enum StickCurve { LINEAR, EXPONENTIAL, LOGARITHMIC }
enum HapticsMode { OFF, LIGHT, MEDIUM, HEAVY }
enum LayoutPreset { DEFAULT, SOUTHPAW, CLAW }

class ControllerConfig {
    // Core inputs
    final double leftStickDeadZone;   // 0.0 - 1.0
    final double rightStickDeadZone;  // 0.0 - 1.0
    final StickCurve lookCurve;
    final StickCurve moveCurve;

    // Misc tuning
    final int lookSensitivity;        // 1 - 100
    final int moveSensitivity;        // 1 - 100
    final HapticsMode haptics;
    final LayoutPreset layout;

    // Per-button remap (e.g., "A" -> "JUMP")
    final Map<String, String> remap;

    // -------- Default constructor (generic, safe defaults)
    ControllerConfig() {
        this.leftStickDeadZone  = 0.12;
        this.rightStickDeadZone = 0.12;
        this.lookCurve          = StickCurve.EXPONENTIAL;
        this.moveCurve          = StickCurve.LINEAR;
        this.lookSensitivity    = 55;
        this.moveSensitivity    = 50;
        this.haptics            = HapticsMode.MEDIUM;
        this.layout             = LayoutPreset.DEFAULT;
        this.remap              = defaultRemap();
    }

    // -------- Parameterized constructor (fully specified)
    ControllerConfig(
            double leftDead, double rightDead,
            StickCurve lookCurve, StickCurve moveCurve,
            int lookSens, int moveSens,
            HapticsMode haptics, LayoutPreset layout,
            Map<String,String> remap
    ) {
        // Basic validation (kept lightweight)
        this.leftStickDeadZone  = clamp01(leftDead);
        this.rightStickDeadZone = clamp01(rightDead);
        this.lookCurve          = Objects.requireNonNull(lookCurve);
        this.moveCurve          = Objects.requireNonNull(moveCurve);
        this.lookSensitivity    = clamp(lookSens, 1, 100);
        this.moveSensitivity    = clamp(moveSens, 1, 100);
        this.haptics            = Objects.requireNonNull(haptics);
        this.layout             = Objects.requireNonNull(layout);
        this.remap              = Collections.unmodifiableMap(new LinkedHashMap<>(remap));
    }

    private static double clamp01(double v) {
        return Math.max(0.0, Math.min(1.0, v));
    }
    private static int clamp(int v, int min, int max) {
        return Math.max(min, Math.min(max, v));
    }
    private static Map<String,String> defaultRemap() {
        Map<String,String> m = new LinkedHashMap<>();
        m.put("A", "JUMP");
        m.put("B", "CROUCH");
        m.put("X", "RELOAD");
        m.put("Y", "SWAP_WEAPON");
        m.put("LB", "THROW_GRENADE");
        m.put("RB", "MELEE");
        m.put("LT", "AIM");
        m.put("RT", "FIRE");
        m.put("START", "PAUSE");
        return Collections.unmodifiableMap(m);
    }

    @Override public String toString() {
        return
            "ControllerConfig {\n" +
            "  leftStickDeadZone  = " + leftStickDeadZone + "\n" +
            "  rightStickDeadZone = " + rightStickDeadZone + "\n" +
            "  lookCurve          = " + lookCurve + "\n" +
            "  moveCurve          = " + moveCurve + "\n" +
            "  lookSensitivity    = " + lookSensitivity + "\n" +
            "  moveSensitivity    = " + moveSensitivity + "\n" +
            "  haptics            = " + haptics + "\n" +
            "  layout             = " + layout + "\n" +
            "  remap              = " + remap + "\n" +
            "}";
    }
}

public class Gamecontroller {
    public static void main(String[] args) {
        // 1) Using DEFAULT constructor (safe, generic game-ready profile)
        ControllerConfig defaultCfg = new ControllerConfig();

        // 2) Using PARAMETERIZED constructor (esports-style, aggressive look, southpaw layout)
        Map<String,String> tourneyRemap = new LinkedHashMap<>();
        tourneyRemap.put("A", "DODGE");
        tourneyRemap.put("B", "ROLL");
        tourneyRemap.put("X", "INTERACT");
        tourneyRemap.put("Y", "ULTIMATE");
        tourneyRemap.put("LB", "PING");
        tourneyRemap.put("RB", "ABILITY_1");
        tourneyRemap.put("LT", "ZOOM");
        tourneyRemap.put("RT", "FIRE");
        tourneyRemap.put("BACK", "SCOREBOARD");

        ControllerConfig proCfg = new ControllerConfig(
                0.08,                 // left deadzone
                0.10,                 // right deadzone
                StickCurve.LOGARITHMIC, // look curve for micro-aim
                StickCurve.LINEAR,      // move curve for predictable strafes
                80,                   // look sensitivity
                60,                   // move sensitivity
                HapticsMode.LIGHT,    // minimal haptics
                LayoutPreset.SOUTHPAW,// swapped sticks
                tourneyRemap
        );

        System.out.println("== Default Controller Config ==");
        System.out.println(defaultCfg);
        System.out.println();
        System.out.println("== Pro (Parameterized) Controller Config ==");
        System.out.println(proCfg);
    }
}
