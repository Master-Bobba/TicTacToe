package org.example;

public enum State {

    X ("X", "X"),
    O ("O", "O"),
    EMPTY (null, " ");

    private final String state;
    private final String sign;

    State(String state, String sign){
        this.state = state;
        this.sign = sign;
    }

    public String getState() {
        return state;
    }

    public String getSign() {
        return sign;
    }
}
