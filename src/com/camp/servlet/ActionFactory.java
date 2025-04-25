package com.camp.servlet;

public class ActionFactory {
   //ΩÃ±€≈Ê
   private ActionFactory(){};

   public static Action getAction(String cmd){
      Action a;
      System.out.println(cmd);
      switch (cmd) {
      case "detail":
    	  a=new DetailAction();
    	  break;
      case "searchResult":
         a=new SearchResultAction();
         break;
      default:
         a=new MainUIAction();
         break;
      }
      return a;
   }
}
