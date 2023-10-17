package org.example;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main4 {
    public static void main(String[] args) {

        Map<String, LocalDate> broadcastDate = new HashMap<>();
        broadcastDate.put("ウルトラマンネクサス", LocalDate.of(2104, 10, 2));
        broadcastDate.put("ウルトラマンネクサス", LocalDate.of(2004, 10, 2));
        broadcastDate.put("ウルトラマンZ", LocalDate.of(2020, 6, 20));
        broadcastDate.put("ウルトラマン", LocalDate.of(1966, 7, 17));
        broadcastDate.put("ウルトラマン80", LocalDate.of(1980, 4, 2));
        broadcastDate.put("ウルトラマンティガ", LocalDate.of(1996, 9, 7));

            LocalDate firstReiwaDate = LocalDate.of(2019, 5, 1);
            LocalDate firstShowaDate = LocalDate.of(1926, 12, 25);
            LocalDate firstHeiseiDate = LocalDate.of(1989, 1, 8);

            Stream<Map.Entry<String, LocalDate>> showabroadcastDate =  broadcastDate.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())// マップのValueの[値]を比べてソートする。日付のためこれを使用(使わないと出力の際abc..のソートの優先順で出る為)
                    .filter(entry ->entry.getValue().isBefore(firstHeiseiDate));// 平成の初日より前の日にち(大正の作品はないので、firstShowaDateは使わない)

            Stream<Map.Entry<String, LocalDate>> heiseibroadcastDate = broadcastDate.entrySet()
                   .stream()
                   .sorted(Map.Entry.comparingByValue())
                   .filter(entry ->entry.getValue().isBefore(firstReiwaDate)&&entry.getValue().isAfter(firstHeiseiDate)||entry.getValue().isEqual(firstHeiseiDate));// 令和の初日より前の日付かつ平成の初日より後、もしくは平成の初日の日にち

            Stream<Map.Entry<String, LocalDate>> reiwabroadcastDate = broadcastDate.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .filter(entry ->  entry.getValue().isEqual(firstReiwaDate)||entry.getValue().isAfter(firstReiwaDate));// 令和の初日から後の日付もしくは令和の初日の日にち

            showabroadcastDate.forEach(entry -> {
                System.out.println("作品名:" + entry.getKey()+ ", 初回放送日程:昭和" + (entry.getValue().getYear()-firstShowaDate.getYear()+1)+"年"+ entry.getValue().getMonthValue()+"月"+ entry.getValue().getDayOfMonth()+"日");
            });
            heiseibroadcastDate.forEach(entry -> {
                System.out.println("作品名:" + entry.getKey() + ", 初回放送日程:平成" + (entry.getValue().getYear()-firstHeiseiDate.getYear()+1)+"年"+ entry.getValue().getMonthValue()+"月"+ entry.getValue().getDayOfMonth()+"日");
            });
            reiwabroadcastDate.forEach(entry -> {
                System.out.println("作品名:" + entry.getKey() + ", 初回放送日程:令和" + (entry.getValue().getYear()-firstReiwaDate.getYear()+1)+"年"+ entry.getValue().getMonthValue()+"月"+ entry.getValue().getDayOfMonth()+"日");
            });
        };
    }
