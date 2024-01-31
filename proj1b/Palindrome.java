import javax.swing.text.StyledEditorKit;

public class Palindrome {
    /**
     * Given a String, wordToDeque should return a Deque where the characters appear in the same order as in the String
     * 
     * @param word
     *            given string
     * @return deque containing the string
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++)
            deque.addLast(Character.valueOf(word.charAt(i)));
        return deque;
    }

    public boolean isPalindrome(String word) {
        if (word == null)
            return false;
        if (word.length() == 0 || word.length() == 1)
            return true;
        String reverse = "";
        Deque d = wordToDeque(word);
        for (int i = 0; i < word.length(); i++) {
            reverse += d.removeLast();
        }
        if (reverse.equals(word))
            return true;
        return false;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null || cc == null)
            return false;
        if (word.length() == 0 || word.length() == 1)
            return true;
        boolean flag = true;
        for (int i = 0, j = word.length() - 1; i < j; ++i, --j) {
            if (!cc.equalChars(word.charAt(i), word.charAt(j))) {
                flag = false;
                break;
            }
        }
        return flag;
    }

}
