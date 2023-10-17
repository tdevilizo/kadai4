package org.example;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main3 {
    public static void main(String[] args) {

        Map<String, LocalDate> broadcastDateMap = new HashMap<>();
        broadcastDateMap.put("ウルトラマンネクサス", LocalDate.of(2004, 10, 2));
        broadcastDateMap.put("ウルトラマンネクサス", LocalDate.of(2104, 10, 2));
        broadcastDateMap.put("ウルトラマンZ", LocalDate.of(2020, 6, 20));
        broadcastDateMap.put("ウルトラマン", LocalDate.of(1966, 7, 17));
        broadcastDateMap.put("ウルトラマン80", LocalDate.of(1980, 4, 2));
        broadcastDateMap.put("ウルトラマンティガ", LocalDate.of(1996, 9, 7));

        Map<String, LocalDate> sortedbroadcastDate = broadcastDateMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())// マップのValueの[値]を比べてソートする。日付のためこれを使用
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                       (a1, a2) -> a1, // 重複の場合、元のエントリを保持(a"1"だから)
                       LinkedHashMap::new // 入れ替えた順番を維持する為LinkedHashMap
                ));
        System.out.println("作品公開順");
        sortedbroadcastDate.forEach((key, value) ->{
            System.out.println(key + ": 初回放送日" + value);//sortedbroadcastDateのkeyとvalueを最初から順にすべて取り出す
        });
        };
    }
