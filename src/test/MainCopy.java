package test;

import java.util.HashSet;
import java.util.Set;

public class MainCopy {
    public static void main(String[] args) {

        StringBuilder item = new StringBuilder("item");
        Set<StringBuilder> finalSet = new HashSet<>();
        finalSet.add(item);

        HashSet<StringBuilder> copySet = new HashSet<>(finalSet);

        item.append("1");
        System.out.println(item);

        StringBuilder next = copySet.iterator().next();
        System.out.println(next);
    }
}