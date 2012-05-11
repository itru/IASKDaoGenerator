package com.kreig133.daogenerator.files.mybatis.mapping;

import com.kreig133.daogenerator.files.PackageAndFileUtils;
import com.kreig133.daogenerator.files.mybatis.DaoJavaClassGenerator;
import com.kreig133.daogenerator.settings.Settings;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * @author kreig133
 * @version 1.0
 */
public abstract class MappingGenerator extends DaoJavaClassGenerator {

    public static MappingGenerator instance (){
        return new IaskMappingGenerator();
    }

    @NotNull
    @Override
    public File getFile() {

        File file = new File(
                Settings.settings().getPathForGeneratedSource() + "/" +
                        PackageAndFileUtils.replacePointBySlash( Settings.settings().getMapperPackage() ) + "/"
                        + getFileName() + getFileNameEnding()
                 );

        PackageAndFileUtils.createDirsAndFile( file.getParentFile() );

        return file;
    }

    abstract protected String getFileNameEnding();
}
