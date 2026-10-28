package combat;

public abstract class AbstractCombatant implements Combatant {
    private final String name;
    private int hp;
    private final int damage;
    private final int armor;
    private final int speed;

    protected AbstractCombatant(String name, int hp, int damage, int armor, int speed) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
        this.armor = armor;
        this.speed = speed;
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
    public int getArmor() {
        return armor;
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
        int dealt = Math.max(0, amount - armor);
        hp = Math.max(0, hp - dealt);
    }
}