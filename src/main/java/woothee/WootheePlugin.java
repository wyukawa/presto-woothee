package woothee;

import com.facebook.presto.spi.Plugin;
import com.google.common.collect.ImmutableSet;

import java.util.Set;

public class WootheePlugin implements Plugin {
    @Override
    public Set<Class<?>> getFunctions() {

        return ImmutableSet.<Class<?>>builder()
                .add(ParseAgentFuntion.class)
                .add(IsParseFuntion.class)
                .build();
    }

}