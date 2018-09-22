package net.grian.hercules.profile;

import net.grian.waterfall.IProperty;
import org.bson.Document;

public final class Property<T> implements IProperty<T> {

    String key;
    T defaultValue;

    public Property(String key, T defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public Property(String key) {
        this(key, null);
    }

    @Override
    public T defaultValue() {
        return defaultValue;
    }

    @Override
    public boolean hasDefaultValue() {
        return defaultValue != null;
    }

    @Override
    public Object getAsSaveType(T t) {
        return t instanceof Enum ? ((Enum) t).name() : t;
    }

    @Override
    public T getFromSave(Document document) {
        return (T) document.get(this.key);
    }

    @Override
    public String key() {
        return this.key;
    }
}