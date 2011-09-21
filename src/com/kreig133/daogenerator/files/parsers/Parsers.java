package com.kreig133.daogenerator.files.parsers;

import com.kreig133.daogenerator.common.settings.FunctionSettings;
import com.kreig133.daogenerator.common.settings.OperationSettings;
import com.kreig133.daogenerator.enums.TestInfoType;
import com.kreig133.daogenerator.parameter.Parameter;
import com.kreig133.daogenerator.enums.Mode;

import java.util.List;

/**
 * @author eshangareev
 * @version 1.0
 */
public class Parsers {

    public static void readLine(
            OperationSettings operationSettings,
            FunctionSettings functionSettings,
            Mode mode,
            String line
    ){
        final List<Parameter> inputParameterList  = functionSettings. getInputParameterList();
        final List<Parameter> outputParameterList = functionSettings.getOutputParameterList();
        final StringBuilder   query               = functionSettings.getSelectQuery        ();

        switch ( mode ){
            case IS_INPUT_PARAMETRS:
                mode.parse( operationSettings, inputParameterList, line );
                break;
            case IS_OUTPUT_PARAMETRS:
                mode.parse( operationSettings, outputParameterList, line );
                break;
            case IS_SELECT_QUERY:
                mode.parse( operationSettings, query, line );
                break;
            case IS_TESTING_QUERY:
                mode.parse( operationSettings, functionSettings, line );
                break;
        }
    }
}