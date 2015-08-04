package com.lnet.tms.coding;

import com.lnet.tms.utility.StringUtils;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class ViewGenerator {

    public static void generateIndexView(Class<?> clazz) throws IOException {
        Map<String, Object> model = GeneratorUtils.buildViewModel(clazz);
        String packageName = model.get("package").toString();

        Path path = Paths.get("", "src\\main\\webapp\\views", packageName, StringUtils.toCamelString(clazz.getSimpleName()), "index.ftl");
        File destFile = new File(path.toString());
        File parent = destFile.getParentFile();
        if(!parent.exists() && !parent.mkdirs()){
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }

        try {
            GeneratorUtils.generate("indexView.ftl", destFile, model);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    public static void generateCreateView(Class<?> clazz) throws IOException {

        Map<String, Object> model = GeneratorUtils.buildViewModel(clazz);
        String packageName = model.get("package").toString();

        Path path = Paths.get("", "src\\main\\webapp\\views", packageName, StringUtils.toCamelString(clazz.getSimpleName()), "create.ftl");
        File destFile = new File(path.toString());
        File parent = destFile.getParentFile();
        if(!parent.exists() && !parent.mkdirs()){
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }

        try {
            GeneratorUtils.generate("createView.ftl", destFile, model);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    public static void generateModifyView(Class<?> clazz) throws IOException {

        Map<String, Object> model = GeneratorUtils.buildViewModel(clazz);
        String packageName = model.get("package").toString();

        Path path = Paths.get("", "src\\main\\webapp\\views", packageName, StringUtils.toCamelString(clazz.getSimpleName()), "modify.ftl");
        File destFile = new File(path.toString());
        File parent = destFile.getParentFile();
        if(!parent.exists() && !parent.mkdirs()){
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }

        try {
            GeneratorUtils.generate("modifyView.ftl", destFile, model);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    public static void generateDetailView(Class<?> clazz) throws IOException {

        Map<String, Object> model = GeneratorUtils.buildViewModel(clazz);
        String packageName = model.get("package").toString();

        Path path = Paths.get("", "src\\main\\webapp\\views", packageName, StringUtils.toCamelString(clazz.getSimpleName()), "detail.ftl");
        File destFile = new File(path.toString());
        File parent = destFile.getParentFile();
        if(!parent.exists() && !parent.mkdirs()){
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }

        try {
            GeneratorUtils.generate("detailView.ftl", destFile, model);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
