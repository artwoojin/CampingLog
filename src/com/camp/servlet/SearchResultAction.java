package com.camp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class SearchResultAction implements Action {

   @Override
   public String execute(HttpServletRequest request) throws ServletException, IOException {
      String page="searchResult.html";
      //처리 데이터를 추출
      String searchTerm=request.getParameter("searchTerm");
      
      
      
      return page;
   }
}
