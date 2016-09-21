package module3;

import module3.part2.ArrayDivider;
import org.junit.Assert;
import org.junit.Test;

public class ArrayDividerTest {

    @Test
    public void checkArrayDividedWithoutRest () {
        ArrayDivider arrayDivider = new ArrayDivider(new int[]{1, 2, 3, 3, 2, 1}, 3);
        Assert.assertArrayEquals(new int []{1,2}, arrayDivider.divide(0));
    }

    @Test
    public void checkArrayDividedWithRestInLastPart () {
        ArrayDivider arrayDivider = new ArrayDivider(new int[]{1, 2, 3, 4, 3, 2, 1}, 3);
        Assert.assertArrayEquals(new int []{3, 2, 1}, arrayDivider.divide(2));

    }

    @Test
    public void checkArrayDividedWithRestSeparatedBetweenParts () {
        ArrayDivider arrayDivider = new ArrayDivider(new int[]{1, 2, 3, 4, 4, 3, 2, 1}, 3);
        Assert.assertArrayEquals(new int []{3, 4, 4}, arrayDivider.divide(1));

    }

    @Test
    public void checkArrayDividedWithRestSeparatedBetweenPartsFirstPart () {
        ArrayDivider arrayDivider = new ArrayDivider(new int[]{1, 2, 3, 4, 4, 3, 2, 1}, 3);
        Assert.assertArrayEquals(new int []{1,2}, arrayDivider.divide(0));

    }

}
