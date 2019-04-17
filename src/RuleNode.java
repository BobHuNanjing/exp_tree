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
}
