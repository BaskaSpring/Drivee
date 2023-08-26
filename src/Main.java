import java.util.ArrayList;
import java.util.List;

class Point {
    double x, y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class Line{
    Point A, B;

    Line(Point A, Point B) {
        this.A = A;
        this.B = B;
    }
}

class PointPositionChecker {

    public static List<Line> check(Line AB, List<Line> lineSegmentList) {
        List<Line> result = new ArrayList<>();
        for (Line line : lineSegmentList) {
            if (isPointBetweenLineSegment(line.A,AB)&&isPointBetweenLineSegment(line.B,AB)){
                if (oneDirection(AB,line)){
                    result.add(line);
                }
            }
        }
        return result;
    }

    public static boolean oneDirection(Line AB, Line CD) {
        if (AB.A.x > AB.B.x) {
            return CD.A.x > CD.B.x;
        } else if (AB.A.x < AB.B.x) {
            return CD.A.x < CD.B.x;
        }
        if (AB.A.y > AB.B.y) {
            return CD.A.y > CD.B.y;
        } else if (AB.A.y < AB.B.y) {
            return CD.A.y < CD.B.y;
        }
        return false;
    }


    public static boolean isPointBetweenLineSegment(Point C, Line AB) {
        double distanceAC = calculateDistance(AB.A, C);
        double distanceBC = calculateDistance(AB.B, C);
        double distanceAB = calculateDistance(AB.A, AB.B);

        return Math.abs(distanceAC + distanceBC - distanceAB) < 0.001;
    }

    private static double calculateDistance(Point P1, Point P2) {
        return Math.sqrt((P2.x - P1.x) * (P2.x - P1.x) + (P2.y - P1.y) * (P2.y - P1.y));
    }

}

public class Main {
    public static void main(String[] args) {
        Point A = new Point(0, 0);
        Point B = new Point(10, 10);
        Line AB = new Line(A, B);

        Point C = new Point(3, 3);
        Point D = new Point(9, 9);
        Line CD = new Line(C, D);

        Line DC = new Line(D, C);

        Point E = new Point(3, 4);
        Point F = new Point(6, 6);
        Line EF = new Line(E, F);

        List<Line> lineSegmentList = new ArrayList<>();
        lineSegmentList.add(CD);
        lineSegmentList.add(DC);
        lineSegmentList.add(EF);

        List<Line> res = PointPositionChecker.check(AB,lineSegmentList);

        for (Line order : res) {
            System.out.println("Start: (" + order.A.x + ", " + order.A.y + "), " +
                    "End: (" + order.B.x + ", " + order.B.y + ")");
        }

    }
}