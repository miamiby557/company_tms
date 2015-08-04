package com.lnet.tms.utility;

import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

public class ReflectUtils {

    public static Object getProperty(Object bean, String name)
    {
        try {
            return PropertyUtils.getProperty(bean, name);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static PropertyDescriptor[] getBeanProperties(Class<?> beanClass) {
        return PropertyUtils.getPropertyDescriptors(beanClass);
    }

    public static PropertyDescriptor getJpaId(Class<?> beanClass) {
        for (PropertyDescriptor descriptor : getBeanProperties(beanClass)) {
            if (isJpaId(descriptor)) return descriptor;
        }
        return null;
    }
    public static boolean isJpaId(PropertyDescriptor descriptor) {
        for (Annotation ann : descriptor.getReadMethod().getAnnotations()) {
            if (ann.annotationType().getName().equals("javax.persistence.Id")) return true;
        }
        return false;
    }
}
