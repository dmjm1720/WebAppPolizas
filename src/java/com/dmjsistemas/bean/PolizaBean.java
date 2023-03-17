package com.dmjsistemas.bean;

import com.dmjsistemas.model.Factf01;
import com.dmjsistemas.util.Conexion;
import java.io.Serializable;
import java.sql.PreparedStatement;
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

@Named(value = "poliza")
@ViewScoped
public class PolizaBean extends Conexion implements Serializable {

    private Factf01 f;
    private List<Factf01> lista;
    private Date fec1;
    private Date fec2;

    public PolizaBean() {
    }

    public Factf01 getF() {
        return f;
    }

    public void setF(Factf01 f) {
        this.f = f;
    }

    public List<Factf01> getLista() {
        return lista;
    }

    public void setLista(List<Factf01> lista) {
        this.lista = lista;
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

    @PostConstruct
    public void init() {
        f = new Factf01();
        lista = new ArrayList<>();
    }

    public void listarFacturasPendientes() {
        lista = new ArrayList<>();
        lista.clear();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String f1 = dateFormat.format(fec1);
        String f2 = dateFormat.format(fec2);
        try {
            ConectarSae();
            Statement stFactf1 = getCnSae().createStatement();
            String sqlFactf1 = "SELECT "
                    + "F.TIP_DOC, F.CVE_DOC, F.CVE_CLPV, F.STATUS, F.FECHA_DOC, F.FECHA_VEN, F.CAN_TOT, F.IMP_TOT1, F.IMP_TOT4, F.CVE_OBS, F.NUM_ALMA, F.ACT_CXC, F.ACT_COI, F.ENLAZADO, "
                    + "F.TIP_DOC_E, F.NUM_MONED, F.TIPCAMB, F.NUM_PAGOS, F.PRIMERPAGO, F.RFC, F.CTLPOL, F.ESCFD, F.AUTORIZA, F.SERIE, F.FOLIO, F.DAT_ENVIO, F.CONTADO, F.CVE_BITA, F.BLOQ, F.FORMAENVIO, "
                    + "F.DES_FIN_PORC, F.DES_TOT_PORC, F.IMPORTE, F.COM_TOT_PORC, F.TIP_DOC_ANT, F.DOC_ANT "
                    + "FROM FACTF01 F "
                    + "INNER JOIN "
                    + "SEGUIMIENTO_FACTURAS SF ON F.CVE_DOC = SF.CVE_DOC WHERE F.STATUS <> 'C' AND SF.PROCESADO=0 AND F.FECHA_DOC BETWEEN '" + f1 + "' AND '" + f2 + "'";
            ResultSet rsFactf1 = stFactf1.executeQuery(sqlFactf1);
            if (!rsFactf1.isBeforeFirst()) {
            } else {
                while (rsFactf1.next()) {
                    f = new Factf01();
                    f.setTipDoc(rsFactf1.getString("TIP_DOC"));
                    f.setCveDoc(rsFactf1.getString("CVE_DOC"));
                    f.setCveClpv(rsFactf1.getString("CVE_CLPV"));
                    f.setStatus(rsFactf1.getString("STATUS"));
                    f.setFechaDoc(rsFactf1.getDate("FECHA_DOC"));
                    f.setFechaVen(rsFactf1.getDate("FECHA_VEN"));
                    f.setCanTot(rsFactf1.getDouble("CAN_TOT"));
                    f.setImpTot1(rsFactf1.getDouble("IMP_TOT1"));
                    f.setImpTot4(rsFactf1.getDouble("IMP_TOT4"));
                    f.setCveObs(rsFactf1.getLong("CVE_OBS"));
                    f.setNumAlma(rsFactf1.getLong("NUM_ALMA"));
                    f.setActCxc(rsFactf1.getString("ACT_CXC"));
                    f.setActCoi(rsFactf1.getString("ACT_COI"));
                    f.setEnlazado(rsFactf1.getString("ENLAZADO"));
                    f.setTipDocE(rsFactf1.getString("TIP_DOC_E"));
                    f.setNumMoned(rsFactf1.getLong("NUM_MONED"));
                    f.setTipcamb(rsFactf1.getDouble("TIPCAMB"));
                    f.setNumPagos(rsFactf1.getLong("NUM_PAGOS"));
                    f.setPrimerpago(rsFactf1.getDouble("PRIMERPAGO"));
                    f.setRfc(rsFactf1.getString("RFC"));
                    f.setCtlpol(rsFactf1.getLong("CTLPOL"));
                    f.setEscfd(rsFactf1.getString("ESCFD"));
                    f.setAutoriza(rsFactf1.getLong("AUTORIZA"));
                    f.setSerie(rsFactf1.getString("SERIE"));
                    f.setFolio(rsFactf1.getLong("FOLIO"));
                    f.setDatEnvio(rsFactf1.getLong("DAT_ENVIO"));
                    f.setContado(rsFactf1.getString("CONTADO"));
                    f.setCveBita(rsFactf1.getLong("CVE_BITA"));
                    f.setBloq(rsFactf1.getString("BLOQ"));
                    f.setFormaenvio(rsFactf1.getString("FORMAENVIO"));
                    f.setDesFinPorc(rsFactf1.getDouble("DES_FIN_PORC"));
                    f.setDesTotPorc(rsFactf1.getDouble("DES_TOT_PORC"));
                    f.setImporte(rsFactf1.getDouble("IMPORTE"));
                    f.setComTotPorc(rsFactf1.getDouble("COM_TOT_PORC"));
                    f.setTipDoc(rsFactf1.getString("TIP_DOC_ANT"));
                    f.setDocAnt(rsFactf1.getString("DOC_ANT"));
                    f.setNombreProveedor(nombreProveedor(f.getCveClpv()));
                    lista.add(f);
                }
            }
            getLista();
            CerrarSae();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void generarPoliza() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String f1 = dateFormat.format(fec1);
        String f2 = dateFormat.format(fec2);
        try {
            ConectarSae();
            Statement stFactf1 = getCnSae().createStatement();
            String sqlFactf1 = "SELECT "
                    + "F.TIP_DOC, F.CVE_DOC, F.CVE_CLPV, F.STATUS, F.FECHA_DOC, F.FECHA_VEN, F.CAN_TOT, F.IMP_TOT1, F.IMP_TOT4, F.CVE_OBS, F.NUM_ALMA, F.ACT_CXC, F.ACT_COI, F.ENLAZADO, "
                    + "F.TIP_DOC_E, F.NUM_MONED, F.TIPCAMB, F.NUM_PAGOS, F.PRIMERPAGO, F.RFC, F.CTLPOL, F.ESCFD, F.AUTORIZA, F.SERIE, F.FOLIO, F.DAT_ENVIO, F.CONTADO, F.CVE_BITA, F.BLOQ, F.FORMAENVIO, "
                    + "F.DES_FIN_PORC, F.DES_TOT_PORC, F.IMPORTE, F.COM_TOT_PORC, F.TIP_DOC_ANT, F.DOC_ANT "
                    + "FROM FACTF01 F "
                    + "INNER JOIN "
                    + "SEGUIMIENTO_FACTURAS SF ON F.CVE_DOC = SF.CVE_DOC WHERE F.STATUS <> 'C' AND SF.PROCESADO=0 AND F.FECHA_DOC BETWEEN '" + f1 + "' AND '" + f2 + "'";
            ResultSet rsFactf1 = stFactf1.executeQuery(sqlFactf1);
            if (!rsFactf1.isBeforeFirst()) {
            } else {
                while (rsFactf1.next()) {
                    f = new Factf01();
                    f.setTipDoc(rsFactf1.getString("TIP_DOC"));
                    f.setCveDoc(rsFactf1.getString("CVE_DOC"));
                    f.setCveClpv(rsFactf1.getString("CVE_CLPV"));
                    f.setStatus(rsFactf1.getString("STATUS"));
                    f.setFechaDoc(rsFactf1.getDate("FECHA_DOC"));
                    f.setFechaVen(rsFactf1.getDate("FECHA_VEN"));
                    f.setCanTot(rsFactf1.getDouble("CAN_TOT"));
                    f.setImpTot1(rsFactf1.getDouble("IMP_TOT1"));
                    f.setImpTot4(rsFactf1.getDouble("IMP_TOT4"));
                    f.setCveObs(rsFactf1.getLong("CVE_OBS"));
                    f.setNumAlma(rsFactf1.getLong("NUM_ALMA"));
                    f.setActCxc(rsFactf1.getString("ACT_CXC"));
                    f.setActCoi(rsFactf1.getString("ACT_COI"));
                    f.setEnlazado(rsFactf1.getString("ENLAZADO"));
                    f.setTipDocE(rsFactf1.getString("TIP_DOC_E"));
                    f.setNumMoned(rsFactf1.getLong("NUM_MONED"));
                    f.setTipcamb(rsFactf1.getDouble("TIPCAMB"));
                    f.setNumPagos(rsFactf1.getLong("NUM_PAGOS"));
                    f.setPrimerpago(rsFactf1.getDouble("PRIMERPAGO"));
                    f.setRfc(rsFactf1.getString("RFC"));
                    f.setCtlpol(rsFactf1.getLong("CTLPOL"));
                    f.setEscfd(rsFactf1.getString("ESCFD"));
                    f.setAutoriza(rsFactf1.getLong("AUTORIZA"));
                    f.setSerie(rsFactf1.getString("SERIE"));
                    f.setFolio(rsFactf1.getLong("FOLIO"));
                    f.setDatEnvio(rsFactf1.getLong("DAT_ENVIO"));
                    f.setContado(rsFactf1.getString("CONTADO"));
                    f.setCveBita(rsFactf1.getLong("CVE_BITA"));
                    f.setBloq(rsFactf1.getString("BLOQ"));
                    f.setFormaenvio(rsFactf1.getString("FORMAENVIO"));
                    f.setDesFinPorc(rsFactf1.getDouble("DES_FIN_PORC"));
                    f.setDesTotPorc(rsFactf1.getDouble("DES_TOT_PORC"));
                    f.setImporte(rsFactf1.getDouble("IMPORTE"));
                    f.setComTotPorc(rsFactf1.getDouble("COM_TOT_PORC"));
                    f.setTipDoc(rsFactf1.getString("TIP_DOC_ANT"));
                    f.setDocAnt(rsFactf1.getString("DOC_ANT"));

                    String fecDoc[] = null;
                    fecDoc = f.getFechaDoc().toString().split("-");
                    String ejercicio = fecDoc[0];
                    String ejercicioAnio = fecDoc[0].substring(2);
                    String mesPeriodo = fecDoc[1];

                    //**FOLIO MÁXIMO PÓLIZA**//
                    String folioConsecutivo = maxPoliza(ejercicioAnio, mesPeriodo, ejercicio);
                    switch (folioConsecutivo.length()) {
                        case 1:
                            folioConsecutivo = "    " + folioConsecutivo;
                            break;
                        case 2:
                            folioConsecutivo = "   " + folioConsecutivo;
                            break;
                        case 3:
                            folioConsecutivo = "  " + folioConsecutivo;
                            break;
                        case 4:
                            folioConsecutivo = "  " + folioConsecutivo;
                            break;
                        default:
                            folioConsecutivo = "" + folioConsecutivo;
                            break;
                    }
                    //**ACTUALIZAR EL FOLIO DE ACUERDO AL NÚMERO DE PÓLIZA GENERADA**//
                    actualizarFolio(mesPeriodo, ejercicio, folioConsecutivo);
                    String nomProv = nombreProveedor(f.getCveClpv());
                    insertarAuxiliaresClienteSubcuenta(f.getCveDoc(), mesPeriodo, ejercicio, folioConsecutivo, f.getFechaDoc().toString(), nomProv, f.getTipcamb(), f.getImporte(), f.getImpTot1(), f.getImpTot4());
                    int totalPartidas = buscarTotalPartidas(ejercicioAnio, mesPeriodo, folioConsecutivo);
                    insertarEncabezadoPoliza(ejercicio, mesPeriodo, folioConsecutivo, f.getFechaDoc().toString(), f.getCveDoc(), nomProv, totalPartidas);
                }
            }
            CerrarSae();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    //**OBTENER EL FOLIO MÁXIMO DE LA POLIZA SIGUIENTE**//
    public String maxPoliza(String anio, String periodo, String ejercicio) {
        String max = "";
        try {
            ConectarCoi();
            String poliza = "POLIZAS" + anio;
            Statement st = getCnCoi().createStatement();
            String sql = "SELECT MAX(NUM_POLIZ) +1 AS MAX FROM " + poliza + " WHERE PERIODO ='" + periodo + "' AND EJERCICIO='" + ejercicio + "' AND TIPO_POLI='Ig'";
            ResultSet rs = st.executeQuery(sql);
            if (!rs.isBeforeFirst()) {

            } else {
                while (rs.next()) {
                    max = rs.getString("MAX");
                }
            }
            CerrarCoi();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return max;
    }

    //**ACTUALIZAR EL FOLIO EN LA TABAL DE FOLIOS**//
    public void actualizarFolio(String mes, String ejercicio, String folioMax) {
        String folio = "FOLIO" + mes;
        try {
            ConectarCoi();
            String sql = "UPDATE FOLIOS SET " + folio + " = " + folioMax + " WHERE EJERCICIO = '" + ejercicio + "' AND TIPPOL='Ig'";
            PreparedStatement ps = getCnCoi().prepareStatement(sql);
            ps.executeUpdate();
            CerrarCoi();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    //**INSERTAR SUBCUENTA**//
    public void insertarAuxiliaresClienteSubcuenta(String cveDoc) {
        try {
            ConectarCoi();
            String sql = "";
            PreparedStatement ps = getCnCoi().prepareStatement(sql);
            ps.executeUpdate();
            CerrarCoi();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    //**INSERTAR SUBCUENTA**//
    public void insertarAuxiliaresClienteSubcuenta(String cveDoc, String mesPeriodo, String ejercicioAnio, String numPoliz, String fechaDoc, String nombreProveedor, Double tipoCambio, Double importe, Double imp1, Double imp4) {
        String auxliar = "AUXILIAR" + ejercicioAnio.substring(2);
        try {
            ConectarCoi();
            //**IEPS E IVA**//
            //**110708900000000000002 CLIENTES SUB-CTA. HABER**//
            String sql1 = "INSERT INTO " + auxliar + " (TIPO_POLI, NUM_POLIZ, NUM_PART, PERIODO, EJERCICIO, NUM_CTA, FECHA_POL, CONCEP_PO, DEBE_HABER, MONTOMOV, NUMDEPTO,TIPCAMBIO,CONTRAPAR,ORDEN, CCOSTOS, CGRUPOS) "
                    + "VALUES ('Ig','" + numPoliz + "','1','" + mesPeriodo + "','" + ejercicioAnio + "','110708900000000000002','" + fechaDoc + "','TRA-" + numPoliz.trim() + " ABONO DE CLIENTES F-" + cveDoc + " / " + nombreProveedor + "','H','" + importe + "','0','" + tipoCambio + "','0','1','0','0');";
            PreparedStatement ps1 = getCnCoi().prepareStatement(sql1);
            ps1.executeUpdate();
            //**110200300000000000002 "BANCOMER, S.A. CTA. 0443088820" DEBE**//
            String sql2 = "INSERT INTO " + auxliar + " (TIPO_POLI, NUM_POLIZ, NUM_PART, PERIODO, EJERCICIO, NUM_CTA, FECHA_POL, CONCEP_PO, DEBE_HABER, MONTOMOV, NUMDEPTO,TIPCAMBIO,CONTRAPAR,ORDEN, CCOSTOS, CGRUPOS) "
                    + "VALUES ('Ig','" + numPoliz + "','2','" + mesPeriodo + "','" + ejercicioAnio + "','110200300000000000002','" + fechaDoc + "','TRA-" + numPoliz.trim() + " ABONO DE CLIENTES F-" + cveDoc + " / " + nombreProveedor + "','D','" + importe + "','0','" + tipoCambio + "','0','2','0','0');";
            PreparedStatement ps2 = getCnCoi().prepareStatement(sql2);
            ps2.executeUpdate();
            //**210200100000000000002 I.E.P.S. PAGADO HABER**//
            String sql3 = "INSERT INTO " + auxliar + " (TIPO_POLI, NUM_POLIZ, NUM_PART, PERIODO, EJERCICIO, NUM_CTA, FECHA_POL, CONCEP_PO, DEBE_HABER, MONTOMOV, NUMDEPTO,TIPCAMBIO,CONTRAPAR,ORDEN, CCOSTOS, CGRUPOS) "
                    + "VALUES ('Ig','" + numPoliz + "','3','" + mesPeriodo + "','" + ejercicioAnio + "','210200100000000000002','" + fechaDoc + "','TRA-" + numPoliz.trim() + " ABONO DE CLIENTES F-" + cveDoc + " / " + nombreProveedor + "','H','" + imp1 + "','0','" + tipoCambio + "','0','3','0','0');";
            PreparedStatement ps3 = getCnCoi().prepareStatement(sql3);
            ps3.executeUpdate();
            //**210100100000000000002 I.E.P.S. PENDIENTE DE PAGO DEBE**//
            String sql4 = "INSERT INTO " + auxliar + " (TIPO_POLI, NUM_POLIZ, NUM_PART, PERIODO, EJERCICIO, NUM_CTA, FECHA_POL, CONCEP_PO, DEBE_HABER, MONTOMOV, NUMDEPTO,TIPCAMBIO,CONTRAPAR,ORDEN, CCOSTOS, CGRUPOS) "
                    + "VALUES ('Ig','" + numPoliz + "','4','" + mesPeriodo + "','" + ejercicioAnio + "','210100100000000000002','" + fechaDoc + "','TRA-" + numPoliz.trim() + " ABONO DE CLIENTES F-" + cveDoc + " / " + nombreProveedor + "','D','" + imp1 + "','0','" + tipoCambio + "','0','4','0','0');";
            PreparedStatement ps4 = getCnCoi().prepareStatement(sql4);
            ps4.executeUpdate();

            //**VALIDAR SI EL IMP_TOT4 TIENE IVA**//
            if (imp4 >= 0.1) {
                //**210200100000000000002 I.E.P.S. PAGADO HABER**//
                String sql5 = "INSERT INTO " + auxliar + " (TIPO_POLI, NUM_POLIZ, NUM_PART, PERIODO, EJERCICIO, NUM_CTA, FECHA_POL, CONCEP_PO, DEBE_HABER, MONTOMOV, NUMDEPTO,TIPCAMBIO,CONTRAPAR,ORDEN, CCOSTOS, CGRUPOS) "
                        + "VALUES ('Ig','" + numPoliz + "','5','" + mesPeriodo + "','" + ejercicioAnio + "','210200100000000000002','" + fechaDoc + "','TRA-" + numPoliz.trim() + " ABONO DE CLIENTES F-" + cveDoc + " / " + nombreProveedor + "','H','" + imp4 + "','0','" + tipoCambio + "','0','5','0','0');";
                PreparedStatement ps5 = getCnCoi().prepareStatement(sql5);
                ps5.executeUpdate();
                //**210800300000000000002 I.V.A. PENDIENTE DE PAGO 16% DEBE**//
                String sql6 = "INSERT INTO " + auxliar + " (TIPO_POLI, NUM_POLIZ, NUM_PART, PERIODO, EJERCICIO, NUM_CTA, FECHA_POL, CONCEP_PO, DEBE_HABER, MONTOMOV, NUMDEPTO,TIPCAMBIO,CONTRAPAR,ORDEN, CCOSTOS, CGRUPOS) "
                        + "VALUES ('Ig','" + numPoliz + "','6','" + mesPeriodo + "','" + ejercicioAnio + "','210800300000000000002','" + fechaDoc + "','TRA-" + numPoliz.trim() + " ABONO DE CLIENTES F-" + cveDoc + " / " + nombreProveedor + "','D','" + imp4 + "','0','" + tipoCambio + "','0','6','0','0');";
                PreparedStatement ps6 = getCnCoi().prepareStatement(sql6);
                ps6.executeUpdate();
            }
            CerrarCoi();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public String nombreProveedor(String cveProv) {
        String nombre = "";
        try {
            ConectarSae();
            String sql = "SELECT NOMBRE FROM CLIE01 WHERE STATUS='A' AND CLAVE='" + cveProv + "'";
            Statement st = getCnSae().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (!rs.isBeforeFirst()) {
                nombre = "CLIENTE NO ENCONTRADO";
            } else {
                while (rs.next()) {
                    nombre = rs.getString("NOMBRE");
                }
            }
            CerrarSae();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return nombre;
    }

    public int buscarTotalPartidas(String ejercicioAnio, String mesPeriodo, String numPoliz) {
        int total = 0;
        String auxliar = "AUXILIAR" + ejercicioAnio;
        try {
            ConectarCoi();
            String sql = "SELECT COUNT (NUM_POLIZ) AS TOTAL FROM " + auxliar + " WHERE TIPO_POLI='Ig' AND PERIODO='" + mesPeriodo + "' AND NUM_POLIZ='" + numPoliz + "';";
            Statement st = getCnCoi().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (!rs.isBeforeFirst()) {
            } else {
                while (rs.next()) {
                    total = rs.getInt("TOTAL");
                }
            }
            CerrarCoi();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return total;
    }

    public void insertarEncabezadoPoliza(String ejercicioAnio, String mesPeriodo, String numPoliz, String fechaDoc, String cveDoc, String nombreProveedor, int totPartida) {
        String poliza = "POLIZAS" + ejercicioAnio.substring(2);
        try {
            ConectarCoi();
            String sql = "INSERT INTO " + poliza + " (TIPO_POLI, NUM_POLIZ, PERIODO, EJERCICIO, FECHA_POL, CONCEP_PO, NUM_PART, LOGAUDITA, CONTABILIZ, NUMPARCUA, TIENEDOCUMENTOS, ORIGEN) "
                    + "VALUES ('Ig','" + numPoliz + "','" + mesPeriodo + "','" + ejercicioAnio + "','" + fechaDoc + "','TRA-" + numPoliz.trim() + " ABONO DE CLIENTES F-" + cveDoc + " / " + nombreProveedor + "','" + totPartida + "','N','N','0','0','SIS-POL-AUT');";
            PreparedStatement ps = getCnCoi().prepareStatement(sql);
            ps.executeUpdate();
            CerrarCoi();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}
