package net.grian.hercules.profile.special;

import net.grian.waterfall.IProperty;
import org.bson.Document;

public class SettingsProperty implements IProperty<ProfileSettings> {

    @Override
    public ProfileSettings defaultValue() {
        return ProfileSettings.defaultSettings();
    }

    @Override
    public boolean hasDefaultValue() {
        return true;
    }

    @Override
    public Object getAsSaveType(ProfileSettings profileSettings) {
        return profileSettings.toBson();
    }

    @Override
    public ProfileSettings getFromSave(Document document) {
        return new ProfileSettings(document);
    }

    @Override
    public String key() {
        return "settings_";
    }

}
