package lab;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class DeanerySystem {
    public static void main(String[] args) {
        try {
            Term term1 = new Term(9, 45, Day.MON);
            System.out.println(term1);                    //Ma się wypisać: "9:45 [90]"
            Term term2 = new Term(10, 15, Day.TUE);
            System.out.println(term2);                    //Ma się wypisać: "10:15 [90]"
            System.out.println(term1.earlierThan(term2)); //Ma się wypisać: "true"
            System.out.println(term1.equals(term2));      //Ma się wypisać: "false"
            System.out.println(term1.endTerm(term2));     //Ma się wypisać: "9:45 [30]"
            System.out.println(term1.endTerm());          //Ma się wypisać: "11:15 [90]"
            System.out.println(Day.TUE.toString());
            System.out.println(Day.SUN.nextDay().toString());

            Lesson programowanieObiektowe = new Lesson(new Term(8, 0, Day.MON), "Programowanie obiektowe", "Stanisław Polak", 2, true);
            System.out.println(programowanieObiektowe);

            Action[] actions = new ActionsParser().parse(args);
            ITimetable timetable = new Timetable1();
            Lesson l1 = new Lesson(timetable, new Term(8, 0, Day.TUE), "Angielski", "Nowak", 1);
            Lesson l2 = new Lesson(timetable, new Term(9, 30, Day.MON), "JTP", "Kowalski", 3);
            timetable.put(l1);
            timetable.put(l2);
            timetable.perform(actions);

            System.out.println(timetable.toString());
        } catch (IllegalArgumentException | NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
}
