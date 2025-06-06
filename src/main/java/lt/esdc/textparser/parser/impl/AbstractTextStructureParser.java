package lt.esdc.textparser.parser.impl;

import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.composite.impl.TextStructure;

/**
 * Abstract base class for parsers that create structured text components.
 * This parser specializes in creating hierarchical text structures by delegating the parsing of
 * individual components to the next parser in the chain. It builds a complete TextStructure
 * by aggregating child components until the entire input is processed.
 * Concrete implementations must define the specific type of TextStructure to create.
 */
abstract class AbstractTextStructureParser extends AbstractTextComponentParser {

  /**
   * Creates a specific type of TextStructure appropriate for the concrete parser implementation.
   *
   * @return a new, empty TextStructure instance
   */
  protected abstract TextStructure getTextStructure();

  /**
   * Parses a chunk of text by creating a TextStructure and populating it with child components.
   * This implementation repeatedly delegates to the next parser in the chain to parse individual
   * elements within the chunk, adding each parsed component as a child of the TextStructure.
   *
   * @param chunk the ParseObject containing the text to parse
   * @return a TextStructure containing the parsed child components
   */
  @Override
  protected TextComponent parseChunk(ParseObject chunk) {
    TextStructure structure = getTextStructure();

    while (!chunk.isDone()) {
      TextComponent child = parseNext(chunk);
      structure.addChild(child);
    }

    return structure;
  }
}
