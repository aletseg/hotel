/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.com.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Estela
 */
@Entity
@Table(name = "propietats_hotel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PropietatsHotel.findAll", query = "SELECT p FROM PropietatsHotel p")
    , @NamedQuery(name = "PropietatsHotel.findById", query = "SELECT p FROM PropietatsHotel p WHERE p.id = :id")
    , @NamedQuery(name = "PropietatsHotel.findByNomHotel", query = "SELECT p FROM PropietatsHotel p WHERE p.nomHotel = :nomHotel")
    , @NamedQuery(name = "PropietatsHotel.findByIdHotelPol", query = "SELECT p FROM PropietatsHotel p WHERE p.idHotelPol = :idHotelPol")
    , @NamedQuery(name = "PropietatsHotel.findByCif", query = "SELECT p FROM PropietatsHotel p WHERE p.cif = :cif")
    , @NamedQuery(name = "PropietatsHotel.findByNumFitxa", query = "SELECT p FROM PropietatsHotel p WHERE p.numFitxa = :numFitxa")
    , @NamedQuery(name = "PropietatsHotel.findByEmail", query = "SELECT p FROM PropietatsHotel p WHERE p.email = :email")})
public class PropietatsHotel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nom_hotel")
    private String nomHotel;
    @Basic(optional = false)
    @Column(name = "id_hotel_pol")
    private String idHotelPol;
    @Column(name = "cif")
    private String cif;
    @Column(name = "num_fitxa")
    private Integer numFitxa;
    @Column(name = "email")
    private String email;

    public PropietatsHotel() {
    }

    public PropietatsHotel(Integer id) {
        this.id = id;
    }

    public PropietatsHotel(Integer id, String nomHotel, String idHotelPol) {
        this.id = id;
        this.nomHotel = nomHotel;
        this.idHotelPol = idHotelPol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomHotel() {
        return nomHotel;
    }

    public void setNomHotel(String nomHotel) {
        this.nomHotel = nomHotel;
    }

    public String getIdHotelPol() {
        return idHotelPol;
    }

    public void setIdHotelPol(String idHotelPol) {
        this.idHotelPol = idHotelPol;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public Integer getNumFitxa() {
        return numFitxa;
    }

    public void setNumFitxa(Integer numFitxa) {
        this.numFitxa = numFitxa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PropietatsHotel)) {
            return false;
        }
        PropietatsHotel other = (PropietatsHotel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.com.entities.PropietatsHotel[ id=" + id + " ]";
    }
    
}
