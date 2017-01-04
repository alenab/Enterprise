package ua.sample.currencies;

import java.util.List;

public class Organization {

    private String title;
    private City city;
    private List<Currency> currencies;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "title='" + title + '\'' +
                ", city=" + city +
                ", currencies=" + currencies +
                '}';
    }
}
