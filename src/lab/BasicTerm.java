package lab;

import java.util.Base64;
import java.util.Objects;

public class BasicTerm {
    protected int hour;
    protected int minute;
    protected int duration;

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

    public BasicTerm(int hour, int minute, int duration) {
        this.hour = hour;
        this.minute = minute;
        this.duration = duration;
    }

    public BasicTerm(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
        this.duration = 90;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(this.getClass() != o.getClass()) return false;
        BasicTerm other = (BasicTerm)o;
        if(this.hour != other.hour) return false;
        if(this.minute != other.minute) return false;
        return this.duration == other.duration;
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
        return h + ":" + m + " [" + this.duration + "]";
    }

    public boolean earlierThan(BasicTerm termin) {
        return this.hour < termin.hour || (this.hour == termin.hour && this.minute < termin.minute);
    }

    public boolean laterThan(BasicTerm termin) {
        return this.hour > termin.hour || (this.hour == termin.hour && this.minute > termin.minute);
    }

    public BasicTerm endTerm(BasicTerm termin) {
        BasicTerm res = new BasicTerm(this.hour, this.minute);
        if(this.earlierThan(termin)) {
            res.setDuration((termin.hour - this.hour) * 60 + termin.minute - this.minute);
        } else {
            res.setDuration(0);
        }
        return res;
    }

    public BasicTerm endTerm() {
        BasicTerm res = new BasicTerm((this.hour + (this.duration + this.minute) / 60), (this.minute + this.duration) % 60);
        res.setDuration(this.duration);
        return res;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hour, minute, duration);
    }
}
