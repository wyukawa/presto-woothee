package woothee;

import io.prestosql.spi.Plugin;
import com.google.common.collect.ImmutableSet;

import java.util.Set;

public class WootheePlugin implements Plugin {
    @Override
    public Set<Class<?>> getFunctions() {

        return ImmutableSet.<Class<?>>builder()
                .add(ParseAgentFunction.class)
                .add(IsParseFunction.class)
                .build();
    }

}