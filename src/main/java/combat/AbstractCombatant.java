package combat;

public abstract class AbstractCombatant implements Combatant {
    private final String name;
    private int hp;
    private final int damage;
    private final int armour;
    private final int speed;
    private final int maxHp;

    protected AbstractCombatant(String name, int hp, int damage, int armour, int speed) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
        this.armour = armour;
        this.speed = speed;
        this.maxHp = hp;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getArmour() {
        return armour;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }

    @Override
    public void takeDamage(int amount) {
        int dealt = Math.max(0, amount - getArmour());
        hp = Math.max(0, hp - dealt);
    }

    @Override
    public void heal(int amount) {
        if (!isAlive() || amount <= 0) {
            return;
        }
        hp = Math.min(maxHp, hp + amount);
    }
}