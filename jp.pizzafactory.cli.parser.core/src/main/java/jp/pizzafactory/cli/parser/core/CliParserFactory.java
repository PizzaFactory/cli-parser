package jp.pizzafactory.cli.parser.core;

import jp.pizzafactory.cli.parser.core.internal.PosixCliParser;

public class CliParserFactory {

	private CliParserFactory() {
		/* Factory class */
	}

	public static ICliParser createPosixCliParser() {
		return new PosixCliParser();
	}
}
