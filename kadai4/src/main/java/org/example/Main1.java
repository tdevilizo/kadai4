import java.util.Arrays;
import java.util.List;

public class Main1 {
    public static void main(String[] args) {
        List<String> colors = Arrays.asList("red", "blue", "yellow", "pink", "green", "black", "skyblue", "winered");

        colors.stream().forEach(color -> System.out.println(color));//ラムダ式
//        colors.stream().forEach(System.out::println); メソッド参照だとこうなる

        List<String> result1 = colors
                .stream()
                .filter(color -> color.endsWith("red"))
                .toList();
        System.out.println("赤系統:" + result1);

        List<String> result2 = colors
                .stream()
                .filter(color -> color.length() >= 7)
                .toList();
        System.out.println("派生色:" + result2);
    }
}
