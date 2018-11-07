package lab;

public class ActionsParser {
    public Action[] parse(String[] arr) {
        int n = 0;
        for(String str : arr) {
            if(str.equals("d-") || str.equals("d+") || str.equals("t-") || str.equals("t+")) {
                n++;
            }
        }
        Action[] res = new Action[n];
        int i = 0;
        for(String str : arr) {
            Action a;
            switch(str) {
                case "d-":
                    a = Action.DAY_EARLIER;
                    break;
                case "d+":
                    a = Action.DAY_LATER;
                    break;
                case "t-":
                    a = Action.TIME_EARLIER;
                    break;
                case "t+":
                    a = Action.TIME_LATER;
                    break;
                default:
                    throw new IllegalArgumentException("Translation " + str + " is incorrect");
            }
            res[i] = a;
            i++;
        }

        return res;
    }
}
