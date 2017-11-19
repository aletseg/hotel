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
@Table(name = "nacionalitats")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nacionalitats.findAll", query = "SELECT n FROM Nacionalitats n")
    , @NamedQuery(name = "Nacionalitats.findByIdNacionalitat", query = "SELECT n FROM Nacionalitats n WHERE n.idNacionalitat = :idNacionalitat")
    , @NamedQuery(name = "Nacionalitats.findByNacionalitat", query = "SELECT n FROM Nacionalitats n WHERE n.nacionalitat = :nacionalitat")
    , @NamedQuery(name = "Nacionalitats.findByEuropa", query = "SELECT n FROM Nacionalitats n WHERE n.europa = :europa")})
public class Nacionalitats implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_nacionalitat")
    private Integer idNacionalitat;
    @Basic(optional = false)
    @Column(name = "nacionalitat")
    private String nacionalitat;
    @Column(name = "europa")
    private Boolean europa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNacio")
    private Collection<Clients> clientsCollection;

    public Nacionalitats() {
    }

    public Nacionalitats(Integer idNacionalitat) {
        this.idNacionalitat = idNacionalitat;
    }

    public Nacionalitats(Integer idNacionalitat, String nacionalitat) {
        this.idNacionalitat = idNacionalitat;
        this.nacionalitat = nacionalitat;
    }

    public Integer getIdNacionalitat() {
        return idNacionalitat;
    }

    public void setIdNacionalitat(Integer idNacionalitat) {
        this.idNacionalitat = idNacionalitat;
    }

    public String getNacionalitat() {
        return nacionalitat;
    }

    public void setNacionalitat(String nacionalitat) {
        this.nacionalitat = nacionalitat;
    }

    public Boolean getEuropa() {
        return europa;
    }

    public void setEuropa(Boolean europa) {
        this.europa = europa;
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
        hash += (idNacionalitat != null ? idNacionalitat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nacionalitats)) {
            return false;
        }
        Nacionalitats other = (Nacionalitats) object;
        if ((this.idNacionalitat == null && other.idNacionalitat != null) || (this.idNacionalitat != null && !this.idNacionalitat.equals(other.idNacionalitat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.com.entities.Nacionalitats[ idNacionalitat=" + idNacionalitat + " ]";
    }
    
}
