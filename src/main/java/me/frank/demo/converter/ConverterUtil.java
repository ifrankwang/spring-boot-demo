package me.frank.demo.converter;

import org.modelmapper.spi.MappingContext;
import org.modelmapper.spi.SourceGetter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王明哲
 */
public class ConverterUtil {

    public <T> List<T> convertIdListToObjectList(MappingContext<List<Long>, List<T>> context,
                                                 Constructable<T> constructable) {
        final List<T> targetList = new ArrayList<>();
        for (long id : context.getSource()) {
            targetList.add(constructable.apply(id));
        }
        return targetList;
    }

    public <T> T convertIdToObject(MappingContext<Long, T> context, Constructable<T> constructable) {
        final long id = context.getSource();
        return constructable.apply(id);
    }

    public <T> List<String> convertObjectToStringList(MappingContext<List<T>, List<String>> context,
                                                      SourceGetter<T> getter) {
        final List<String> targetList = new ArrayList<>();
        for (T object : context.getSource()) {
            targetList.add((String) getter.get(object));
        }
        return targetList;
    }

    public <T> boolean convertObjectToBoolean(MappingContext<T, Boolean> context, Determinable<T> determinable) {
        return determinable.apply(context.getSource());
    }

    public <T> Double convertObjectListToDouble(MappingContext<List<T>, Double> context,
                                                Calculable<T, Double> calculable) {
        return context.getSource().stream().mapToDouble(calculable::apply).sum();
    }
}
