package net.grian.hercules.util;

import net.grian.hercules.profile.Profile;

public class ProfileProperty<T> {

    private T value;
    private String databaseKey;

    public ProfileProperty(T value, String key) {
        this.value = value;
        this.databaseKey = key;
    }

    public void update(Profile profile, T value) {
        update(profile, value, false);
    }

    public void update(Profile profile, T value, boolean database) {
        this.value = value;

    }

    public void saveOnDB(Profile profile) {

    }

    public T get() {
        return value;
    }

}
