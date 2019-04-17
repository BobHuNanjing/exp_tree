import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.*;
public class regex {
    public static void main( String args[] ){

        // 按指定模式在字符串查找
        String line = "[\"hello\",\"hey\"],[\"yes\"],[\"good\"]";
        //String pattern = "(\\[.*\\],)(\\[.*\\],)(\\[.*\\])";
        String pattern = "\\[(.*?)\\]";
        String[] array = line.split(pattern);
        //System.out.println(array[0]);
        HashMap<String, HashSet<String>> ruleList = new HashMap<>();
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        int count = 0;
        String head=null,pos=null,neg = null;
        while (m.find( )) {
            if(count == 0){
                head = m.group(1);
                System.out.println(head);
            }else if(count == 1){
                pos = m.group(1);
                System.out.println(pos);
            }else if(count == 2){
                neg = m.group(1);
                System.out.println(neg);
            }
            count++;
        }

        HashSet<String> heads = new HashSet<>();
        HashSet<String> pBody = new HashSet<>();
        HashSet<String> nBody = new HashSet<>();

        String[] arr = head.split("\\,");
        for (String str :
                arr) {
            heads.add(str);
        }
        ruleList.put("head",heads);
        for (String str :
                pos.split(",")) {
            pBody.add(str);
        }
        ruleList.put("pos",pBody);
        for (String str :
                neg.split(",")) {
            nBody.add(str);
        }
        ruleList.put("neg",nBody);
        for (String key:
             ruleList.keySet()) {
            System.out.println(key+":"+ruleList.get(key));
        }
    }
}
