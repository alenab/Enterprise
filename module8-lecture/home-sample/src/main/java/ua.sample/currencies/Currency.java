package ua.sample.currencies;

public class Currency {

    private String shortName;
    private String name;
    private double valueAsk;
    private double valueBid;

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValueAsk() {
        return valueAsk;
    }

    public void setValueAsk(double valueAsk) {
        this.valueAsk = valueAsk;
    }

    public double getValueBid() {
        return valueBid;
    }

    public void setValueBid(double valueBid) {
        this.valueBid = valueBid;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "shortName='" + shortName + '\'' +
                ", name='" + name + '\'' +
                ", valueAsk=" + valueAsk +
                ", valueBid=" + valueBid +
                '}';
    }
}

