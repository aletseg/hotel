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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Estela
 */
@Entity
@Table(name = "empleats")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleats.findAll", query = "SELECT e FROM Empleats e")
    , @NamedQuery(name = "Empleats.findByIdEmpleat", query = "SELECT e FROM Empleats e WHERE e.idEmpleat = :idEmpleat")
    , @NamedQuery(name = "Empleats.findByNom", query = "SELECT e FROM Empleats e WHERE e.nom = :nom")
    , @NamedQuery(name = "Empleats.findByTipo", query = "SELECT e FROM Empleats e WHERE e.tipo = :tipo")})
public class Empleats implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_empleat")
    private Integer idEmpleat;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Lob
    @Column(name = "contrasenya")
    private String contrasenya;
    @Column(name = "tipo")
    private String tipo;

    public Empleats() {
    }

    public Empleats(Integer idEmpleat) {
        this.idEmpleat = idEmpleat;
    }

    public Empleats(Integer idEmpleat, String nom) {
        this.idEmpleat = idEmpleat;
        this.nom = nom;
    }

    public Empleats(Integer idEmpleat, String nom, String contrasenya) {
        this.idEmpleat = idEmpleat;
        this.nom = nom;
        this.contrasenya = contrasenya;
    }

    public Integer getIdEmpleat() {
        return idEmpleat;
    }

    public void setIdEmpleat(Integer idEmpleat) {
        this.idEmpleat = idEmpleat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleat != null ? idEmpleat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleats)) {
            return false;
        }
        Empleats other = (Empleats) object;
        if ((this.idEmpleat == null && other.idEmpleat != null) || (this.idEmpleat != null && !this.idEmpleat.equals(other.idEmpleat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.com.entities.Empleats[ idEmpleat=" + idEmpleat + " ]";
    }
    
}
