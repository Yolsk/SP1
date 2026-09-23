public class SP1HeroBuilder {

    public class Character {
        String name;
        int healthPoints;
        int maxHealth;
        int level;
        int experiencePoints;
        double gold ;
        boolean isAlive;
        char classType;
        int attackPower;
        Item[] inventory;

        Character(String name, int maxHealth, int level, int experiencePoints, double gold, char classType, int attackPower) {
            isAlive = true;
            this.name = name;
            this.maxHealth = maxHealth;
            this.healthPoints = maxHealth;
            this.level = level;
            this.experiencePoints = experiencePoints;
            this.gold = gold;
            this.classType = classType;
            this.attackPower = attackPower;
        }

        Character(String name, char classType) {
            this.name = name;
            this.classType = classType;
        }


        void printCharacterSheet() {
            System.out.println("=== Character Sheet===");
            System.out.println("Name: " + name);
            System.out.println("Health Points: " + healthPoints);
            System.out.println("Max Health: " + maxHealth);
            System.out.println("Level: " + level);
            System.out.println("Experience points: " + experiencePoints);
            System.out.println("Gold: " + gold);
            System.out.println("Is alive: " + isAlive);
            System.out.println("Class: " + classType);

            printClassDescription();

        }
        void printClassDescription() {
            switch (classType) {
                case 'W':
                    System.out.println("Warrior, hack and slash!");
                    break;
                case 'M':
                    System.out.println("Mage, cast fireball!");
                    break;
                case 'R':
                    System.out.println("Rogue, get ready to backstab!");
                    break;
                default:
                    System.out.println("No class found");
            }
        }
        void takeDamage(int amount) {
            healthPoints = healthPoints - amount;
            if (healthPoints <= 0) {
                isAlive = false;
                System.out.println(name + " is dead");
                healthPoints = 0;

            } else {
                System.out.println("Incoming damage: " + amount);
                System.out.println("Health after combat: " + healthPoints);
                System.out.println(name + " is still alive!");
            }
        }
        void attack(Character target) {
            System.out.println(name + " attacks " + target.name + "!");
            target.takeDamage(attackPower);
        }
        void heal(int amount) {
            healthPoints += amount;
            if (healthPoints > maxHealth) {
                healthPoints = maxHealth;
                System.out.println(name + " is fully healed");
            } else {
                System.out.println(name + "'s Health Points: " + healthPoints);
            }
        }
        void addGold(double amount) {
            gold += amount;
            System.out.println("You received " + amount);
            System.out.println("Total gold: " + gold);
        }
        boolean removeGold(double amount) {
            if (gold > 0 && gold > amount) {  //Man kan ikke fjerne mere guld, end man har
                gold -= amount;
                System.out.println("You lost " + amount);
                System.out.println("Total gold: " + gold);
                return true;
            }
            System.out.println("Total gold: " + gold);
            return false;
        }
        void addXP(int amount) {
            experiencePoints += amount;
            System.out.println("You received " + amount + " XP");
            System.out.println("Total XP: " + experiencePoints);

            if (experiencePoints >= 1000 * level) {
                levelUp();
            }
        }
        void levelUp() {
            level++;
            experiencePoints = 0;
            maxHealth += 20;
            healthPoints += 20;
            System.out.println("Level up!");
            System.out.println("Level: " + level);
            System.out.println("Experience points: " + experiencePoints);
            System.out.println("maxHealth: " + maxHealth);
        }

        boolean isHealthCritical() {
            if (healthPoints < (maxHealth * 0.25)) {
                System.out.println("Warning: Health critical!");
                return true;
            }
            return false;
        }
        boolean isAlive() {
            if (healthPoints > 0) {
                return true;
            } else {
                return false;
            }
        }
        double getHealthPercentage() {
            return (((double) healthPoints) / maxHealth * 100);
        }
        void printInventory() {
            System.out.println("\nInventory Items for " + name + ": ");
            if (inventory != null){
                for (Item item : inventory) {
                    System.out.println("\t" + item.name);
                    System.out.println("\t" + item.weight);
                    System.out.println("\t" + item.value);
                    System.out.println();
                }
                System.out.println(inventory.length + " items in inventory");
            } else {
                System.out.println("No items");
            }
        }
    }

    public class Item {
        String name;
        double weight;
        double value;

        Item(String name, double weight, double value) {
            this.name = name;
            this.weight = weight;
            this.value = value;
        }
    }

    public class Weapon extends Item {
        int damage;
        int durability;

        Weapon(String name, double weight, double value, int damage, int durability) {
            super(name, weight, value);
            this.damage = damage;
            this.durability = durability;
        }
    }
        public class Armor extends Item {
        int defence;
        int durability;

        Armor(String name, double weight, double value, int defence, int durability) {
            super(name, weight, value);

            this.defence = defence;
            this.durability = durability;
        }
    }

    void main() {
        // Character initialization
        Character character1 = new Character("Jakob", 100, 6, 6000, 150, 'R', 30);
        Character character2 = new Character("Isa",80, 3, 1000, 1000, 'M', 20);

        character1.printCharacterSheet();

        System.out.println();
        character2.printCharacterSheet();
        System.out.println();

        // Creating Items
        Item cloak = new Item("Cloak", 1, 800.50);

        Weapon sword = new Weapon("Silver Sword", 10, 10000, 30, 100);

        Armor breastplate = new Armor("Leather Breatplate", 2, 1000,30, 100);

        character1.inventory = new Item[]{cloak, sword, breastplate};


        character1.printInventory();
        System.out.println();

        // Simuerling af combat //
        System.out.println("/// Engaged Combat /// ");
        character1.attack(character2);
        System.out.println();
        character1.attack(character2);
        System.out.println();
        character2.attack(character1);
        System.out.println();
        character1.attack(character2);
        System.out.println("/// Combat Ended /// ");
    }
}
