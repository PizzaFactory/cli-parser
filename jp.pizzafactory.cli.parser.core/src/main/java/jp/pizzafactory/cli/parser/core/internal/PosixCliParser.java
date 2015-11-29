package jp.pizzafactory.cli.parser.core.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import jp.pizzafactory.cli.parser.core.ICliParser;

import org.apache.commons.lang3.text.StrSubstitutor;

public class PosixCliParser implements ICliParser {

	private static final String DOUBLE_QUOTE = "\"";
	private static final String SINGLE_QUOTE = "'";

	@Override
	public String[] parse(String cmdline) {
		return parse(cmdline, new HashMap<String, String>());
	}

	@Override
	public String[] parse(String cmdline, Map<String, String> envVar) {
		Scanner scanner = new Scanner(cmdline);
		List<String> args = new ArrayList<String>();

		while (scanner.hasNext()) {
			final boolean isReplacable;
			final String token;
			// TODO: Support escape character support.
			if (scanner.hasNext(DOUBLE_QUOTE + ".*")) {
				token = getQuoted(scanner, DOUBLE_QUOTE);
				isReplacable = true;
			} else if (scanner.hasNext(SINGLE_QUOTE + ".*")) {
				token = getQuoted(scanner, SINGLE_QUOTE);
				isReplacable = false;
			} else {
				token = scanner.next();
				isReplacable = true;
			}

			if (isReplacable) {
				args.add(substitute(token, envVar));
			} else {
				args.add(token);
			}
		}

		return args.toArray(new String[args.size()]);
	}

	private String substitute(String token, Map<String, String> envVar) {
		StrSubstitutor substitutor = new StrSubstitutor(envVar);
		return substitutor.replace(token);
	}

	private String getQuoted(Scanner scanner, String quoteChar) {
		String subtoken = "";
		Pattern delimiter = scanner.delimiter();
		scanner.useDelimiter(quoteChar);
		scanner.next();
		while (scanner.hasNext()) {
			subtoken += scanner.next();
			if (subtoken.endsWith("\\")) {
				subtoken = subtoken.substring(0, subtoken.length() - 1)
						+ quoteChar;
			} else {
				break;
			}
		}
		scanner.useDelimiter(delimiter);
		scanner.next(quoteChar);

		return subtoken;
	}
}
