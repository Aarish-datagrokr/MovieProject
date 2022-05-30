package org.aarish.movies.servlets;
import org.json.JSONObject;
import org.aarish.movies.model.*;
import org.aarish.movies.repository.*;
import org.aarish.movies.exception.*;
import jakarta.servlet.http.*;
import java.io.*;
public class addMovie extends HttpServlet
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
BufferedReader br=request.getReader();
StringBuffer b=new StringBuffer();
String d;
while(true)
{
d=br.readLine();
if(d==null) break;
b.append(d);
}
String movieName=b.toString();
Movie movie=new Movie();
movie.setName(movieName);
MovieRepository movieRepository=new MovieRepository();
try
{
movieRepository.addMovie(movie);
}catch(RepoException daoException)
{
PrintWriter pw=response.getWriter();
JSONObject obj=new JSONObject();
String exception=daoException.getMessage();
obj.put("response",exception);
response.setContentType("application/json");
pw.print(obj);
pw.flush();
}
PrintWriter pw=response.getWriter();
JSONObject obj=new JSONObject();
obj.put("response","Movie added.");
response.setContentType("application/json");
pw.print(obj);
pw.flush();
}catch(Exception exception)
{
System.out.println(exception);
}
}
}