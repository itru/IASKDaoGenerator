//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.03.07 at 02:23:59 PM YEKT 
//


package com.kreig133.daogenerator.jaxb;

import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.annotation.*;

/**
 * <p>Java class for parameterType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="parameterType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="type" use="required" type="{com.aplana.dao-generator}javaType" />
 *       &lt;attribute name="inOut" type="{com.aplana.dao-generator}inOutType" />
 *       &lt;attribute name="defaultValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="testValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="renameTo" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="sqlType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="comment" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="jdbcType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parameterType", propOrder = {
        "value"
})
public class ParameterType {

    @XmlValue
    protected String value;
    @XmlAttribute(required = true)
    protected String name;
    @XmlAttribute(required = true)
    protected JavaType type;
    @XmlAttribute
    protected InOutType inOut;
    @XmlAttribute
    protected String defaultValue;
    @XmlAttribute
    protected String testValue;
    @XmlAttribute
    protected String renameTo;
    @XmlAttribute
    protected String sqlType;
    @XmlAttribute
    protected String comment;
    @XmlAttribute
    protected String jdbcType;

    /**
     * Gets the value of the value property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the type property.
     *
     * @return
     *     possible object is
     *     {@link JavaType }
     *
     */
    public JavaType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value
     *     allowed object is
     *     {@link JavaType }
     *
     */
    public void setType(JavaType value) {
        this.type = value;
    }

    /**
     * Gets the value of the inOut property.
     *
     * @return
     *     possible object is
     *     {@link InOutType }
     *
     */
    public InOutType getInOut() {
        return inOut;
    }

    /**
     * Sets the value of the inOut property.
     *
     * @param value
     *     allowed object is
     *     {@link InOutType }
     *
     */
    public void setInOut(InOutType value) {
        this.inOut = value;
    }

    /**
     * Gets the value of the defaultValue property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * Sets the value of the defaultValue property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDefaultValue(String value) {
        this.defaultValue = value;
    }

    /**
     * Gets the value of the testValue property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTestValue() {
        return testValue;
    }

    /**
     * Sets the value of the testValue property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTestValue(String value) {
        this.testValue = value;
    }

    /**
     * Gets the value of the renameTo property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRenameTo() {
        return renameTo;
    }

    /**
     * Sets the value of the renameTo property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRenameTo(String value) {
        this.renameTo = value;
    }

    /**
     * Gets the value of the sqlType property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSqlType() {
        return sqlType;
    }

    /**
     * Sets the value of the sqlType property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSqlType(String value) {
        this.sqlType = value;
    }

    /**
     * Gets the value of the comment property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setComment(String value) {
        this.comment = value;
    }


    /**
     * Gets the value of the jdbcType property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getJdbcType() {
        return jdbcType;
    }

    /**
     * Sets the value of the jdbcType property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setJdbcType(String value) {
        this.jdbcType = value;
    }

    public String getCommentForJavaDoc() {
        if ( StringUtils.isEmpty( comment ) || comment.toLowerCase().equals( "null" ) ) {
            return renameTo;
        }

        return comment;
    }

    @NotNull
    public String getDefaultValueForJavaCode() {
        return defaultValue.trim() +
                ( type == JavaType.LONG ?
                        ( "null".equals( defaultValue.toLowerCase().trim() ) ? "" : "L" ) : "" );
    }
    
    @Nullable
    public static String getDefaultTestValue( @NotNull ParameterType parameterType ) {
        switch ( parameterType.getType() ) {
            case DATE:
                return "12-12-2012 0:00:00.000";
            case BYTE:
            case DOUBLE:
            case LONG:
                return "0";
            case STRING:
                return " ";
            default:
                return null;
        }
    }
}
