package module1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;


public class Main {
    static int probes = 100;

    public enum CollectionType {
        ARRAY_LIST,
        LINKED_LIST,
        HASH_SET,
        TREE_SET
    }

    public static void main(String[] args) {
        int[] collectionSize = new int[]{10_000, 100_000, 1000_000};
        for (int size : collectionSize) {
            String fileName = size + "collections.csv";
            System.out.println("_            add\tget\t\tremove\t\tcontains\tpopulate\titerator.add\titerator.remove");
            printToFile(fileName, "", "add (ms) ", "get (ms)", "remove (ms)", "contains (ms)", "populate (ms)", "iterator.add (ms)",
                    "iterator.remove (ms)");

            for (CollectionType collectionType : CollectionType.values()) {
                Collection item = generateCollection(collectionType, size);

                String collectionName = item.getClass().getSimpleName();

                generateCollection(collectionType, size);
                double avgAdd = operationDurationAdd(item);

                generateCollection(collectionType, size);
                double avgRemove = operationDurationRemove(item);

                generateCollection(collectionType, size);
                double avgContains = operationDurationContains(item);

                generateCollection(collectionType, size);
                double avgPopulate = operationDurationPopulate(item);

                double avgGet = Double.NaN;
                double avgIteratorAdd = Double.NaN;
                double avgIteratorRemove = Double.NaN;
                if (item instanceof List) {
                    generateCollection(collectionType, size);
                    avgGet = operationDurationGet((List) item);

                    generateCollection(collectionType, size);
                    avgIteratorAdd = operationDurationIteratorAdd((List) item);

                    generateCollection(collectionType, size);
                    avgIteratorRemove = operationDurationIteratorRemove((List) item);
                }
                System.out.format("%s:\t%4.2f\t%4.2f\t%4.2f\t\t%4.2f\t\t%4.2f\t\t%4.2f\t\t\t%4.2f\t\n",
                        collectionName, avgAdd, avgGet, avgRemove, avgContains, avgPopulate, avgIteratorAdd, avgIteratorRemove);
                printToFile(fileName, collectionName, avgAdd, avgGet, avgRemove, avgContains, avgPopulate, avgIteratorAdd, avgIteratorRemove);

            }
        }
    }

    public static Collection generateCollection(CollectionType collectionType, int size) {
        Collection item = null;
        switch (collectionType) {
            case ARRAY_LIST:
                item = new ArrayList<>();
                break;
            case LINKED_LIST:
                item = new LinkedList<>();
                break;
            case HASH_SET:
                item = new HashSet<>();
                break;
            case TREE_SET:
                item = new TreeSet<>();
                break;
        }

        for (int i = 0; i < size; i++) {
            item.add(i);
        }
        return item;
    }

    private static double operationDurationAdd(Collection collection) {
        long timeAdd = 0;
        for (int i = 0; i < probes; i++) {
            long startTime = System.currentTimeMillis();
            collection.add(5);
            timeAdd += System.currentTimeMillis() - startTime;
        }
        return timeAdd / probes;
    }

    private static double operationDurationGet(List collection) {
        long timeGet = 0;
        for (int i = 0; i < probes; i++) {
            long startTime = System.currentTimeMillis();
            collection.get(9_999);
            timeGet += System.currentTimeMillis() - startTime;
        }
        return timeGet / probes;
    }

    private static double operationDurationRemove(Collection collection) {
        long timeRemove = 0;
        for (int i = 0; i < probes; i++) {
            long startTime = System.currentTimeMillis();
            collection.remove(9_999);
            timeRemove += System.currentTimeMillis() - startTime;
        }
        return timeRemove / probes;
    }

    private static double operationDurationContains(Collection collection) {
        long timeContains = 0;
        for (int i = 0; i < probes; i++) {
            long startTime = System.currentTimeMillis();
            collection.contains(9_999);
            timeContains += System.currentTimeMillis() - startTime;
        }
        return timeContains / probes;
    }

    private static double operationDurationPopulate(Collection collection) {
        long timePopulate = 0;
        ArrayList arrayListPopulate = new ArrayList();
        for (int i = 0; i < probes; i++) {
            long startTime = System.currentTimeMillis();
            collection.addAll(arrayListPopulate);
            timePopulate += System.currentTimeMillis() - startTime;
        }
        return timePopulate / probes;
    }

    private static double operationDurationIteratorAdd(List collection) {
        long timeIteratorAdd = 0;
        ListIterator listIterator = collection.listIterator();
        for (int i = 0; i < probes; i++) {
            long startTime = System.currentTimeMillis();
            listIterator.add(0);
            timeIteratorAdd += System.currentTimeMillis() - startTime;
        }
        return timeIteratorAdd / probes;
    }

    private static double operationDurationIteratorRemove(List collection) {
        long timeIteratorRemove = 0;
        ListIterator listIterator = collection.listIterator();
        for (int i = 0; i < probes; i++) {
            long startTime = System.currentTimeMillis();
            listIterator.next();
            listIterator.remove();
            timeIteratorRemove += System.currentTimeMillis() - startTime;
        }
        return timeIteratorRemove / probes;
    }

    public static void printToFile(String fileName, Object... values) {
        StringJoiner joiner = new StringJoiner(System.getProperty("path.separator"));
        for (Object value : values) {
            joiner.add(String.format("\"%s\"", value.toString()));
        }
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.append(joiner.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

