package lab;

import java.util.*;

public abstract class AbstractTimetable implements ITimetable {
    protected LinkedHashMap<Term, Lesson> schedule = new LinkedHashMap<>();

    @Override
    public boolean canBeTransferredTo(Term term, boolean full_time) {
        if((term.getDay() == Day.SAT || term.getDay() == Day.SUN) && full_time) return false;
        if(term.getDay() == Day.FRI && term.endTerm().laterThan(new Term(17, 0)) && full_time) return false;
        if(term.getDay() != Day.SAT && term.getDay() != Day.SUN && term.getDay() != Day.FRI && !full_time) return false;
        if(term.getDay() == Day.FRI && term.earlierThan(new Term(17, 0)) && !full_time) return false;
        if(term.earlierThan(new Term(8, 0))) return false;
        if(term.endTerm().laterThan(new Term(20, 0))) return false;
        if(busy(term)) return false;
        return true;
    }

    @Override
    public boolean busy(Term term) {
        for(Lesson l : schedule.values()) {
            if(term.getDay() == l.getTerm().getDay())
                if((!l.getTerm().earlierThan(term) && term.endTerm().laterThan(l.getTerm())) || (term.earlierThan(l.getTerm().endTerm()) && !l.getTerm().endTerm().laterThan(term.endTerm())))
                    return true;
        }
        return false;
    }

    @Override
    public boolean put(Lesson lesson) {
        if(canBeTransferredTo(lesson.getTerm(), lesson.isFull_time())) {
            schedule.put(lesson.getTerm(), lesson);
            return true;
        }
        throw new IllegalArgumentException("Cannot transfer");
    }

    // TODO
    @Override
    public void perform(Action[] actions) {
        int l = schedule.size();
        Iterator<Map.Entry<Term, Lesson>> iter = schedule.entrySet().iterator();
        for(int i = 0; i < actions.length; i++) {
            if(!iter.hasNext()) iter = schedule.entrySet().iterator();
            Map.Entry<Term, Lesson> elem = iter.next();
            Lesson lesson = elem.getValue();
            switch(actions[i]) {
                case DAY_LATER:
                    schedule.put(lesson.getTerm(), schedule.remove(lesson).laterDay());
                case DAY_EARLIER:
                    schedule.put(lesson.getTerm(), schedule.remove(lesson).earlierDay());
                case TIME_LATER:
                    schedule.put(lesson.getTerm(), schedule.remove(lesson).laterTime());
                case TIME_EARLIER:
                    schedule.put(lesson.getTerm(), schedule.remove(lesson).earlierTime());
            }
        }
    }

    @Override
    public Object get(Term term) {
        Lesson l = schedule.get(term);
        if(l != null) return l;
        throw new NoSuchElementException();
    }

    @Override
    public String toString() {
        Day firstDay = Day.MON;
        Day lastDay = Day.SUN;
        Term firstTerm = new Term(8,0,firstDay);
        Term lastTerm = new Term(20,0,lastDay);
        Day day = null;
        Term term = null;
        String res = "";
        while(res.length() < 12) {
            res += " ";
        }
        res += "*";
        int dayCount = 0;
        for(day = firstDay ; day.compareTo(lastDay) <= 0 && dayCount < 7; day = day.nextDay(), dayCount++) {
            String a = day.toString();
            while(a.length() < 12) {
                a += " ";
            }
            res += a;
            res += "*";
        }
        res += "\n";
        for(int i = 0; i < 12; i++) {
            res += " ";
        }
        for(int i = 0; i < 92; i++) {
            res += "*";
        }
        res += "\n";
        for(term = firstTerm ; term.earlierThan(lastTerm) ; term = term.endTerm()) {
            dayCount = 0;
            String b = "";
            if(term.getMinute() < 10) {
                b = "0";
            }
            String a = term.getHour() + ":" + b + term.getMinute() + "";
            String c = "";
            if(term.endTerm().getMinute() < 10) {
                c = "0";
            }
            a += "-" + term.endTerm().getHour() + ":" + c + term.endTerm().getMinute();
            while(a.length() < 12) {
                a += " ";
            }
            res += a;
            res += "*";
            for(day = firstDay ; day.compareTo(lastDay) <= 0 && dayCount < 7; day = day.nextDay(), dayCount++) {
                term.setDay(day);
                res += "";
                a = "";
                if(this.busy(term)) {
                    Lesson tmp = (Lesson)this.get(term);
                    a += tmp.getName();
                }
                while(a.length() < 12) {
                    a += " ";
                }
                res += a;
                res += "*";
            }
            res += "\n";
            for(int i = 0; i < 12; i++) {
                res += " ";
            }
            for(int i = 0; i < 92; i++) {
                res += "*";
            }
            res += "\n";
        }
        return res;
    }
}
