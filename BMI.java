import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Weight (kg): ");
		double weightKg = input.nextDouble();
		System.out.print("Height (m): ");
		double heightMeters = input.nextDouble();

		if (weightKg <= 0 || heightMeters <= 0) {
			System.out.println("Weight and height must be greater than zero.");
		} else {
			double bmi = weightKg / (heightMeters * heightMeters);
			System.out.printf("BMI: %.1f%n", bmi);

			if (bmi < 18.5) {
				System.out.println("Category: Underweight");
			} else if (bmi < 25.0) {
				System.out.println("Category: Normal weight");
			} else if (bmi < 30.0) {
				System.out.println("Category: Overweight");
			} else {
				System.out.println("Category: Obesity");
			}
		}

		input.close();
	}
}
