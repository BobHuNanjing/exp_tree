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
    public String getNodeElement() {
        String ruleNodeElement = "r:" + "head:[";
        for (String headLit : rule.getHead()) {
            ruleNodeElement = ruleNodeElement + headLit + ",";
        }

        ruleNodeElement += "];positiveBody:[";
        for (String posLit : rule.getPositiveBody()) {
            ruleNodeElement = ruleNodeElement + posLit + ",";
        }

        ruleNodeElement += "];negativeBody:[";
        for (String negLit : rule.getNegativeBody()) {
            ruleNodeElement = ruleNodeElement + negLit + ",";
        }

        ruleNodeElement +="].";

        return ruleNodeElement;

    }
}
