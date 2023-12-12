package dev.kafein.interactivenpcs.conversation.text;

public interface Renderer {
    String render(String text);

    static Renderer letterByLetter() {
        return LetterByLetterRenderer.getInstance();
    }
}
