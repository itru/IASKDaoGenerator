package com.kreig133.daogenerator.enums;

import com.kreig133.daogenerator.parsers.IParser;
import com.kreig133.daogenerator.parsers.InputParameterParser;
import com.kreig133.daogenerator.parsers.OutputParametrParser;
import com.kreig133.daogenerator.parsers.QueryParser;

/**
 * @author eshangareev
 * @version 1.0
 */
public enum Mode {
    IS_INPUT_PARAMETRS ( new InputParameterParser() ),
    IS_OUTPUT_PARAMETRS( new OutputParametrParser() ),
    IS_SELECT_QUERY    ( new QueryParser         () );

    Mode( IParser parser ) {
        this.parser = parser;
    }

    IParser parser;

    public void parse( Object input, String lineForParse ){
        parser.parse( input, lineForParse );
    }


    public static Mode getByName( String name ){
        name = name.trim().toLowerCase();

        if( "is_input_parametrs".equals( name )){
            return IS_INPUT_PARAMETRS;
        }
        if( "is_output_parametrs".equals( name )){
            return IS_OUTPUT_PARAMETRS;
        }
        if( "is_select_query".equals( name )){
            return IS_SELECT_QUERY;
        }

        return null;
    }
}
