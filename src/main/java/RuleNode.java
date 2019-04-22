import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class RuleNode extends Node{
    Rule rule;

    public Rule getRule() {
        return rule;
    }


    public RuleNode(Rule r) {
        RuleLoader ruleLoader = new RuleLoader("rules.txt");
        ruleLoader.loadRulesIntoList();
        this.rule = r;
    }

    @Override
    @JsonIgnore
    public String getNodeElement() {
        String ruleNodeElement = "head:[";
        for (String headLit : rule.getHead()) {
            ruleNodeElement = ruleNodeElement + headLit + ",";
        }
        ruleNodeElement = ruleNodeElement.substring(0,ruleNodeElement.length()-1) + "]\n";

        ruleNodeElement += "positiveBody:[";
        for (String posLit : rule.getPositiveBody()) {
            ruleNodeElement = ruleNodeElement + posLit + ",";
        }
        ruleNodeElement = ruleNodeElement.substring(0,ruleNodeElement.length()-1) + "]\n";

        ruleNodeElement += "negativeBody:[";
        for (String negLit : rule.getNegativeBody()) {
            ruleNodeElement = ruleNodeElement + negLit + ",";
        }

        ruleNodeElement = ruleNodeElement.substring(0,ruleNodeElement.length()-1) + "]";

        return ruleNodeElement;
    }

}
