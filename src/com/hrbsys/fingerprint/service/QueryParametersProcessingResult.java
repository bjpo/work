package com.hrbsys.fingerprint.service;

import java.util.Arrays;

/**
 *
 * @author Li
 */
class QueryParametersProcessingResult {

    private String condition;
    private String sequence;
    private Object[] conditionValue;

    public QueryParametersProcessingResult() {
        this.condition = "";
        this.sequence = "";
        this.conditionValue = new Object[]{};
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public Object[] getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(Object[] conditionValue) {
        this.conditionValue = conditionValue;
    }

    @Override
    public String toString() {
        return "QueryParametersProcessingResult{" + "condition=" + condition + ", sequence=" + sequence + ", conditionValue=" + Arrays.toString(conditionValue) + '}';
    }
}