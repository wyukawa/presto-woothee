package woothee;

import java.util.Map;

import static com.facebook.presto.spi.type.VarcharType.VARCHAR;
import com.facebook.presto.spi.block.Block;
import com.facebook.presto.spi.block.BlockBuilder;
import com.facebook.presto.spi.block.BlockBuilderStatus;
import com.facebook.presto.spi.function.Description;
import com.facebook.presto.spi.function.ScalarFunction;
import com.facebook.presto.spi.function.SqlNullable;
import com.facebook.presto.spi.function.SqlType;
import com.facebook.presto.spi.type.StandardTypes;

import io.airlift.slice.Slice;
import io.airlift.slice.Slices;

import is.tagomor.woothee.Classifier;

public class ParseAgentFuntion {
    @ScalarFunction("parse_agent")
    @Description("Returns Map, which has keys such as 'category', 'name', 'os', 'version', 'vendor' and 'os_version'")
    @SqlType("map<varchar,varchar>")
    public static Block parseAgent(@SqlNullable @SqlType(StandardTypes.VARCHAR) Slice slice) {
        String argument = slice.toStringUtf8();
        Map<String, String> stringMap = Classifier.parse(argument);
        BlockBuilder builder = VARCHAR.createBlockBuilder(new BlockBuilderStatus(), stringMap.size());
        for (Map.Entry<String, String> entry : stringMap.entrySet()) {
            VARCHAR.writeSlice(builder, Slices.utf8Slice(entry.getKey()));
            VARCHAR.writeSlice(builder, Slices.utf8Slice(entry.getValue()));
        }
        return builder.build();
    }
}
