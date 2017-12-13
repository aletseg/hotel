/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.com.entities.servlets;

import hotel.com.entities.Clients;
import hotel.com.entities.Empleats;
import hotel.com.entities.Estancies;
import hotel.com.entities.EstanciesHostes;
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
import javax.persistence.criteria.CriteriaQuery;
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
                if(num == ""){
                    request.setAttribute("Error", "Error al clicar la habitació");
                    List<TipoHabitacions> llistaHabitacions = tornaHabitacions();
                    request.setAttribute("llistaHabitacions", llistaHabitacions); 
                    path = "/dadesGenerals/home.jsp";
                }else{
                Integer numHab = Integer.parseInt(num);
                Habitacions habitacio = tornaHabitacio(numHab);
                List<Nacionalitats> llistaNac = tornaNacionalitats();
                List<TipoDocuments> llistaDoc = tornaDocuments();
                request.setAttribute("habitacio", habitacio);
                request.setAttribute("llistaNac", llistaNac);
                request.setAttribute("llistaDoc", llistaDoc);
                path= "/dadesGenerals/formCheckIn.jsp";
                }
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
                Clients nouClient = null;
                SimpleDateFormat plantillaData = new SimpleDateFormat("yyyy-MM-dd");
                Date fetxaExpDoc = null;
                Date fetxaNaixement = null;
                try{
                   fetxaExpDoc = plantillaData.parse(dataExpedicioDoc);
                   fetxaNaixement = plantillaData.parse(dataNaixement);  
                }catch(Exception e){
                  System.out.println("Alguna de les dates te un format incorrecte");  
                }
                nouClient = new Clients(numDocument,fetxaExpDoc,nom,cognom,congnom2,fetxaNaixement,sexe,nacio,tDocument);
                int numHab = parseInt(request.getParameter("numHab"));
                Habitacions numHabitacio = new Habitacions(numHab);
                Date data = new Date();
                Estancies estancia = new Estancies(data,numHabitacio);

                Clients clientIns = insertarTornaClient(nouClient);
                Estancies EstanciaIns = insertarTornaEstancia(estancia);
                
                
              //  Clients clientCercat = cercaClient(numDocument);
            //  insertar(estancia);
             //   Estancies trobaEstancia = cercaEstancia(numHabitacio, data);
                if(clientIns != null && EstanciaIns != null ){
                    EstanciesHostes estanciaHoste = new EstanciesHostes(data, EstanciaIns, clientIns);
                    EstanciesHostes estanciaHosteNova = insertarTornaEstanciaHostes(estanciaHoste);
                     if(estanciaHosteNova.getIdEstanciaHoste() != null){
                       canviaEstatHabitacio(numHabitacio, "Ocupat");  
                    }
                }
                missatge = "S'ha insertat correctament el client";
                request.setAttribute("client",clientIns);
                request.setAttribute("missatge", missatge);
                List<TipoHabitacions> llistaHabitacions = tornaHabitacions();
                request.setAttribute("llistaHabitacions", llistaHabitacions);
                path= "/dadesGenerals/home.jsp";
                request.getRequestDispatcher(path).forward(request, response);
                break;
            }//Final alta estancia
            
            case "carregaEstanciaBaixa":{
                String num = request.getParameter("numHab");
                if(num == ""){
                    request.setAttribute("Error", "Error al clicar la habitació");
                    List<TipoHabitacions> llistaHabitacions = tornaHabitacions();
                    request.setAttribute("llistaHabitacions", llistaHabitacions); 
                    path = "/dadesGenerals/home.jsp";
                }else{
             
             Integer numHab = Integer.parseInt(num);
             Habitacions numHabitacioCercat = tornaHabitacio(numHab); 
             Estancies numEstanciaHab = tornaEstancia(numHabitacioCercat);
             EstanciesHostes estanciaHoste = tornaEstanciaHoste(numEstanciaHab);
             Date dataBaixa = new Date();
             baixaEstanciaHoste(estanciaHoste,dataBaixa);
             baixaEstancia(numEstanciaHab,dataBaixa);
             canviaEstatHabitacio(numHabitacioCercat,"Lliure");
             List<TipoHabitacions> llistaHabitacions = tornaHabitacions();
             request.setAttribute("llistaHabitacions", llistaHabitacions);
             path= "/dadesGenerals/home.jsp";
                }
             request.getRequestDispatcher(path).forward(request, response); 
             break;   
            }// Final CarregaEstancia
            
            case "mostraFitxa":{
              String num = request.getParameter("numHab");
               if(num == ""){
                    request.setAttribute("Error", "Error al clicar la habitació");
                    List<TipoHabitacions> llistaHabitacions = tornaHabitacions();
                    request.setAttribute("llistaHabitacions", llistaHabitacions); 
                    path = "/dadesGenerals/home.jsp";
                }else{
             Integer numHab = Integer.parseInt(num);
             Habitacions numHabitacioCercat = tornaHabitacio(numHab); 
             Estancies numEstanciaHab = tornaEstancia(numHabitacioCercat);
             EstanciesHostes estanciaHoste = tornaEstanciaHoste(numEstanciaHab);
             Clients clientBaixa = tornaClientEstancia(estanciaHoste);
             if(numEstanciaHab != null && estanciaHoste != null && clientBaixa != null){
                 path = "dadesGenerals/dadesClient.jsp";
                 request.setAttribute("habitacio", numHabitacioCercat);
                 request.setAttribute("estanciaHoste", estanciaHoste);
                 request.setAttribute("client", clientBaixa);
                 request.setAttribute("titol", "Baixa Client"); 
             }else{
                List<TipoHabitacions> llistaHabitacions = tornaHabitacions();
               request.setAttribute("llistaHabitacions", llistaHabitacions);
               request.setAttribute("missatge","Aquesta habitació no es pot donar de baixa, no té cap client");
               path = "/dadesGenerals/home.jsp";
               
             }
               }
             request.getRequestDispatcher(path).forward(request, response); 
             break;   
              
            }
            
            case "canviarUsuari":{
             //  request.getSession().removeAttribute("empleat"); 
              request.getSession().removeAttribute("empleat");
              response.sendRedirect("index.html");
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
            em.close();
            emf.close();
        }
        return habitacio;
    }
    // Torna el Llistat de nacionalitats
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
   
   public Estancies insertarTornaEstancia(Estancies estancia){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HotelPU");
        EntityManager em = emf.createEntityManager();
         em.getTransaction().begin();
         em.persist(estancia);
         em.flush();
         em.getTransaction().commit();         
         em.close();
         emf.close();
         return estancia;
   }
   // Inserta En estancies hosta i torna la tuppla creada.
    public EstanciesHostes insertarTornaEstanciaHostes(EstanciesHostes estanciaHoste){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HotelPU");
        EntityManager em = emf.createEntityManager();
         em.getTransaction().begin();
         em.persist(estanciaHoste);
         em.flush();
         em.getTransaction().commit();
         em.close();
         emf.close();
         return estanciaHoste;
   }
   
   public Clients insertarTornaClient(Clients client){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HotelPU");
        EntityManager em = emf.createEntityManager();
         em.getTransaction().begin();
         em.persist(client);
         em.flush();
         em.getTransaction().commit();
         em.close();
         emf.close();
         return client;
   }
   
   
   // cercar Client per numDocument
   private Clients cercaClient(String numdocument){
        TypedQuery<Clients> a = null;
        EntityManagerFactory emf = null;
        EntityManager em = null;
        Clients client = null;
        try {
            emf = Persistence.createEntityManagerFactory("HotelPU");
            em = emf.createEntityManager();
            a = em.createQuery("select c from Clients c where c.numDocument=:pNumDocument", Clients.class);
            a.setParameter("pNumDocument", numdocument);
            client = a.getSingleResult();

        } catch (Exception e) {
            System.err.println("Error: " + e);
        } finally {
            em.close();
            emf.close();
        }
       return client;
   }
   
    private Estancies cercaEstancia(Habitacions numHabitacio, Date data){
        TypedQuery<Estancies> a = null;
        EntityManagerFactory emf = null;
        EntityManager em = null;
        Estancies estancia = null;
        try {
            emf = Persistence.createEntityManagerFactory("HotelPU");
            em = emf.createEntityManager();
            a = em.createQuery("select e from Estancies e where e.dataEntrada=:pDataEntrada  and e.numHabitacio =:pNumHabitacio", Estancies.class);
            a.setParameter("pNumHabitacio", numHabitacio);
            a.setParameter("pDataEntrada",data);
            estancia = a.getSingleResult();

        } catch (Exception e) {
            System.err.println("Error: " + e);
        } finally {
            em.close();
            emf.close();
        }
       return estancia;
   }
    

    public void canviaEstatHabitacio(Habitacions habitacio, String estat){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HotelPU");
        EntityManager em = emf.createEntityManager();
        Habitacions estatHabitacio = em.find(Habitacions.class, habitacio.getNumero());
        estatHabitacio.setEstat(estat);
        em.getTransaction().begin();
        em.merge(estatHabitacio);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

  //  Cercar Estancia per el num Habitacio
    private Estancies tornaEstancia(Habitacions numHabitacio){
        
        TypedQuery<Integer> a = null;
        TypedQuery<Estancies> b = null;
        EntityManagerFactory emf = null;
        EntityManager em = null;
        Integer num = 0;
        Estancies estancia = null;
        try {
            emf = Persistence.createEntityManagerFactory("HotelPU");
            em = emf.createEntityManager();          
                        
            a = em.createQuery("select max(e.idEstancia) from Estancies e where e.numHabitacio=:pNumHabitacio", Integer.class);
            //"select e, max(e.idEstancia) from Estancies e where e.numHabitacio=:pNumHabitacio"
            a.setParameter("pNumHabitacio", numHabitacio);          
            num = a.getSingleResult();
            b = em.createQuery("select e from Estancies e where e.idEstancia=:pNum", Estancies.class);
            b.setParameter("pNum", num);
            estancia =  b.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error: " + e);
        } finally {
            em.close();
            emf.close();
        }
       return estancia;
    }
    

    
    //Torna una estanciaHostes pasant una Estancia
    private EstanciesHostes tornaEstanciaHoste(Estancies estancia){
        TypedQuery<EstanciesHostes> a = null;
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EstanciesHostes estanciaHoste = null;
        try {
            emf = Persistence.createEntityManagerFactory("HotelPU");
            em = emf.createEntityManager();
            a = em.createQuery("select e from EstanciesHostes e where e.estanciaId =:pEstancia", EstanciesHostes.class);
            a.setParameter("pEstancia", estancia);          
            estanciaHoste = a.getSingleResult();

        } catch (Exception e) {
            System.err.println("Error: " + e);
        } finally {
            em.close();
            emf.close();
        }
       return estanciaHoste;
    }
    
    // Cerca el client per la estancia
    private Clients tornaClientEstancia(EstanciesHostes estanciaHoste){
        TypedQuery<Clients> a = null;
        EntityManagerFactory emf = null;
        EntityManager em = null;
        Clients client= null;
        try {
            emf = Persistence.createEntityManagerFactory("HotelPU");
            em = emf.createEntityManager();
            a = em.createQuery("select c from Clients c where c.estanciesHostesCollection=:pEstanciaHoste", Clients.class);
            a.setParameter("pEstanciaHoste", estanciaHoste);          
            client = a.getSingleResult();

        } catch (Exception e) {
            System.err.println("Error: " + e);
        } finally {
            em.close();
            emf.close();
        }
       return client;
    }
    
    //Afeix data sortida a la EstanciHoste
    public void baixaEstanciaHoste(EstanciesHostes estanciaHostes, Date dataBaixa){
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("HotelPU");
       EntityManager em = emf.createEntityManager();
       EstanciesHostes numEstanciaHoste = em.find(EstanciesHostes.class, estanciaHostes.getIdEstanciaHoste());
       numEstanciaHoste.setDataSortida(dataBaixa);
       em.getTransaction().begin();
        em.merge(numEstanciaHoste);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
    
   // Afegeix Data Baixa a la Estancia
    public void baixaEstancia(Estancies estancia, Date dataBaixa){
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("HotelPU");
      EntityManager em = emf.createEntityManager(); 
      Estancies idEstancia = em.find(Estancies.class, estancia.getIdEstancia());
      idEstancia.setDataSortida(dataBaixa);
      em.getTransaction().begin();
      em.merge(idEstancia);
      em.getTransaction().commit();
      em.close();
      emf.close();
    }

   
    private void If(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }
}
