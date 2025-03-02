import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Путь к файлу по умолчанию
        String defaultFilePath = "E:\\projekt GUI installer\\laba1\\src\\data.txt";
        List<Double> numbers = readNumbersFromFile(defaultFilePath);

        // Если файл по умолчанию не найден, завершить программу
        if (numbers == null) {
            System.out.println("Файл не найден ");
            return;
        }

        System.out.println("Массив: " + numbers);

        System.out.println("Сумма элементов: " + sum(numbers));
        System.out.println("Среднее значение: " + average(numbers));
        System.out.println("Максимальный элемент: " + max(numbers));
        System.out.println("Минимальный элемент: " + min(numbers));
        System.out.println("Дисперсия: " + variance(numbers));

        List<Double> sortedNumbers = quickSort(new ArrayList<>(numbers));
        System.out.println("Отсортированный массив: " + sortedNumbers);
    }

    private static List<Double> readNumbersFromFile(String filePath) {
        List<Double> numbers = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] tokens = line.split(",");
                for (String token : tokens) {
                    // Удаляем пробелы и проверяем, не пустая ли строка
                    token = token.trim();
                    if (!token.isEmpty()) {
                        try {
                            numbers.add(Double.parseDouble(token));
                        } catch (NumberFormatException e) {
                            System.out.println("Ошибка формата числа: " + token);
                            return null;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // Сообщение об ошибке убрано
            return null;
        }
        return numbers;
    }

    private static double sum(List<Double> numbers) {
        double sum = 0;
        for (double num : numbers) {
            sum += num;
        }
        return sum;
    }

    private static double average(List<Double> numbers) {
        return sum(numbers) / numbers.size();
    }

    private static double max(List<Double> numbers) {
        double max = numbers.get(0);
        for (double num : numbers) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    private static double min(List<Double> numbers) {
        double min = numbers.get(0);
        for (double num : numbers) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    private static double variance(List<Double> numbers) {
        double mean = average(numbers);
        double temp = 0;
        for (double num : numbers) {
            temp += (num - mean) * (num - mean);
        }
        return temp / numbers.size();
    }

    private static List<Double> quickSort(List<Double> numbers) {
        if (numbers.size() <= 1) {
            return numbers;
        }
        double pivot = numbers.get(numbers.size() / 2);
        List<Double> less = new ArrayList<>();
        List<Double> equal = new ArrayList<>();
        List<Double> greater = new ArrayList<>();

        for (double num : numbers) {
            if (num < pivot) {
                less.add(num);
            } else if (num > pivot) {
                greater.add(num);
            } else {
                equal.add(num);
            }
        }

        List<Double> sorted = new ArrayList<>(quickSort(less));
        sorted.addAll(equal);
        sorted.addAll(quickSort(greater));
        return sorted;
    }
}