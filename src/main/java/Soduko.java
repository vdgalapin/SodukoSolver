/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author galap
 */
public class Soduko{
    public static void main(String[] args) throws Exception{
        
        
        
 
            System.out.println("Soduko Board");
         
            SodukoBoard board1 = new SodukoBoard();
            System.out.println("Solving...");
            board1.solve();
            board1.PrintSoduko();
       
    }
}
