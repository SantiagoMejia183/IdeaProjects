public class HexDecoder {

    public static void main(String[] args){
        double decimal;

        String commandLineArg = args[0];
        decimal = hexConvert(commandLineArg);
        System.out.print((long)decimal);
    }

    public static double hexConvert(String hexArgument) {
        char[] charHexArray = hexArgument.toCharArray();
        int increment;
        int hexMultiple;
        double hexSixteen;
        double decimal = 0;
        double partialSum;
        char subtractChar;

        for (increment = 0; increment < charHexArray.length; ++increment) {

            if (charHexArray[increment] == 'x') {
                charHexArray[increment] = '0';
            }
            switch(charHexArray[increment]){

                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                    charHexArray[increment] = Character.toUpperCase(charHexArray[increment]);
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                    subtractChar = '7';
                    break;
                default:
                    subtractChar = '0';
                    break;
            }
                hexMultiple = (((int) (charHexArray[increment])) - subtractChar);
                hexSixteen = (Math.pow(16, (charHexArray.length - 1 - increment)));

                partialSum = (hexSixteen * hexMultiple);
                decimal = partialSum + decimal;
            }
        return decimal;
        }
    }