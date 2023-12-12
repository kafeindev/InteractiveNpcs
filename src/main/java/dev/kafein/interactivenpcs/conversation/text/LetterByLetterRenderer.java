package dev.kafein.interactivenpcs.conversation.text;

final class LetterByLetterRenderer implements Renderer {
    private static final LetterByLetterRenderer INSTANCE = new LetterByLetterRenderer();

    private LetterByLetterRenderer() {
    }

    public static LetterByLetterRenderer getInstance() {
        return INSTANCE;
    }
    
    @Override
    public String render(String text) {
        return null;
    }
}
