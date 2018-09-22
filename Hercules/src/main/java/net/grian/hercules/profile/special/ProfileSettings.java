package net.grian.hercules.profile.special;

import net.grian.hercules.profile.BsonSerializable;
import net.grian.hercules.profile.Property;
import org.bson.Document;

public final class ProfileSettings implements BsonSerializable {



    // >>>>>>>>> DEFAULT SETTINGS AND MAPPING <<<<<<<<<

    public static final Property<Boolean> StaffChatEnabled = new Property<>("sc_enabled");

    // >>>>>>>>> DEFAULT SETTINGS AND MAPPING <<<<<<<<<



    private static final ProfileSettings DEFAULT;

    static {
        DEFAULT = new ProfileSettings();
    }

    private ProfileSettings() {}

    ProfileSettings(Document document) {

    }

    public <T> T getSetting() {
        return null;
    }

    public static ProfileSettings defaultSettings() {
        return DEFAULT;
    }

    @Override
    public Document toBson() {
        return null;
    }
}