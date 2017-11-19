/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.com.entities.servlets;

import hotel.com.entities.Empleats;
import hotel.com.entities.JPACryptoConverter;
import hotel.com.entities.TipoHabitacions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Estela
 */
@WebServlet(name = "dadesGestio", urlPatterns = {"/dadesGestio"})
public class dadesGestio extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String clau = "Aquaris";
        String opcio = request.getParameter("opcio");
        String missatge = "";
        String url = "";
        String path= "";
        
        switch (opcio){
            case "login":{
              String usuari = request.getParameter("usuari");
              String contrasenya = request.getParameter("contrasenya");

              Empleats empleat= confirmaLogin(usuari, contrasenya);
              if(empleat == null){
                request.setAttribute("missatge","Usuari o contrasenya incorrecta");
                path ="/dadesGenerals/index.jsp";
                    
              }else{
               //   List<TipoHabitacions> llistaHabitacions = new ArrayList<TipoHabitacions>();
                     List<TipoHabitacions> llistaHabitacions = tornaHabitacions();
                  request.setAttribute("llistaHabitacions",llistaHabitacions);
                  if(request.getSession().getAttribute("empleat")== null){
                     request.getSession().setAttribute(("empleat"),empleat);

                  }
                  path ="/dadesGenerals/home.jsp";
              }
              request.getRequestDispatcher(path).forward(request, response);
              break;
            }
        }
    }
    
    
 protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private Empleats confirmaLogin(String usuari, String contrasenya){
         TypedQuery <Empleats> a = null;
         EntityManagerFactory emf = null;
         EntityManager em = null;
         Empleats empleat = null;
         
         try {
            emf = Persistence.createEntityManagerFactory("HotelPU");
            em = emf.createEntityManager();
            a = em.createQuery("select e from Empleats e where e.nom =:pNom and e.contrasenya=:pContrasenya",Empleats.class);
            a.setParameter("pNom",usuari); 
            a.setParameter("pContrasenya",contrasenya);
            empleat = a.getSingleResult();
           
         } catch (Exception e) {
             System.err.println("Error: "+e); 
         } finally {
             em.close();
            emf.close();
         }
         
        
        return empleat;
        
    }
    
    private List<TipoHabitacions> tornaHabitacions(){
        List <TipoHabitacions> llista = null;
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try{
             emf = Persistence.createEntityManagerFactory("HotelPU");
             em = emf.createEntityManager();
            TypedQuery <TipoHabitacions> consulta = em.createQuery("select distinct h from Habitacions h join TipoHabitacions t order by h.numero", TipoHabitacions.class);
                llista = consulta.getResultList(); 
        }catch(Exception e){
          System.err.println("Error: "+e);   
        }finally{
           em.close();
           emf.close();
        }
        return llista;
    }

    private void If(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
