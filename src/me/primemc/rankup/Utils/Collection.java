package me.primemc.rankup.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collection {
	
	@SafeVarargs
	public static <E> List<E> listOf(E... elementos) {
		
        return new ArrayList<>(Arrays.asList(elementos));
        
    }

}
