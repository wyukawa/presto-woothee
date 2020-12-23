package woothee;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.prestosql.operator.scalar.AbstractTestFunctions;
import io.prestosql.spi.type.BooleanType;

/**
 * https://github.com/woothee/woothee/tree/master/testsets
 * */
public class IsParseFunctionTest extends AbstractTestFunctions {
    @BeforeClass
    protected void registerFunctions()
    {
        functionAssertions.installPlugin(new WootheePlugin());
    }

    @Test
    public void testIsPC() {
        assertTrue("is_pc('Sleipnir/2.9.9')");
        assertTrue("is_pc('Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_4; ja-jp) AppleWebKit/525.18 (KHTML, like Gecko) Version/3.1.2 Safari/525.20.1')");
        assertTrue("is_pc('Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; InfoPath.1)')");
    }

    @Test
    public void testIsSmartPhone() {
        assertTrue("is_smartphone('Mozilla/5.0 (Linux; U; Android 2.3.5; ja-jp; ISW11F Build/FGK500) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1')");
        assertTrue("is_smartphone('Mozilla/5.0 (iPhone; CPU iPhone OS 5_0_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9A405 Safari/7534.48.3')");
        assertTrue("is_smartphone('Mozilla/5.0 (Mobile; rv:18.0) Gecko/18.0 Firefox/18.0')");
    }

    @Test
    public void testIsMobilePhone() {
        assertTrue("is_mobilephone('KDDI-TS3V UP.Browser/6.2_7.2.7.1.K.6.210 (GUI) MMP/2.0')");
        assertTrue("is_mobilephone('DoCoMo/2.0 SH01A(c100;TB;W24H16)')");
        assertTrue("is_mobilephone('Mozilla/5.0 (jig browser core; SH03B)')");
        assertTrue("is_mobilephone('SoftBank/1.0/841SH/SHJ001/SN000000000000000 Browser/NetFront/3.5 Profile/MIDP-2.0 Configuration/CLDC-1.1')");
        assertTrue("is_mobilephone('Mozilla/3.0(WILLCOM;TOSHIBA/WX320T/2;1/1/C128) NetFront/3.4')");
    }

    @Test
    public void testIsAppliance() {
        assertTrue("is_appliance('Mozilla/5.0 (Nintendo 3DS; U; ; ja) Version/1.7455.JP')");
    }

    @Test
    public void testIsCrawler() {
        assertTrue("is_crawler('Mozilla/5.0 (compatible; Yahoo! Slurp; http://help.yahoo.com/help/us/ysearch/slurp)')");
        assertTrue("is_crawler('Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)')");
        assertTrue("is_crawler('emBot-GalaBuzz/Nutch-1.0 (http://emining.jp/; em@galabuzz.jp)')");
    }

    @Test
    public void testIsMisc() {
        assertTrue("is_misc('AppleSyndication/56.1')");
    }

    @Test
    public void testIsUnknown() {
    }

    private void assertTrue(String projection) {
        assertFunction(projection, BooleanType.BOOLEAN, true);
    }
}
