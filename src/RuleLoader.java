import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RuleLoader {

    String ruleFileName;
    ArrayList<Rule> rules = new ArrayList<>();
    //RuleLoader ruleLoader = new RuleLoader("rules.txt");

    public RuleLoader(String ruleFileName) {
        this.ruleFileName = ruleFileName;
    }

    public ArrayList<String> loadRulesFromFile() {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            FileReader fr = new FileReader(ruleFileName);
            BufferedReader bf = new BufferedReader(fr);
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                arrayList.add(str);
            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;

    }

    public void loadRulesIntoList() {
        ArrayList<String> rule = new ArrayList<>();
        rule = loadRulesFromFile();
        HashSet<String> heads = new HashSet<>();
        HashSet<String> pBody = new HashSet<>();
        HashSet<String> nBody = new HashSet<>();
        int count = 0;
        for (String line :
                rule) {
            String pattern = "\\[(.*?)\\]";
            HashMap<String, HashSet<String>> ruleList = new HashMap<>();
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(line);
            String head = null, pos = null, neg = null;
            while (m.find()) {
                if (count == 0) {
                    head = m.group(1);
                    //System.out.println(head);
                } else if (count == 1) {
                    pos = m.group(1);
                    //System.out.println(pos);
                } else if (count == 2) {
                    neg = m.group(1);
                    //System.out.println(neg);
                }
                count++;
            }
            count = 0;

            for (String str :
                    head.split("\\,")) {
                heads.add(str);
            }
            ruleList.put("head", heads);
            for (String str :
                    pos.split("\\,")) {
                pBody.add(str);
            }
            ruleList.put("positiveBody", pBody);
            for (String str :
                    neg.split("\\,")) {
                nBody.add(str);
            }
            ruleList.put("negativeBody", nBody);

            rules.add(new Rule(ruleList));
            heads.clear();
            pBody.clear();
            nBody.clear();
        }

    }

    public ArrayList<Rule> getRules() {
        return rules;
    }

}
