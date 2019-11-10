/*
 * #### requires ps-version 3.0 ####
 * <#
 *    Version:        0.1
 *    Author:         Camilo Nieto
 *    Creation Date:  Thursday, September 19th 2019, 12:48:34 pm
 *    File: App.java
 *    Copyright (c) 2019 Your Company
 * 
 * .LICENSE
 * Free software created by Camilo Esteban Nieto Barrera
 *  
 */

package app;

/**
 * In case you want two agents to play each other use app.noplayer.Environment
 * In case you want that your agent play against another player use app.multiplayer.Environment
 */
import app.multiplayer.Environment;

public class App {
    public static void main(String[] args) throws Exception {
        Environment e = new Environment();
        e.init();
    }
}