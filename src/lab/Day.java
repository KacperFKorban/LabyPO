package lab;

public enum Day {
    MON("Poniedziałek") {
        @Override
        public Day nextDay() {
            return TUE;
        }

        @Override
        public Day prevDay() {
            return SUN;
        }

        @Override
        public int value() {
            return 0;
        }
    },
    TUE("Wtorek") {
        @Override
        public Day nextDay() {
            return WED;
        }

        @Override
        public Day prevDay() {
            return MON;
        }

        @Override
        public int value() {
            return 1;
        }
    },
    WED("Środa") {
        @Override
        public Day nextDay() {
            return THU;
        }

        @Override
        public Day prevDay() { return TUE; }

        @Override
        public int value() {
            return 2;
        }
    },
    THU("Czwartek") {
        @Override
        public Day nextDay() {
            return FRI;
        }

        @Override
        public Day prevDay() {
            return WED;
        }

        @Override
        public int value() {
            return 3;
        }
    },
    FRI("Piątek") {
        @Override
        public Day nextDay() {
            return SAT;
        }

        @Override
        public Day prevDay() {
            return THU;
        }

        @Override
        public int value() {
            return 4;
        }
    },
    SAT("Sobota") {
        @Override
        public Day nextDay() {
            return SUN;
        }

        @Override
        public Day prevDay() {
            return FRI;
        }

        @Override
        public int value() {
            return 5;
        }
    },
    SUN("Niedziela") {
        @Override
        public Day nextDay() {
            return MON;
        }

        @Override
        public Day prevDay() {
            return SAT;
        }

        @Override
        public int value() {
            return 6;
        }
    };

    private String dayName;

    private Day(String dayName) {
        this.dayName = dayName;
    }

    @Override
    public String toString() {
        return this.dayName;
    }

    public abstract Day nextDay();

    public abstract Day prevDay();

    public abstract int value();
}
