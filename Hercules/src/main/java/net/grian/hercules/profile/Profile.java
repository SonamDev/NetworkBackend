package net.grian.hercules.profile;

import net.grian.hercules.profile.special.SettingsProperty;
import net.grian.waterfall.IProfile;
import net.grian.waterfall.IProperty;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unchecked")
public class Profile implements IProfile {

    public static final Property<UUID> UUID = new Property<>("uuid");
    public static final Property<String> Username = new Property<>("username");
    public static final Property<String> Rank = new Property<>("rank");
    public static final Property<String> PackageRank = new Property<>("package_rank");
    public static final Property<String> Finder = new Property<>("finder");
    public static final SettingsProperty Settings = new SettingsProperty();

    private static final Map<String, IProperty> PROPERTY_MAP = new HashMap<String, IProperty>()
    {{
        put(UUID.key, UUID);
        put(Username.key, Username);
        put(Rank.key, Rank);
        put(PackageRank.key, PackageRank);
        put(Finder.key, Finder);
        put(Settings.key(), Settings);

    }};

    private Document document;

    private ConcurrentHashMap<IProperty, Object> propertyMap = new ConcurrentHashMap<>();

    public Profile(Document document) {
        this.document = document;
        setup();
    }

    private void setup() {



    }

    public <T> void saveProperty(IProperty<T> property, T value) {

    }

    @Override
    public <T> T getProperty(IProperty<T> property) {
        return (T) propertyMap.get(property);
    }

    public <T> void setProperty(IProperty<T> property, T value) {
        propertyMap.put(property, value);
        document.put(property.key(), property.getAsSaveType(value));
    }

    @Override
    public void save() {

    }

}
