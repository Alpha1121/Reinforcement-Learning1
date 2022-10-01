import java.util.*;
import java.math.*;

class Assignment1Q3 {
    
    public static void main(String[] args) {
        //
        //Given inputs
        //

        
        //Representing belief state grid as a Array with 12 coordinate states; considering [1,1] = 1 and [1,2] = 2 and so on... until [3,3] = 11 and [3,4] = 12
        double[] gridUnknown = new double[12];  // where initial state is unknown
        double[] gridknown = new double[12];    // where initial state is known
  
        // initial states as given in question
            boolean initialStateGiven = false;
            initialStateGiven = true;
            // int initialState = 11; // because [3,2] = 11; considering [1,1] = 1 and [1,2] = 2 and so on... until [3,4] = 12
            int initialState = 1;  // because [1,1] = 1 is considered as 1
        
        // List of actions
            // List<String> actions = Arrays.asList("Up", "Up", "Up");
            // List<String> actions = Arrays.asList("Up", "Up", "Up");
            // List<String> actions = Arrays.asList("Right", "Right", "Up");
            List<String> actions = Arrays.asList("Up", "Right", "Right", "Right");
            
        // List of observations
            // List<Integer> obs = Arrays.asList(2, 2, 2);
            // List<Integer> obs = Arrays.asList(1, 1, 1);
            // List<Integer> obs = Arrays.asList(1, 1, 0);
            List<Integer> obs = Arrays.asList(2, 2, 2, 1);
                
            
        // ------------------------- INITIALIZE initial belief state of Grid where initial state is unknown ---------

            //row 1 is 0.1111 everywhere
            for(int i=1; i<=4; i++) {
                gridUnknown[i-1] = 0.1111;
            }

            //row 2 is 0.111 at [2,1], [2,3]
                gridUnknown[4]= 0.1111;
                gridUnknown[6]= 0.1111;

            //row 3 is 0.111 everywhere, other than the terminal state [2][3]
                gridUnknown[8]= 0.1111;
                gridUnknown[9]= 0.1111;
                gridUnknown[10]= 0.1111;
        // ------------------------- INITIALIZED belief state grid where initial state is unknown ------------



        // ------------------------- INITIALIZE initial belief state of Grid where initial state is known ---------
                gridknown[initialState - 1] = 1.0;
        // ------------------------- INITIALIZED belief state grid where initial state is known ------------


        // ----------------------- UPDATING P(e|s') grid ------------------------------- //
        double[][] gridObserved = new double[3][12];

        //sensing end 
            gridObserved[0][7] = 1.0;  // where 0 is the sensor reporting 'end' and 7 denotes state[2,4]
            gridObserved[0][11] = 1.0;
        
        //for non-terminal in 3rd column and sensing 1 wall
            gridObserved[1][2] = 0.9;   //where 1 denotes 1-wall and 2 denotes [1,3]
            gridObserved[1][6] = 0.9;
            gridObserved[1][10] = 0.9;


            //sensing 2 walls
            gridObserved[2][2] = 0.1;   //where first 2 denotes 2-walls  
            gridObserved[2][6] = 0.1;   //where 2 denotes 2 walls and 6 denotes [2,3]
            gridObserved[2][10] = 0.1;


        // for all other non terminal states
            //sensing 1 wall
                //row 1
            gridObserved[1][0] = 0.1;   //where first 1 represents 1 wall and second 0 represents state[1,1]
            gridObserved[1][1] = 0.1;
            gridObserved[1][3] = 0.1; 

                //row2
            gridObserved[1][4] = 0.1; 
                
                //row3
            gridObserved[1][8] = 0.1;   //where 8 is state [3,1] 
            gridObserved[1][9] = 0.1;

            //sensing 2 walls
                //row 1
            gridObserved[2][0] = 0.9;   //where first 2 represents 2 walls and second 0 represents state[1,1]
            gridObserved[2][1] = 0.9;   //where 1 denotes state[1,2]
            gridObserved[2][3] = 0.9; 

                //row2
            gridObserved[2][4] = 0.9; 
                
                //row3
            gridObserved[2][8] = 0.9;   //where 8 is state [3,1] 
            gridObserved[2][9] = 0.9;

         // ----------------------- UPDATED P(e|s') grid ------------------------------- //

            


        // ------------------------------------------- UPDATING Transition matrix ----------------------------------------------- //
        //Populating the transition matrix using a 3D array where 1st[] represents action, second[] represents the state action is being taken in, and 3rd[] represents the resulting state
        //  
        double[][][] transition= new double[4][12][12]; 

            // For UP action we will use transition[0][...][...]
            for (int i=1; i<=12; i++) {

                //Populating  all probabilities when action UP taken in state [1,1], 
                if (i==1) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 1 || j == 2) {
                            transition[0][i-1][j-1] = 0.1;   //for state[1,1] and [1,2]
                        }
                        
                        if(j==5) {
                            transition[0][i-1][j-1] = 0.8;  //for state [2,1]
                        }

                    }
                }

                //Populating  all probabilities when action UP taken in state [1,2], 
                if (i == 2) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 1 || j == 3) {
                            transition[0][i-1][j-1] = 0.1;   //for state[1,1] and [1,3]
                        }
                        
                        if(j==2) {
                            transition[0][i-1][j-1] = 0.8;  //for state [1,2]
                        }

                    }
                }

                //Populating all probabilities when action UP taken in state [1,3], 
                if (i == 3) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 2 || j == 4) {
                            transition[0][i-1][j-1] = 0.1;   //for state[1,2] and [1,4]
                        }
                        
                        if(j==7) {
                            transition[0][i-1][j-1] = 0.8;  //for state [2,3]
                        }
                    }
                }

                //Populating all probabilities when action UP taken in state [1,4], 
                if (i == 4) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 3 || j == 4) {
                            transition[0][i-1][j-1] = 0.1;   //for state[1,3] and [1,4]
                        }
                        
                        if(j==8) {
                            transition[0][i-1][j-1] = 0.8;  //for state [2,4]
                        }
                    }
                }

                //Populating all probabilities when action UP taken in state [2,1], 
                if (i == 5) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 5) {
                            transition[0][i-1][j-1] = 0.2;   //for state[2,1]
                        }
                        
                        if(j==9) {
                            transition[0][i-1][j-1] = 0.8;  //for state [3,1]
                        }
                    }
                }

                //no actions can be taken in state[2,2]
                
                //Populating all probabilities when action UP taken in state [2,3], 
                if (i == 7) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 7 || j==8) {
                            transition[0][i-1][j-1] = 0.1;   //for state[2,3] and [2,4]
                        }
                        
                        if(j==11) {
                            transition[0][i-1][j-1] = 0.8;  //for state [3,3]
                        }
                    }
                }

                //Populating all probabilities when action UP taken in state [3,1], 
                if (i == 9) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 10) {
                            transition[0][i-1][j-1] = 0.1;   //for state[3,2]
                        }
                        
                        if(j==9) {
                            transition[0][i-1][j-1] = 0.9;  //for state [3,1]
                        }
                    }
                }

                //Populating all probabilities when action UP taken in state [3,2], 
                if (i == 10) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 9 || j ==11) {
                            transition[0][i-1][j-1] = 0.1;   //for state[3,1] and [3,3]
                        }
                        
                        if(j==10) {
                            transition[0][i-1][j-1] = 0.8;  //for state [3,2]
                        }
                    }
                }

                //Populating all probabilities when action UP taken in state [3,3], 
                if (i == 11) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 10 || j ==12) {
                            transition[0][i-1][j-1] = 0.1;   //for state[3,2] and [3,4] 
                        }

                        if(j==11) {
                            transition[0][i-1][j-1] = 0.8;  //for state [3,3]
                        }
                    }
                } 
                
            }


            // For DOWN action we will use transition[1][...][...]
            for (int i=1; i<=12; i++) {

                //Populating  all probabilities when action DOWN taken in state [1,1], 
                if (i==1) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 2) {
                            transition[1][i-1][j-1] = 0.1;   //for state[1,2]
                        }
                        
                        if(j==1) {
                            transition[1][i-1][j-1] = 0.9;  //for state [1,1]
                        }

                    }
                }

                //Populating  all probabilities when action DOWN taken in state [1,2], 
                if (i == 2) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 1 || j == 3) {
                            transition[1][i-1][j-1] = 0.1;   //for state[1,1] and [1,3]
                        }
                        
                        if(j==2) {
                            transition[1][i-1][j-1] = 0.8;  //for state [1,2]
                        }

                    }
                }

                //Populating all probabilities when action DOWN taken in state [1,3], 
                if (i == 3) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 2 || j == 4) {
                            transition[1][i-1][j-1] = 0.1;   //for state[1,2] and [1,4]
                        }
                        
                        if(j == 3) {
                            transition[1][i-1][j-1] = 0.8;  //for state [1,3]
                        }
                    }
                }

                //Populating all probabilities when action DOWN taken in state [1,4], 
                if (i == 4) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 3) {
                            transition[1][i-1][j-1] = 0.1;   //for state[1,3]
                        }
                        
                        if(j==4) {
                            transition[1][i-1][j-1] = 0.9;  //for state [1,4]
                        }
                    }
                }

                //Populating all probabilities when action DOWN taken in state [2,1], 
                if (i == 5) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 5) {
                            transition[1][i-1][j-1] = 0.2;   //for state[2,1]
                        }
                        
                        if(j==1) {
                            transition[1][i-1][j-1] = 0.8;  //for state [1,1]
                        }
                    }
                }

                //no actions can be taken in state[2,2]
                
                //Populating all probabilities when action DOWN taken in state [2,3], 
                if (i == 7) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 7 || j==8) {
                            transition[1][i-1][j-1] = 0.1;   //for state[2,3] and [2,4]
                        }
                        
                        if(j==3) {
                            transition[1][i-1][j-1] = 0.8;  //for state [1,3]
                        }
                    }
                }

                //no actions can be taken in state[2,4]

                //Populating all probabilities when action DOWN taken in state [3,1], 
                if (i == 9) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 9 || j == 10) {
                            transition[1][i-1][j-1] = 0.1;   //for state[3,1] and [3,2]
                        }
                        
                        if(j==5) {
                            transition[1][i-1][j-1] = 0.8;  //for state [2,1]
                        }
                    }
                }

                //Populating all probabilities when action DOWN taken in state [3,2], 
                if (i == 10) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 9 || j ==11) {
                            transition[1][i-1][j-1] = 0.1;   //for state[3,1] and [3,3]
                        }
                        
                        if(j==10) {
                            transition[1][i-1][j-1] = 0.8;  //for state [3,2]
                        }
                    }
                }

                //Populating all probabilities when action DOWN taken in state [3,3], 
                if (i == 11) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 10 || j ==12) {
                            transition[1][i-1][j-1] = 0.1;   //for state[3,2] and [3,4] 
                        }

                        if(j==7) {
                            transition[1][i-1][j-1] = 0.8;  //for state [2,3]
                        }
                    }
                } 
                
            }

            // For LEFT action we will use transition[2][...][...]
            for (int i=1; i<=12; i++) {

                //Populating  all probabilities when action LEFT taken in state [1,1], 
                if (i==1) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 1) {
                            transition[2][i-1][j-1] = 0.9;   //for state[1,1]
                        }
                        
                        if(j==5) {
                            transition[2][i-1][j-1] = 0.1;  //for state [2,1]
                        }

                    }
                }

                //Populating  all probabilities when action LEFT taken in state [1,2], 
                if (i == 2) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 2) {
                            transition[2][i-1][j-1] = 0.2;   //for state[1,2]
                        }
                        
                        if(j==1) {
                            transition[2][i-1][j-1] = 0.8;  //for state [1,1]
                        }

                    }
                }

                //Populating all probabilities when action LEFT taken in state [1,3], 
                if (i == 3) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 3 || j == 7) {
                            transition[2][i-1][j-1] = 0.1;   //for state[1,3] and [2,3]
                        }
                        
                        if(j == 2) {
                            transition[2][i-1][j-1] = 0.8;  //for state [1,2]
                        }
                    }
                }

                //Populating all probabilities when action LEFT taken in state [1,4], 
                if (i == 4) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 4 || j == 8) {
                            transition[2][i-1][j-1] = 0.1;   //for state[1,4] and [2,4]
                        }
                        
                        if(j==3) {
                            transition[2][i-1][j-1] = 0.8;  //for state [1,3]
                        }
                    }
                }

                //Populating all probabilities when action LEFT taken in state [2,1], 
                if (i == 5) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 1 || j == 9) {
                            transition[2][i-1][j-1] = 0.1;   //for state[1,1] and [3,1]
                        }
                        
                        if(j==5) {
                            transition[2][i-1][j-1] = 0.8;  //for state [2,1]
                        }
                    }
                }

                //no actions can be taken in state[2,2]
                
                //Populating all probabilities when action LEFT taken in state [2,3], 
                if (i == 7) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 3 || j==11) {
                            transition[2][i-1][j-1] = 0.1;   //for state[3,3] and [1,3]
                        }
                        
                        if(j==7) {
                            transition[2][i-1][j-1] = 0.8;  //for state [2,3]
                        }
                    }
                }

                //no actions can be taken in state[2,4]

                //Populating all probabilities when action LEFT taken in state [3,1], 
                if (i == 9) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 5) {
                            transition[2][i-1][j-1] = 0.1;   //for state[2,1]
                        }
                        
                        if(j==9) {
                            transition[2][i-1][j-1] = 0.9;  //for state [3,1]
                        }
                    }
                }

                //Populating all probabilities when action LEFT taken in state [3,2], 
                if (i == 10) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 10) {
                            transition[2][i-1][j-1] = 0.2;   //for state[3,2]
                        }
                        
                        if(j==9) {
                            transition[2][i-1][j-1] = 0.8;  //for state [3,1]
                        }
                    }
                }

                //Populating all probabilities when action LEFT taken in state [3,3], 
                if (i == 11) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 7 || j == 11) {
                            transition[2][i-1][j-1] = 0.1;   //for state[3,3] and [2,3] 
                        }

                        if(j==10) {
                            transition[2][i-1][j-1] = 0.8;  //for state [3,2]
                        }
                    }
                } 
                
            }


            // For RIGHT action we will use transition[3][...][...]
            for (int i=1; i<=12; i++) {

                //Populating  all probabilities when action RIGHT taken in state [1,1], 
                if (i==1) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 1 || j == 5) {
                            transition[3][i-1][j-1] = 0.1;   //for state[1,1] and [2,1]
                        }
                        
                        if(j==2) {
                            transition[3][i-1][j-1] = 0.8;  //for state [1,2]
                        }

                    }
                }

                //Populating  all probabilities when action RIGHT taken in state [1,2], 
                if (i == 2) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 2) {
                            transition[3][i-1][j-1] = 0.2;   //for state[1,2]
                        }
                        
                        if(j==3) {
                            transition[3][i-1][j-1] = 0.8;  //for state [1,3]
                        }

                    }
                }

                //Populating all probabilities when action RIGHT taken in state [1,3], 
                if (i == 3) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 3 || j == 7) {
                            transition[3][i-1][j-1] = 0.1;   //for state[1,3] and [2,3]
                        }
                        
                        if(j == 4) {
                            transition[3][i-1][j-1] = 0.8;  //for state [1,4]
                        }
                    }
                }

                //Populating all probabilities when action RIGHT taken in state [1,4], 
                if (i == 4) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 8) {
                            transition[3][i-1][j-1] = 0.1;   //for state[2,4] 
                        }
                        
                        if(j==4) {
                            transition[3][i-1][j-1] = 0.9;  //for state [1,4]
                        }
                    }
                }

                //Populating all probabilities when action RIGHT taken in state [2,1], 
                if (i == 5) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 1 || j == 9) {
                            transition[3][i-1][j-1] = 0.1;   //for state[1,1] and [3,1]
                        }
                        
                        if(j==5) {
                            transition[3][i-1][j-1] = 0.8;  //for state [2,1]
                        }
                    }
                }

                //no actions can be taken in state[2,2]
                
                //Populating all probabilities when action RIGHT taken in state [2,3], 
                if (i == 7) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 3 || j==11) {
                            transition[3][i-1][j-1] = 0.1;   //for state[3,3] and [1,3]
                        }
                        
                        if(j==8) {
                            transition[3][i-1][j-1] = 0.8;  //for state [2,4]
                        }
                    }
                }

                //no actions can be taken in state[2,4]

                //Populating all probabilities when action RIGHT taken in state [3,1], 
                if (i == 9) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 5 || j == 9) {
                            transition[3][i-1][j-1] = 0.1;   //for state[2,1] and [3,1]
                        }
                        
                        if(j==10) {
                            transition[3][i-1][j-1] = 0.8;  //for state [3,2]
                        }
                    }
                }

                //Populating all probabilities when action RIGHT taken in state [3,2], 
                if (i == 10) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 10) {
                            transition[3][i-1][j-1] = 0.2;   //for state[3,2]
                        }
                        
                        if(j==11) {
                            transition[3][i-1][j-1] = 0.8;  //for state [3,3]
                        }
                    }
                }

                //Populating all probabilities when action taken in state [3,3], 
                if (i == 11) { 
                   
                    for(int j=1; j<=12; j++) { //traversing through all possible end states and putting in values where needed
                        if(j == 7 || j == 11) {
                            transition[3][i-1][j-1] = 0.1;   //for state[3,3] and [2,3] 
                        }

                        if(j==12) {
                            transition[3][i-1][j-1] = 0.8;  //for state [3,2]
                        }
                    }
                } 
                
            }
        // ----------------------- UPDATED Transition matrix ----------------- //






        //
            // ------------------   For sequence of actions where initial state is unknown --------------------
        // 
        
        if (!initialStateGiven) {
            
            // Calculate b'(s') = a P(e|s') [P(s'|a,s1)b(s1) + P(s'|a,s2)b(s2).....] for each s' in grid

            //traversing through all actions
            for(int i=0; i<actions.size(); i++) {
                // Creating a clone of the grid in which changes will be made and after an action is taken, the new grid becomes gridKnown
                double[] NewGrid = gridUnknown.clone();
            
                //Looking at the action taken 
    
                // if action is "UP"
                if(actions.get(i).equals("Up")) {
                
                    for(int sFinal=1; sFinal<=12; sFinal++) {
                        
                        double summationForSFinal = 0.0;


                        if (sFinal ==1) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                    
                                if (sInitial == 1 || sInitial == 2) {  //Calculating the summation part of the equation using only initial states that can lead to sFinal
                                    summationForSFinal += (transition[0][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                    //We now have summationForSFinal which is = [P(s'|a,s1)b(s1) + P(s'|a,s2)b(s2).....]
                                } 
                            }

                        } else if(sFinal == 2) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 1 || sInitial == 2 || sInitial == 3) {
                                    summationForSFinal += (transition[0][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }

                        } else if(sFinal == 3) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 2 || sInitial == 4) {
                                    summationForSFinal += (transition[0][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }
                        } else if(sFinal == 4) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 3 || sInitial == 4) {
                                    summationForSFinal += (transition[0][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }
                        } else if(sFinal == 5) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 1 || sInitial == 5) {
                                    summationForSFinal += (transition[0][sInitial-1][sFinal-1]* gridUnknown[sInitial-1]);
                                } 
                            }

                        } else if(sFinal == 6) {
                            summationForSFinal = 0.0;

                        } else if(sFinal == 7) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 3 || sInitial == 7) {
                                    summationForSFinal += (transition[0][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }
                        } else if(sFinal == 8) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 7 || sInitial == 4) {
                                    summationForSFinal += (transition[0][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }

                        } else if(sFinal == 9) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 5 || sInitial == 9 || sInitial == 10) {
                                    summationForSFinal += (transition[0][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }

                        } else if(sFinal == 10) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 9 || sInitial == 10 || sInitial == 11) {
                                    summationForSFinal += (transition[0][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }

                        } else if(sFinal == 11) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 10 || sInitial == 7) {
                                    summationForSFinal += (transition[0][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }
                        } else if(sFinal == 12) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 11) {
                                    summationForSFinal += (transition[0][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }
                        }
                        
                        //Multiplying summation with P(e|s')
                        NewGrid[sFinal-1] = gridObserved[obs.get(i)][sFinal-1]*summationForSFinal;   
                    }
                    gridUnknown = NewGrid;
                }
    
                // if action is "Down"
                if(actions.get(i).equals("Down")) {
                
                    for(int sFinal=1; sFinal<=12; sFinal++) {
                            
                        double summationForSFinal = 0.0;


                        if (sFinal ==1) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                //Calculating the summation part of the equation using only initial states that can lead to sFinal
                                if (sInitial == 1 || sInitial == 2 ||sFinal == 5) {  
                                    summationForSFinal += (transition[1][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                    //We now have summationForSFinal which is = [P(s'|a,s1)b(s1) + P(s'|a,s2)b(s2).....]
                                } 
                            }

                        } else if(sFinal == 2) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 1 || sInitial == 2 || sInitial == 3) {
                                    summationForSFinal += (transition[1][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }

                        } else if(sFinal == 3) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 2 || sInitial == 3 || sFinal ==4) {
                                    summationForSFinal += (transition[1][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }
                        } else if(sFinal == 4) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 3 || sInitial == 4) {
                                    summationForSFinal += (transition[1][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }
                        } else if(sFinal == 5) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 5 || sInitial == 9) {
                                    summationForSFinal += (transition[1][sInitial-1][sFinal-1]* gridUnknown[sInitial-1]);
                                } 
                            }

                        } else if(sFinal == 6) {
                            summationForSFinal = 0.0;

                        } else if(sFinal == 7) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 7 || sInitial == 11) {
                                    summationForSFinal += (transition[1][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }
                        } else if(sFinal == 8) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 7) {
                                    summationForSFinal += (transition[1][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }

                        } else if(sFinal == 9) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 9 || sInitial == 10) {
                                    summationForSFinal += (transition[1][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }

                        } else if(sFinal == 10) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 9 || sInitial == 10 || sInitial == 11) {
                                    summationForSFinal += (transition[1][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }

                        } else if(sFinal == 11) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 10) {
                                    summationForSFinal += (transition[1][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }
                        } else if(sFinal == 12) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 11) {
                                    summationForSFinal += (transition[1][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }
                        }
                        
                        //Multiplying summation with P(e|s')
                        NewGrid[sFinal-1] = gridObserved[obs.get(i)][sFinal-1]*summationForSFinal;   
                    }
                    gridUnknown = NewGrid;
                }
    
                // if action is "Left"
                if(actions.get(i).equals("Left")) {
                
                    //updating belief state grid values without normalization constant
                    for(int sFinal=1; sFinal<=12; sFinal++) {
        
                        double summationForSFinal = 0.0;


                        if (sFinal ==1) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                //Calculating the summation part of the equation using only initial states that can lead to sFinal
                                if (sInitial == 1 || sInitial == 2 || sInitial == 5) {  
                                    summationForSFinal += (transition[2][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                    //We now have summationForSFinal which is = [P(s'|a,s1)b(s1) + P(s'|a,s2)b(s2).....]
                                } 
                            }

                        } else if(sFinal == 2) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 2 || sInitial == 3) {
                                    summationForSFinal += (transition[2][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }

                        } else if(sFinal == 3) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 3 || sInitial ==4 || sInitial ==7) {
                                    summationForSFinal += (transition[2][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }
                        } else if(sFinal == 4) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 4) {
                                    summationForSFinal += (transition[2][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }
                        } else if(sFinal == 5) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 1 || sInitial == 5 || sInitial == 9) {
                                    summationForSFinal += (transition[2][sInitial-1][sFinal-1]* gridUnknown[sInitial-1]);
                                } 
                            }

                        } else if(sFinal == 6) {
                            summationForSFinal = 0.0;

                        } else if(sFinal == 7) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 3 || sInitial == 7 || sInitial == 11) {
                                    summationForSFinal += (transition[2][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }
                        } else if(sFinal == 8) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 4) {
                                    summationForSFinal += (transition[2][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }

                        } else if(sFinal == 9) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 5 || sInitial == 9 || sInitial == 10) {
                                    summationForSFinal += (transition[2][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }

                        } else if(sFinal == 10) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 10 || sInitial == 11) {
                                    summationForSFinal += (transition[2][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }

                        } else if(sFinal == 11) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 7 || sInitial == 11) {
                                    summationForSFinal += (transition[2][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }
                        } else if(sFinal == 12) {
                            summationForSFinal = 0.0;
                        }
                        
                        //Multiplying summation with P(e|s')
                        NewGrid[sFinal-1] = gridObserved[obs.get(i)][sFinal-1]*summationForSFinal;   
                    }
                    gridUnknown = NewGrid;
    
                }
    
                // if action is "Right"
                if(actions.get(i).equals("Right")) {
                
                    //updating belief state grid values without normalization constant
                    //updating belief state grid values without normalization constant
                    for(int sFinal=1; sFinal<=12; sFinal++) {
        
                        double summationForSFinal = 0.0;

                        if (sFinal ==1) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                //Calculating the summation part of the equation using only initial states that can lead to sFinal
                                if (sInitial == 1 || sInitial == 5) {  
                                    summationForSFinal += (transition[3][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                    //We now have summationForSFinal which is = [P(s'|a,s1)b(s1) + P(s'|a,s2)b(s2).....]
                                } 
                            }

                        } else if(sFinal == 2) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 1 || sInitial == 2) {
                                    summationForSFinal += (transition[3][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }

                        } else if(sFinal == 3) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 2 || sInitial == 3 || sInitial == 7) {
                                    summationForSFinal += (transition[3][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }
                        } else if(sFinal == 4) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 3 || sInitial == 4) {
                                    summationForSFinal += (transition[3][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }
                        } else if(sFinal == 5) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 1 || sInitial == 5 || sInitial == 9) {
                                    summationForSFinal += (transition[3][sInitial-1][sFinal-1]* gridUnknown[sInitial-1]);
                                } 
                            }

                        } else if(sFinal == 6) {
                            summationForSFinal = 0.0;

                        } else if(sFinal == 7) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 3 || sInitial == 7 || sInitial == 11) {
                                    summationForSFinal += (transition[3][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }
                        } else if(sFinal == 8) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 4 || sInitial == 7) {
                                    summationForSFinal += (transition[3][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }

                        } else if(sFinal == 9) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 5 || sInitial == 9) {
                                    summationForSFinal += (transition[3][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }

                        } else if(sFinal == 10) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 9 || sInitial == 10) {
                                    summationForSFinal += (transition[3][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }

                        } else if(sFinal == 11) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 7 || sInitial == 10 || sInitial == 11) {
                                    summationForSFinal += (transition[3][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }
                        } else if(sFinal == 12) {
                            for(int sInitial=1; sInitial<=12; sInitial++) {
                                if (sInitial == 11) {
                                    summationForSFinal += (transition[3][sInitial-1][sFinal-1] * gridUnknown[sInitial-1]);
                                } 
                            }
                        }
                        
                        //Multiplying summation with P(e|s')
                        NewGrid[sFinal-1] = gridObserved[obs.get(i)][sFinal-1]*summationForSFinal;   
                        
                    } 
                    gridUnknown = NewGrid;
                }

                //Normalizing gridUnknown after action is taken
                normalize(gridUnknown);
                normalize(NewGrid);
            }

            for (int i=0; i<12; i++) {
                BigDecimal bd = new BigDecimal(gridUnknown[i]).setScale(4, RoundingMode.HALF_UP);
                gridUnknown[i] = bd.doubleValue();
            }


        }   //----------------------------------------------- END of beliefStateGrid update where initial state is unknown ----------------------------
        else { 
            // ----------------------------------------------- START of beliefStategrid update where initial state is known ---------------------------
                
                // Calculate b'(s') = a P(e|s') [P(s'|a,s1)b(s1) + P(s'|a,s2)b(s2).....] for each s' in grid

                //traversing through all actions
                for(int i=0; i<actions.size(); i++) {
                    // Creating a clone of the grid in which changes will be made and after an action is taken, the new grid becomes gridKnown
                    double[] NewGrid = gridknown.clone();
                
                    //Looking at the action taken 
        
                    // if action is "UP"
                    if(actions.get(i).equals("Up")) {

                        //Calculating the summation part of the equation
                        for(int sFinal=1; sFinal<=12; sFinal++) {
                            
                            double summationForSFinal = 0.0;
                            if (sFinal ==1) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                        
                                    if (sInitial == 1 || sInitial == 2) {  //using only initial states that can lead to sFinal
                                        summationForSFinal += (transition[0][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                        //We now have summationForSFinal which is = [P(s'|a,s1)b(s1) + P(s'|a,s2)b(s2).....]
                                    } 
                                }

                            } else if(sFinal == 2) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 1 || sInitial == 2 || sInitial == 3) {
                                        summationForSFinal += (transition[0][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }

                            } else if(sFinal == 3) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 2 || sInitial == 4) {
                                        summationForSFinal += (transition[0][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }
                            } else if(sFinal == 4) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 3 || sInitial == 4) {
                                        summationForSFinal += (transition[0][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }
                            } else if(sFinal == 5) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 1 || sInitial == 5) {
                                        summationForSFinal += (transition[0][sInitial-1][sFinal-1]* gridknown[sInitial-1]);
                                    } 
                                }

                            } else if(sFinal == 6) {
                                summationForSFinal = 0.0;

                            } else if(sFinal == 7) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 3 || sInitial == 7) {
                                        summationForSFinal += (transition[0][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }
                            } else if(sFinal == 8) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 7 || sInitial == 4) {
                                        summationForSFinal += (transition[0][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }

                            } else if(sFinal == 9) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 5 || sInitial == 9 || sInitial == 10) {
                                        summationForSFinal += (transition[0][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }

                            } else if(sFinal == 10) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 9 || sInitial == 10 || sInitial == 11) {
                                        summationForSFinal += (transition[0][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }

                            } else if(sFinal == 11) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sFinal == 11 || sInitial == 10 || sInitial == 7) {
                                        summationForSFinal += (transition[0][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }
                            } else if(sFinal == 12) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 11) {
                                        summationForSFinal += (transition[0][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }
                            }
                            
                            //Multiplying summation with P(e|s')
                            NewGrid[sFinal-1] = gridObserved[obs.get(i)][sFinal-1]*summationForSFinal;  
                        }
                        // Getting rid of the old grid and swtiching to the new grid
                        gridknown = NewGrid;
                    }
        
                    // if action is "Down"
                    if(actions.get(i).equals("Down")) {
                    
                        System.out.println("action Down taken");
                        for(int sFinal=1; sFinal<=12; sFinal++) {
                            
                            double summationForSFinal = 0.0;


                            if (sFinal ==1) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    //Calculating the summation part of the equation using only initial states that can lead to sFinal
                                    if (sInitial == 1 || sInitial == 2 ||sFinal == 5) {  
                                        summationForSFinal += (transition[1][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                        //We now have summationForSFinal which is = [P(s'|a,s1)b(s1) + P(s'|a,s2)b(s2).....]
                                    } 
                                }

                            } else if(sFinal == 2) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 1 || sInitial == 2 || sInitial == 3) {
                                        summationForSFinal += (transition[1][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }

                            } else if(sFinal == 3) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 2 || sInitial == 3 || sFinal ==4) {
                                        summationForSFinal += (transition[1][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }
                            } else if(sFinal == 4) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 3 || sInitial == 4) {
                                        summationForSFinal += (transition[1][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }
                            } else if(sFinal == 5) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 5 || sInitial == 9) {
                                        summationForSFinal += (transition[1][sInitial-1][sFinal-1]* gridknown[sInitial-1]);
                                    } 
                                }

                            } else if(sFinal == 6) {
                                summationForSFinal = 0.0;

                            } else if(sFinal == 7) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 7 || sInitial == 11) {
                                        summationForSFinal += (transition[1][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }
                            } else if(sFinal == 8) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 7) {
                                        summationForSFinal += (transition[1][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }

                            } else if(sFinal == 9) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 9 || sInitial == 10) {
                                        summationForSFinal += (transition[1][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }

                            } else if(sFinal == 10) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 9 || sInitial == 10 || sInitial == 11) {
                                        summationForSFinal += (transition[1][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }

                            } else if(sFinal == 11) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 10) {
                                        summationForSFinal += (transition[1][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }
                            } else if(sFinal == 12) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 11) {
                                        summationForSFinal += (transition[1][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }
                            }
                            
                            //Multiplying summation with P(e|s')
                            NewGrid[sFinal-1] = gridObserved[obs.get(i)][sFinal-1]*summationForSFinal;   
                        }
                        // Getting rid of the old grid and swtiching to the new grid
                        gridknown = NewGrid;
                    }
        
                    // if action is "Left"
                    if(actions.get(i).equals("Left")) {
                    
                        //updating belief state grid values without normalization constant
                        for(int sFinal=1; sFinal<=12; sFinal++) {
        
                            double summationForSFinal = 0.0;


                            if (sFinal ==1) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    //Calculating the summation part of the equation using only initial states that can lead to sFinal
                                    if (sInitial == 1 || sInitial == 2 || sInitial == 5) {  
                                        summationForSFinal += (transition[2][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                        //We now have summationForSFinal which is = [P(s'|a,s1)b(s1) + P(s'|a,s2)b(s2).....]
                                    } 
                                }

                            } else if(sFinal == 2) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 2 || sInitial == 3) {
                                        summationForSFinal += (transition[2][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }

                            } else if(sFinal == 3) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 3 || sInitial ==4 || sInitial ==7) {
                                        summationForSFinal += (transition[2][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }
                            } else if(sFinal == 4) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 4) {
                                        summationForSFinal += (transition[2][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }
                            } else if(sFinal == 5) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 1 || sInitial == 5 || sInitial == 9) {
                                        summationForSFinal += (transition[2][sInitial-1][sFinal-1]* gridknown[sInitial-1]);
                                    } 
                                }

                            } else if(sFinal == 6) {
                                summationForSFinal = 0.0;

                            } else if(sFinal == 7) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 3 || sInitial == 7 || sInitial == 11) {
                                        summationForSFinal += (transition[2][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }
                            } else if(sFinal == 8) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 4) {
                                        summationForSFinal += (transition[2][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }

                            } else if(sFinal == 9) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 5 || sInitial == 9 || sInitial == 10) {
                                        summationForSFinal += (transition[2][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }

                            } else if(sFinal == 10) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 10 || sInitial == 11) {
                                        summationForSFinal += (transition[2][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }

                            } else if(sFinal == 11) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 7 || sInitial == 11) {
                                        summationForSFinal += (transition[2][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }
                            } else if(sFinal == 12) {
                                summationForSFinal = 0.0;
                            }
                            
                            //Multiplying summation with P(e|s')
                            NewGrid[sFinal-1] = gridObserved[obs.get(i)][sFinal-1]*summationForSFinal;   
                        }
                        // Getting rid of the old grid and swtiching to the new grid
                        gridknown = NewGrid;
                    
                    }
        
                    // if action is "Right"
                    if(actions.get(i).equals("Right")) {

                        //updating belief state grid values without normalization constant
                        for(int sFinal=1; sFinal<=12; sFinal++) {
        
                            double summationForSFinal = 0.0;

                            if (sFinal ==1) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    //Calculating the summation part of the equation using only initial states that can lead to sFinal
                                    if (sInitial == 1 || sInitial == 5) {  
                                        summationForSFinal += (transition[3][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                        //We now have summationForSFinal which is = [P(s'|a,s1)b(s1) + P(s'|a,s2)b(s2).....]
                                    } 
                                }

                            } else if(sFinal == 2) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 1 || sInitial == 2) {
                                        summationForSFinal += (transition[3][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }

                            } else if(sFinal == 3) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 2 || sInitial == 3 || sInitial == 7) {
                                        summationForSFinal += (transition[3][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }
                            } else if(sFinal == 4) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 3 || sInitial == 4) {
                                        summationForSFinal += (transition[3][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }
                            } else if(sFinal == 5) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 1 || sInitial == 5 || sInitial == 9) {
                                        summationForSFinal += (transition[3][sInitial-1][sFinal-1]* gridknown[sInitial-1]);
                                    } 
                                }

                            } else if(sFinal == 6) {
                                summationForSFinal = 0.0;

                            } else if(sFinal == 7) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 3 || sInitial == 7 || sInitial == 11) {
                                        summationForSFinal += (transition[3][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }
                            } else if(sFinal == 8) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 4 || sInitial == 7) {
                                        summationForSFinal += (transition[3][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }

                            } else if(sFinal == 9) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 5 || sInitial == 9) {
                                        summationForSFinal += (transition[3][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }

                            } else if(sFinal == 10) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 9 || sInitial == 10) {
                                        summationForSFinal += (transition[3][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }

                            } else if(sFinal == 11) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 7 || sInitial == 10 || sInitial == 11) {
                                        summationForSFinal += (transition[3][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }
                            } else if(sFinal == 12) {
                                for(int sInitial=1; sInitial<=12; sInitial++) {
                                    if (sInitial == 11) {
                                        summationForSFinal += (transition[3][sInitial-1][sFinal-1] * gridknown[sInitial-1]);
                                    } 
                                }
                            }
                            
                            //Multiplying summation with P(e|s')
                            NewGrid[sFinal-1] = gridObserved[obs.get(i)][sFinal-1]*summationForSFinal;   
                        } 
                        // Getting rid of the old grid and swtiching to the new grid
                        gridknown = NewGrid;
                    }

                    //Normalizing gridUnknown after action is taken
                    normalize(gridknown);
                    normalize(NewGrid);
                }

                for (int i=0; i<12; i++) {
                    BigDecimal bd = new BigDecimal(gridknown[i]).setScale(4, RoundingMode.HALF_UP);
                    gridknown[i] = bd.doubleValue();
                }

        }
        
        for (int i=3; i>=1; i--) {
            System.out.println(gridknown[(i*4)-4] + " | " + gridknown[(i*4)-3] + " | " + gridknown[(i*4)-2] + " | " + gridknown[(i*4)-1] );
        }
        System.out.println();

        for (int i=3; i>=1; i--) {
            System.out.println("" + gridUnknown[(i*4)-4] + " | " + gridUnknown[(i*4)-3] + " | " + gridUnknown[(i*4)-2] + " | " + gridUnknown[(i*4)-1] );
        }
        
    }

    public static double[] normalize(double[] array) {
        double sum = sum(array);
        for (int k = 0; k < array.length; k++) {
            array[k] = array[k] / sum;
        }
        return array;
    }

    public static double sum(double[] items) {
        double total = 0;
        for (double item : items) {
            total += item;
        }
        return total;
    }

    
}
