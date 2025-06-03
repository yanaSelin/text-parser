package lt.esdc.textparser.parser.impl;

import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.composite.impl.TextStructure;

abstract class AbstractTextStructureParser extends AbstractTextComponentParser {
  @Override
  protected TextComponent parseChunk(ParseObject chunk) {
    TextStructure structure = new TextStructure();

    while (!chunk.isDone()) {
      TextComponent child = parseNext(chunk);
      structure.addChild(child);
    }

    return structure;
  }
}
