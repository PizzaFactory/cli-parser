package jp.pizzafactory.cli.parser.core.internal;

import static org.hamcrest.collection.IsArrayContainingInOrder.arrayContaining;
import static org.junit.Assert.assertThat;
import jp.pizzafactory.cli.parser.core.CliParserFactory;
import jp.pizzafactory.cli.parser.core.ICliParser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PosixCliParserTest {

	ICliParser parser;

	@Before
	public void tearUp() {
		parser = CliParserFactory.createPosixCliParser();
	}

	@After
	public void tearDown() {
		parser = null;
	}

	@Test
	public void test() {
		String[] args = parser.parse("a b c");

		assertThat(args, arrayContaining("a", "b", "c"));
	}

	@Test
	public void testDoubleQuoted() {
		String[] args = parser.parse("a \" b \" c");

		assertThat(args, arrayContaining("a", " b ", "c"));
	}

	@Test
	public void testEscapeInDoubleQuoted() {
		String[] args = parser.parse("a \" b \\\" \" c");

		assertThat(args, arrayContaining("a", " b \" ", "c"));
	}

	@Test
	public void testSingleQuoted() {
		String[] args = parser.parse("a ' b ' c");

		assertThat(args, arrayContaining("a", " b ", "c"));
	}
}
