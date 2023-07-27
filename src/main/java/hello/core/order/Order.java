package hello.core.order;

public class Order {
    private long memberId;
    private String itemName;
    private int itemPrice;
    private int dicountPrice;

    public Order(long memberId, String itemName, int itemPrice, int dicountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.dicountPrice = dicountPrice;
    }

    public int calculatePrice() {
        return itemPrice - dicountPrice;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getDicountPrice() {
        return dicountPrice;
    }

    public void setDicountPrice(int dicountPrice) {
        this.dicountPrice = dicountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", dicountPrice=" + dicountPrice +
                '}';
    }
}
