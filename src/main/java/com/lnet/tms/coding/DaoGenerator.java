package com.lnet.tms.coding;

import com.lnet.tms.utility.StringUtils;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class DaoGenerator {

    public static void generate(Class<?> clazz) throws IOException {

        String packageName = StringUtils.splitCamelCaseString(clazz.getSimpleName()).get(0).toLowerCase();

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("modelName", clazz.getSimpleName());
        model.put("modelIdType", "UUID");
        model.put("package", packageName);

        Path path = Paths.get("", "src\\main\\java\\com\\lnet\\tms\\dao", packageName, clazz.getSimpleName() + "Dao.java");
        File destFile = new File(path.toString());
        File parent = destFile.getParentFile();
        if(!parent.exists() && !parent.mkdirs()){
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }

        try {
            GeneratorUtils.generate("dao.ftl", destFile, model);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
