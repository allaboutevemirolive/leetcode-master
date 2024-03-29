import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class F336 {
    public List<List<Integer>> palindromePairs(String[] words) {

        List<List<Integer>> res = new ArrayList<>();

        HashMap<String, Integer> map = new HashMap<>();
        boolean[] dict = new boolean[5001];
        int cnt = 0;
        for (String word : words) {
            map.put(word, cnt);
            cnt++;
            dict[word.length()] = true;
        }
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.length() > 0) {
                char[] ch = word.toCharArray();

                String re = new StringBuilder(word).reverse().toString();

                for (int j = 0; j < ch.length - 1; j++) {
                    if (dict[j + 1] && isPalindrome(ch, j + 1, ch.length - 1)) {
                        String check = re.substring(ch.length - j - 1, ch.length);

                        if (map.containsKey(check)) {
                            res.add(Arrays.asList(i, map.get(check)));
                        }
                    }
                }
                System.out.println("=====================================");
                System.out.println("ch: " + Arrays.toString(ch) + "\nre: " + re);

                for (int j = 0; j < ch.length - 1; j++) {
                    System.out.println();
                    System.out.println("i : " + i);
                    System.out.println("j: " + j);

                    System.out.print("ch(0, j(inclusive)): " );
                    for (int k = 0; k >= 0 && k <= j; k++) {
                        System.out.print(ch[k]);
                    }
                    System.out.println();

                    boolean isPalindrome = isPalindrome(ch, 0, j);

                    System.out.println("isPalindrome: " + isPalindrome);
                    System.out.println("dict[ch.length - j - 1]: " + dict[ch.length - j - 1]);

                    if (dict[ch.length - j - 1] && isPalindrome) {
                        System.out.println("is palindrome");

                        String check = re.substring(0, ch.length - j - 1);
                        System.out.println("re: " + re + "\nch.length - j - 1: " + (ch.length - j - 1));
                        System.out.println("check: " + check);

                        if (map.containsKey(check)) {
                            System.out.println("map.containsKey(check): " + map.containsKey(check));
                            System.out.println("map: " + map);
                            System.out.println("i: " + i);
                            System.out.println("res(before): " + res);
                            res.add(Arrays.asList(map.get(check), i));
                            System.out.println("res(after) : " + res);
                        } else {
                            System.out.println("not map.containsKey(check): " + map.containsKey(check));
                        }
                    } else {
                        System.out.println("not palindrome");
                    }
                }
                if (isPalindrome(ch, 0, ch.length - 1)) {
                    if (map.containsKey("")) {
                        int index = map.get("");
                        res.add(Arrays.asList(index, i));
                        res.add(Arrays.asList(i, index));
                    }
                } else if (map.containsKey(re)) {
                    res.add(Arrays.asList(i, map.get(re)));
                }
            }
        }
        return res;
    }

    private boolean isPalindrome(char[] ch, int left, int right) {
        while (left < right) {
            if (ch[left] != ch[right])
                return false;
            left++;
            right--;
        }
        return true;
    }
}