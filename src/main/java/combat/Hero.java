package combat;

public class Hero extends AbstractCombatant {
    private int weaponDamage;
    private int armourBonus;

    public Hero(String name, int hp, int damage, int armour, int speed) {
        super(name, hp, damage, armour, speed);
    }

    public void equipWeapon(int damage) {
        weaponDamage = damage;
    }

    public void equipArmour(int armourBonus) {
        this.armourBonus = armourBonus;
    }

    @Override
    public int getDamage() {
        return super.getDamage() + weaponDamage;
    }

    @Override
    public int getArmour() {
        return super.getArmour() + armourBonus;
    }
}