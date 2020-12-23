package woothee;

import static io.prestosql.util.StructuralTestUtil.mapType;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.prestosql.operator.scalar.AbstractTestFunctions;
import io.prestosql.spi.type.VarcharType;

public class ParseAgentFunctionTest extends AbstractTestFunctions {
    @BeforeClass
    protected void registerFunctions()
    {
        functionAssertions.installPlugin(new WootheePlugin());
    }

    @Test
    public void testParseAgent() {
        assertParseAgent("parse_agent('Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; InfoPath.1)')",
                       ImmutableMap.builder()
                                   .put("os", "Windows 2000")
                                   .put("vendor", "Microsoft")
                                   .put("os_version", "NT 5.0")
                                   .put("name", "Internet Explorer")
                                   .put("category", "pc")
                                   .put("version", "6.0")
                                   .build());

        assertParseAgent("parse_agent('Mozilla/5.0 (Linux; U; Android 2.3.5; ja-jp; ISW11F Build/FGK500) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1')",
                         ImmutableMap.builder()
                                     .put("os", "Android")
                                     .put("vendor", "Apple")
                                     .put("os_version", "2.3.5")
                                     .put("name", "Safari")
                                     .put("category", "smartphone")
                                     .put("version", "4.0")
                                     .build());
    }

    private void assertParseAgent(String projection, Object expected) {
        assertFunction(projection, mapType(VarcharType.VARCHAR, VarcharType.VARCHAR), expected);
    }
}
