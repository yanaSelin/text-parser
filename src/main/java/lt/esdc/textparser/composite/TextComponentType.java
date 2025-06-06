package lt.esdc.textparser.composite;

/**
 * Enumeration of possible text component types in the text parsing hierarchy.
 * Defines the different levels of text structure from complete text down to individual symbols.
 */
public enum TextComponentType {
    /**
     * Represents a complete text document.
     */
    TEXT,

    /**
     * Represents a paragraph within text.
     */
    PARAGRAPH,

    /**
     * Represents a sentence within a paragraph.
     */
    SENTENCE,

    /**
     * Represents a word within a sentence.
     */
    WORD,

    /**
     * Represents a single symbol (character) within a word.
     */
    SYMBOL,
}
