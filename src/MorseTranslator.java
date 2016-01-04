import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

public class MorseTranslator {

	private Character[] romanArray = new Character[26];
	private final String[] morseArray = {
			".-", "-...", "-.-.", "-..", ".",
			"..-.", "--.", "....", "..", ".---",
			"-.-", ".-..", "--", "-.", "---",
			".--.", "--.-", ".-.", "...", "-",
			"..-", "...-", ".--", "-..-", "-.--", "--.."};


	private HashMap<String, Character> morseToRoman = new HashMap<>(26, 1);
	private HashMap<Character, String> romanToMorse = new HashMap<>(26, 1);

	public MorseTranslator(){
		for (int i = 0; i < 26; ++i) {
			//char charotAdd = (char) i+26;
			romanArray[i] = new Character((char) (i+65));
			morseToRoman.put(morseArray[i], romanArray[i]);
			romanToMorse.put(romanArray[i], morseArray[i]);
		}
	}

	public char toRoman(String morseSymbol){
		return morseToRoman.containsKey(morseSymbol) ? morseToRoman.get(morseSymbol).charValue() : '?';
	}
	public String toMorse(char romanSymbol){
		Character toRetrieve = new Character(romanSymbol);
		return romanToMorse.containsKey((toRetrieve)) ? romanToMorse.get(toRetrieve) : "?";
	}

}
