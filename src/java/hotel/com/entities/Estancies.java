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
@Table(name = "estancies")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estancies.findAll", query = "SELECT e FROM Estancies e")
    , @NamedQuery(name = "Estancies.findByIdEstancia", query = "SELECT e FROM Estancies e WHERE e.idEstancia = :idEstancia")
    , @NamedQuery(name = "Estancies.findByDataEntrada", query = "SELECT e FROM Estancies e WHERE e.dataEntrada = :dataEntrada")
    , @NamedQuery(name = "Estancies.findByDataSortida", query = "SELECT e FROM Estancies e WHERE e.dataSortida = :dataSortida")
    , @NamedQuery(name = "Estancies.findByIdEmpleatEntrada", query = "SELECT e FROM Estancies e WHERE e.idEmpleatEntrada = :idEmpleatEntrada")})
public class Estancies implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estancia")
    private Integer idEstancia;
    @Basic(optional = false)
    @Column(name = "data_entrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEntrada;
    @Column(name = "data_sortida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataSortida;
    @Column(name = "id_empleat_entrada")
    private Integer idEmpleatEntrada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estanciaId")
    private Collection<EstanciesHostes> estanciesHostesCollection;
    @JoinColumn(name = "num_habitacio", referencedColumnName = "numero")
    @ManyToOne(optional = false)
    private Habitacions numHabitacio;

    public Estancies() {
    }

    public Estancies(Integer idEstancia) {
        this.idEstancia = idEstancia;
    }

    public Estancies(Integer idEstancia, Date dataEntrada) {
        this.idEstancia = idEstancia;
        this.dataEntrada = dataEntrada;
    }

    public Integer getIdEstancia() {
        return idEstancia;
    }

    public void setIdEstancia(Integer idEstancia) {
        this.idEstancia = idEstancia;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSortida() {
        return dataSortida;
    }

    public void setDataSortida(Date dataSortida) {
        this.dataSortida = dataSortida;
    }

    public Integer getIdEmpleatEntrada() {
        return idEmpleatEntrada;
    }

    public void setIdEmpleatEntrada(Integer idEmpleatEntrada) {
        this.idEmpleatEntrada = idEmpleatEntrada;
    }

    @XmlTransient
    public Collection<EstanciesHostes> getEstanciesHostesCollection() {
        return estanciesHostesCollection;
    }

    public void setEstanciesHostesCollection(Collection<EstanciesHostes> estanciesHostesCollection) {
        this.estanciesHostesCollection = estanciesHostesCollection;
    }

    public Habitacions getNumHabitacio() {
        return numHabitacio;
    }

    public void setNumHabitacio(Habitacions numHabitacio) {
        this.numHabitacio = numHabitacio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstancia != null ? idEstancia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estancies)) {
            return false;
        }
        Estancies other = (Estancies) object;
        if ((this.idEstancia == null && other.idEstancia != null) || (this.idEstancia != null && !this.idEstancia.equals(other.idEstancia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.com.entities.Estancies[ idEstancia=" + idEstancia + " ]";
    }
    
}
