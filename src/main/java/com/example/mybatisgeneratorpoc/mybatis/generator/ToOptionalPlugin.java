package com.example.mybatisgeneratorpoc.mybatis.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import static java.util.stream.Collectors.*;


public class ToOptionalPlugin
    extends PluginAdapter {

    private final List<String> excludeColumns = new ArrayList<>();

    private boolean converted;

    @Override
    public void setProperties(final Properties properties) {
        final var property = properties.getProperty("excludeColumns");

        if (Objects.isNull(property) || property.isEmpty())
            return;

		this.excludeColumns.addAll(Arrays.stream(property.split(",")).collect(toList()));
    }

    @Override
    public boolean validate(final List<String> warnings) {
        return true;
    }

    @Override
    public void initialized(final IntrospectedTable introspectedTable) {
    }

    @Override
    public boolean modelGetterMethodGenerated(final Method method, final TopLevelClass topLevelClass,
        final IntrospectedColumn introspectedColumn, final IntrospectedTable introspectedTable, final ModelClassType modelClassType) {

        if (this.excludeColumns.contains(introspectedColumn.getActualColumnName()))
            return true;

        if (introspectedColumn.isNullable()) {
            final var optMethod = new Method(method);
            optMethod.setName(method.getName() + "Opt");
            optMethod.setReturnType(
                new FullyQualifiedJavaType("Optional<" + method.getReturnType().orElseThrow() + ">"));
            optMethod.getBodyLines().clear();
            optMethod.getBodyLines().add("return Optional.ofNullable(" + introspectedColumn.getJavaProperty() + ");");
            topLevelClass.addMethod(optMethod);
			this.converted = true;
        }

        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(final TopLevelClass topLevelClass, final IntrospectedTable introspectedTable) {

        if (this.converted) {
            topLevelClass.addImportedType("java.util.Optional");
			this.converted = false;
        }

        return true;
    }
}
