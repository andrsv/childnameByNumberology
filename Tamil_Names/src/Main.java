import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	static char[] acceptedFirstLetters = {'K'};
	static int[] acceptedNumbers = {1};

	public static void main(String[] args) {
		List<String> names = new ArrayList<String>();
		List<String> acceptedNames = new ArrayList<String>();
		List<String> filenames = new ArrayList<String>();
//		filenames.add("ForeignNames-BPRT.txt");
//		filenames.add("names_Selected-BIR.txt");
//		filenames.add("NorskeNavnBPRT.txt");
//		filenames.add("tamilNamesPopular2014-BPRT.txt");
//		filenames.add("tamilGirl-K.txt");
//		filenames.add("tamilGirl-K1.txt");
//		filenames.add("tamilNamesByThusa-K.txt");
//		filenames.add("tamilNamesFormCheckAll-K.txt");
//		filenames.add("tamilNamesFormOnlyMyHealth-K.txt");
//		filenames.add("top1000babyNames2012US.txt");
//		filenames.add("top1000babyNames2013US.txt");
//		filenames.add("indianGirlNamesFromNriol-K.txt");
		filenames.add("hinduGirlNamesFromHindunames-K.txt");
		for (String filename : filenames) {
			File file = new File(filename);
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				while ((line = br.readLine()) != null) {
					if (!names.contains(line)) {
						names.add(line);
					}
				}
				br.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		for (String name : names) {
			int number = 0;
			for (int i = 0; i < name.length(); i++) {
				char ch = Character.toUpperCase(name.charAt(i));
				switch (ch) {
					case 'A':
					case 'I':
					case 'J':
					case 'Q':
					case 'Y':
						number += 1;
						break;
					case 'B':
					case 'K':
					case 'R':
						number += 2;
						break;
					case 'C':
					case 'G':
					case 'L':
					case 'S':
						number += 3;
						break;
					case 'D':
					case 'T':
					case 'M':
						number += 4;
						break;
					case 'E':
					case 'H':
					case 'N':
					case 'X':
						number += 5;
						break;
					case 'U':
					case 'V':
					case 'W':
						number += 6;
						break;
					case 'O':
					case 'Z':
						number += 7;
						break;
					case 'F':
					case 'P':
						number += 8;
						break;
					case '-':
						number += 9;
						break;
					case 'Æ':
					case 'Ø':
					case 'Å':
					case '.':
						number -= 10000;
						break;
				}
			}

			number = decreaseNumberToOneDigit(number);

			if ((hasAcceptedNumber(number)) && (hasAcceptedFirstLetter(name))) {
				acceptedNames.add(name);
				System.out.println("******* Name: " + name + ", Number: " + number); // Display the string.
			} else {
				System.out.println("Name: " + name + ", Number: " + number); // Display the string.
			}
		}
		java.util.Collections.sort(acceptedNames);
		int i = 0;
		System.out.println("Names checked: " + names.size() + ", names accepted: " + acceptedNames.size());
		for (String name : acceptedNames) {
			i++;
			System.out.println(/*i + ": " + */name);
		}
	}

	private static boolean hasAcceptedNumber(int number) {
		for (int acceptedNumber : acceptedNumbers) {
			if (acceptedNumber == number) {
				return true;
			}
		}
		return false;
	}

	private static boolean hasAcceptedFirstLetter(String name) {
		for (char acceptedFirstLetter : acceptedFirstLetters) {
			if (Character.toUpperCase(acceptedFirstLetter) == name.charAt(0)) {
				return true;
			}
		}
		return false;
	}

	private static int decreaseNumberToOneDigit(int number) {
		while (number >= 100) {
//				System.out.println("Number greater than 100: " + number);
			number = number / 100 + (number % 100) / 10 + number % 10;
		}
		while (number >= 10) {
//				System.out.println("Number greater than 10: " + number);
			number = (number % 100) / 10 + number % 10;
		}
		return number;
	}
}
