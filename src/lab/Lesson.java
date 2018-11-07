package lab;

public class Lesson {
    private Term term;
    private String name;
    private String teacherName;
    private int year;
    private boolean full_time;
    private ITimetable timetable;

    public Lesson(Term term, String name, String teacherName, int year, boolean full_time) {
        this.term = term;
        this.name = name;
        this.teacherName = teacherName;
        this.year = year;
        this.full_time = full_time;
    }

    public Lesson(ITimetable timetable, Term term, String name, String teacherName, int year) {
        this.timetable = timetable;
        this.term = term;
        this.name = name;
        this.teacherName = teacherName;
        this.year = year;
        this.full_time = true;
    }

    public Lesson earlierDay() {
        if(timetable.canBeTransferredTo(new Term(term.getHour(), term.getMinute(), term.getDay().prevDay()), full_time)) {
            this.term.setDay(term.getDay().prevDay());
            return this;
        }
        throw new IllegalArgumentException();
    }

    public Lesson laterDay() {
        if(timetable.canBeTransferredTo(new Term(term.getHour(), term.getMinute(), term.getDay().nextDay()), full_time)) {
            this.term.setDay(term.getDay().nextDay());
            return this;
        }
        throw new IllegalArgumentException();
    }

    public Lesson earlierTime() {
        int d = this.term.getDuration();
        int hour = this.term.getHour() - (d / 60);
        int minute = this.term.getMinute() - (d % 60);
        hour = hour + (minute / 60);
        minute = minute % 60;
        if(minute < 0) {
            hour--;
            minute = minute + 60;
        }
        Day day = this.term.getDay();
        Term newTerm = new Term(hour, minute, day);
        newTerm.setDuration(d);

        if(timetable.canBeTransferredTo(newTerm, full_time)) {
            this.term.setDay(day);
            this.term.setDuration(d);
            this.term.setHour(hour);
            this.term.setMinute(minute);
            return this;
        }

        throw new IllegalArgumentException();
    }

    public Lesson laterTime() {
        int d = this.term.getDuration();
        int hour = this.term.getHour() + (d / 60);
        int minute = this.term.getMinute() + (d % 60);
        hour = hour + (minute / 60);
        minute = minute % 60;

        Day day = this.term.getDay();

        Term newTerm = new Term(hour, minute, day);
        newTerm.setDuration(d);

        if(timetable.canBeTransferredTo(newTerm, full_time)) {
            this.term.setDay(day);
            this.term.setDuration(d);
            this.term.setHour(hour);
            this.term.setMinute(minute);
            return this;
        }
        throw new IllegalArgumentException();
    }

    public String parseYear() {
        switch(year) {
            case 1:
                return "Pierwszy";
            case 2:
                return "Drugi";
            case 3:
                return "Trzeci";
            case 4:
                return "Czwarty";
            case 5:
                return "Piąty";
        }
        throw new RuntimeException("Invalid year");
    }

    public String parseFullTime() {
        if(full_time) {
            return "Stacjonarnych";
        }
        return "Niestacjonarnych";
    }

    @Override
    public String toString() {
        String m1 = term.getMinute() + "";
        if(term.getMinute() < 10) {
            m1 = "0" + m1;
        }
        String m2 = term.endTerm().getMinute() + "";
        if(term.endTerm().getMinute() < 10) {
            m2 = "0" + m2;
        }

        return name + " (" + term.getDay() + " " + term.getHour() + ":" + m1 + "-" +
                term.endTerm().getHour() + ":" + m2 + ")\n" + this.parseYear() +
                " rok studiów " + this.parseFullTime() + "\nProwadzący: " + teacherName;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isFull_time() {
        return full_time;
    }

    public void setFull_time(boolean full_time) {
        this.full_time = full_time;
    }

    public ITimetable getTimetable() {
        return timetable;
    }

    public void setTimetable(ITimetable timetable) {
        this.timetable = timetable;
    }
}
