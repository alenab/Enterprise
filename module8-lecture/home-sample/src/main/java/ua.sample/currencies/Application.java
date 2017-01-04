package ua.sample.currencies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Application {
    public static void main(String[] args) throws IOException {
        String jsonPro = getCurrencyJSONPro();
        JSONObject json = new JSONObject(jsonPro);
        getDate(json);

        getOrganizations(json).forEach(System.out :: println);
    }

    public static List<Organization> getOrganizations(JSONObject json) {
        List<Organization> organizationList = new ArrayList<>();
        JSONArray organizations = json.getJSONArray("organizations");
        for (int i = 0; i < organizations.length(); i++) {
            JSONObject object = organizations.getJSONObject(i);
            Organization organization = new Organization();
            organization.setTitle(object.getString("title"));

            City city = getCityById(json, object.getString("cityId"));
            organization.setCity(city);

            List<Currency> currenciesList = new ArrayList<>();
            JSONObject currencies = object.getJSONObject("currencies");
            Set<String> set = currencies.keySet();
            for (String item : set) {
                JSONObject jsonObject = currencies.getJSONObject(item);
                Currency currency = new Currency();
                currency.setShortName(item);
                currency.setName(getCurrencyName(json, item));
                currency.setValueAsk(jsonObject.getDouble("ask"));
                currency.setValueBid(jsonObject.getDouble("bid"));
                currenciesList.add(currency);
            }
            organization.setCurrencies(currenciesList);
            organizationList.add(organization);
        }
        return organizationList;
    }

    private static String getCurrencyName(JSONObject json, String shortName) {
        JSONObject currencies = json.getJSONObject("currencies");
        return currencies.getString(shortName);
    }

    private static String getDate(JSONObject json) {
        String pattern = "EEEE, d MMMM yyyy HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault());
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(json.getString("date"));
        String result = zonedDateTime.format(formatter);
        return result;
    }


    private static List<City> getCities(JSONObject json) {
        JSONObject cities = json.getJSONObject("cities");
        Set<String> keys = cities.keySet();
        List<City> result = new ArrayList<>();
        for (String key : keys) {
            City city = new City();
            city.setId(key);
            city.setName(cities.getString(key));
            result.add(city);

        }
        return result;
    }

    private static City getCityById(JSONObject object, String CityId) {
        List<City> cities = getCities(object);
        for (City city : cities) {
            if (city.getId().equals(CityId)) {
                return city;
            }
        }
        return null;
    }


    public static String getCurrencyJSONPro() throws IOException {
        URL url = new URL("http://resources.finance.ua/ru/public/currency-cash.json");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        StringBuilder builder = new StringBuilder();
        try (Scanner in = new Scanner(connection.getInputStream(), "UTF-8")) {
            while (in.hasNextLine()) {
                builder.append(in.nextLine());
                builder.append("\n");
            }
        }
        return builder.toString();
    }

}
