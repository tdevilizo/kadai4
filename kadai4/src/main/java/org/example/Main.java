package org.example;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Map<String, LocalDate> broadcastDateMap = new HashMap<>();
        broadcastDateMap.put("ウルトラマンネクサス", LocalDate.of(2004, 10, 2));
        broadcastDateMap.put("ウルトラマンネクサス", LocalDate.of(2014, 10, 2));//ダミー
        broadcastDateMap.put("ウルトラマンZ", LocalDate.of(2020, 6, 20));
        broadcastDateMap.put("ウルトラマン", LocalDate.of(1966, 7, 17));
        broadcastDateMap.put("ウルトラマン80", LocalDate.of(1980, 4, 2));
        broadcastDateMap.put("ウルトラマンティガ", LocalDate.of(1996, 9, 7));
        broadcastDateMap.put("ウルトラマンタロウ", LocalDate.of(1973, 4, 3));

        LocalDate firstShowaDate = LocalDate.of(1926, 12, 25);
        LocalDate firstHeiseiDate = LocalDate.of(1989, 1, 8);
        LocalDate firstReiwaDate = LocalDate.of(2019, 5, 1);

        // 昭和の日程のみフィルタ
        Map<String, LocalDate> showaBroadcastDate = broadcastDateMap
                .entrySet()//Map内のエントリー（キーとバリューの両方）を取得
                .stream()
                .sorted(Map.Entry.comparingByValue())// マップのバリューの[値]を比べてソートする。日付のためこれを使用
                .filter(entry -> entry.getValue().isBefore(firstHeiseiDate))// 平成の初日より前の日にち(大正の作品はないので、平成ほど細かくしていない )
                .collect(Collectors.toMap(
                        Map.Entry::getKey,//entry -> entry.getKey() ←ラムダ式だとこう書く
                        Map.Entry::getValue,//entry -> entry.getValue(),←ラムダ式だとこう書く
                       (a1, a2) -> a1, // 重複の場合、元のエントリを保持(a"1"だから)
                       LinkedHashMap::new // 入れ替えた順番を維持する為LinkedHashMap
               ));
        // 平成の日程のみフィルター
        Map<String, LocalDate> heiseiBroadcastDate = broadcastDateMap
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .filter(entry -> entry.getValue().isBefore(firstReiwaDate)&&entry.getValue().isAfter(firstHeiseiDate)||entry.getValue().isEqual(firstHeiseiDate))// 令和の初日より前の日付かつ平成の初日より後、もしくは平成の初日の日にち
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a1, a2) -> a1,
                        LinkedHashMap::new
                ));
        // 令和の日程のみフィルター
        Map<String, LocalDate> reiwaBroadcastDate = broadcastDateMap
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .filter(entry -> entry.getValue().isAfter(firstReiwaDate)||entry.getValue().isEqual(firstReiwaDate))// 令和の初日から後の日付、もしくは令和の初日の日にち
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a1, a2) -> a1,
                        LinkedHashMap::new
                ));
        
        System.out.println("作品公開順");
        showaBroadcastDate.forEach((key, value) ->{
            System.out.println(key + ": 初回放送日:昭和" + (value.getYear()-firstShowaDate.getYear()+1)+"年"+ value.getMonthValue()+"月"+ value.getDayOfMonth()+"日");//showaBroadcastDateのkeyとvalueを最初から順にすべて取り出す
        });
        heiseiBroadcastDate.forEach((key, value) ->{
            System.out.println(key + ": 初回放送日:平成"+ (value.getYear()-firstHeiseiDate.getYear()+1)+"年"+ value.getMonthValue()+"月"+ value.getDayOfMonth()+"日");//heiseiBroadcastDateのkeyとvalueを最初から順にすべて取り出す
        });
        reiwaBroadcastDate.forEach((key, value) ->{
            System.out.println(key + ": 初回放送日:令和"+ (value.getYear()-firstReiwaDate.getYear()+1)+"年"+ value.getMonthValue()+"月"+ value.getDayOfMonth()+"日");//reiwaBroadcastDateのkeyとvalueを最初から順にすべて取り出す
        });
        };
    }
