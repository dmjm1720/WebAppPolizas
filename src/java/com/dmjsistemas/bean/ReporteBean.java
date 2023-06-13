package com.dmjsistemas.bean;

import com.dmjsistemas.model.Reporte;
import com.dmjsistemas.util.Conexion;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "reporte")
@ViewScoped
public class ReporteBean extends Conexion implements Serializable {

    private List<Reporte> listaReporte;
    private Reporte reporte;
    private Date fec1;
    private Date fec2;

    public ReporteBean() {

    }

    @PostConstruct
    public void init() {
        listaReporte = new ArrayList<>();
        reporte = new Reporte();
    }

    public List<Reporte> getListaReporte() {
        return listaReporte;
    }

    public void setListaReporte(List<Reporte> listaReporte) {
        this.listaReporte = listaReporte;
    }

    public Reporte getReporte() {
        return reporte;
    }

    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

    public Date getFec1() {
        return fec1;
    }

    public void setFec1(Date fec1) {
        this.fec1 = fec1;
    }

    public Date getFec2() {
        return fec2;
    }

    public void setFec2(Date fec2) {
        this.fec2 = fec2;
    }

    public void listarReporte() {
        listaReporte = new ArrayList<>();
        listaReporte.clear();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String f1 = dateFormat.format(fec1);
        String f2 = dateFormat.format(fec2);

        try {
            ConectarSae();
            Statement st = getCnSae().createStatement();
            String sql = "SELECT CLIE01.NOMBRE, CLIE01.RFC, CLIE01.ESTADO, CUEN_DET01.FECHA_APLI, CUEN_DET01.NO_FACTURA, CUEN_DET01.DOCTO, PAR_FACTF01.CVE_ART, INVE01.DESCR, PAR_FACTF01.IMPU1,"
                    + "PAR_FACTF01.CANT, PAR_FACTF01.TOTIMP1, PAR_FACTF01.TOT_PARTIDA, PAR_FACTF01.PREC, FACTF01.UUID "
                    + "FROM INVE01 INNER JOIN "
                    + "PAR_FACTF01 INNER JOIN "
                    + "CLIE01 INNER JOIN "
                    + "CUEN_DET01 ON CLIE01.CLAVE = CUEN_DET01.CVE_CLIE ON PAR_FACTF01.CVE_DOC = CUEN_DET01.NO_FACTURA ON INVE01.CVE_ART = PAR_FACTF01.CVE_ART INNER JOIN " 
                    + "FACTF01 ON PAR_FACTF01.CVE_DOC = FACTF01.CVE_DOC "
                    + "WHERE PAR_FACTF01.IMPU1 >0 AND CUEN_DET01.FECHA_APLI BETWEEN '" + f1 + "' AND '" + f2 + "' ";
            ResultSet rs = st.executeQuery(sql);
            if(!rs.isBeforeFirst()){
                
            }else{
                while(rs.next()){
                    reporte = new Reporte();
                    reporte.setNombre(rs.getString("NOMBRE"));
                    reporte.setRfc(rs.getString("RFC"));
                    reporte.setEstado(rs.getString("ESTADO"));
                    reporte.setFechaAplicacion(rs.getDate("FECHA_APLI"));
                    reporte.setFactura(rs.getString("NO_FACTURA"));
                    reporte.setFolioPoliza(rs.getString("DOCTO"));
                    reporte.setCveArticulo(rs.getString("CVE_ART"));
                    reporte.setDescripcion(rs.getString("DESCR"));
                    reporte.setImpu01(rs.getString("IMPU1"));
                    reporte.setCantidad(rs.getInt("CANT"));
                    reporte.setTotalImpu01(rs.getDouble("TOTIMP1"));
                    reporte.setTotalPartida(rs.getDouble("TOT_PARTIDA"));
                    reporte.setPrecio(rs.getDouble("PREC"));
                    reporte.setUuid(rs.getString("UUID"));
                    listaReporte.add(reporte);
                }
            }
            getListaReporte();
            CerrarSae();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
