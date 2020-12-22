package woothee;

import io.prestosql.spi.function.Description;
import io.prestosql.spi.function.ScalarFunction;
import io.prestosql.spi.function.SqlNullable;
import io.prestosql.spi.function.SqlType;
import io.prestosql.spi.type.StandardTypes;
import io.airlift.slice.Slice;
import is.tagomor.woothee.Classifier;
import is.tagomor.woothee.DataSet;


public class IsParseFunction {

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
