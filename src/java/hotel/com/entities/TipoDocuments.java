/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.com.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Estela
 */
@Entity
@Table(name = "tipo_documents")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDocuments.findAll", query = "SELECT t FROM TipoDocuments t")
    , @NamedQuery(name = "TipoDocuments.findByIdDocument", query = "SELECT t FROM TipoDocuments t WHERE t.idDocument = :idDocument")
    , @NamedQuery(name = "TipoDocuments.findByDocument", query = "SELECT t FROM TipoDocuments t WHERE t.document = :document")})
public class TipoDocuments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_document")
    private String idDocument;
    @Column(name = "document")
    private String document;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoDocument")
    private Collection<Clients> clientsCollection;

    public TipoDocuments() {
    }

    public TipoDocuments(String idDocument) {
        this.idDocument = idDocument;
    }

    public String getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(String idDocument) {
        this.idDocument = idDocument;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    @XmlTransient
    public Collection<Clients> getClientsCollection() {
        return clientsCollection;
    }

    public void setClientsCollection(Collection<Clients> clientsCollection) {
        this.clientsCollection = clientsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocument != null ? idDocument.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDocuments)) {
            return false;
        }
        TipoDocuments other = (TipoDocuments) object;
        if ((this.idDocument == null && other.idDocument != null) || (this.idDocument != null && !this.idDocument.equals(other.idDocument))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.com.entities.TipoDocuments[ idDocument=" + idDocument + " ]";
    }
    
}
