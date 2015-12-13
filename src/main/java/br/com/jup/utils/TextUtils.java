package br.com.jup.utils;

import java.text.Normalizer;

/**
 * Classe utilitária para textos
 * @author renatorodrigues
 *
 */
public class TextUtils {
	
	public static String removerAcentos(String str) {
	    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

}
