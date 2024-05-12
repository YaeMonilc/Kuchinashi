package ltd.bauhinia.enum

enum class KotlinTokens(
    val originName: String,
    val aliasName: String
) {
    AS("as", "作为"),
    BREAK("break", "跳出"),
    CLASS("class", "类"),
    CONTINUE("continue", "跳过"),
    DO("do", "任务"),
    ELSE("else", "否则"),
    FALSE("false", "假"),
    FOR("for", "计次循环"),
    FUN("fun", "函数"),
    IF("if", "如果"),
    IN("in", "逆变"),
    INTERFACE("interface", "接口"),
    IS("is", "是"),
    NULL("null", "空值"),
    OBJECT("object", "实例"),
    PACKAGE("package", "包名"),
    RETURN("return", "返回"),
    SUPER("super", "父类"),
    THIS("this", "本类"),
    THROW("throw", "抛出"),
    TRUE("true", "真"),
    TRY("try", "尝试"),
    TYPEALIAS("typealias", "别名"),
    TYPEOF("typeof", "获取类型"),
    VAL("val", "值"),
    VAR("var", "变量"),
    WHEN("when", "判断"),
    WHILE("while", "循环"),
    //SOFT
    BY("by", "委托于"),
    CATCH("catch", "捕获"),
    CONSTRUCTOR("constructor", "构造器"),
    FINALLY("finally", "结束"),
    GET("get", "获取"),
    IMPORT("import", "导入"),
    INIT("init", "初始化"),
    SET("set", "设置"),
    WHERE("where", "约束"),
    //MODIFIER
    ABSTRACT("abstract", "抽象"),
    ANNOTATION("annotation", "注解"),
    COMPANION("companion", "伴生"),
    CONST("const", "常量"),
    CROSSINLINE("crossinline", "交叉内联"),
    DATA("data", "数据"),
    ENUM("enum", "枚举"),
    EXTERNAL("external", "外部"),
    FINAL("final", "最终"),
    INFIX("infix", "中缀"),
    INLINE("inline", "内联"),
    INNER("inner", "内部"),
    INTERNAL("internal", "模块可见"),
    LATEINIT("lateinit", "暂不初始化"),
    NOINLINE("noinline", "不内联"),
    OPEN("open", "可继承"),
    OPERATOR("operator", "关键字"),
    OUT("out", "协变"),
    OVERRIDE("override", "重写"),
    PRIVATE("private", "私有"),
    PROTECTED("protected", "隐藏"),
    PUBLIC("public", "公开"),
    REIFIED("reified", "擦除"),
    SEALED("sealed", "密封"),
    SUSPEND("suspend", "挂起"),
    TAILREC("tailrec", "递归优化"),
    VARARG("vararg", "多参数"),
    FIELD("field", "字段"),
    IT("it", "其");

    companion object {
        fun getByOriginName(originName: String): KotlinTokens? {
            return entries.firstOrNull {
                it.originName == originName
            }
        }

        fun getByAliasName(aliasName: String): KotlinTokens? {
            return entries.firstOrNull {
                it.aliasName == aliasName
            }
        }
    }
}