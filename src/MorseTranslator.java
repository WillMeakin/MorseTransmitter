import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

public class MorseTranslator {

	private Character[] romanArray = new Character[41];
	private final String[] morseArray = {
			"-----", ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.",
			".-", "-...", "-.-.", "-..", ".",
			"..-.", "--.", "....", "..", ".---",
			"-.-", ".-..", "--", "-.", "---",
			".--.", "--.-", ".-.", "...", "-",
			"..-", "...-", ".--", "-..-", "-.--", "--..",
			".-.-.-", "--..--", "..--..", "-..-.", ".--.-."
	};


	private HashMap<String, Character> morseToRoman = new HashMap<>(36, 1);
	private HashMap<Character, String> romanToMorse = new HashMap<>(36, 1);

	public MorseTranslator(){

		for (int i = 0; i < 10; ++i){
			romanArray[i] = new Character((""+i).charAt(0));
			morseToRoman.put(morseArray[i], romanArray[i]);
			romanToMorse.put(romanArray[i], morseArray[i]);
		}


		for (int i = 10; i < 36; ++i) {
			romanArray[i] = new Character((char) (i+55));
			morseToRoman.put(morseArray[i], romanArray[i]);
			romanToMorse.put(romanArray[i], morseArray[i]);
		}

		romanArray[36] = new Character('.');
		morseToRoman.put(morseArray[36], romanArray[36]);
		romanToMorse.put(romanArray[36], morseArray[36]);

		romanArray[37] = new Character(',');
		morseToRoman.put(morseArray[37], romanArray[37]);
		romanToMorse.put(romanArray[37], morseArray[37]);

		romanArray[38] = new Character('?');
		morseToRoman.put(morseArray[38], romanArray[38]);
		romanToMorse.put(romanArray[38], morseArray[38]);

		romanArray[39] = new Character('/');
		morseToRoman.put(morseArray[39], romanArray[39]);
		romanToMorse.put(romanArray[39], morseArray[39]);

		romanArray[40] = new Character('@');
		morseToRoman.put(morseArray[40], romanArray[40]);
		romanToMorse.put(romanArray[40], morseArray[40]);
	}

	public char toRoman(String morseSymbol){
		return morseToRoman.containsKey(morseSymbol) ? morseToRoman.get(morseSymbol).charValue() : '!';
	}
	public String toMorse(char romanSymbol){
		Character toRetrieve = new Character(romanSymbol);
		return romanToMorse.containsKey((toRetrieve)) ? romanToMorse.get(toRetrieve) : "!";
	}

}
