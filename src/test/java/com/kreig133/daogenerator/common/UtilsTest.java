package com.kreig133.daogenerator.common;

import com.kreig133.daogenerator.jaxb.DaoMethod;
import com.kreig133.daogenerator.jaxb.ParameterType;
import com.kreig133.daogenerator.jaxb.ParametersType;
import com.kreig133.daogenerator.settings.Settings;
import junit.framework.Assert;
import org.junit.Test;

import static com.kreig133.daogenerator.files.mybatis.DaoJavaClassGenerator.checkToNeedOwnInClass;

/**
 * @author eshangareev
 * @version 1.0
 */
public class UtilsTest{
    @Test
    public void testConvertPBNameToName() throws Exception {
        final String fuCk_off_aLL = Utils.convertPBNameToName( "FuCk_off_aLL" );
        Assert.assertEquals( "fuCkOffALL", fuCk_off_aLL );
    }
    
    @Test
    public void testCheckToNeedOwnInClass(){

        final DaoMethod daoMethod = new DaoMethod();
        daoMethod.setInputParametrs( new ParametersType() );
        daoMethod.getInputParametrs().getParameter().add( new ParameterType() );
        
        Assert.assertFalse( checkToNeedOwnInClass( daoMethod ) );

        daoMethod.getInputParametrs().getParameter().add( new ParameterType() );

        Assert.assertTrue( checkToNeedOwnInClass( daoMethod ) );
    }
    
    
}
