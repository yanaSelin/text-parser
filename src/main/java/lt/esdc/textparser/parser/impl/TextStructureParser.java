package lt.esdc.textparser.parser.impl;

import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.composite.impl.TextStructure;

public abstract class TextStructureParser extends BaseComponentParser {
  @Override
  protected TextComponent buildComponent(String text) {
    TextStructure structure = new TextStructure();

    int pointer = 0;
    while (pointer < text.length()) {
      TextComponent child = parseNext(text.substring(pointer));
      structure.addChild(child);
      pointer += child.length();
    }

    return structure;
  }
}
