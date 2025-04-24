package com.camp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.camp.model.TagDAO;
import com.camp.model.TagVO;

public class TagListAction implements Action {
	  @Override
	  public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int categoryId = Integer.parseInt(request.getParameter("categoryId"));

	    TagDAO dao = new TagDAO();
	    List<TagVO> tags = dao.getTagsByCategory(categoryId);

	    JsonArray arr = new JsonArray();
	    for (TagVO tag : tags) {
	      JsonObject obj = new JsonObject();
	      obj.addProperty("tagId", tag.getTagId());
	      obj.addProperty("tagName", tag.getTagName());
	      arr.add(obj);
	    }

	    response.setContentType("application/json; charset=UTF-8");
	    response.getWriter().print(new Gson().toJson(tags)); // List<TagVO>
	    return null;

	    
	  }
	}
