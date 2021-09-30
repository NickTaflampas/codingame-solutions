import java.util.*;

class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int maxHeight = -1;
        int maxMountain = -1;

        while (true) {
            for (int i = 0; i < 8; i++) {
                int mountainH = in.nextInt(); // represents the height of one mountain.
                if (mountainH > maxHeight) { maxHeight = mountainH; maxMountain = i; }
            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            System.out.println(maxMountain); // The index of the mountain to fire on.
            maxHeight = -1;
        }
    }
}