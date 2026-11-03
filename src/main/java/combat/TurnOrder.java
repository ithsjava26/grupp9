package combat;

public class TurnOrder {

    // Faster combatant acts first; the hero wins ties
    public Combatant whoActsFirst(Combatant hero, Combatant enemy) {
        if (enemy.getSpeed() > hero.getSpeed()) {
            return enemy;
        }
        return hero;
    }
}