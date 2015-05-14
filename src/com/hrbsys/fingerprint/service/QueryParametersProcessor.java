package com.hrbsys.fingerprint.service;

import java.lang.reflect.Field;

/**
 *
 * @author Li
 */
class QueryParametersProcessor
{
    public static QueryParametersProcessingResult process(Object object, String sort, String order) throws IllegalArgumentException, IllegalAccessException
    {
        QueryParametersProcessingResult result=new QueryParametersProcessingResult();
        if (object != null && sort == null && order == null)
        {
            Object[] objects=getConditionAndConditionValue(object);
            result.setCondition((String)objects[0]);
            result.setConditionValue((Object[])objects[1]);
        }
        if (sort != null && order != null)
        {
            result.setSequence(getSequence(sort, order));
            if(object !=null)
            {
                Object[] objects=getConditionAndConditionValue(object);
                result.setCondition((String)objects[0]);
                result.setConditionValue((Object[])objects[1]);
            }
        }
        return result;
    }

    private static Object[] getConditionAndConditionValue(Object object) throws IllegalArgumentException, IllegalAccessException
    {
        StringBuilder conditionBuilder=new StringBuilder();
        StringBuilder conditionValueBuilder=new StringBuilder();
        for (Field field : object.getClass().getDeclaredFields())
        {
            field.setAccessible(true);
            if (field.get(object) != null && !field.get(object).equals(""))
            {
                conditionBuilder.append(" and ").append(field.getName()).append(" like ").append("?");
                conditionValueBuilder.append("%").append(field.get(object)).append("%").append(",");
            }
        }
        return new Object[]{conditionBuilder.toString(), conditionValueBuilder.toString().isEmpty() ? new Object[]{} : conditionValueBuilder.toString().split(",")};
    }

    private static String getSequence(String sort, String order)
    {
        StringBuilder sequenceBuilder=new StringBuilder();
        sequenceBuilder.append(" order by ").append(sort).append(" ").append(order);
        return sequenceBuilder.toString();
    }
}