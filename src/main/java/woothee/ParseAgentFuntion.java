package woothee;

import com.facebook.presto.spi.PageBuilder;
import com.facebook.presto.spi.block.Block;
import com.facebook.presto.spi.block.BlockBuilder;
import com.facebook.presto.spi.function.Description;
import com.facebook.presto.spi.function.ScalarFunction;
import com.facebook.presto.spi.function.SqlType;
import com.facebook.presto.spi.function.TypeParameter;
import com.facebook.presto.spi.type.StandardTypes;
import com.facebook.presto.spi.type.Type;
import com.google.common.collect.ImmutableList;
import io.airlift.slice.Slice;
import io.airlift.slice.Slices;
import is.tagomor.woothee.Classifier;

import java.util.Map;

import static com.facebook.presto.spi.type.VarcharType.VARCHAR;

@ScalarFunction("parse_agent")
@Description("Returns Map, which has keys such as 'category', 'name', 'os', 'version', 'vendor' and 'os_version'")
public class ParseAgentFuntion {

    private final PageBuilder pageBuilder;

    public ParseAgentFuntion(@TypeParameter("map<varchar,varchar>") Type mapType) {
        pageBuilder = new PageBuilder(ImmutableList.of(mapType));
    }

    @SqlType("map<varchar,varchar>")
    public Block parseAgent(@TypeParameter("map<varchar,varchar>") Type mapType, @SqlType(StandardTypes.VARCHAR) Slice slice) {
        String argument = slice.toStringUtf8();
        Map<String, String> stringMap = Classifier.parse(argument);

        if (pageBuilder.isFull()) {
            pageBuilder.reset();
        }

        BlockBuilder blockBuilder = pageBuilder.getBlockBuilder(0);
        BlockBuilder singleMapBlockBuilder = blockBuilder.beginBlockEntry();
        for (Map.Entry<String, String> entry : stringMap.entrySet()) {
            VARCHAR.writeSlice(singleMapBlockBuilder, Slices.utf8Slice(entry.getKey()));
            VARCHAR.writeSlice(singleMapBlockBuilder, Slices.utf8Slice(entry.getValue()));
        }
        blockBuilder.closeEntry();
        pageBuilder.declarePosition();

        return (Block) mapType.getObject(blockBuilder, blockBuilder.getPositionCount() - 1);
    }

}
