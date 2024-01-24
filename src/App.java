import java.util.*;
import javax.sound.sampled.SourceDataLine;
public class App {
    HashMap<Integer, Character> gameMap = new HashMap<>();
    Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        //init
        Player p1 = new Player("1", 'O');
        Player p2 = new Player("2", 'X');
        Player robot = new Player("Robot", 'X');
        App m1 = new App();
        //
        m1.mapFiller();

        do{
            int temp = InputHelper.getRangedInt(m1.scan, "Welcome to Tic-Tac-Toe - Enter 1 to play against another player or 2 to play against a robot", 1, 2);
            if(temp == 1){
                m1.pvp(p1, p2);
            }else if(temp == 2){
                m1.pve(p1, robot);
            }
            m1.clearMap();
        }while(InputHelper.getYNConfirm(m1.scan, "Y/N to continue"));

    }

    public void displayMethod(){
        //print map
        System.out.println(gameMap.get(1) + " | " + gameMap.get(4)+ " | " + gameMap.get(7));
        System.out.println("----------");
        System.out.println(gameMap.get(2) + " | " + gameMap.get(5) + " | " + gameMap.get(8));
        System.out.println("----------");
        System.out.println(gameMap.get(3) + " | " + gameMap.get(6) + " | " + gameMap.get(9));
    }


    public void moveCollector(Player player){
        boolean sentinel = true;
        //adds move to map
        do{
        
            int row = InputHelper.getRangedInt(scan, "Player " + player.getIdentity() +" enter move row [1 - 3]", 1, 3);
            int column = InputHelper.getRangedInt(scan, "Enter move column [1 - 3]", 1, 3);
            if (column == 1) {
                int calc = row * column;
                sentinel = !gameMap.replace(calc, '-', player.getXo());
            }else if(column == 2){
                int calc = row + column + 1;
                sentinel = !gameMap.replace(calc, '-', player.getXo());
            }else if(column == 3){
                int calc = column + row + 3;
                sentinel = !gameMap.replace(calc, '-', player.getXo());
            }
            if(sentinel == true){
                System.out.println("Move Already Occupies This Space - Try Again");
            }
        }while(sentinel);
        
    }


    public void mapFiller(){
        //populate map with filler values
        for (int i = 1; i <= 9; i++) {
            gameMap.put(i, '-');
        }
    }

    public boolean hasWon(Player player){

        //checking columns
        int ccounter1 = 0;
        int ccounter2 = 0;
        int ccounter3 = 0;
        int rcounter1 = 0;
        int rcounter2 = 0;
        int rcounter3 = 0;
        int dcounter1 = 0;
        int dcounter2 = 0;
       
        for (int i = 1; i <= 3; i++) {
            
            if (gameMap.get(i) == player.getXo()) {
                ccounter1++;
                
            }
            if (ccounter1 == 3) {
                return true;
            }
        }
        for (int i = 4; i <= 6; i++) {
            
            if (gameMap.get(i) == player.getXo()) {
                ccounter2++;
            }
            if (ccounter2 == 3) {
                return true;
            }
        }
        for (int i = 7; i <= 9; i++) {
            
            if (gameMap.get(i) == player.getXo()) {
                ccounter3++;
            }
            if (ccounter3 == 3) {
                return true;
            }
        }

        //checking rows
        for (int i = 1; i <= 7; i = i+3) {
            
            if (gameMap.get(i) == player.getXo()) {
                rcounter1++;
            }
            if (rcounter1 == 3) {
                return true;
            }
        }
        for (int i = 2; i <= 8; i = i+3) {
           
            if (gameMap.get(i) == player.getXo()) {
                rcounter2++;
            }
            if (rcounter2 == 3) {
                return true;
            }
        }
        for (int i = 3; i <= 9; i = i+3) {
            
            if (gameMap.get(i) == player.getXo()) {
                rcounter3++;
            }
            if (rcounter3 == 3) {
                return true;
            }
        }

        //checking diagonal
        for (int i = 1; i <= 9; i = i+4) {
            if (gameMap.get(i) == player.getXo()) {
                dcounter1++;
            }
            if (dcounter1 == 3) {
                return true;
            }
        }
        for (int i = 3; i <= 7; i = i+2) {
            if (gameMap.get(i) == player.getXo()) {
                dcounter2++;
            }
            if (dcounter2 == 3) {
                return true;
            }
        }
        return false;
        
    }

    public static int getRandomIntNotInMap(HashMap<Integer, Character> map){
        Boolean s = true;
        do{
            int randomNum = 1 + (int)(Math.random() * ((9 - 1) + 1));
            if(map.get(randomNum) == 'X' || map.get(randomNum) == 'O'){
                s = false;
            }else{
                return randomNum;
            }
        }while(s);
        return -1;

    }
    public static void rbtFlow(int randomNum, HashMap<Integer, Character> map){
        HashMap<Integer, Boolean> nmap = new HashMap<>();
        nmap.put(1, gameChecker(1, 3, 1, map));
        nmap.put(2, gameChecker(4, 6, 1, map));
        nmap.put(3, gameChecker(7, 9, 1, map));

        nmap.put(4, gameChecker(1, 7, 3, map));
        nmap.put(5, gameChecker(2, 8, 3, map));
        nmap.put(6, gameChecker(3, 9, 3, map));
        
        nmap.put(7, gameChecker(3, 7, 2, map));
        nmap.put(8, gameChecker(1, 9, 4, map));
        if (!map.containsValue(true)) {
            map.put(randomNum, 'X');
        }
        
    }

    public static boolean gameChecker(int min, int max, int interval, HashMap<Integer, Character> map){
        int counter = 0;
        for (int i = min; i <= max; i = i + interval) {
            if (map.get(i) == 'O') {
                counter++;
            }
        }
        if (counter == 2) {
            for (int i = min; i <= max; i = i + interval) {
                if (map.get(i) == '-') {
                    return map.replace(i,'-', 'X');
                }
            }
        }
        return false;

    }

    public void clearMap(){
        gameMap.replaceAll((key, oldValue)
        -> '-');
    }

    public void pvp(Player p1, Player p2){
        boolean sentinel = false;
        do {
            // player 1
            moveCollector(p1);
            displayMethod();
            sentinel = hasWon(p1);
            if (sentinel) {
                System.out.println("Player 1 has won!");
                break;
            }
            if (!gameMap.containsValue('-')) {
                System.out.println("Tie");
                break;
            }
            // player 2
            moveCollector(p2);
            displayMethod();
            sentinel = hasWon(p2);
            if (sentinel) {
                System.out.println("Player 2 has won!");
                break;
            }
        } while (!sentinel);
    }

    public void pve(Player p1, Player rbt){
        boolean sentinel = false;
        do {
            // player 1
            moveCollector(p1);
            displayMethod();
            sentinel = hasWon(p1);
            if (sentinel) {
                System.out.println("Player 1 has won!");
                break;
            }
            System.out.println("");
            // bot
            System.out.println("Computer Move:");
            rbtFlow(getRandomIntNotInMap(gameMap), gameMap);
            displayMethod();
            sentinel = hasWon(rbt);
            if (sentinel) {
                System.out.println("Machine has won!");
                break;
            }
        } while (!sentinel);
    }

}