package com.company;


import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        Kirill kirill1 = new Kirill(1, 202305, 1, 100, 0, "asdfasdf");
        Kirill kirill2 = new Kirill(2, 202304, 2, 5000, 0, "asdf");
        Kirill kirill3 = new Kirill(3, 202303, 1, 700, 0, "124d");
        Kirill kirill4 = new Kirill(4, 202302, 1, 40, 0, "asdf");
        Kirill kirill5 = new Kirill(5, 202212, 3, 100, 0, "bdfsg");
        Kirill kirill6 = new Kirill(6, 202211, 2, null, 0, "345dfa");
        Kirill kirill7 = new Kirill(7, 202305, 2, 15, 0, "adsfg903854");
        Kirill kirill18 = new Kirill(8, 202301, 1, 100, 0, "sdfghhsdf");


        Kirill kirillOnec1 = new Kirill(9, 202305, 1, 50, 1, "34faf");
        Kirill kirillOnec2 = new Kirill(10, 202304, 1, 10, 1, "sdgf3902");
        Kirill kirillOnec3 = new Kirill(11, 202303, 1, 300, 1, "asdfkl;9");
        Kirill kirillOnec4 = new Kirill(12, 202302, 1, 40, 1, "asdfkj893");
        Kirill kirillOnec5 = new Kirill(13, 202212, 1, 25, 1, "opfgdhi990");
        Kirill kirillOnec6 = new Kirill(14, 202211, 1, null, 1, "sdioap9");
        Kirill kirillOnec7 = new Kirill(15, 202305, 2, 60, 1, "dasofi90");
        Kirill kirillOnec8 = new Kirill(16, 202301, 1, 15, 1, "asd90f8");


        Kirill kirillIIKO1 = new Kirill(17, 202305, 1, 50, 2, "zzzz");
        Kirill kirillIIKO2 = new Kirill(18, 202304, 1, 10, 2, "zzz");
        Kirill kirillIIKO3 = new Kirill(19, 202303, 1, 300, 2, "z");
        Kirill kirillIIKO4 = new Kirill(20, 202302, 1, 40, 2, "zz");
        Kirill kirillIIKO5 = new Kirill(21, 202212, 1, 25, 2, "zzzzzz");
        Kirill kirillIIKO6 = new Kirill(22, 202211, 1, 11, 2, "zzz");
        Kirill kirillIIKO7 = new Kirill(23, 202305, 2, 60, 2, "zzzzz");
        Kirill kirillIIKO8 = new Kirill(24, 202301, 1, 15, 2, "zzzzzzzz");


        List<Kirill> kirillsManual = new ArrayList<>();
        kirillsManual.add(kirill1);
        kirillsManual.add(kirill2);
        kirillsManual.add(kirill3);
        kirillsManual.add(kirill4);
        kirillsManual.add(kirill5);
        kirillsManual.add(kirill6);
        kirillsManual.add(kirill7);
        kirillsManual.add(kirill18);


        List<Kirill> kirillsOnec = new ArrayList<>();
        kirillsOnec.add(kirillOnec1);
        kirillsOnec.add(kirillOnec2);
        kirillsOnec.add(kirillOnec3);
        kirillsOnec.add(kirillOnec4);
        kirillsOnec.add(kirillOnec5);
        kirillsOnec.add(kirillOnec6);
        kirillsOnec.add(kirillOnec7);
        kirillsOnec.add(kirillOnec8);

        List<Kirill> kirillsIIKOs = new ArrayList<>();
        kirillsIIKOs.add(kirillIIKO1);
        kirillsIIKOs.add(kirillIIKO2);
        kirillsIIKOs.add(kirillIIKO3);
        kirillsIIKOs.add(kirillIIKO4);
        kirillsIIKOs.add(kirillIIKO5);
        kirillsIIKOs.add(kirillIIKO6);
        kirillsIIKOs.add(kirillIIKO7);
        kirillsIIKOs.add(kirillIIKO8);


        Map<Integer, List<Kirill>> collectManual = kirillsManual.stream()
                .collect(Collectors.groupingBy(Kirill::getDate));

        Map<Integer, List<Kirill>> collectOnec = kirillsOnec.stream()
                .collect(Collectors.groupingBy(Kirill::getDate));

        Map<Integer, List<Kirill>> collectIIKO = kirillsIIKOs.stream()
                .collect(groupingBy(Kirill::getDate));

        Set<Integer> collectDate = Stream.concat(Stream.concat(collectManual.keySet().stream(), collectOnec.keySet().stream()), collectIIKO.keySet().stream())
                .collect(Collectors.toSet());


       Map<Integer, Collection<Kirill>> mergedResult = new HashMap<>();
        Map<Integer, Kirill> result = new HashMap<>();

        collectDate.forEach(date -> {
            List<Kirill> dateManual = collectManual.get(date);
            List<Kirill> dateOnec = collectOnec.get(date);
            List<Kirill> dateIIKO = collectIIKO.get(date);

            //дописать условие, если dateManual != null && dateOnec != null, но в обоих списках нет одинаковых ресторанов по id_rest
        //т.е. нет пар ресторанов из ручного и автоматического ввода
            List<Kirill> collectAll = Stream.concat(Stream.concat(dateManual.stream(), dateOnec.stream()), dateIIKO.stream()).collect(toList());

           collectAll.stream()
                        .collect(Collectors.collectingAndThen(
                                Collectors.toMap(
                                        Kirill::getRestId,
                                        Function.identity(),
                                        Kirill::merge),
                                m -> mergedResult.put(date, m.values())));
        });


        for(Map.Entry<Integer, Collection<Kirill>> kirill: mergedResult.entrySet()) {
            kirill.getValue()
                    .forEach(dto -> {
                        Integer sumMoney = kirill.getValue().stream()
                                .filter(element -> element.getMoney() != null)
                                .reduce(0, (sum, element) -> sum + element.getMoney(), Integer::sum);
                        Kirill kirillBuild = new Kirill(null, dto.getDate(), dto.getRestId(), sumMoney, dto.getSource());
                        result.put(kirill.getKey(), kirillBuild);
                    });


        }

        for(Map.Entry<Integer, Kirill> kirill: result.entrySet()) {
            System.out.println(kirill.toString());
        }



        List<Discounter> discounters = List.of(
                Discounter.christmasDiscounter(),
                Discounter.easterDiscounter(),
                Discounter.newYearDiscounter());


        Discounter reduce = discounters.stream()
                .reduce(v -> v, Discounter::combine);
        BigDecimal apply = reduce.apply(BigDecimal.valueOf(100));

        BiFunction<Integer, Integer, Integer> integerFunction = Integer::sum;







        System.out.println(result);
    }

    //ТУТ МЕТОДЫ

    public static Set<String> hasRightToDataItem(String... rights) {
        int  a = 4;
        while ( a --> 0) {

        }

        Set<String> items = Arrays.stream(rights)
                .flatMap(item -> Stream.of(item + "_VIEW", item + "_EDIT"))
                .collect(Collectors.toSet());
        return items;
    }



    static CompletableFuture<Double> getFinalSum(Integer money) {
        return CompletableFuture.supplyAsync(() -> money * 2.5D)
                .thenApply(coin -> {
                    System.out.println("Итог: " + coin);
                    return coin;
                });
    }

    public static long listIteration(List<Integer> list) {
        long startDate = new Date().getTime();
        //List<Integer> list2 = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < 2000000000; i++) {
            sum += i;
        }
        System.out.println("sumList: " + sum);
        long finishDate = new Date().getTime();
        return finishDate - startDate;
    }

    public static long streamIteration(List<Integer> list) {
        long startDate = new Date().getTime();
        int sum = IntStream.range(0, 2000000000)
                .parallel()
                .sum();
        System.out.println("sumStream: " + sum);
        long finishDate = new Date().getTime();
        return finishDate - startDate;
    }

    private static void blockingOperation() {
        try {
            Thread.sleep(1000);
            System.out.println("lol");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
