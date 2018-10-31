import java.util.*;

public class Main {

    public static void main(String[] args) {
//        System.out.println(findBiggestChild("HARRY", "SALLY"));
//        System.out.println(findBiggestChild("AA", "BB"));
//        System.out.println(findBiggestChild("SHINCHAN", "NOHARAAA"));
        System.out.println(findBiggestChild("ABCDEF", "FBDAMN"));
    }

    private static long findBiggestChild(String s1, String s2) {

        Map<Character, Set<Integer>> s1PosMap = getCharPositionMap(s1);
        Map<Character, Set<Integer>> s2PosMap = getCharPositionMap(s2);


        Result r1 = getMatchCount(s1, s2PosMap);
        Result r2 = getMatchCount(s2, s1PosMap);

        System.out.println(r1.chars);
        System.out.println(r2.chars);

        return Math.max(r1.count, r2.count);

    }

    private static Result getMatchCount(String string, Map<Character, Set<Integer>> otherStringPositionMap) {
        Integer count = 0;
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < string.length(); i++) {
            Character c = string.charAt(i);
            if (otherStringPositionMap.containsKey(c)) {
                Set<Integer> positions = otherStringPositionMap.get(c);
                Integer position = i;
                Optional<Integer> firstPosition = positions.stream().filter(p -> p >= position).findFirst();
                if (firstPosition.isPresent()) {
                    count++;
                    positions.remove(firstPosition.get());
                    chars.add(c);
                }
            }
        }
        Result res = new Result();
        res.count = count;
        res.chars = chars;
        return res;
    }

    private static Map<Character, Set<Integer>> getCharPositionMap(String string) {
        Map<Character, Set<Integer>> positionMap = new LinkedHashMap<>();
        for (int i = 0; i < string.length(); i++) {
            Character c = string.charAt(i);
            if (!positionMap.containsKey(c)) {
                Set<Integer> positionSet = new LinkedHashSet<>();
                positionSet.add(i);
                positionMap.put(c, positionSet);
            } else {
                positionMap.get(c).add(i);
            }
        }
        return positionMap;
    }
}

class Result {
    int count;
    List<Character> chars;
}


