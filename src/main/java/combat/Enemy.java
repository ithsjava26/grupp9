package combat;

public class Enemy extends AbstractCombatant {
    public Enemy(String name, int hp, int damage, int armor, int speed) {
        super(name, hp, damage, armor, speed);
    }
}