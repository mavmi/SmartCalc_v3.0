package mavmi.smartCalc.core.parser.operator;

public enum OPERATOR {
    ADD,
    SUB,
    MUL,
    DIV,
    MOD,
    POW,
    UN_PL,
    UN_MIN,
    OP_PAREN,
    CL_PAREN,
    COS,
    SIN,
    TAN,
    ACOS,
    ASIN,
    ATAN,
    SQRT,
    LN,
    LOG;

    /**
     * Convert string to enum operator
     *
     * @param val                   String to parse
     * @throws OperatorException    On invalid input
     */
    public static OPERATOR fromString(String val) {
        switch (val) {
            case "+":
                return ADD;
            case "-":
                return SUB;
            case "*":
                return MUL;
            case "/":
                return DIV;
            case "mod":
                return MOD;
            case "^":
                return POW;
            case "(":
                return OP_PAREN;
            case ")":
                return CL_PAREN;
            case "cos":
                return COS;
            case "sin":
                return SIN;
            case "tan":
                return TAN;
            case "acos":
                return ACOS;
            case "asin":
                return ASIN;
            case "atan":
                return ATAN;
            case "sqrt":
                return SQRT;
            case "ln":
                return LN;
            case "log":
                return LOG;
        }

        throw new OperatorException("Invalid operation: " + val);
    }

    public static String toString(OPERATOR operator) {
        switch (operator) {
            case ADD:
                return "+";
            case SUB:
                return "-";
            case MUL:
                return "*";
            case DIV:
                return "/";
            case MOD:
                return "mod";
            case POW:
                return "^";
            case OP_PAREN:
                return "(";
            case CL_PAREN:
                return ")";
            case COS:
                return "cos";
            case SIN:
                return "sin";
            case TAN:
                return "tan";
            case ACOS:
                return "acos";
            case ASIN:
                return "asin";
            case ATAN:
                return "atan";
            case SQRT:
                return "sqrt";
            case LN:
                return "ln";
            case LOG:
                return "log";
            default:
                return null;
        }
    }

    public static boolean isBinaryFunction(OPERATOR operator) {
        switch (operator) {
            case ADD:
            case SUB:
            case MUL:
            case DIV:
            case MOD:
            case POW:
                return true;
            default:
                return false;
        }
    }

    public static boolean isPrefixFunction(OPERATOR operator) {
        switch (operator) {
            case COS:
            case SIN:
            case TAN:
            case ACOS:
            case ASIN:
            case ATAN:
            case SQRT:
            case LN:
            case LOG:
                return true;
            default:
                return false;
        }
    }

    public static int getDefaultPriority(OPERATOR operator) {
        switch (operator) {
            case ADD:
            case SUB:
                return 1;
            case MUL:
            case DIV:
            case MOD:
                return 2;
            case POW:
                return 3;
            case OP_PAREN:
            case CL_PAREN:
                return 4;
            default:
                return 0;
        }
    }
}
