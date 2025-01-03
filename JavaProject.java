import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter today's date (use format YYYY-MM-DD):");
        String todayInput = scanner.nextLine();

        System.out.println("Choose your date format: 1 for Indian (DD-MM-YYYY), 2 for American (MM-DD-YYYY), 3 for International (YYYY-MM-DD):");
        int dateFormatOption = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Choose your option: 1 for DOB or 2 for AGE:");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter the date or age as per option selected ");
        String input = scanner.nextLine();

        String[] todayParts = todayInput.split("-");
        String[] inputParts = input.split("-");

        if (todayParts.length != 3 || inputParts.length != 3) {
            System.out.println("Invalid date format provided.");
            return;
        }

        int todayYear = Integer.parseInt(todayParts[0]);
        int todayMonth = Integer.parseInt(todayParts[1]);
        if (todayMonth > 12) {
            System.out.println("Enter a proper month in today's date.");
            return;
        }
        int todayDay = Integer.parseInt(todayParts[2]);
        if (todayDay > getDaysInMonth(todayMonth - 1, todayYear)) {
            System.out.println("Enter a proper day in today's date.");
            return;
        }

        int inputYear = dateFormatOption == 3 ? Integer.parseInt(inputParts[0]) : Integer.parseInt(inputParts[2]);
        int inputMonth = dateFormatOption == 2 ? Integer.parseInt(inputParts[0]) : Integer.parseInt(inputParts[1]);
        int inputDay = dateFormatOption == 1 ? Integer.parseInt(inputParts[0]) : Integer.parseInt(inputParts[1]);

        if (inputMonth > 12) {
            System.out.println("Enter a proper month in the input date.");
            return;
        }
        if (inputDay > getDaysInMonth(inputMonth - 1, inputYear)) {
            System.out.println("Enter a proper day in the input date.");
            return;
        }

        if (option == 2) { // Calculate Age
            int years = todayYear - inputYear;
            int months = todayMonth - inputMonth;
            int days = todayDay - inputDay;

            if (days < 0) {
                months--;
                days += getDaysInMonth(todayMonth - 1 == 0 ? 11 : todayMonth - 1 - 1, todayMonth - 1 == 0 ? todayYear - 1 : todayYear);
            }

            if (months < 0) {
                years--;
                months += 12;
            }

            System.out.println("Age: " + years + " Years, " + months + " Months, " + days + " Days");
        } else if (option == 1) { // Calculate DOB
            int years = todayYear - inputYear;
            int months = todayMonth;
            int days = todayDay;

            System.out.printf("Date of Birth: %02d-%02d-%04d\n", days, months, inputYear);
        } else {
            System.out.println("Invalid option");
        }

        scanner.close();
    }

    private static int getDaysInMonth(int month, int year) {
        int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (month == 1 && isLeapYear(year)) {
            return 29;
        }
        return daysInMonths[month];
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
