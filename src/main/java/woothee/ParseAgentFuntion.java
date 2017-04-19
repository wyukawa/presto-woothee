package woothee;

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
import is.tagomor.woothee.DataSet;

import java.util.Map;

import static com.facebook.presto.spi.type.VarcharType.VARCHAR;

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

    @ScalarFunction("is_pc")
    @Description("Returns Boolean: map['category'] is a pc or not.")
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean isPC(@SqlNullable @SqlType(StandardTypes.VARCHAR) Slice slice) {
        String argument = slice.toStringUtf8();
        return Classifier.parse(argument).get(DataSet.ATTRIBUTE_CATEGORY).equals(DataSet.DATASET_CATEGORY_PC);
    }

    @ScalarFunction("is_smartphone")
    @Description("Returns Boolean: map['category'] is a smartphone or not.")
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean IsSmartPhone(@SqlNullable @SqlType(StandardTypes.VARCHAR) Slice slice) {
        String argument = slice.toStringUtf8();
        return Classifier.parse(argument).get(DataSet.ATTRIBUTE_CATEGORY).equals(DataSet.DATASET_CATEGORY_SMARTPHONE);
    }

    @ScalarFunction("is_mobilephone")
    @Description("Returns Boolean: map['category'] is a mobilephone or not.")
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean IsMobilePhone(@SqlNullable @SqlType(StandardTypes.VARCHAR) Slice slice) {
        String argument = slice.toStringUtf8();
        return Classifier.parse(argument).get(DataSet.ATTRIBUTE_CATEGORY).equals(DataSet.DATASET_CATEGORY_MOBILEPHONE);
    }

    @ScalarFunction("is_appliance")
    @Description("Returns Boolean: map['category'] is a appliance or not.")
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean IsAppliance(@SqlNullable @SqlType(StandardTypes.VARCHAR) Slice slice) {
        String argument = slice.toStringUtf8();
        return Classifier.parse(argument).get(DataSet.ATTRIBUTE_CATEGORY).equals(DataSet.DATASET_CATEGORY_APPLIANCE);
    }

    @ScalarFunction("is_crawler")
    @Description("Returns Boolean: map['category'] is a crawler or not.")
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean IsCrawler(@SqlNullable @SqlType(StandardTypes.VARCHAR) Slice slice) {
        String argument = slice.toStringUtf8();
        return Classifier.parse(argument).get(DataSet.ATTRIBUTE_CATEGORY).equals(DataSet.DATASET_CATEGORY_CRAWLER);
    }

    @ScalarFunction("is_misc")
    @Description("Returns Boolean: map['category'] is a misc or not.")
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean IsMisc(@SqlNullable @SqlType(StandardTypes.VARCHAR) Slice slice) {
        String argument = slice.toStringUtf8();
        return Classifier.parse(argument).get(DataSet.ATTRIBUTE_CATEGORY).equals(DataSet.DATASET_CATEGORY_MISC);
    }

    @ScalarFunction("is_unknown")
    @Description("Returns Boolean: map['category'] is a unknown or not.")
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean IsUnknown(@SqlNullable @SqlType(StandardTypes.VARCHAR) Slice slice) {
        String argument = slice.toStringUtf8();
        return Classifier.parse(argument).get(DataSet.ATTRIBUTE_CATEGORY).equals(DataSet.VALUE_UNKNOWN);
    }
}
