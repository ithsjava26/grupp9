package combat;

// One attack during a turn: who hit whom, for how much, and the target's hp afterwards
public record TurnEvent(String attacker, String target, int damage, int targetHpLeft) {
}
