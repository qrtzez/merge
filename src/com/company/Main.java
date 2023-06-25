package com.company;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Kirill kirill1 = new Kirill(1, 202305, 1, null, 0);
        Kirill kirill2 = new Kirill(2, 202304, 2, null, 0);
        Kirill kirill3 = new Kirill(3, 202303, 1, null, 0);
        Kirill kirill4 = new Kirill(4, 202302, 1, null, 0);
        Kirill kirill5 = new Kirill(5, 202301, 1, 1000, 0);
        Kirill kirill6 = new Kirill(6, 202305, 2, null, 0);


        Kirill kirillOnec1 = new Kirill(5, 202305, 1, 50, 1);
        Kirill kirillOnec2 = new Kirill(6, 202304, 1, 10, 1);
        Kirill kirillOnec3 = new Kirill(7, 202303, 1, 300, 1);
        Kirill kirillOnec4 = new Kirill(8, 202302, 1, 40, 1);
        Kirill kirillOnec5 = new Kirill(9, 202212, 1, 25, 1);
        Kirill kirillOnec6 = new Kirill(10, 202211, 1, 90, 1);
        Kirill kirillOnec7 = new Kirill(11, 202305, 2, 60, 1);
        Kirill kirillOnec8 = new Kirill(12, 202301, 1, 15, 1);


        List<Kirill> kirillsManual = new ArrayList<>();
        kirillsManual.add(kirill1);
        kirillsManual.add(kirill2);
        kirillsManual.add(kirill3);
        kirillsManual.add(kirill4);
        kirillsManual.add(kirill5);
        kirillsManual.add(kirill6);

        List<Kirill> kirillsOnec = new ArrayList<>();
        kirillsOnec.add(kirillOnec1);
        kirillsOnec.add(kirillOnec2);
        kirillsOnec.add(kirillOnec3);
        kirillsOnec.add(kirillOnec4);
        kirillsOnec.add(kirillOnec5);
        kirillsOnec.add(kirillOnec6);
        kirillsOnec.add(kirillOnec7);
        kirillsOnec.add(kirillOnec8);


        Map<Integer, List<Kirill>> collectManual = kirillsManual.stream()
                .collect(Collectors.groupingBy(Kirill::getDate));

        Map<Integer, List<Kirill>> collectOnec = kirillsOnec.stream()
                .collect(Collectors.groupingBy(Kirill::getDate));

        Set<Integer> collectDate = Stream.concat(collectManual.keySet().stream(), collectOnec.keySet().stream())
                .collect(Collectors.toSet());


       Map<Integer, List<Kirill>> mergedResult = new HashMap<>();
        Map<Integer, Kirill> result = new HashMap<>();

        collectDate.forEach(date -> {
            List<Kirill> dateManual = collectManual.get(date);
            List<Kirill> dateOnec = collectOnec.get(date);

            //дописать условие, если dateManual != null && dateOnec != null, но в обоих списках нет одинаковых ресторанов по id_rest
            //т.е. нет пар ресторанов из ручного и автоматического ввода

            if (dateManual != null && dateOnec != null) {
                Stream.concat(dateManual.stream(), dateOnec.stream())
                        .collect(Collectors.collectingAndThen(
                                Collectors.toMap(
                                        Kirill::getRestId,
                                        Function.identity(),
                                        Kirill::merge),
                                m -> mergedResult.put(date, m.values().stream()
                                        .filter(kirill -> kirill.getSource() == 0)
                                        .collect(Collectors.toList()))));
            }

            if (dateManual != null && dateOnec == null) {
                mergedResult.put(date, dateManual);
            }

            if (dateManual == null && dateOnec != null) {
                mergedResult.put(date, dateOnec);
            }

        });


        for(Map.Entry<Integer, List<Kirill>> kirill: mergedResult.entrySet()) {
            kirill.getValue()
                    .forEach(dto -> {
                        Integer sumMoney = kirill.getValue().stream()
                                .filter(element -> element.getMoney() != null)
                                .reduce(0, (sum, element) -> sum + element.getMoney(), Integer::sum);
                        result.put(kirill.getKey(), new Kirill(null, null, dto.getRestId(), sumMoney, null));
                    });


        }

        for(Map.Entry<Integer, Kirill> kirill: result.entrySet()) {
            System.out.println(kirill.toString());
        }



        /*for (Kirill kirill : kirills) {
            Field[] declaredFields = kirill.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                boolean isNull = false;
                try {
                    field.setAccessible(true);
                    isNull = field.get(kirill) == null;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (isNull) {
                    field.setAccessible(true);
                    try {
                        field.set(kirill, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

            System.out.println(kirill1.toString());
        }*/
    }
}
