package com.kreig133.daogenerator.files.equalshashbuilder;

import com.kreig133.daogenerator.jaxb.ParameterType;
import com.kreig133.daogenerator.jaxb.ParametersType;

import java.util.ArrayList;

import static java.util.Collections.sort;

/**
 * @author zildarius
 * @version 1.0
 */
public class EqualsMethodBuilder {

    private static final String METHOD_BEGIN = "@Override\n" +
            "    public boolean equals(Object o) {\n" +
            "        if (this == o) return true;\n"+
            "        if (!(o instanceof %s)) return false;\n"+
            "\n"+
            "        %s that = (%s) o;"+
            "\n"+"\n";

    private static final String METHOD_END = "\n" +
            "        return true;\n" +
            "    }";

    private static final String PARAMETER = "        if (%s != null ? !%s.equals(that.%s) : that.%s !=" + " null) return " +
            "false;"+
            "\n";

    public static String buildEqualsMethod(ParametersType params, String className){

        StringBuilder result = new StringBuilder (String.format( METHOD_BEGIN, className, className, className ));

        ArrayList<String> paramList = new ArrayList<String>();
        for (ParameterType param : params.getParameter()){
            paramList.add(param.getRenameTo());
        }
        sort(paramList);

        for (String currentParam : paramList){
            result.append( String.format(PARAMETER, currentParam,currentParam,currentParam,currentParam));
        }

        result.append(METHOD_END);

        return result.toString();
    }


}
