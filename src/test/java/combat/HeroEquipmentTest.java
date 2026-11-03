package combat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeroEquipmentTest {

    // A weapon adds its damage on top of the base damage
    @Test
    void weaponAddsToBaseDamage() {
        Hero hero = new Hero("Hero", 20, 5, 1, 3);

        hero.equipWeapon(3);

        assertEquals(8, hero.getDamage());
    }

    // A new weapon replaces the old one instead of stacking
    @Test
    void newWeaponReplacesOldWeapon() {
        Hero hero = new Hero("Hero", 20, 5, 1, 3);

        hero.equipWeapon(3);
        hero.equipWeapon(6);

        assertEquals(11, hero.getDamage());
    }

    // Armour adds its defence on top of the base armour
    @Test
    void armourAddsToBaseArmour() {
        Hero hero = new Hero("Hero", 20, 5, 1, 3);

        hero.equipArmour(2);

        assertEquals(3, hero.getArmour());
    }

    // Equipped armour reduces the damage the hero takes: 10 - (1 + 2) = 7
    @Test
    void equippedArmourReducesDamageTaken() {
        Hero hero = new Hero("Hero", 20, 5, 1, 3);
        hero.equipArmour(2);

        hero.takeDamage(10);

        assertEquals(13, hero.getHp());
    }
}