package net.grian.commons.bson;

import org.bson.Document;

import java.util.Arrays;

public class GrianDocument extends Document {

    public GrianDocument(String key, Object value) {
        super(key, value);
    }

    public GrianDocument() {
        super();
    }

    @Override
    public Object get(Object key) {

        String _key = (String) key;

        if(_key.contains(".")) {
            String finalKey;
            System.out.println(_key);
            String[] suf = _key.split("\\.");
            System.out.println(suf.length);
            System.out.println(Arrays.toString(suf));
            finalKey = suf[suf.length - 1];
            System.out.println("Final key: " + finalKey);
            suf = Arrays.copyOf(suf, suf.length - 1);
            Document finalDocument = null;
            for (String s : suf) {
                System.out.println(s);
                Document doc = super.get(s, GrianDocument.class);
                if(doc==null)
                    break;
                finalDocument = doc;
            }
            System.out.println(finalDocument);
            return finalDocument != null ? finalDocument.get(finalKey) : null;
        }

        return super.get(key);
    }

}
