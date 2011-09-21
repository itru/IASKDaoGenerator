package com.kreig133.daogenerator.common;

import com.kreig133.daogenerator.common.strategy.FuctionalObject;
import com.kreig133.daogenerator.parameter.Parameter;

import java.util.List;

/**
 * @author eshangareev
 * @version 1.0
 */
public class StringBufferUtils {

    public static void insertEscapedParamName(StringBuilder builder, String parameterName ){
        builder.append( "#{" ).append( parameterName ).append( "}" );
    }

    public static void iterateForParameterList(
            StringBuilder builder,
            List<Parameter> parameterList,
            FuctionalObject functionalObject
    ) {
        iterateForParameterList( builder, parameterList, 1, functionalObject );
    }

    public static void iterateForParameterList(
            StringBuilder builder,
            List<Parameter> parameterList,
            int tabs,
            FuctionalObject functionalObject

    ) {
        boolean first = true;

        for ( Parameter p : parameterList ) {
            if ( functionalObject.filter( p ) ) {
                for ( int i = 0; i < tabs; i++ ) {
                    builder.append( "    " );
                }
                if ( ! first ) {
                    builder.append( "," );
                } else {
                    first = false;
                }
                functionalObject.writeString( builder, p );
                builder.append( "\n" );
            }
        }
    }

    public static void getJavaDocString( StringBuilder builder, String[] commentsLine ) {

        boolean commentsNotEmpty = false;
        for ( String string : commentsLine ) {
            if ( string != null && ! ( "".equals( string ) ) ) {
                commentsNotEmpty = true;
                break;
            }
        }

        if ( ! commentsNotEmpty ) return;

        builder.append( "\t/**\n" );
        for ( String comment : commentsLine ) {
            builder.append( "\t * " );
            builder.append( comment );
            builder.append( "\n" );
        }
        builder.append( "\t */\n" );
    }

    public static StringBuilder insertTabs( StringBuilder builder, int tabsQuantity ){
        for( int i = 0 ; i < tabsQuantity; i ++ ){
            builder.append( "    " );
        }
        return builder;
    }
}