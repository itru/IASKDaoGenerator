package com.kreig133.daogenerator.files;

import com.kreig133.daogenerator.DaoGenerator;
import com.kreig133.daogenerator.jaxb.NamingUtils;
import com.kreig133.daogenerator.common.Utils;
import com.kreig133.daogenerator.enums.ClassType;
import com.kreig133.daogenerator.enums.Scope;
import com.kreig133.daogenerator.jaxb.DaoMethod;
import com.kreig133.daogenerator.jaxb.JavaType;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.*;

/**
 * @author kreig133
 * @version 1.0
 */
abstract public class JavaClassGenerator extends Generator {

    protected static final String JAVA_EXTENSION = ".java";
    protected static final String DATE_IMPORT = "java.util.Date";

    @NotNull
    protected String        javaDocForFile = "Generated by DaoGenerator-";
    @Nullable
    protected String        _package;
    @NotNull
    protected Set<String>   imports = new HashSet<String>();
    @NotNull
    protected JavaDocGenerator jDoc = new JavaDocGenerator();

    abstract public File getFile();
    abstract public void generateHead();
    abstract public void generateBody( @NotNull DaoMethod daoMethod );
    @Nullable
    abstract public String getFileName();

    protected JavaClassGenerator() {
        updateBuilder();
    }

    public void reset(){
        imports.clear();
        builder = new StringBuilder();
        _package = null;
//        javaDocForFile = null;
    }

    protected void generateFoot() {
        insertLine().append( "}" );
    }

    @NotNull
    public String getResult(){
        generateFoot();
        String s = builder.toString().replaceAll( "\\n{2,}", "\n\n" );

        updateBuilder();

        jDoc.insertJavaDoc( javaDocForFile + DaoGenerator.VERSION );
        insertPackageLine( _package );

        for ( String anImport : imports ) {
            insertImport( anImport );
        }
        insertLine();

        jDoc.insertJavaDoc( false, true, "" );

        return builder.toString() + s;
    }

    protected void setPackage( String packageString ){
        this._package = packageString;
    }

    protected void generateMethodSignature(
            @NotNull Scope scope,
            @Nullable String outputClass,
            @NotNull String methodName,
            @Nullable List<String> inputParams,
            @Nullable List<String> throwsing,
            boolean signatureOnly
    ){
        insertTabs().append( scope.value() ).append( " " )
                .append( StringUtils.isNotEmpty( outputClass ) ? outputClass : "void" ).append( " " ).append(
                methodName )
                .append( "(" );

        boolean needNewLineForParam = inputParams != null && inputParams.size() > 2;

        if ( needNewLineForParam ) {
            insertLine();
            increaseNestingLevel();
            insertTabs();
        }
        if ( Utils.collectionNotEmpty( inputParams ) ) {
            builder.append( StringUtils.join( inputParams.iterator(), ", " ) );
        }
        if(  needNewLineForParam ){
            insertLine();
            decreaseNestingLevel();
            insertTabs();
        }
        builder.append( ")" );

        if ( Utils.collectionNotEmpty( throwsing ) ) {
            builder.append( " throws " );
            builder.append( StringUtils.join( throwsing.iterator(), ", " ) );
        }
        builder.append( signatureOnly ? "" : " {" );
        increaseNestingLevel();
    }

    private void insertPackageLine( String packageName ) {
        builder.append( "package " ).append( packageName ).append( ";" );
        insertLine();
        insertLine();
    }


    //<editor-fold desc="Работа с импортами">
    /**
     * @param clazz
     */
    protected void addImport( String clazz ) {
        imports.add( clazz );
    }

    private void insertImport( String path ){
        builder.append( "import " ).append( path ).append( ";" );
        insertLine();
    }
    //</editor-fold>

    protected void insertSerialVersionUID() {
        insertTabs().append( Scope.PRIVATE.value() ).append( " static final long serialVersionUID = " );
        builder.append( ( long ) ( Math.random() * Long.MAX_VALUE ) ).append( "L;" );
        insertLine();
        insertLine();
    }

    protected void writeEmptyConstructor( String className ) {
        Utils.insertTabs( builder, 1 ).append( Scope.PUBLIC.value() ).
                append( " " ).append( className ).append( "(){");
        insertLine();
        increaseNestingLevel();
        closeMethodOrInnerClassDefinition();
    }


    protected void insertClassDeclaration(
            ClassType classType,
            String name,
            @Nullable String parentClassName,
            @Nullable List<String> interfaces
    ) {
        insertClassDeclaration( classType, name, false, parentClassName, interfaces );
    }

    protected void insertClassDeclaration(
            ClassType classType,
            String name,
            boolean _static,
            @Nullable String parentClassName,
            @Nullable List<String> interfaces
    ) {
        insertTabs().append( Scope.PUBLIC.value() )
                .append( _static ? " static " : " " ).append( classType ).append( " " ).append( name );

        if ( ! ( parentClassName == null || "".equals( parentClassName.trim() ) ) ) {
            builder.append( " extends " ).append( parentClassName );
        }

        if ( Utils.collectionNotEmpty( interfaces ) ) {
            builder.append( " implements " );
            builder.append( StringUtils.join( interfaces.iterator(), ", " ) );
        }

        builder.append( "{" );
        insertLine();
        insertLine();
        increaseNestingLevel();
    }

    protected void generateGetter(
            String javaDoc,
            @NotNull JavaType javaType,
            String name
            
    ){
        generateGetterSignature( javaDoc, javaType, name );

        insertTabs().append( "return " ).append( name ).append( ";");
        insertLine();
        closeMethodOrInnerClassDefinition();
    }

    protected void generateGetterSignature( String javaDoc, @NotNull JavaType javaType, String name ) {
        if ( javaType == JavaType.DATE ) {
            addImport( DATE_IMPORT );
        }

        jDoc.insertJavaDoc( true, jDoc.wrapCommentForGetter( javaDoc ) );

        generateMethodSignature(
                Scope.PUBLIC,
                javaType.value(),
                "get" + NamingUtils.convertNameForGettersAndSetters( name ),
                null,
                null,
                false
        );
        insertLine();
    }

    public void generateSetter(
            String javaDoc,
            @NotNull JavaType javaType,
            String name
    ){
        generateSetterSignature( javaDoc, javaType, name );
        insertTabs().append( "this." ).append( name ).append( " = " ).append( name ).append( ";" );
        insertLine();
        closeMethodOrInnerClassDefinition();
    }

    protected void generateSetterSignature( String javaDoc, @NotNull JavaType javaType, String name ) {
        generateSetterSignature( javaDoc, javaType, name, false );
    }

    protected void generateSetterSignature( String javaDoc, @NotNull JavaType javaType, String name, boolean forModel ) {
        jDoc.insertJavaDoc( forModel, jDoc.wrapCommentForSetter( javaDoc ) );
        generateMethodSignature(
                Scope.PUBLIC,
                forModel ? javaType.value() : null,
                "set" +NamingUtils.convertNameForGettersAndSetters( name ),
                Arrays.asList( javaType.value() + " " + name ),
                null,
                false
        );
        insertLine();
    }

    protected void closeMethodOrInnerClassDefinition() {
        decreaseNestingLevel();
        insertTabs().append( "}" );
        insertLine();
        insertLine();
    }

    protected void closeStatement() {
        builder.append( ");" );
        insertLine();
    }

    final protected void updateBuilder() {
        setNestingLevel( 0 );
        jDoc.setNestingLevel( 0 );
        builder = new StringBuilder();
        jDoc.setBuilder( builder );
    }

    @Override
    protected void increaseNestingLevel() {
        super.increaseNestingLevel();
        jDoc.setNestingLevel( nestingLevel );
    }

    @Override
    protected void decreaseNestingLevel() {
        super.decreaseNestingLevel();
        jDoc.setNestingLevel( nestingLevel );
    }
}