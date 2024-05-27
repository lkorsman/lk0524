/*
  This class is meant to create an instance of the Tool object based on a Tool.Code
 */

import java.math.BigDecimal;

public class ToolRepository {

    /**
     *
     * @param code the enum Tool.Code associated with a Tool
     * @return Tool instance based on Tool.Code
     */
    public static Tool getToolByCode(Tool.Code code) {
        switch (code) {
            case CHNS -> {
                return new Tool(
                        code,
                        ApplicationConstants.TOOL_TYPE_CHAINSAW,
                        ApplicationConstants.BRAND_STIHL,
                        BigDecimal.valueOf(1.49),
                        true,
                        false,
                        true
                );
            }
            case JAKR -> {
                return new Tool(
                        code,
                        ApplicationConstants.TOOL_TYPE_JACKHAMMER,
                        ApplicationConstants.BRAND_RIDGID,
                        BigDecimal.valueOf(2.99),
                        true,
                        false,
                        false
                );
            }
            case JAKD -> {
                return new Tool(
                        code,
                        ApplicationConstants.TOOL_TYPE_JACKHAMMER,
                        ApplicationConstants.BRAND_DEWALT,
                        BigDecimal.valueOf(2.99),
                        true,
                        false,
                        false
                );
            }
            case LADW -> {
                return new Tool(
                        code,
                        ApplicationConstants.TOOL_TYPE_LADDER,
                        ApplicationConstants.BRAND_WERNER,
                        BigDecimal.valueOf(1.99),
                        true,
                        true,
                        false
                );
            }
            default -> {
                throw new IllegalArgumentException("Invalid tool code: " + code);
            }
        }
    }
}
