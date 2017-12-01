/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.com.entities.servlets;

import hotel.com.entities.Clients;
import hotel.com.entities.Empleats;
import hotel.com.entities.Habitacions;

import hotel.com.entities.Nacionalitats;
import hotel.com.entities.TipoDocuments;
import hotel.com.entities.TipoHabitacions;
import java.io.IOException;
import static java.lang.Integer.parseInt;

import java.text.SimpleDateFormat;

import java.util.Date;
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
        String opcio = request.getParameter("opcio");
        String missatge = "";
        String url = "";
        String path = "";

        switch (opcio) {
            case "login": {
                String usuari = request.getParameter("usuari");
                String contrasenya = request.getParameter("contrasenya");

                Empleats empleat = confirmaLogin(usuari, contrasenya);
                if (empleat == null) {
                    request.setAttribute("missatge", "Usuari o contrasenya incorrecta");
                    path = "/dadesGenerals/index.jsp";

                } else {
                    //   List<TipoHabitacions> llistaHabitacions = new ArrayList<TipoHabitacions>();
                    List<TipoHabitacions> llistaHabitacions = tornaHabitacions();
                    request.setAttribute("llistaHabitacions", llistaHabitacions);
                    if (request.getSession().getAttribute("empleat") == null) {
                        request.getSession().setAttribute(("empleat"), empleat);

                    }
                    path = "/dadesGenerals/home.jsp";
                }
                request.getRequestDispatcher(path).forward(request, response);
                break;
            }
            case "carregaDades": {
                String num = request.getParameter("numHab");
                Integer numHab = Integer.parseInt(num);
                Habitacions habitacio = tornaHabitacio(numHab);
                List<Nacionalitats> llistaNac = tornaNacionalitats();
                List<TipoDocuments> llistaDoc = tornaDocuments();
                request.setAttribute("habitacio", habitacio);
                request.setAttribute("llistaNac", llistaNac);
                request.setAttribute("llistaDoc", llistaDoc);
                path= "/dadesGenerals/formCheckIn.jsp";
                request.getRequestDispatcher(path).forward(request, response);
                break;
            }
            case "cancelar":{
               List<TipoHabitacions> llistaHabitacions = tornaHabitacions();
               request.setAttribute("llistaHabitacions", llistaHabitacions); 
               path = "/dadesGenerals/home.jsp";
               request.getRequestDispatcher(path).forward(request, response);
            }
            case "altaEstancia":{
           
                String nom = request.getParameter("nom");
                String cognom = request.getParameter("cognom");
                String congnom2 =request.getParameter("cognom2");
                String tipoDocument = request.getParameter("tipoDocument");
                TipoDocuments tDocument = new TipoDocuments(tipoDocument);
                String numDocument = request.getParameter("numDocument");
                String dataExpedicioDoc = request.getParameter("dataExpedicio");
                String nacionalitat = request.getParameter("nacionalitat");
                Nacionalitats nacio = new Nacionalitats();
                nacio.setIdNacionalitat(parseInt(nacionalitat));
                String dataNaixement = request.getParameter("dataNaixement");
                String sexe = request.getParameter("sexe");
                String observacions = request.getParameter("observacions");
                Clients nou = null;
                SimpleDateFormat plantillaData = new SimpleDateFormat("yyyy-MM-dd");
                Date fetxaExpDoc = null;
                Date fetxaNaixement = null;
                try{
                   fetxaExpDoc = plantillaData.parse(dataExpedicioDoc);
                   fetxaNaixement = plantillaData.parse(dataNaixement);  
                }catch(Exception e){
                  System.out.println("Alguna de les dates te un format incorrecte");  
                }
                nou = new Clients(numDocument,fetxaExpDoc,nom,cognom,congnom2,fetxaNaixement,sexe,nacio,tDocument);
                  

//                nou.setNom(nom);
//                nou.setCognom1(cognom);
//                if(congnom2!=null){
//                nou.setCognom2(congnom2);
//                }
//                nou.setTipoDocument(tDocument);
//                nou.setNumDocument(numDocument);
                
                
                insertar(nou);
                
                
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

    //Loguin del empleat
    private Empleats confirmaLogin(String usuari, String contrasenya) {
        TypedQuery<Empleats> a = null;
        EntityManagerFactory emf = null;
        EntityManager em = null;
        Empleats empleat = null;

        try {
            emf = Persistence.createEntityManagerFactory("HotelPU");
            em = emf.createEntityManager();
            a = em.createQuery("select e from Empleats e where e.nom =:pNom and e.contrasenya=:pContrasenya", Empleats.class);
            a.setParameter("pNom", usuari);
            a.setParameter("pContrasenya", contrasenya);
            empleat = a.getSingleResult();

        } catch (Exception e) {
            System.err.println("Error: " + e);
        } finally {
            em.close();
            emf.close();
        }

        return empleat;

    }

    //Torna el llistat d'habitacions per la pàgina principal
    private List<TipoHabitacions> tornaHabitacions() {
        List<TipoHabitacions> llista = null;
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("HotelPU");
            em = emf.createEntityManager();
            TypedQuery<TipoHabitacions> consulta = em.createQuery("select distinct h from Habitacions h join TipoHabitacions t order by h.numero", TipoHabitacions.class);
            llista = consulta.getResultList();

        } catch (Exception e) {
            System.err.println("Error: " + e);
        } finally {
            em.close();
            emf.close();
        }
        return llista;
    }

    // Torna una habitacio en concret
    private Habitacions tornaHabitacio(int numHab) {
        TypedQuery<Habitacions> a = null;
        EntityManagerFactory emf = null;
        EntityManager em = null;
        Habitacions habitacio = null;
        try {
            emf = Persistence.createEntityManagerFactory("HotelPU");
            em = emf.createEntityManager();
            a = em.createQuery("select h from Habitacions h join TipoHabitacions t where h.numero = :pNum", Habitacions.class);
            a.setParameter("pNum", numHab);
            habitacio = a.getSingleResult();

        } catch (Exception e) {
            System.err.println("Error: " + e);
        } finally {

        }
        return habitacio;
    }

    private List<Nacionalitats> tornaNacionalitats() {
        List<Nacionalitats> llistaNac = null;
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("HotelPU");
            em = emf.createEntityManager();
            TypedQuery<Nacionalitats> consulta = em.createQuery("select n from Nacionalitats n order by n.nacionalitat asc", Nacionalitats.class);
            llistaNac = consulta.getResultList();
        } catch (Exception e) {
            System.err.println("Error: " + e);
        } finally {
            em.close();
            emf.close();
        }
        return llistaNac;

    }

    // Torna els tipos de documents per emplenar select.
    private List<TipoDocuments> tornaDocuments() {
        List<TipoDocuments> llistaDocs = null;
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("HotelPU");
            em = emf.createEntityManager();
            TypedQuery<TipoDocuments> consulta = em.createQuery("select d from TipoDocuments d", TipoDocuments.class);
            llistaDocs = consulta.getResultList();
        } catch (Exception e) {
            System.err.println("Error: " + e);
        } finally {
            em.close();
            emf.close();
        }
        return llistaDocs;
    }
    
    // Insert d'objectes
   public void insertar (Object object){
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("HotelPU");
       EntityManager em = emf.createEntityManager();
         em.getTransaction().begin();
         em.persist(object);
         em.getTransaction().commit();
            em.close();
        emf.close();
   }

   
    private void If(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }
}
