public class Arrays {

    public static void main(String[] args){

        int[][] fillRightArray = new int [5][8];
        fillRight(fillRightArray, 2);
        printArray(fillRightArray);
        System.out.println();

        int[][] fillDownArray = new int [5][8];
        fillDown(fillDownArray, -2);
        printArray(fillDownArray);
        System.out.println();

        System.out.println(getRowSum(fillRightArray, 0));
        System.out.println(getColSum(fillDownArray, 0));

    }


    public static void printArray(int[][] array) {


        for (int row = 0; row < array.length; ++row) {
            for (int col = 0; col < array[0].length; ++col) {

                System.out.format("%-10d",	array[row][col]);
            }

            System.out.println();

        }
    }


    public static int getColSum(int[][] array, int colIndex){

        int colSum = 0;

        if((colIndex >= array.length) || (colIndex < 0)){
            return -1;
        }
        for( int i = 0; i < array.length; ++i) {

                colSum = array[i][colIndex] + colSum;
        }
        return colSum;
    }

    public static int getRowSum(int[][] array, int rowIndex){

        int rowSum = 0;

        if((rowIndex >= array.length) || (rowIndex < 0)){
            return -1;
        }
        for( int j = 0; j < array[0].length; ++j) {

            rowSum = array[rowIndex][j] + rowSum;
        }
        return rowSum;


    }

    public static void fillDown(int[][] array, int step){
        int count = 1;

        for( int i = 0; i < array[0].length; ++i)
        {
            for( int j = 0; j < array.length; ++j){

                (array[j][i]) = step * count;
                count++;
            }
        }
    }

    public static void fillRight(int[][] array, int step){
        int count = 1;

        for( int i = 0; i < array.length; ++i)
        {
            for( int j = 0; j < array[0].length; ++j){

                (array[i][j]) = step * count;
                count++;
            }
        }
    }

}
