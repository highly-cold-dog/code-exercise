package juc.thread.countdouwnlatch_demo;

/**
 * 枚举类型的使用案例
 * 枚举不是key_value  类似数据库
 *
 * @author dlf
 * @date 2021/4/8 22:32
 */
public enum CountryEnum {

    ONE(1, "齐"),
    TWO(2, "楚"),
    THREE(3, "燕"),
    FOUR(4, "赵"),
    FIVE(5, "魏"),
    SIX(6, "韩");

    private Integer reCode;
    private String reMessage;

    CountryEnum(Integer reCode, String reMessage) {
        this.reCode = reCode;
        this.reMessage = reMessage;
    }

    public Integer getReCode() {
        return reCode;
    }

    public void setReCode(Integer reCode) {
        this.reCode = reCode;
    }

    public String getReMessage() {
        return reMessage;
    }

    public void setReMessage(String reMessage) {
        this.reMessage = reMessage;
    }

    public static CountryEnum foreach_countryEnum(int index) {
        CountryEnum[] countryEnumsArray = CountryEnum.values();
        for (CountryEnum element : countryEnumsArray) {
            // 找得到就返回对应的    element
            if (index == element.getReCode()) {
                return element;
            }
        }
        return null;
    }
}
