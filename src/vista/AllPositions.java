/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author Ariel
 */
public class AllPositions {
    
    public int getPosX(int id){
        int res=0;
        switch (id){
            case 0: res=220; break;
            case 1: res=300; break;
            case 2: res=430; break;
            case 3: res=370; break;
            case 4: res=180; break;
            case 5: res=345; break;
            case 6: res=371; break;
        }
        return res;
    }
    public int getPosY(int id){
        int res=0;
        switch (id){
            case 0: res=310; break;
            case 1: res=350; break;
            case 2: res=370; break;
            case 3: res=230; break;
            case 4: res=60; break;
            case 5: res=425; break;
            case 6: res=550; break;
        }
        return res;
    }
}
