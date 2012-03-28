package com.kreig133.daogenerator.files.mybatis.mapping;

import com.kreig133.daogenerator.common.Utils;
import com.kreig133.daogenerator.enums.ClassType;
import com.kreig133.daogenerator.enums.MethodType;
import com.kreig133.daogenerator.jaxb.DaoMethod;
import com.kreig133.daogenerator.jaxb.ParameterType;
import com.kreig133.daogenerator.jaxb.SelectType;
import com.kreig133.daogenerator.settings.Settings;
import com.kreig133.daogenerator.sql.creators.QueryCreator;

import java.io.IOException;
import java.util.List;

import static com.kreig133.daogenerator.common.Utils.stringNotEmpty;

/**
 * @author kreig133
 * @version 1.0
 */
public class DepoMappingGenerator extends MappingGenerator{

    public static final String MAPPER_PREFIX = "Dao";

    @Override
    public void generateBody( DaoMethod daoMethod ) throws IOException{
        insertLine();
        generateAnnotation( daoMethod );
        generateMethodSignature( daoMethod, MethodType.MAPPER );
        builder.append( ";" );
        decreaseNestingLevel();
        insertLine();
    }

    @Override
    public String getFileName() {
        return Settings.settings().getOperationName() + MAPPER_PREFIX;
    }

    @Override
    protected String getFileNameEnding() {
        return  JAVA_EXTENSION;
    }

    @Override
    public void generateHead() throws IOException {

        setPackage( Settings.settings().getMapperPackage() );
        addDaoFilesImports();
        addImport( "org.apache.ibatis.annotations.*" );
        addImport( "org.apache.ibatis.mapping.StatementType" );
        addImport( "org.apache.ibatis.annotations.CacheNamespace" );
        builder.append( "@CacheNamespace(implementation = org.mybatis.caches.ehcache.EhcacheCache.class)" );
        insertLine();
        insertClassDeclaration(
                ClassType.INTERFACE,
                Settings.settings().getOperationName() + MAPPER_PREFIX,
                null,
                null
        );
    }

    private void generateAnnotation(
            DaoMethod daoMethod
    ){

        if( daoMethod.getCommon().getConfiguration().isMultipleResult() ) {
            addImport( "java.util.List" );
        }
        
        SelectType selectType = daoMethod.getSelectType();

        assert selectType != null ;

        insertTabs().append( "@" ).append( selectType.getAnnotation() ).append( "(" );
        insertLine();

        increaseNestingLevel();
        wrapWithQuotesAndWrite(
                QueryCreator.newInstance( daoMethod ).generateExecuteQuery( daoMethod, false ).replaceAll( "\"", "\\\\\"" )
        );
        decreaseNestingLevel();

        insertTabs().append( ")" );
        insertLine();
        if( daoMethod.getSelectType() == SelectType.CALL ) {
            insertTabs().append( "@Options(statementType=StatementType.CALLABLE");
            if( daoMethod.getInputParametrs().isWithPaging() ) {
                builder.append( ", useCache=false" );
            }
            builder.append( ")" );
            insertLine();
        }

        generateNameMapping( daoMethod );

        final List<Integer> indexOfUnnamedParameters = daoMethod.getOutputParametrs().getIndexOfUnnamedParameters();

        if( ! indexOfUnnamedParameters.isEmpty() ) {
            if ( indexOfUnnamedParameters.size() > 1 ) {
                throw new RuntimeException( "Не реализованная функциональность!" );
            }
        }
    }

    private void generateNameMapping( DaoMethod daoMethod ) {
        insertTabs().append( "@Results({");
        insertLine();
        boolean first = true;
        increaseNestingLevel();
        for ( ParameterType parameterType : daoMethod.getOutputParametrs().getParameter() ) {
            if ( ! first ) {
                builder.append( "," );
                insertLine();
            } else {
                first = false;
            }
            insertTabs().append( "@Result(property = \"" ).append( parameterType.getRenameTo() )
                    .append( "\", column = \"" ).append( parameterType.getName() ).append( "\")" );

        }
        decreaseNestingLevel();
        insertLine();
        insertTabs().append( "})" );
        insertLine();
    }

    /**
     * Обрамляет каждую новую строку в кавычки и конкатенацию строк
     * @param string
     * @return
     */
    public StringBuilder wrapWithQuotesAndWrite( String string ) {

        String[] strings = string.split( "[\n\r][\n\r]?" );

        strings = deleteEmptyStrings( strings );

        for ( int i = 0; i < strings.length; i++ ) {
            if ( i != 0 ) {
                builder.append( " +" );
                insertLine();
            }
            insertTabs().append( "\" " ).append( strings[ i ] ).append( "\"" );
        }
        insertLine();
        return builder;
    }

    /**
     * Удаляет пустые строки из массива
     * @param in
     * @return
     */
    private static String[] deleteEmptyStrings( String[] in ) {
        String[] temp = new String[ in.length ];

        int length = 0;

        for ( String s : in ) {
            if ( stringNotEmpty( s ) ) {
                temp[ length ] = s;
                length++;
            }
        }

        String[] result = new String[ length ];

        System.arraycopy( temp, 0, result, 0, length );

        return result;
    }
}
