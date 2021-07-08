package az.crbn.common.util;

import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.Objects;
import java.util.stream.Stream;

@UtilityClass
public class BeanUtilsWrapper {

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> Objects.isNull(wrappedSource.getPropertyValue(propertyName)))
                .toArray(String[]::new);
    }

    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

}
