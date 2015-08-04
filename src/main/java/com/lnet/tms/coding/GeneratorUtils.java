package com.lnet.tms.coding;

import com.lnet.tms.utility.ReflectUtils;
import com.lnet.tms.utility.StringUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.*;

public class GeneratorUtils {

    public static boolean isModelProperty(PropertyDescriptor descriptor)
    {
        List<Class<?>> whiteList = new ArrayList<>();
        whiteList.add(String.class);
        whiteList.add(UUID.class);
        whiteList.add(Date.class);
        whiteList.add(Timestamp.class);
        whiteList.add(Integer.class);
        whiteList.add(Double.class);
        whiteList.add(Number.class);
        whiteList.add(Short.class);
        whiteList.add(Long.class);
        whiteList.add(BigInteger.class);
        whiteList.add(BigDecimal.class);
        whiteList.add(Boolean.class);

        whiteList.add(int.class);
        whiteList.add(boolean.class);

        return whiteList.contains(descriptor.getPropertyType());
    }

    public static List<Map<String, Object>> getModelProperties(Class<?> beanClass) {
        List<Map<String, Object>> result = new ArrayList<>();

        for (PropertyDescriptor descriptor : ReflectUtils.getBeanProperties(beanClass)) {
            if (!isModelProperty(descriptor)) continue;

            Map<String, Object> field = new HashMap<>();
            field.put("descriptor", descriptor);
            field.put("name", descriptor.getName());
            field.put("type", descriptor.getPropertyType().getName());
            field.put("isJpaId", ReflectUtils.isJpaId(descriptor));
            result.add(field);

            System.out.println(field.get("name") + " \t " + field.get("type") + " \t " + field.get("isJpaId"));
        }

        return result;
    }

    public static Map<String, Object> buildViewModel(Class<?> clazz)
    {
        String packageName = StringUtils.splitCamelCaseString(clazz.getSimpleName()).get(0).toLowerCase();
        PropertyDescriptor jpaId = ReflectUtils.getJpaId(clazz);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("modelName", clazz.getSimpleName());
        model.put("modelIdType", jpaId.getPropertyType().getSimpleName());
        model.put("camelModelName", StringUtils.toCamelString(clazz.getSimpleName()));
        model.put("package", packageName);
        model.put("jpaId", jpaId.getName());
        model.put("props", GeneratorUtils.getModelProperties(clazz));

        return model;
    }


    public static void generate(String templateName, File destFile, Object model) throws IOException, TemplateException {

        Path currentRelativePath = Paths.get("", "templates");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);
        File templatePath = new File(s);

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setDirectoryForTemplateLoading(templatePath);
        cfg.setDefaultEncoding("UTF-8");

        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        FileWriter codeFile = new FileWriter(destFile);
        Template template = cfg.getTemplate(templateName);
        template.process(model, codeFile);
        codeFile.flush();
        codeFile.close();
    }
}
