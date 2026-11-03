package combat;

import java.util.ArrayList;
import java.util.List;

public class Battle {
    private final Combatant hero;
    private final Combatant enemy;
    private final TurnOrder turnOrder = new TurnOrder();

    public Battle(Combatant hero, Combatant enemy) {
        this.hero = hero;
        this.enemy = enemy;
    }

    // Runs one turn: both sides attack in speed order, a dead combatant does not act
    public List<TurnEvent> playTurn(AttackStrategy heroAttack) {
        List<TurnEvent> events = new ArrayList<>();

        if (isOver()) {
            return events;
        }

        Combatant first = turnOrder.whoActsFirst(hero, enemy);
        Combatant second = (first == hero) ? enemy : hero;

        act(first, second, heroAttack, events);
        if (second.isAlive()) {
            act(second, first, heroAttack, events);
        }
        return events;
    }

    public boolean isOver() {
        return !hero.isAlive() || !enemy.isAlive();
    }

    // Records one attack with the damage that actually got through
    private void act(Combatant attacker, Combatant target, AttackStrategy heroAttack, List<TurnEvent> events) {
        AttackStrategy strategy = (attacker == hero) ? heroAttack : new NormalAttack();
        int hpBefore = target.getHp();

        attacker.attack(target, strategy);

        events.add(new TurnEvent(attacker.getName(), target.getName(),
                hpBefore - target.getHp(), target.getHp()));
    }
}