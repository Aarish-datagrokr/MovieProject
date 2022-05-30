package org.aarish.movies.servlets;
import org.json.JSONObject;
import org.aarish.movies.model.*;
import org.aarish.movies.repository.*;
import org.aarish.movies.exception.*;
import java.util.*;
import jakarta.servlet.http.*;

import java.io.*;
import com.google.gson.*;
public class getMovies extends HttpServlet
{
@Override
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
}catch(IOException e)
{
System.out.println(e);
}
}
@Override
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
try
{
PrintWriter pw=response.getWriter();
MovieRepository movieRepository=new MovieRepository();
List<String> movies=movieRepository.getMovies();

response.setContentType("application/json");
Gson gson=new Gson();
String json=gson.toJson(movies);
pw.print(json);
pw.flush();
}catch(RepoException io)
{
PrintWriter pw=response.getWriter();
JSONObject obj=new JSONObject();
String exception=io.getMessage();
obj.put("response",exception);
response.setContentType("application/json");
pw.print(obj);
pw.flush();
}
}catch(Exception exception)
{
System.out.println(exception);
}
}
}