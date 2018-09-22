import net.grian.commons.bson.GrianDocument;
import org.junit.Test;

public class GrianDocumentTest {

    @Test
    public void grianDocumentTest() {

        GrianDocument document = new GrianDocument("shit", new GrianDocument("woops", new GrianDocument("wew", "ahhhh")));

        System.out.println(document.get("shit.woops.wew"));

    }

}
