package com.company;

import java.util.Vector;

public class Score {
    public static final Integer ADVANTAGE = 45;
    public static final String ADVANTAGE_TEXT = "Ad";
    public static final Integer NAME_FIELD = 18;
    public static final Integer NAME_MAX_LENGTH = 14;

    enum Mode{WIMBLEDON, REGULAR}
    private Mode mode;
    private Integer sets1, games1, points1,
            sets2, games2, points2;
    private boolean tiebreak;
    private Vector<FinishedSet> finishedSets;
    private String player1, player2;

    Score(){
        initialize();
        mode = Mode.REGULAR;
    } //end default Constructor
    Score(Score scr){
        sets1   = scr.sets1;   sets2   = scr.sets2;
        games1  = scr.games1;  games2  = scr.games2;
        points1 = scr.points1; points2 = scr.points2;

        tiebreak = scr.tiebreak;
        finishedSets = new Vector<>();
        for(var finishedSet : scr.finishedSets)
            finishedSets.add(finishedSet);
        player1 = scr.player1;
        player2 = scr.player2;
        mode = scr.mode;
    }
    void initialize(){
        sets1 = sets2 = 0;
        games1 = games2 = 0;
        points1 = points2 = 0;
        tiebreak = false;
        finishedSets = new Vector<>();
        player1 = new String("P1");
        player2 = new String("P2");
    } //end initialize
    void setMode(Mode newMode){
        mode = newMode;
    } //end setMode
    Mode getMode(){
        return mode;
    } //end getMode
    void reset(){
        initialize();
    } //end reset
    private void incrementPlayer1_regular(){
        if(points1 < 40 && points2 < 40){
            if(points1 == 0)       points1 = 15;
            else if(points1 == 15) points1 = 30;
            else if(points1 == 30) points1 = 40;
        }
        else if(points1 == 40 && points2 < 40){
            games1++;
            points1 = points2 = 0;
            if(games1 >= 6 && games1-games2 >= 2){
                sets1++;
                finishedSets.add(new FinishedSet(games1, games2));
                games1 = games2 = 0;
            }
        }
        else if(points1 < 40 && points2 == 40){
            if(points1 == 0)       points1 = 15;
            else if(points1 == 15) points1 = 30;
            else if(points1 == 30) points1 = 40;
        }
        else if(points1 == 40 && points2 == 40){
            points1 = ADVANTAGE;
        }
        else if(points1 == ADVANTAGE){
            games1++;
            points1 = points2 = 0;
            if((games1 == 6 && games2 <= 4) || games1 == 7){
                sets1++;
                finishedSets.add(new FinishedSet(games1, games2));
                games1 = games2 = 0;
            }
        }
        else if(points2 == ADVANTAGE){
            points2 = 40;
        }
        if(games1 == 6 && games2 == 6) tiebreak = true;
    } //end incrementPlayer1_regular
    private void incrementPlayer1_tiebreak(){
        points1++;
        if(points1 >= 7 && points1 - points2 >= 2){
            games1++;
            sets1++;
            finishedSets.add(new FinishedSet(games1, games2));
            games1 = games2 = 0;
            points1 = points2 = 0;
            tiebreak = false;
        }
    } //end incrementPlayer1_tiebreak
    public void incrementPlayer1(){
        if(tiebreak)
            incrementPlayer1_tiebreak();
        else
            incrementPlayer1_regular();
    } //end incrementPlayer1
    private void incrementPlayer2_regular(){
        if(points2 < 40 && points1 < 40){
            if(points2 == 0)       points2 = 15;
            else if(points2 == 15) points2 = 30;
            else if(points2 == 30) points2 = 40;
        }
        else if(points2 == 40 && points1 < 40){
            games2++;
            points1 = points2 = 0;
            if((games2 == 6 && games1 <= 4) || games2 == 7){
                sets2++;
                finishedSets.add(new FinishedSet(games1, games2));
                games1 = games2 = 0;
            }
        }
        else if(points2 < 40 && points1 == 40){
            if(points2 == 0)       points2 = 15;
            else if(points2 == 15) points2 = 30;
            else if(points2 == 30) points2 = 40;
        }
        else if(points2 == 40 && points1 == 40){
            points2 = ADVANTAGE;
        }
        else if(points2 == ADVANTAGE){
            games2++;
            points1 = points2 = 0;
            if((games2 == 6 && games1 <= 4) || games2 == 7){
                sets2++;
                finishedSets.add(new FinishedSet(games1, games2));
                games1 = games2 = 0;
            }
        }
        else if(points1 == ADVANTAGE){
            points1 = 40;
        }
        if(games1 == 6 && games2 == 6) tiebreak = true;
    } //end incrementPlayer2_regular
    private void incrementPlayer2_tiebreak(){
        points2++;
        if(points2 >= 7 && points2 - points1 >= 2){
            games2++;
            sets2++;
            finishedSets.add(new FinishedSet(games1, games2));
            games1 = games2 = 0;
            points1 = points2 = 0;
            tiebreak = false;
        }
    } //end incrementPlayer2_tiebreak
    public void incrementPlayer2(){
        if(tiebreak)
            incrementPlayer2_tiebreak();
        else
            incrementPlayer2_regular();
    } //end incrementPlayer2
    void displayRegular(){
        System.out.print(StringAssistant.placeNameInField(player1));
        for(var finishedSet : finishedSets)
            System.out.print(finishedSet.getGames1() + "  ");
        System.out.print(games1 + " " + (points1 != ADVANTAGE ? points1 : ADVANTAGE_TEXT) + "\n");

        System.out.print(StringAssistant.placeNameInField(player2));
        for(var finishedSet : finishedSets)
            System.out.print(finishedSet.getGames2() + "  ");
        System.out.print(games2 + " " + (points2 != ADVANTAGE ? points2 : ADVANTAGE_TEXT) + "\n");
        System.out.flush();
    } //end displayRegular
    void displayWimbledon(){
        System.out.print(StringAssistant.placeNameInField(player1));
        System.out.print(sets1 + "  " + games1 + "  " + (points1 != ADVANTAGE ? points1 : ADVANTAGE_TEXT) + "\n");
        System.out.print(StringAssistant.placeNameInField(player2));
        System.out.print(sets2 + "  " + games2 + "  " + (points2 != ADVANTAGE ? points2 : ADVANTAGE_TEXT) + "\n");
    }//end displayWimbledon
    public void displayOnConsole(){
        if(mode == Mode.REGULAR){
            this.displayRegular();
        }
        else if(mode == Mode.WIMBLEDON){
            this.displayWimbledon();
        }
    } //end displayOnConsole
    @Override
    public String toString(){
        StringBuilder scoreBuilder = new StringBuilder();
        if(mode == Mode.REGULAR){
            scoreBuilder.append(StringAssistant.placeNameInField(player1));
            for(var finishedSet : finishedSets)
                scoreBuilder.append(finishedSet.getGames1() + "  ");
            scoreBuilder.append(games1 + "  " + (points1 != ADVANTAGE ? points1 : ADVANTAGE_TEXT) + "\n");

            scoreBuilder.append(StringAssistant.placeNameInField(player2));
            for(var finishedSet : finishedSets)
                scoreBuilder.append(finishedSet.getGames2() + "  ");
            scoreBuilder.append(games2 + "  " + (points2 != ADVANTAGE ? points2 : ADVANTAGE_TEXT));
        }
        else{
            scoreBuilder.append(StringAssistant.placeNameInField(player1) + sets1 + "  " + games1 + "  " +
                    (points1 != ADVANTAGE ? points1 : ADVANTAGE_TEXT) + "\n" +
                    StringAssistant.placeNameInField(player2) + sets2 + "  " + games2 + "  " +
                    (points1 != ADVANTAGE ? points1 : ADVANTAGE_TEXT));
        }
        return scoreBuilder.toString();
    } //end toString
} //end Score