import java.util.*;
import java.lang.Math;
public class Main {

    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);

        int length;
        int width;
        int radius;
        int sideATriangle;
        int sideBTriangle;
        int sideCTriangle;

        System.out.print("Enter length of rectangle (positive integer): ");
        length = scanner.nextInt();

        System.out.print("Enter width of rectangle (positive integer): ");
        width = scanner.nextInt();

        System.out.print("Now enter the radius of the circle (positive integer): ");
        radius = scanner.nextInt();

        System.out.print("Enter side A of triangle (positive integer): ");
        sideATriangle = scanner.nextInt();

        System.out.print("Enter side B of triangle (positive integer): ");
        sideBTriangle = scanner.nextInt();

        System.out.print("Enter side C of triangle (positive integer): ");
        sideCTriangle = scanner.nextInt();


        int perimeterOfRectangle = 2 *(length + width);
        int areaOfRectangle = width * length;

        double circumferenceOfCircle = (Math.PI) * 2 * radius;
        double areaOfCircle = (Math.PI) * Math.pow(radius,2);

        int perimeterOfTriangle = sideATriangle + sideBTriangle + sideCTriangle;
        int S = (sideATriangle + sideBTriangle + sideCTriangle)/2;
        double areaOfTriangle =  Math.sqrt((S)*(S-sideATriangle)*(S-sideBTriangle)*(S-sideCTriangle));



        System.out.println("\n" + "Area of rectangle: " + areaOfRectangle);
        System.out.println("Area of circle: " + areaOfCircle);
        System.out.println("Perimeter of rectangle: " + perimeterOfRectangle);
        System.out.println("Circumference of Circle: " + circumferenceOfCircle);
        System.out.println("Area of Triangle: " + areaOfTriangle);
        System.out.println("Perimeter of Triangle: "+ perimeterOfTriangle + "\n");




        System.out.print("Enter the radius of the sphere: ");
        int radiusSphere = scanner.nextInt();

        System.out.print("Enter the diameter of the rectangular prism: ");
        int diameterRectangularPrism = scanner.nextInt();

        System.out.print("Enter the height of the rectangular prism: ");
        int heightRectangularPrism = scanner.nextInt();

        System.out.print("Enter the width of the rectangular prism: ");

        int widthRectangularPrism = scanner.nextInt();


        System.out.print("Enter the height of the cylinder: ");
        int heightCylinder = scanner.nextInt();

        System.out.print("Enter the radius of the cylinder: ");
        int radiusCylinder = scanner.nextInt();

        double areaOfSphere = 4 * (Math.PI) * Math.pow(radiusSphere,2);
        double volumeOfSphere = 4 * (Math.PI) * ((Math.pow(radiusSphere,3))/3);
        double surfaceAreaOfRectangularPrism = 2 * ((heightRectangularPrism * diameterRectangularPrism) +
                (heightRectangularPrism * widthRectangularPrism) + (diameterRectangularPrism * widthRectangularPrism));
        double volumeOfRectangularPrism = (heightRectangularPrism * diameterRectangularPrism * widthRectangularPrism);
        double surfaceAreaCylinder = (2 * (Math.PI) * radiusCylinder *heightCylinder ) +
                (2 * (Math.PI)*Math.pow(radiusCylinder,2));
        double volumeCylinder = (Math.PI)* Math.pow(radiusCylinder,2) * heightCylinder;

        System.out.println("\n" + "The surface area of the sphere is: " + areaOfSphere );
        System.out.println("The volume of the sphere is: " + volumeOfSphere);
        System.out.println("The surface area of the rectangular prism is: " + surfaceAreaOfRectangularPrism);
        System.out.println("The volume of the rectangular prism is: " + volumeOfRectangularPrism);
        System.out.println("The surface area of the cylinder is: " + surfaceAreaCylinder);
        System.out.println("The volume of the cylinder is: " + volumeCylinder);





    }


}
