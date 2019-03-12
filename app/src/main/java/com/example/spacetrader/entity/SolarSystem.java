package com.example.spacetrader.entity;

/**
 * The SolarSystem class represents a solar system in the Universe. Each solarSystem
 * has a collection of planets predetermined during game creation. Each solarSystem
 * has a distinct coordinate in the universe. Once a player is at a solarSystem, they
 * can travel to any of its planets.
 */
public class SolarSystem{
    private String name;
    private Coordinate coordinate;
    private Planet[] planets;

    /**
     * Constructor for the Solar System
     * Randomly assigns a name
     * @param coordinate Location of the Solar System.
     */
    public SolarSystem(Coordinate coordinate) {
        this.name = generateRandomName();
        this.coordinate = coordinate;
        int planetNumber = 1 + (int)(Math.random() * 5);
        planets = new Planet[planetNumber];
        for (int i = 0; i < planetNumber; i++) {
            planets[i] = new Planet();
        }
    }

    /**
     * Gets the array of planets in the solarSystem
     * @return array of planets
     */
    public Planet [] getPlanets() {
        return planets;
    }

    /**
     * Gets if the planet is contained in the current solarSystem
     * @param planet
     * @return
     */
    public boolean contains(Planet planet) {
        for (Planet p: planets) {
            if (p.equals(planet)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the SolarSystem name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the coordinates of the Solar System
     * @return coordinate
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Transforms the coordinate to be plotted on a map of certain dimensions
     * @param width of the map
     * @param height of the map
     * @param buffer pixels of map frame that are left empty
     * @return new Coordinate
     */
    public Coordinate transformCoordinate(int width, int height, int buffer) {
        return coordinate.transform(width, height, buffer);
    }

    /**
     * Generates a random name or random string of numbers and letters.
     * @return
     */
    private String generateRandomName() {
        int randomInt = (int)(Math.random() * solarSystemNames.length);
        String randomName = solarSystemNames[randomInt];
        solarSystemNames[randomInt] = ""
                + (char)(65 + (int)(Math.random() * 26)) + (int)(Math.random() * 10000);
        return randomName;
    }

    private static String solarSystemNames[] =
            {"Acamar", "Adahn", "Aldea", "Andevian", "Antedi", "Balosnee", "Baratas", "Brax",
             "Bretel", "Calondia", "Campor", "Capelle", "Carzon", "Castor", "Cestus", "Cheron",
             "Courteney", "Daled", "Damast", "Davlos", "Deneb", "Deneva", "Devidia", "Draylon",
             "Drema", "Endor", "Esmee", "Exo", "Ferris", "Festen", "Fourmi", "Frolix", "Gemulon",
             "Guinifer", "Hades", "Hamlet", "Helena", "Hulst", "Iodine", "Iralius", "Janus",
             "Japori", "Jarada", "Jason", "Kaylon", "Khefka", "Kira", "Klaatu", "Klaestron",
             "Korma", "Kravat", "Krios", "Laertes", "Largo", "Lave", "Ligon", "Lowry", "Magrat",
             "Malcoria", "Melina", "Mentar", "Merik", "Mintaka", "Montor", "Mordan", "Myrthe",
             "Nelvana", "Nix", "Nyle", "Odet", "Og", "Omega", "Omphalos", "Orias", "Othello",
             "Parade", "Penthara", "Picard", "Pollux", "Quator", "Rakhar", "Ran", "Regulas",
             "Relva", "Rhymus", "Rochani", "Rubicum", "Rutia", "Sarpeidon", "Sefalla", "Seltrice",
             "Sigma", "Sol", "Somari", "Stakoron", "Styris", "Talani", "Tamus", "Tantalos",
             "Tanuga", "Tarchannen", "Terosa", "Thera", "Titan", "Torin", "Triacus", "Turkana",
             "Tyrus", "Umberlee", "Utopia", "Vadera", "Vagra", "Vandor", "Ventax", "Xenon",
             "Xerxes", "Yew", "Yojimbo", "Zalkon", "Zuul"};

    @Override
    public String toString() {
        String returnString = "Solar System: " + name + " " + coordinate + "\n";
        for (Planet p: planets) {
            returnString += "\t\t" + p.toString() + "\n";
        }
        return returnString;
    }
}
