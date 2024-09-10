import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение (например: \"слово 1\" + \"слово 2\"");
        String input = scanner.nextLine().trim();

        try {
            String result = evaluateExpression(input);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static String evaluateExpression(String input) {
        String[] parts = input.split(" ");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Выражение должно содержать три части: 'строка операция строка/число'.");
        }

        String firstString = parts[0].replaceAll("\"", ""); // Убираем кавычки
        String operation = parts[1];
        String secondPart = parts[2];

        if (firstString.length() > 10) {
            throw new IllegalArgumentException("Длина первой строки не может превышать 10 символов.");
        }

        StringBuilder result = new StringBuilder();

        switch (operation) {
            case "+":
                result.append(firstString).append(secondPart.replaceAll("\"", ""));
                break;
            case "-":
                result.append(firstString.replace(secondPart.replaceAll("\"", ""), ""));
                break;
            case "*":
                int multiplier = parsePositiveInt(secondPart);
                result.append(firstString.repeat(multiplier));
                break;
            case "/":
                int divider = parsePositiveInt(secondPart);
                if (divider < 1) {
                    throw new IllegalArgumentException("Делитель должен быть >= 1.");
                }
                int newLength = firstString.length() / divider;
                result.append(firstString.substring(0, newLength));
                break;
            default:
                throw new IllegalArgumentException("Недопустимая операция: " + operation);
        }

        String finalResult = result.toString();
        if (finalResult.length() > 40) {
            finalResult = finalResult.substring(0, 40) + "...";
        }

        return "\"" + finalResult + "\"";
    }

    private static int parsePositiveInt(String str) {
        try {
            int number = Integer.parseInt(str);
            if (number < 1 || number > 10) {
                throw new IllegalArgumentException("Число должно быть от 1 до 10.");
            }
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Чимло должно ыбть от 1 до 10.");
        }
    }
}
