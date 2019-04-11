import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * 类的实现描述:
 *
 * @author liqun.wu
 */
public class SingleResourceConfigurationTest {
    @Test
    public void get() throws IOException {
        // vv SingleResourceConfigurationTest
        Configuration conf = new Configuration();
        conf.addResource("configuration-1.xml");
        assertThat(conf.get("color"), is("yellow"));
        assertThat(conf.getInt("size", 0), is(10));
        assertThat(conf.get("breadth", "wide"), is("wide"));
        // ^^ SingleResourceConfigurationTest
    }

}
