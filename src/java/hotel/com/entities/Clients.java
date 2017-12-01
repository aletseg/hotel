/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.com.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Estela
 */
@Entity
@Table(name = "clients")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clients.findAll", query = "SELECT c FROM Clients c")
    , @NamedQuery(name = "Clients.findByIdClient", query = "SELECT c FROM Clients c WHERE c.idClient = :idClient")
    , @NamedQuery(name = "Clients.findByNumDocument", query = "SELECT c FROM Clients c WHERE c.numDocument = :numDocument")
    , @NamedQuery(name = "Clients.findByDataExpedicioDoc", query = "SELECT c FROM Clients c WHERE c.dataExpedicioDoc = :dataExpedicioDoc")
    , @NamedQuery(name = "Clients.findByNom", query = "SELECT c FROM Clients c WHERE c.nom = :nom")
    , @NamedQuery(name = "Clients.findByCognom1", query = "SELECT c FROM Clients c WHERE c.cognom1 = :cognom1")
    , @NamedQuery(name = "Clients.findByCognom2", query = "SELECT c FROM Clients c WHERE c.cognom2 = :cognom2")
    , @NamedQuery(name = "Clients.findByDataNaixement", query = "SELECT c FROM Clients c WHERE c.dataNaixement = :dataNaixement")
    , @NamedQuery(name = "Clients.findBySexe", query = "SELECT c FROM Clients c WHERE c.sexe = :sexe")
    , @NamedQuery(name = "Clients.findByObservacions", query = "SELECT c FROM Clients c WHERE c.observacions = :observacions")})
public class Clients implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_client")
    private Integer idClient;
    @Basic(optional = false)
    @Column(name = "num_document")
    private String numDocument;
    @Basic(optional = false)
    @Column(name = "data_expedicio_doc")
    @Temporal(TemporalType.DATE)
    private Date dataExpedicioDoc;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "cognom1")
    private String cognom1;
    @Column(name = "cognom2")
    private String cognom2;
    @Basic(optional = false)
    @Column(name = "data_naixement")
    @Temporal(TemporalType.DATE)
    private Date dataNaixement;
    @Basic(optional = false)
    @Column(name = "sexe")
    private String sexe;
    @Column(name = "observacions")
    private String observacions;
    @JoinColumn(name = "id_nacio", referencedColumnName = "id_nacionalitat")
    @ManyToOne(optional = false)
    private Nacionalitats idNacio;
    @JoinColumn(name = "tipo_document", referencedColumnName = "id_document")
    @ManyToOne(optional = false)
    private TipoDocuments tipoDocument;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientId")
    private Collection<EstanciesHostes> estanciesHostesCollection;
    @OneToMany(mappedBy = "idClientCom")
    private Collection<Comentaris> comentarisCollection;

    public Clients() {
    }

 

    public Clients(String numDocument, Date dataExpedicioDoc, String nom, String cognom1, String cognom2, Date dataNaixement, String sexe, Nacionalitats idNacio, TipoDocuments tipoDocument) {
        this.numDocument = numDocument;
        this.dataExpedicioDoc = dataExpedicioDoc;
        this.nom = nom;
        this.cognom1 = cognom1;
        this.cognom2 = cognom2;
        this.dataNaixement = dataNaixement;
        this.sexe = sexe;
        this.idNacio = idNacio;
        this.tipoDocument = tipoDocument;
    }

    public Clients(String numDocument, Date dataExpedicioDoc, String nom, String cognom1, String cognom2, Date dataNaixement, String sexe, String observacions, Nacionalitats idNacio, TipoDocuments tipoDocument) {
        this.numDocument = numDocument;
        this.dataExpedicioDoc = dataExpedicioDoc;
        this.nom = nom;
        this.cognom1 = cognom1;
        this.cognom2 = cognom2;
        this.dataNaixement = dataNaixement;
        this.sexe = sexe;
        this.observacions = observacions;
        this.idNacio = idNacio;
        this.tipoDocument = tipoDocument;
    }
    

   

    

  
    
    

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getNumDocument() {
        return numDocument;
    }

    public void setNumDocument(String numDocument) {
        this.numDocument = numDocument;
    }

    public Date getDataExpedicioDoc() {
        return dataExpedicioDoc;
    }

    public void setDataExpedicioDoc(Date dataExpedicioDoc) {
        this.dataExpedicioDoc = dataExpedicioDoc;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom1() {
        return cognom1;
    }

    public void setCognom1(String cognom1) {
        this.cognom1 = cognom1;
    }

    public String getCognom2() {
        return cognom2;
    }

    public void setCognom2(String cognom2) {
        this.cognom2 = cognom2;
    }

    public Date getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement(Date dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getObservacions() {
        return observacions;
    }

    public void setObservacions(String observacions) {
        this.observacions = observacions;
    }

    public Nacionalitats getIdNacio() {
        return idNacio;
    }

    public void setIdNacio(Nacionalitats idNacio) {
        this.idNacio = idNacio;
    }

    public TipoDocuments getTipoDocument() {
        return tipoDocument;
    }

    public void setTipoDocument(TipoDocuments tipoDocument) {
        this.tipoDocument = tipoDocument;
    }

    @XmlTransient
    public Collection<EstanciesHostes> getEstanciesHostesCollection() {
        return estanciesHostesCollection;
    }

    public void setEstanciesHostesCollection(Collection<EstanciesHostes> estanciesHostesCollection) {
        this.estanciesHostesCollection = estanciesHostesCollection;
    }

    @XmlTransient
    public Collection<Comentaris> getComentarisCollection() {
        return comentarisCollection;
    }

    public void setComentarisCollection(Collection<Comentaris> comentarisCollection) {
        this.comentarisCollection = comentarisCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClient != null ? idClient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clients)) {
            return false;
        }
        Clients other = (Clients) object;
        if ((this.idClient == null && other.idClient != null) || (this.idClient != null && !this.idClient.equals(other.idClient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.com.entities.Clients[ idClient=" + idClient + " ]";
    }
    
}
