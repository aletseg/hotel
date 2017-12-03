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
@Table(name = "estancies_hostes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstanciesHostes.findAll", query = "SELECT e FROM EstanciesHostes e")
    , @NamedQuery(name = "EstanciesHostes.findByIdEstanciaHoste", query = "SELECT e FROM EstanciesHostes e WHERE e.idEstanciaHoste = :idEstanciaHoste")
    , @NamedQuery(name = "EstanciesHostes.findByDataEntrada", query = "SELECT e FROM EstanciesHostes e WHERE e.dataEntrada = :dataEntrada")
    , @NamedQuery(name = "EstanciesHostes.findByDataSortida", query = "SELECT e FROM EstanciesHostes e WHERE e.dataSortida = :dataSortida")})
public class EstanciesHostes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estancia_hoste")
    private Integer idEstanciaHoste;
    @Basic(optional = false)
    @Column(name = "data_entrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEntrada;
    @Column(name = "data_sortida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataSortida;
    @JoinColumn(name = "estancia_id", referencedColumnName = "id_estancia")
    @ManyToOne(optional = false)
    private Estancies estanciaId;
    @JoinColumn(name = "client_id", referencedColumnName = "id_client")
    @ManyToOne(optional = false)
    private Clients clientId;

    public EstanciesHostes() {
    }

    public EstanciesHostes(Integer idEstanciaHoste) {
        this.idEstanciaHoste = idEstanciaHoste;
    }

    public EstanciesHostes(Integer idEstanciaHoste, Date dataEntrada) {
        this.idEstanciaHoste = idEstanciaHoste;
        this.dataEntrada = dataEntrada;
    }

    public EstanciesHostes(Date dataEntrada, Estancies estanciaId, Clients clientId) {
        this.dataEntrada = dataEntrada;
        this.estanciaId = estanciaId;
        this.clientId = clientId;
    }
    

    public Integer getIdEstanciaHoste() {
        return idEstanciaHoste;
    }

    public void setIdEstanciaHoste(Integer idEstanciaHoste) {
        this.idEstanciaHoste = idEstanciaHoste;
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

    public Estancies getEstanciaId() {
        return estanciaId;
    }

    public void setEstanciaId(Estancies estanciaId) {
        this.estanciaId = estanciaId;
    }

    public Clients getClientId() {
        return clientId;
    }

    public void setClientId(Clients clientId) {
        this.clientId = clientId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstanciaHoste != null ? idEstanciaHoste.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstanciesHostes)) {
            return false;
        }
        EstanciesHostes other = (EstanciesHostes) object;
        if ((this.idEstanciaHoste == null && other.idEstanciaHoste != null) || (this.idEstanciaHoste != null && !this.idEstanciaHoste.equals(other.idEstanciaHoste))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.com.entities.EstanciesHostes[ idEstanciaHoste=" + idEstanciaHoste + " ]";
    }
    
}
