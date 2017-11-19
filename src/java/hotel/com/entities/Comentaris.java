/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.com.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Estela
 */
@Entity
@Table(name = "comentaris")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comentaris.findAll", query = "SELECT c FROM Comentaris c")
    , @NamedQuery(name = "Comentaris.findByIdComentari", query = "SELECT c FROM Comentaris c WHERE c.idComentari = :idComentari")
    , @NamedQuery(name = "Comentaris.findByFetxa", query = "SELECT c FROM Comentaris c WHERE c.fetxa = :fetxa")})
public class Comentaris implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_comentari")
    private Integer idComentari;
    @Column(name = "fetxa")
    @Temporal(TemporalType.DATE)
    private Date fetxa;
    @JoinColumn(name = "id_client_com", referencedColumnName = "id_client")
    @ManyToOne
    private Clients idClientCom;

    public Comentaris() {
    }

    public Comentaris(Integer idComentari) {
        this.idComentari = idComentari;
    }

    public Integer getIdComentari() {
        return idComentari;
    }

    public void setIdComentari(Integer idComentari) {
        this.idComentari = idComentari;
    }

    public Date getFetxa() {
        return fetxa;
    }

    public void setFetxa(Date fetxa) {
        this.fetxa = fetxa;
    }

    public Clients getIdClientCom() {
        return idClientCom;
    }

    public void setIdClientCom(Clients idClientCom) {
        this.idClientCom = idClientCom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComentari != null ? idComentari.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comentaris)) {
            return false;
        }
        Comentaris other = (Comentaris) object;
        if ((this.idComentari == null && other.idComentari != null) || (this.idComentari != null && !this.idComentari.equals(other.idComentari))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.com.entities.Comentaris[ idComentari=" + idComentari + " ]";
    }
    
}
