/*
  This class is meant to create an instance of the Tool object based on a Tool.Code
 */

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
                        1.49,
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
                        2.99,
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
                        2.99,
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
                        1.99,
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
