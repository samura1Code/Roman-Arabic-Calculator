public enum RomanValues {
    // Определение римских чисел и их значений
    M(1000), CM(900), D(500), CD(400), C(100), XC(90), L(50), XL(40),
    X(10), IX(9), V(5), IV(4), I(1);

    private final int value;

    RomanValues(int value) {
        this.value = value;
    }
    // Метод получения числа
    public int getValue() {
        return value;
    }
    // Метод для преобразования римского числа в арабское
    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;
// Получение всех значений римских чисел
        RomanValues[] romanNumerals = RomanValues.values();

        int i = 0;

        while ((!romanNumeral.isEmpty()) && (i < romanNumerals.length)) {
            RomanValues symbol = romanNumerals[i];
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }
        if (!romanNumeral.isEmpty()) {
            throw new IllegalArgumentException(input + " не может быть преобразовано в римское число");
        }
        return result;
    }
    // Метод для преобразования арабского числа в римское
    public static String arabicToRoman(int result) {
        if ((result <= 0) || (result > 4000)) {
            throw new IllegalArgumentException("Римские числа могут быть только положительные!");
        }

        RomanValues[] romanNumerals = RomanValues.values();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((result > 0) && (i < romanNumerals.length)) {
            RomanValues currentSymbol = romanNumerals[i];
            if (currentSymbol.getValue() <= result) {
                sb.append(currentSymbol.name());
                result -= currentSymbol.getValue();
            } else {
                i++;
            }
        }
        return sb.toString();
    }
}