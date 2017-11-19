/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.com.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
@Table(name = "tipo_habitacions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoHabitacions.findAll", query = "SELECT t FROM TipoHabitacions t")
    , @NamedQuery(name = "TipoHabitacions.findByIdTipo", query = "SELECT t FROM TipoHabitacions t WHERE t.idTipo = :idTipo")
    , @NamedQuery(name = "TipoHabitacions.findByPreu", query = "SELECT t FROM TipoHabitacions t WHERE t.preu = :preu")
    , @NamedQuery(name = "TipoHabitacions.findByDescripcio", query = "SELECT t FROM TipoHabitacions t WHERE t.descripcio = :descripcio")})
public class TipoHabitacions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_tipo")
    private String idTipo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preu")
    private Float preu;
    @Basic(optional = false)
    @Column(name = "descripcio")
    private String descripcio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoHab")
    private List<Habitacions> habitacionsList;

    public TipoHabitacions() {
    }

    public TipoHabitacions(String idTipo) {
        this.idTipo = idTipo;
    }

    public TipoHabitacions(String idTipo, String descripcio) {
        this.idTipo = idTipo;
        this.descripcio = descripcio;
    }

    public TipoHabitacions(String idTipo, Float preu, String descripcio, List<Habitacions> habitacionsList) {
        this.idTipo = idTipo;
        this.preu = preu;
        this.descripcio = descripcio;
        this.habitacionsList = habitacionsList;
    }

    public String getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(String idTipo) {
        this.idTipo = idTipo;
    }

    public Float getPreu() {
        return preu;
    }

    public void setPreu(Float preu) {
        this.preu = preu;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    @XmlTransient
    public List<Habitacions> getHabitacionsList() {
        return habitacionsList;
    }

    public void setHabitacionsList(List<Habitacions> habitacionsList) {
        this.habitacionsList = habitacionsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipo != null ? idTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoHabitacions)) {
            return false;
        }
        TipoHabitacions other = (TipoHabitacions) object;
        if ((this.idTipo == null && other.idTipo != null) || (this.idTipo != null && !this.idTipo.equals(other.idTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.com.entities.TipoHabitacions[ idTipo=" + idTipo + " ]";
    }
    
}
