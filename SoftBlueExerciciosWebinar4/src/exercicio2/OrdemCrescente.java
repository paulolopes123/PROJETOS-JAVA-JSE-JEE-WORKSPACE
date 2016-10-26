package exercicio2;

import java.util.Set;
import java.util.TreeSet;

public class OrdemCrescente {

	public static void main(String[] args) {

		Set<String> set = new TreeSet<>(new StringComparator());
		set.add("Java");
		set.add("PHP");
		set.add("C++");
		set.add("C");
		set.add("C#");
		set.add("JavaScript");

		for (String item : set) {
			System.out.println(item);

		}
	}
}
