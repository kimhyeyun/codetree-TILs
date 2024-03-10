import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int answer = Integer.MAX_VALUE;
        String exp = br.readLine();
        String[] subs = exp.split("-");

        for (String sub : subs) {
            String[] adds = sub.split("\\+");

            int tmp = 0;
            for (String add : adds) {
                tmp += Integer.parseInt(add);
            }

            if (answer == Integer.MAX_VALUE) answer = tmp;
            else answer -= tmp;
        }

        System.out.println(answer);
    }
}