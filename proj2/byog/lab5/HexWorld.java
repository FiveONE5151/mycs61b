package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.algs4.SET;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static final TETile[] TILES_SET = {Tileset.FLOOR, Tileset.SAND, Tileset.TREE, Tileset.WALL, Tileset.GRASS,
        Tileset.MOUNTAIN, Tileset.FLOWER, Tileset.LOCKED_DOOR, Tileset.PLAYER};

    /**
     * check whether the given position can hold a hexagon of given size
     *
     * @param width
     *            the width of the world
     * @param height
     *            the height of the world
     * @param p
     *            the left-down position of the hexagon
     * @param hexWidth
     *            maximum width of the hexagon
     * @param hexHeight
     *            height of the hexagon
     * @return true when the position can hold the hexagon, vice versa
     */
    public static boolean boundCheck(int width, int height, Position p, int hexWidth, int hexHeight) {
        if (p.getX() < 0 || p.getY() < 0 || p.getX() >= width || p.getY() >= height)
            throw new IllegalArgumentException("Coordinate of the hexagon is out of bound");
        if (p.getX() + hexWidth > width || p.getY() + hexHeight > height)
            return false;
        return true;
    }

    public static int hexHeightOf(int size) {
        return 2 * size;
    }

    public static int hexWidthOf(int size) {
        return size + 2 * (size - 1);
    }

    /**
     * add a hexagon of the certain size to a given position in the world
     *
     * @param tiles
     *            the world array
     * @param p
     *            the bottom-left position of the hexagon
     * @param size
     *            the size of hexagon
     * @param hexagonStyle
     *            the style of hexagon range from 0-8
     */
    public static void addHexagon(TETile[][] tiles, Position p, int size, int hexagonStyle) {
        int width = tiles[0].length;
        int height = tiles.length;
        // bound check
        int hexWidth = hexWidthOf(size);
        int hexHeight = hexHeightOf(size);
        if (!boundCheck(width, height, p, hexWidth, hexHeight))
            throw new IllegalArgumentException(
                "Hexagon at (" + p.getX() + "," + p.getY() + ") of size " + size + " is out of bound");
        // modify the tiles array according to the given coordinates
        int delta = 100;

        // first half
        for (int j = 0, row = p.getY() + hexHeight - j; j < hexHeight / 2; ++j, row = p.getY() + hexHeight - j) {
            for (int i = 0, column = i + p.getX(); i < hexWidth; ++i, column = i + p.getX()) {
                if (i < size - j - 1 || i > hexWidth - size + j)
                    continue;
                else
                    tiles[column][row] = TILES_SET[hexagonStyle];
            }
        }
        // second half
        for (int j = size, row = p.getY() + hexHeight - j; j < hexHeight; ++j, row = p.getY() + hexHeight - j) {
            for (int i = 0, column = i + p.getX(); i < hexWidth; ++i, column = i + p.getX()) {
                if (i < -size + j || i > hexWidth + size - j - 1)
                    continue;
                else
                    tiles[column][row] = TILES_SET[hexagonStyle];
            }
        }
    }

    /**
     * add a cloumn of hexagons, the bottom-left position is position p
     *
     * since a row of adjacent hexagons are difficult to denote in array, and a column would be easy to denote adjacent
     * hexagons(just add the Y with hexHeight)
     *
     * So we use a column as a unit to draw
     * 
     * @param tiles
     *            world array
     * @param p
     *            bottom-left of the column
     * @param size
     *            the size of hexagon
     * @param n
     *            the number of hexagons of the column
     */
    public static void addColumnOfRandomHexagon(TETile[][] tiles, Position p, int size, int n) {
        int hexWidth = hexWidthOf(size);
        int hexHeight = hexHeightOf(size);
        Random random = new Random(2024 + p.getX());
        Position ptr = new Position(p.getX(), p.getY());
        for (; ptr.getY() < p.getY() + n * hexHeight; ptr.setY(ptr.getY() + hexHeight)) {
            addHexagon(tiles, ptr, size, random.nextInt(TILES_SET.length));
        }
    }

    public static Position topRightOf(Position p, int size) {
        int hexWidth = hexWidthOf(size);
        int hexHeight = hexHeightOf(size);
        int ry = p.getY() + size;
        int rx = p.getX() + hexWidth - size + 1;
        if (rx + hexWidth > WIDTH || ry + hexHeight > HEIGHT)
            throw new IllegalArgumentException(
                "The top-right of hexagon at (" + p.getX() + "," + p.getY() + ") is out of bound");
        return new Position(rx, ry);
    }

    public static Position topLeftOf(Position p, int size) {
        int hexWidth = hexWidthOf(size);
        int hexHeight = hexHeightOf(size);
        int ry = p.getY() + size;
        int rx = p.getX() - hexWidth + size - 1;
        if (rx + hexWidth > WIDTH || ry + hexHeight > HEIGHT)
            throw new IllegalArgumentException(
                "The top-right of hexagon at (" + p.getX() + "," + p.getY() + ") is out of bound");
        return new Position(rx, ry);
    }

    public static Position bottomRightOf(Position p, int size) {
        int hexWidth = hexWidthOf(size);
        int hexHeight = hexHeightOf(size);
        int ry = p.getY() - size;
        int rx = p.getX() + hexWidth - size + 1;
        if (rx + hexWidth > WIDTH || ry + hexHeight < 0)
            throw new IllegalArgumentException(
                "The bottom-right of hexagon at (" + p.getX() + "," + p.getY() + ") is out of bound");
        return new Position(rx, ry);
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        int worldWidth = 40;
        int worldHeight = 40;
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        Position origin = new Position(0, 16);
        for (int i = 0, n = 0; i < 5; i++) {
            if (i < 3) {
                n = 3 + i;
                addColumnOfRandomHexagon(world, origin, 3, n);
                origin = bottomRightOf(origin, 3);
            } else if (i == 3) {
                origin = topRightOf(topLeftOf(origin, 3), 3);
                n = 7 - i;
                addColumnOfRandomHexagon(world, origin, 3, n);
                origin = topRightOf(origin, 3);
            } else {
                n = 7 - i;
                addColumnOfRandomHexagon(world, origin, 3, n);
                origin = topRightOf(origin, 3);
            }
        }
        ter.renderFrame(world);
    }
}
