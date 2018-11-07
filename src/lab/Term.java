package lab;

import java.util.Objects;

public class Term extends BasicTerm{
    private Day day;

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Term(int hour, int minute, Day day) {
        super(hour, minute);
        this.day = day;
    }

    public Term(int hour, int minute, int duration, Day day) {
        super(hour, minute, duration);
        this.day = day;
    }

    public Term(int hour, int minute) {
        super(hour, minute);
        this.day = Day.MON;
    }

    @Override
    public String toString() {
        String m;
        if(this.minute < 10) {
            m = "0" + this.minute;
        } else {
            m = "" + this.minute;
        }
        String h;
        if(this.hour == 0) {
            h = "0" + this.hour;
        } else {
            h = "" + this.hour;
        }
        return h + ":" + m + " [" + this.duration + "]" + day.toString();
    }


    public Term endTerm(Term termin) {
        Term res = new Term(this.hour, this.minute);
        if(this.earlierThan(termin)) {
            res.setDuration((termin.hour - this.hour) * 60 + termin.minute - this.minute);
        } else {
            res.setDuration(0);
        }
        res.setDay(this.day);
        return res;
    }

    public Term endTerm() {
        Term res = new Term((this.hour + (this.duration + this.minute) / 60), (this.minute + this.duration) % 60);
        res.setDuration(this.duration);
        res.setDay(this.day);
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(this.getClass() != o.getClass()) return false;
        Term other = (Term)o;
        if(this.day != other.day) return false;
        if(this.hour != other.hour) return false;
        if(this.minute != other.minute) return false;
        return this.duration == other.duration;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(day);
    }
}
