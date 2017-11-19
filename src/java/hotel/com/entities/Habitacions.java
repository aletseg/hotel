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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "habitacions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Habitacions.findAll", query = "SELECT h FROM Habitacions h")
    , @NamedQuery(name = "Habitacions.findByNumero", query = "SELECT h FROM Habitacions h WHERE h.numero = :numero")
    , @NamedQuery(name = "Habitacions.findByPis", query = "SELECT h FROM Habitacions h WHERE h.pis = :pis")
    , @NamedQuery(name = "Habitacions.findByEstat", query = "SELECT h FROM Habitacions h WHERE h.estat = :estat")
    , @NamedQuery(name = "Habitacions.findByObservacions", query = "SELECT h FROM Habitacions h WHERE h.observacions = :observacions")})
public class Habitacions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "pis")
    private Integer pis;
    @Basic(optional = false)
    @Column(name = "estat")
    private String estat;
    @Column(name = "observacions")
    private String observacions;
    @JoinColumn(name = "tipo_hab", referencedColumnName = "id_tipo")
    @ManyToOne(optional = false)
    private TipoHabitacions tipoHab;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numHabitacio")
    private Collection<Estancies> estanciesCollection;

    public Habitacions() {
    }

    public Habitacions(Integer numero) {
        this.numero = numero;
    }

    public Habitacions(Integer numero, String estat) {
        this.numero = numero;
        this.estat = estat;
    }

    public Habitacions(Integer numero, Integer pis, String estat, String observacions, TipoHabitacions tipoHab) {
        this.numero = numero;
        this.pis = pis;
        this.estat = estat;
        this.observacions = observacions;
        this.tipoHab = tipoHab;
    }
    
    public Habitacions(Integer numero, Integer pis, String estat, String observacions, TipoHabitacions tipoHab, Collection<Estancies> estanciesCollection) {
        this.numero = numero;
        this.pis = pis;
        this.estat = estat;
        this.observacions = observacions;
        this.tipoHab = tipoHab;
        this.estanciesCollection = estanciesCollection;
    }
    

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getPis() {
        return pis;
    }

    public void setPis(Integer pis) {
        this.pis = pis;
    }

    public String getEstat() {
        return estat;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }

    public String getObservacions() {
        return observacions;
    }

    public void setObservacions(String observacions) {
        this.observacions = observacions;
    }

    public TipoHabitacions getTipoHab() {
        return tipoHab;
    }

    public void setTipoHab(TipoHabitacions tipoHab) {
        this.tipoHab = tipoHab;
    }

    @XmlTransient
    public Collection<Estancies> getEstanciesCollection() {
        return estanciesCollection;
    }

    public void setEstanciesCollection(Collection<Estancies> estanciesCollection) {
        this.estanciesCollection = estanciesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numero != null ? numero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Habitacions)) {
            return false;
        }
        Habitacions other = (Habitacions) object;
        if ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.com.entities.Habitacions[ numero=" + numero + " ]";
    }
    
}
