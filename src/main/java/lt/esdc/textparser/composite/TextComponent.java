package lt.esdc.textparser.composite;

import java.util.List;

public interface TextComponent {
  TextComponentType getType();
  int length();
  List<TextComponent> getComponents(TextComponentType type);
  boolean removeComponent(TextComponent component);
}
