package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.algs4.In;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static int WIDTH = 50;
    private static int HEIGHT = 50;
    private static final TETile[] TILES_SET = {Tileset.FLOOR, Tileset.SAND, Tileset.TREE, Tileset.WALL, Tileset.GRASS,
        Tileset.MOUNTAIN, Tileset.FLOWER, Tileset.LOCKED_DOOR, Tileset.PLAYER};

    public static void setWorld(int w, int h) {
        WIDTH = w;
        HEIGHT = h;
    }

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
     * calculate the exact width of given row
     * 
     * @param size
     *            size of hexagon
     * @param row
     *            the row coordinate inside the hexagon, not the entire canvas
     * @return width
     */
    public static int hexRowWidth(int size, int row) {
        if (row < size)
            return size + 2 * row;
        return size + (size - 1) * 2 - (row - size) * 2;
    }

    /**
     * calculate the row offset of the given row
     * 
     * @param size
     *            size of hexagon
     * @param row
     *            the row coordinate inside the hexagon, not the entire canvas
     * @return the row offset e.g. for hexagon of size 3, the offset from row 0 to row 5 is: "2,1,0,1,2"
     *
     */
    public static int hexRowOffset(int size, int row) {
        if (row < size) {
            return size - 1 - row;
        }
        return row - size;
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

        for (int row = 0, yCoordinate; row < size * 2; ++row) {
            yCoordinate = p.getY() + row;
            for (int column = 0, xCoordinate = p.getX() + hexRowOffset(size, row); column < hexRowWidth(size, row);
                ++column, ++xCoordinate) {

                tiles[xCoordinate][yCoordinate] = TILES_SET[hexagonStyle];
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

    private static void initWorld(int worldWidth, int worldHeight, TETile[][] world) {
        for (int x = 0; x < worldWidth; x += 1) {
            for (int y = 0; y < worldHeight; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        System.out.println("Size of hexagon(>1): ");
        In in = new In();
        int hexSize = in.readInt();
        int hexWidth = hexWidthOf(hexSize);
        int hexHeight = hexHeightOf(hexSize);
        int worldWidth = hexWidth * 5;
        int worldHeight = hexHeight * 5;
        ter.initialize(worldWidth, worldHeight);
        setWorld(worldWidth, worldHeight);
        TETile[][] world = new TETile[worldWidth][worldHeight];
        initWorld(worldWidth, worldHeight, world);
        Position origin = new Position(0, hexHeight);

        for (int i = 0, n = 0; i < 5; i++) {
            if (i < 3) {
                n = 3 + i;
                addColumnOfRandomHexagon(world, origin, hexSize, n);
                origin = bottomRightOf(origin, hexSize);
            } else if (i == 3) {
                origin = topRightOf(topLeftOf(origin, hexSize), hexSize);
                n = 7 - i;
                addColumnOfRandomHexagon(world, origin, hexSize, n);
                origin = topRightOf(origin, hexSize);
            } else {
                n = 7 - i;
                addColumnOfRandomHexagon(world, origin, hexSize, n);
                origin = topRightOf(origin, hexSize);
            }
        }
        ter.renderFrame(world);
    }

}
