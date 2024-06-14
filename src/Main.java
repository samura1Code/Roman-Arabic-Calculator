import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Создание объекта Scanner для чтения ввода с консоли
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение:");
        // Чтение введенного пользователем выражения
        String input = scanner.nextLine();
        try {
            // Вычисление результата и вывод его на консоль
            System.out.println("Ответ: " + calc(input));
        } catch (Exception e) {
            // Обработка исключений и вывод сообщения об ошибке
            System.out.println(e.getMessage());
        }
    }

    public static String calc(String input) throws Exception {
        // Удаление всех пробелов из введенного выражения
        String newInput = input.replaceAll(" ", "");
        // Разделение выражения на операнды
        String[] numbers = newInput.split("[-+*/]");
        // Проверка на наличие двух операндов и одного оператора
        if ((numbers.length != 2) || newInput.matches("[\\-+*/]")) {
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        String operandOneStr = numbers[0];
        String operandTwoStr = numbers[1];
        int operandOne = 0;
        int operandTwo = 0;
        char operator = newInput.charAt(operandOneStr.length());
        // Проверка, являются ли операнды римскими числами
        if((isRoman(operandOneStr) && isRoman(operandTwoStr))){
            operandOne = RomanValues.romanToArabic(operandOneStr);
            operandTwo = RomanValues.romanToArabic(operandTwoStr);
        }
        // Проверка, являются ли операнды арабскими числами
        else if((isArabic(operandOneStr) && isArabic(operandTwoStr))){
            operandOne = Integer.parseInt(operandOneStr);
            operandTwo = Integer.parseInt(operandTwoStr);
        }else{
            throw new Exception("Допускается ввод либо только римских цифр, либо только арабских!");
        }

        int result;
        // Проверка, находятся ли операнды в диапазоне от 0 до 10 включительно
        if (((operandOne > 0) && (operandTwo > 0)) && ((operandOne <= 10) && (operandTwo <= 10))) {
            switch (operator) {
                case '+':
                    result = operandOne + operandTwo;
                    break;
                case '-':
                    result = operandOne - operandTwo;
                    break;
                case '*':
                    result = operandOne * operandTwo;
                    break;
                case '/':
                    if (operandTwo == 0) {
                        throw new Exception("Деление на ноль!");
                    } else {
                        result = operandOne / operandTwo;
                    }
                    break;
                default:
                    throw new Exception("Некорректный оператор");
            }
        } else {
            throw new Exception("Числа должны быть в диапазоне от 1 до 10 включительно.");
        }
        // Если операнды римские, конвертировать результат обратно в римские цифры
        if(isRoman(operandTwoStr)) {
            return RomanValues.arabicToRoman(result);
        }
        // Возврат результата операций с арабскими цифрами
        return String.valueOf(result);
    }

    //Проверка, являются ли строка римским числом
    public static boolean isRoman(String numbers){
        return numbers.matches("^(I|II|III|IV|V|VI|VII|VIII|IX|X)$");
    }
    //Проверка, является ли строка арабским числом
    public static boolean isArabic(String numbers){
        return (numbers.matches("\\d+"));
    }
}
