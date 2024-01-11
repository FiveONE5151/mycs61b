/**
 * CITE:https://blog.csdn.net/qq_41885673/article/details/120120089 GBK TO UTF8
 * CODING
 */
public class NBody {
    /**
     * Given a file name as a String, it should return a double corresponding to the
     * radius of the universe in that file
     * 
     * @param file the file name
     * @return radius of the universe in the file
     */
    public static double readRadius(String file) {
        In input = new In(file);
        input.readInt();
        return input.readDouble();
    }

    /**
     * Given a file name, it should return an array of Planet corresponding to the
     * planets in the file
     * 
     * @param file the file name
     * @return array of Planet of the file
     */
    public static Planet[] readPlanets(String file) {
        In input = new In(file);
        int arraySize = input.readInt();
        input.readDouble();
        Planet[] planets = new Planet[arraySize];
        for (int i = 0; i < arraySize; ++i) {
            planets[i] = new Planet(input.readDouble(), input.readDouble(), input.readDouble(), input.readDouble(),
                    input.readDouble(), input.readString());
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);
        StdDraw.setScale(-1 * radius, radius);
        StdDraw.clear();

        /*
         * StdDraw.picture(0, 0, "./images/starfield.jpg");
         * // StdDraw.show();
         * for (Planet planet : planets) {
         * planet.draw();
         * }
         */

        StdDraw.enableDoubleBuffering();
        double[] xForces = new double[planets.length];
        double[] yForces = new double[planets.length];
        for (int t = 0; t <= T; t += dt) {
            StdDraw.picture(0, 0, "./images/starfield.jpg");
            for (int i = 0; i < yForces.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
                planets[i].update(dt, xForces[i], yForces[i]);
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
