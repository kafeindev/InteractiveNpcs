package dev.kafein.interactivenpcs.conversation.text.font;

public enum CharWidth {
    A(3),
    a(3),
    B(3),
    b(3),
    C(3),
    c(3),
    D(3),
    d(3),
    E(3),
    e(3),
    F(3),
    f(2),
    G(3),
    g(3),
    H(3),
    h(3),
    I(2),
    i(1),
    J(3),
    j(3),
    K(3),
    k(2),
    L(3),
    l(2),
    M(3),
    m(3),
    N(3),
    n(3),
    O(3),
    o(3),
    P(3),
    p(3),
    Q(3),
    q(3),
    R(3),
    r(3),
    S(3),
    s(3),
    T(3),
    t(2),
    U(3),
    u(3),
    V(3),
    v(3),
    W(3),
    w(3),
    X(3),
    x(3),
    Y(3),
    y(3),
    Z(3),
    z(3),
    NUM_0(3),
    NUM_1(3),
    NUM_2(3),
    NUM_3(3),
    NUM_4(3),
    NUM_5(3),
    NUM_6(3),
    NUM_7(3),
    NUM_8(3),
    NUM_9(3),
    EXCLAMATION('!', 1),
    AT('@', 3),
    POUND('#', 3),
    DOLLAR('$', 3),
    PERCENT('%', 3),
    CARET('^', 3),
    AMPERSAND('&', 3),
    ASTERISK('*', 2),
    LEFT_PAREN('(', 2),
    RIGHT_PAREN(')', 2),
    MINUS('-', 3),
    UNDERSCORE('_', 3),
    PLUS('+', 3),
    EQUALS('=', 3),
    LEFT_BRACKET('[', 2),
    RIGHT_BRACKET(']', 2),
    LEFT_BRACE('{', 2),
    RIGHT_BRACE('}', 2),
    COLON(':', 1),
    SEMICOLON(';', 1),
    QUOTE('\'', 1),
    DOUBLE_QUOTE('"', 2),
    COMMA(',', 1),
    PERIOD('.', 1),
    SLASH('/', 3),
    BACKSLASH('\\', 3),
    PIPE('|', 1),
    TILDE('~', 3),
    BACKTICK('`', 1),
    LESS_THAN('<', 2),
    GREATER_THAN('>', 2),
    QUESTION('?', 3),
    SPACE(' ', 2),
    NEWLINE('\n', 0),
    TAB('\t', 3),
    BACKSPACE('\b', 0);

    private final char character;
    private final int width;

    CharWidth(int width) {
        this.character = name().charAt(name().length() - 1);
        this.width = width;
    }

    CharWidth(char character, int width) {
        this.character = character;
        this.width = width;
    }

    public int getWidth() {
        return this.width;
    }

    public char getCharacter() {
        return this.character;
    }
}
