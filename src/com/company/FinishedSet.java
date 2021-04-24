package com.company;

public class FinishedSet {
    private Integer games1, games2;

    FinishedSet(){
        games1 = games2 = 0;
    }
    void setGames1(Integer g){
        games1 = g;
    }
    Integer getGames1(){
        return games1;
    }
    void setGames2(Integer g){
        games2 = g;
    }
    Integer getGames2(){
        return games2;
    }
    boolean exists(){
        return games1 != 0 || games2 != 0;
    }
}