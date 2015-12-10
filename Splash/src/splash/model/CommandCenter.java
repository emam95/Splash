/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

import java.util.Stack;

/**
 *
 * @author Hesham
 */
public class CommandCenter {
    private static Stack<Command> past= new Stack<>();
    private static Stack<Command> fut = new Stack<>();
    
    public static void ExecuteCommand(Command cmd)
    {
        fut.clear();
        cmd.execute();
        past.push(cmd);
    }
    
    public static void Undo()
    {
        if(!past.isEmpty())
        {
            fut.push(past.pop());
            fut.peek().unexecute();
        }else System.out.println("Nothing to undo.");
    }
    public static void Redo()
    {
        if(!fut.isEmpty())
        {
            past.push(fut.pop());
            past.peek().execute();
        }else System.out.println("Nothing to redo.");
    }
}
