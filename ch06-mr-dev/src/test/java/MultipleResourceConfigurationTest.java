import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;

/**
 * 类的实现描述:
 *
 * @author liqun.wu
 */
public class MultipleResourceConfigurationTest {

    @Test
    public void get() throws IOException {
        Configuration conf = new Configuration();
        conf.addResource("configuration-1.xml");
        conf.addResource("configuration-2.xml");

        Assert.assertThat(conf.get("color"), is("yellow"));
        assertThat(conf.getInt("size", 0), is(12));

        assertThat(conf.get("weight"), is("heavy"));

        // variable expansion
        // vv MultipleResourceConfigurationTest-Expansion
        assertThat(conf.get("size-weight"), is("12,heavy"));
        // ^^ MultipleResourceConfigurationTest-Expansion

        // variable expansion with system properties
        // vv MultipleResourceConfigurationTest-SystemExpansion
        System.setProperty("size", "14");
        assertThat(conf.get("size-weight"), is("14,heavy"));
        // ^^ MultipleResourceConfigurationTest-SystemExpansion

        // system properties are not picked up
        // vv MultipleResourceConfigurationTest-NoSystemByDefault
        System.setProperty("length", "2");
        assertThat(conf.get("length"), is((String) null));

    }
}
