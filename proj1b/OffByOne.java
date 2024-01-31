public class OffByOne implements CharacterComparator {

    private boolean isAlphabetic(char c) {
        if (c >= 'A' && c <= 'z')
            return true;
        return false;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (!(isAlphabetic(x) && isAlphabetic(y)))
            return false;
        if (Math.abs(x - y) == 1)
            return true;
        return false;
    }
}
