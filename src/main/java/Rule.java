import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;

import static java.lang.Boolean.FALSE;

public class Rule {
    HashSet<String> head=new HashSet<>();
    HashSet<String> positiveBody = new HashSet<>();
    HashSet<String> negativeBody = new HashSet<>();


    public Rule(HashMap<String,HashSet<String>> ruleList) {
        HashMap<String,String> functions = new HashMap<>();
        functions.put("head","setHead");
        functions.put("positiveBody","setPositiveBody");
        functions.put("negativeBody","setNegativeBody");
        for (String part :
                functions.keySet()) {
                try {
                    Method m = this.getClass().getDeclaredMethod(functions.get(part),HashSet.class);
                    m.setAccessible(true);
                    //System.out.println(ruleList.get(part));
                    m.invoke(this,ruleList.get(part));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }

    public HashSet<String> getHead() {
        return head;
    }

    public void setHead(HashSet<String> str) {
        //System.out.println("Head"+str+val);
        for (String item: str) {
            head.add(item);
        }
    }

    public HashSet<String> getPositiveBody() {
        return positiveBody;
    }

    public void setPositiveBody(HashSet<String> str) {
        for (String item: str) {
            positiveBody.add(item);
        }
    }

    public HashSet<String> getNegativeBody() {
        return negativeBody;
    }

    public void setNegativeBody(HashSet<String> str) {
        for (String item: str) {
            negativeBody.add(item);
        }
    }

    public void printRule(){
        System.out.println("head:"+head+", pos:"+positiveBody+",neg:"+negativeBody);
    }
}
