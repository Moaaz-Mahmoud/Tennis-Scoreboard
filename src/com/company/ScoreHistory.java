package com.company;

import java.util.Stack;

public class ScoreHistory {
    Stack<Score> previousScores;

    ScoreHistory(){
        previousScores = new Stack<>();
    }
    public void add(Score newScore){
        previousScores.push(newScore);
    }
    public void removeLastScore(){
        previousScores.pop();
    }

    public Score getLastScore() {
        return previousScores.peek();
    }

    public boolean isEmpty(){
        return previousScores.isEmpty();
    }
}
