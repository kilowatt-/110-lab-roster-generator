package model;

import java.util.*;

public class CSV {
    private Map<String, Set<String>> uniqueValues;
    private List<Row> rows;

    public CSV() {
        uniqueValues = new HashMap<>();
        rows = new ArrayList<>();
    }

    public void addHeader(String s) {
        if (!uniqueValues.containsKey(s))
            uniqueValues.put(s, new HashSet<>());
    }

    public void addValue(String header, String value) {
        Row r = new Row();

        r.insertRow(header, value);

        rows.add(r);

        Set<String> uniqueStrings = uniqueValues.get(header);

        if (!uniqueStrings.contains(value))
            uniqueStrings.add(value);
    }


    private class Row {
        Map<String, String> pairs;

        public Row() {
            pairs = new HashMap<>();
        }

        public void insertRow(String header, String value) {
            pairs.put(header, value);
        }

        public void deleteRow(String header) {
            pairs.remove(header);
        }

        public Set<String> getHeaders() {
            return pairs.keySet();
        }

        public String getValue(String value) {
            return pairs.get(value);
        }


    }
}
