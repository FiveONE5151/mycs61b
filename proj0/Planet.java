public class Planet {

    /**
     * Its current x position
     */
    public double xxPos;
    /**
     * Its current y position
     */
    public double yyPos;
    /**
     * Its current velocity in the x direction
     */
    public double xxVel;
    /**
     * Its current velocity in the y direction
     */
    public double yyVel;
    /**
     * Its mass
     */
    public double mass;
    /**
     * The name of the file that corresponds to the image that depicts the body
     */
    public String imgFileName;
    /**
     * The gravitational force constant
     */
    private static final double gConstant = 6.67 * Math.pow(10, -11);

    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Planet(Planet body) {
        xxPos = body.xxPos;
        yyPos = body.yyPos;
        xxVel = body.yyPos;
        yyVel = body.yyPos;
        mass = body.mass;
        imgFileName = body.imgFileName;
    }

    /**
     * Calculates the distance between two Planets
     * 
     * @param planet
     * @return double distance between two planets
     */
    public double calcDistance(Planet planet) {
        return Math.sqrt(Math.pow(xxPos - planet.xxPos, 2) + Math.pow(yyPos - planet.yyPos, 2));
    }

    /**
     * Calculate the force exerted on this planet by the given planet
     * 
     * @param planet given planet
     * @return double force
     */
    public double calcForceExertedBy(Planet planet) {
        return (gConstant * mass * planet.mass) / (Math.pow(this.calcDistance(planet), 2));
    }

    /**
     * Calculate the force exerted in X direction
     * 
     * @param planet given planet
     * @return double force in X
     */
    public double calcForceExertedByX(Planet planet) {
        double dx = planet.xxPos - this.xxPos;
        return this.calcForceExertedBy(planet) * (dx / this.calcDistance(planet));
    }

    /**
     * Calculate the force exerted in X direction
     * 
     * @param planet given planet
     * @return double force in Y
     */
    public double calcForceExertedByY(Planet planet) {
        double dy = planet.yyPos - this.yyPos;
        return this.calcForceExertedBy(planet) * (dy / this.calcDistance(planet));
    }

    /**
     * calculates the net X force exerted by all planets in that array upon the
     * current planet.
     * 
     * @param planets array of planets
     * @return the sum of force in X
     */
    public double calcNetForceExertedByX(Planet[] planets) {
        double tempX = 0,
                sumX = 0;
        for (Planet planet : planets) {
            if (planet.equals(this))
                continue;
            tempX = this.calcForceExertedByX(planet);
            sumX += tempX;
        }
        return sumX;
    }

    /**
     * calculates the net Y force exerted by all planets in that array upon the
     * current planet.
     * 
     * @param planets array of planets
     * @return the sum of force in Y
     */
    public double calcNetForceExertedByY(Planet[] planets) {
        double tempY = 0,
                sumY = 0;
        for (Planet planet : planets) {
            if (planet.equals(this))
                continue;
            tempY = this.calcForceExertedByY(planet);
            sumY += tempY;
        }
        return sumY;
    }

    /**
     * determines how much the forces exerted on the body will cause that body to
     * accelerate, and the resulting change in the body’s velocity and position in a
     * small period of time dt
     * 
     * @param dt    time elapse
     * @param fNetX Net force in X
     * @param fNetY Net force in Y
     */
    public void update(double dt, double fNetX, double fNetY) {
        double aNetX = fNetX / mass;
        double aNetY = fNetY / mass;
        xxVel = xxVel + dt * aNetX;
        yyVel = yyVel + dt * aNetY;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, new String("./images/" + imgFileName));
    }
}