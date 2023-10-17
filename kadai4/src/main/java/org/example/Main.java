package org.example;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Map<String, LocalDate> birthdayMap = new HashMap<>();
        birthdayMap.put("大空大地", LocalDate.of(2015, 7, 14));
        birthdayMap.put("大空大地", LocalDate.of(2025, 7, 14));//ダミー
        birthdayMap.put("弧門一輝", LocalDate.of(2004, 10, 2));
        birthdayMap.put("令和一", LocalDate.of(2019, 5, 1));
        birthdayMap.put("早田進", LocalDate.of(1966, 7, 17));
        birthdayMap.put("平成尾張", LocalDate.of(2019, 4, 30));
        birthdayMap.put("マドカダイゴ", LocalDate.of(1996, 9, 7));
        birthdayMap.put("東光太郎", LocalDate.of(1973, 4, 3));

        LocalDate firstShowaDate = LocalDate.of(1926, 12, 25);
        LocalDate firstHeiseiDate = LocalDate.of(1989, 1, 8);
        LocalDate firstReiwaDate = LocalDate.of(2019, 5, 1);

        // 昭和の日程のみフィルタ
        Map<String, LocalDate> showaBirthday = birthdayMap
                .entrySet()//Map内のエントリー（キーとバリューの両方）を取得
                .stream()
                .sorted(Map.Entry.comparingByValue())// マップのバリューの[値]を比べてソートする。日付のためこれを使用
                .filter(entry -> entry.getValue().isBefore(firstHeiseiDate)&&entry.getValue().isAfter(firstShowaDate)||entry.getValue().isEqual(firstShowaDate))// 平成の初日より前の日付かつ昭和の初日より後、もしくは昭和の初日の日にち
                .collect(Collectors.toMap(
                        Map.Entry::getKey,//entry -> entry.getKey() ←ラムダ式だとこう書く
                        Map.Entry::getValue,//entry -> entry.getValue(),←ラムダ式だとこう書く
                       (a1, a2) -> a1, // 重複の場合、元のエントリを保持(a"1"だから)
                       LinkedHashMap::new // 入れ替えた順番を維持する為LinkedHashMap
               ));
        // 平成の日程のみフィルター
        Map<String, LocalDate> heiseiBirthday = birthdayMap
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
        Map<String, LocalDate> reiwaBirthday = birthdayMap
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
        showaBirthday.forEach((key, value) ->{
            System.out.println(key + ": 誕生日:昭和" + (value.getYear()-firstShowaDate.getYear()+1)+"年"+ value.getMonthValue()+"月"+ value.getDayOfMonth()+"日");//showaBirthdayのkeyとvalueを最初から順にすべて取り出す
        });
        heiseiBirthday.forEach((key, value) ->{
            System.out.println(key + ": 誕生日:平成"+ (value.getYear()-firstHeiseiDate.getYear()+1)+"年"+ value.getMonthValue()+"月"+ value.getDayOfMonth()+"日");//heiseiBirthdayのkeyとvalueを最初から順にすべて取り出す
        });
        reiwaBirthday.forEach((key, value) ->{
            System.out.println(key + ": 誕生日:令和"+ (value.getYear()-firstReiwaDate.getYear()+1)+"年"+ value.getMonthValue()+"月"+ value.getDayOfMonth()+"日");//reiwaBirthdayのkeyとvalueを最初から順にすべて取り出す
        });
        }
    }
