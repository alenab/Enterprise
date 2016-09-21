package module3.part2;

public class ArrayDivider {
    public int[] values;
    public int numberOfThreads;

    public ArrayDivider(int[] values, int numberOfThreads) {
        this.values = values;
        this.numberOfThreads = numberOfThreads;
    }

    public int[] divide(int numberOfPart) {
        int partsWithRest = values.length % numberOfThreads;
        int partsWithoutRest = numberOfThreads - partsWithRest;
        if ((numberOfThreads -partsWithRest) - numberOfPart > 0) {
            int[] partArray = new int[values.length / numberOfThreads];
            for (int i = 0; i < partArray.length; i++) {
                partArray[i] = values[i + partArray.length * numberOfPart];
            }
            return partArray;
        } else {
            int[] partArray = new int[values.length / numberOfThreads + 1];
            for (int i = 0; i < partArray.length; i++) {
                partArray[i] = values[i +(partArray.length -1) * partsWithoutRest + partArray.length*(numberOfPart -partsWithoutRest) ];
            }
            return partArray;
        }
    }

//        if ((numberOfPart != (numberOfThreads - 1)) || (values.length % numberOfThreads == 0)) {
//            int[] partArray = new int[values.length / numberOfThreads];
//            for (int i = 0; i < partArray.length; i++) {
//                partArray[i] = values[i + partArray.length * numberOfPart];
//            }
//            System.out.println("Part " + numberOfPart);
//            for (int element : partArray) {
//                System.out.print(element + ",");
//            }
//            return partArray;
//        } else {
//            int[] partArray = new int[values.length / numberOfThreads + values.length % numberOfThreads];
//            for (int i = 0; i < partArray.length; i++) {
//                partArray[i] = values[i + (partArray.length - values.length % numberOfThreads) * numberOfPart];
//            }
//            System.out.println("Part " + numberOfPart);
//            for (int element : partArray) {
//                System.out.print(element + ",");
//            }
//            return partArray;
//        }
//    }
}
