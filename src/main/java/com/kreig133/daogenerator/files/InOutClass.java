package com.kreig133.daogenerator.files;

import com.kreig133.daogenerator.common.strategy.FunctionalObjectWithoutFilter;
import com.kreig133.daogenerator.enums.ClassType;
import com.kreig133.daogenerator.jaxb.ParameterType;

import java.util.ArrayList;
import java.util.List;
import static com.kreig133.daogenerator.common.StringBuilderUtils.*;
import static com.kreig133.daogenerator.files.JavaFilesUtils.*;

/**
 * @author eshangareev
 * @version 1.0
 */
public class InOutClass {
    private static final String COMMENT_TO_CLASS = "/**\n" +
                                           " * Generated by DaoGenerator 0.5\n"+
                                           " * @author eshangareev\n" +
                                           " * @version 1.0\n" +
                                           " */\n";

    private final String package_;


    private final List<ParameterType> parameters;
    private final String name;

    public InOutClass( String package_, List<ParameterType> parameters, String name ) {
        this.package_ = package_;
        this.parameters = parameters;
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        insertPackageLine( builder, package_ );

        insertImport( builder, "java.io.Serializable" );
        insertImport( builder, "java.util.*" );
        builder.append( "\n" );

        builder.append( COMMENT_TO_CLASS );

        insertClassDeclaration(
                ClassType.Class,
                builder,
                name,
                null,
                new ArrayList<String>(){{add( "Serializable" );}}
        );

        writeSerialVersionUID( builder );
        writeEmptyConstructor( builder, name );
        writeFullConstructor ( builder );

        for(ParameterType p : parameters){
            builder.append( p.toString() );
        }

        for( ParameterType p: parameters ){
            p.generateGetter( builder );
            p.generateSetter( builder );
        }

        writeToString( builder );
        builder.append( "}" );

        return builder.toString();
    }



    private void writeFullConstructor( StringBuilder builder ) {
        insertTabs( builder, 1 ).append( "public " ).append( name ).append( "(\n" );
        iterateForParameterList( builder, parameters, 2, new FunctionalObjectWithoutFilter() {
            @Override
            public void writeString( StringBuilder builder, ParameterType p ) {
                builder.append( p.getType().value() ).append( " " ).append( p.getRenameTo() );
            }
        } );

        insertTabs( builder, 1 ).append( "){\n" );
        for( ParameterType p: parameters ){
            insertTabs( builder, 2 ).append( "this." ).append( p.getName() ).append( " = " ).append( p.getName() )
                    .append( ";\n" );
        }
        builder.append( "    }\n\n" );
    }

    private void writeToString( StringBuilder builder ){
        insertTabs( builder, 1 ).append( "@Override\n" );
        insertTabs( builder, 1 ).append( "public String toString(){\n" );
        insertTabs( builder, 2 ).append( "return \"" ).append( name ).append( "[\"\n" );
        for( int i =  0; i < parameters.size(); i ++ ){
            ParameterType parameter = parameters.get( i );
            insertTabs( builder,3 ).append( "+\"" ).append( i != 0 ? ", " : ""  )
                    .append( parameter.getName() ).append( " = \"+" )
                    .append( parameter.getName() ).append( "\n" );
        }
        insertTabs( builder, 2 ).append( "+\"]\";\n" );
        insertTabs( builder, 1 ).append( "}" );
    }


    public String getName() {
        return name;
    }

    public List<ParameterType> getParameters() {
        return parameters;
    }
}