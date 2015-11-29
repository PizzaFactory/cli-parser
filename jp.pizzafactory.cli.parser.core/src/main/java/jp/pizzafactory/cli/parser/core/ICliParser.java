package jp.pizzafactory.cli.parser.core;

import java.util.Map;

public interface ICliParser {

	String[] parse(String cmdline);

	String[] parse(String cmdline, Map<String, String> envVar);
}
