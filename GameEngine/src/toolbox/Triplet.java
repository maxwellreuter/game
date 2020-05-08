package toolbox;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Triplet class
public class Triplet<U, V, T>
{
	public final U first;   	// first field of a Triplet
	public final V second;  	// second field of a Triplet
	public final T third;   	// third field of a Triplet

	// Constructs a new Triplet with the given values
	public Triplet(U first, V second, T third)
	{
		this.first = first;
		this.second = second;
		this.third = third;
	}

	@Override
	public boolean equals(Object o)
	{
		/* Checks specified object is "equal to" current object or not */

		if (this == o) return true;

		if (o == null || getClass() != o.getClass())
			return false;

		Triplet triplet = (Triplet) o;

		// call equals() method of the underlying objects
		if (!first.equals(triplet.first) ||
				!second.equals(triplet.second) ||
				!third.equals(triplet.third))
			return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		/* Computes hash code for an object by using hash codes of 
		the underlying objects */

		int result = first.hashCode();
		result = 31 * result + second.hashCode();
		result = 31 * result + third.hashCode();
		return result;
	}

	@Override
	public String toString()
	{
		return "(" + first + ", " + second + ", " + third + ")";
	}

	// Factory method for creating a Typed immutable instance of Triplet
	public static <U, V, T> Triplet <U, V, T> of(U a, V b, T c)
	{
		return new Triplet <>(a, b, c);
	}
}

// Program to implement Triplet Class in Java
class Tuple
{
	public static void main(String[] args)
	{
		Triplet<String, Integer, Character> p1 = Triplet.of("David", 26, 'M');
		Triplet<String, Integer, Character> p2 = Triplet.of("Lisa", 20, 'F');
		Triplet<String, Integer, Character> p3 = Triplet.of("David", 26, 'M');

		List<Triplet<String, Integer, Character>> pairs = new ArrayList<>();
		pairs.add(p1);
		pairs.add(p2);
		pairs.add(p3);

		System.out.println(pairs);

		Set<Triplet<String, Integer, Character>> 
						distinctTriplets = new HashSet<>(pairs);
		System.out.println(distinctTriplets);
	}
}