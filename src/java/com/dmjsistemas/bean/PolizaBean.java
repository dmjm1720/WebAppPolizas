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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@Named(value = "poliza")
@ViewScoped
public class PolizaBean extends Conexion implements Serializable {

    private Factf01 f;
    private List<Factf01> lista;
    private Factf01 fac;
    private Date fec1;
    private Date fec2;
    private List<String> listaFacturas;
    private String filtroCliente;
    private List<String> listaCliente;
    List<String> listarTodo;
    private Double totalesIEPS;

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

    public List<String> getListaFacturas() {
        return listaFacturas;
    }

    public void setListaFacturas(List<String> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }

    public Factf01 getFac() {
        return fac;
    }

    public void setFac(Factf01 fac) {
        this.fac = fac;
    }

    public String getFiltroCliente() {
        return filtroCliente;
    }

    public void setFiltroCliente(String filtroCliente) {
        this.filtroCliente = filtroCliente;
    }

    public List<String> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<String> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public List<String> getListarTodo() {
        return listarTodo;
    }

    public void setListarTodo(List<String> listarTodo) {
        this.listarTodo = listarTodo;
    }

    public Double getTotalesIEPS() {
        return totalesIEPS;
    }

    public void setTotalesIEPS(Double totalesIEPS) {
        this.totalesIEPS = totalesIEPS;
    }

    @PostConstruct
    public void init() {
        f = new Factf01();
        lista = new ArrayList<>();
        listaFacturas = new ArrayList<>();
        fac = new Factf01();
        listaCliente = new ArrayList<>();
        listarTodo = new ArrayList();
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
            totalesIEPS = 0.0;
            String sqlFactf1 = "SELECT "
                    + "F.TIP_DOC, F.CVE_DOC, F.CVE_CLPV, F.STATUS, F.FECHA_DOC, F.FECHA_VEN, F.CAN_TOT, F.IMP_TOT1, F.IMP_TOT4, F.CVE_OBS, F.NUM_ALMA, F.ACT_CXC, F.ACT_COI, F.ENLAZADO, "
                    + "F.TIP_DOC_E, F.NUM_MONED, F.TIPCAMB, F.NUM_PAGOS, F.PRIMERPAGO, F.RFC, F.CTLPOL, F.ESCFD, F.AUTORIZA, F.SERIE, F.FOLIO, F.DAT_ENVIO, F.CONTADO, F.CVE_BITA, F.BLOQ, F.FORMAENVIO, "
                    + "F.DES_FIN_PORC, F.DES_TOT_PORC, F.IMPORTE, F.COM_TOT_PORC, F.TIP_DOC_ANT, F.DOC_ANT, C.NOMBRE, CT.DOCTO, CT.FECHA_APLI "
                    + "FROM FACTF01 F "
                    + "INNER JOIN "
                    + "CUEN_DET01 CT ON F.CVE_DOC = CT.NO_FACTURA "
                    + "INNER JOIN "
                    + "CLIE01 C ON F.CVE_CLPV = C.CLAVE "
                    + "WHERE F.STATUS <> 'C' AND F.IMP_TOT1>0 AND F.TIP_DOC_E IN ('R','O') AND CT.CTLPOL=0 AND CT.FECHA_APLI BETWEEN '" + f1 + "' AND '" + f2 + "'";
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
                    totalesIEPS = totalesIEPS + rsFactf1.getDouble("IMP_TOT1");
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
                    f.setNombreProveedor(rsFactf1.getString("NOMBRE"));
                    //f.setSeleccionar(rsFactf1.getBoolean("SELECCIONAR"));
                    f.setNoPoliza(rsFactf1.getString("DOCTO"));
                    f.setFechaPago(rsFactf1.getDate("FECHA_APLI"));
                    lista.add(f);
                }
            }
            System.out.println("Totales IEPS: " + totalesIEPS);
            getLista();
            CerrarSae();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void facturasSeleccionadas() {
        generarPoliza();

        String info = "Se han enviado las facturas seleccionadas a la poliza";
        PrimeFaces.current().executeScript("Swal.fire({\n"
                + "  position: 'top-center',\n"
                + "  icon: 'success',\n"
                + "  title: '¡Aviso!',\n"
                + "  text: '" + info + "',\n"
                + "  showConfirmButton: false,\n"
                + "  timer: 8000\n"
                + "})");
    }

    public void generarPoliza() {
        try {
            ConectarSae();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String f1 = dateFormat.format(fec1);
            String f2 = dateFormat.format(fec2);
            Statement stFactf1 = getCnSae().createStatement();
            String sqlFactf1 = "SELECT "
                    + "F.TIP_DOC, F.CVE_DOC, F.CVE_CLPV, F.STATUS, F.FECHA_DOC, F.FECHA_VEN, F.CAN_TOT, F.IMP_TOT1, F.IMP_TOT4, F.CVE_OBS, F.NUM_ALMA, F.ACT_CXC, F.ACT_COI, F.ENLAZADO, "
                    + "F.TIP_DOC_E, F.NUM_MONED, F.TIPCAMB, F.NUM_PAGOS, F.PRIMERPAGO, F.RFC, F.CTLPOL, F.ESCFD, F.AUTORIZA, F.SERIE, F.FOLIO, F.DAT_ENVIO, F.CONTADO, F.CVE_BITA, F.BLOQ, F.FORMAENVIO, "
                    + "F.DES_FIN_PORC, F.DES_TOT_PORC, F.IMPORTE, F.COM_TOT_PORC, F.TIP_DOC_ANT, F.DOC_ANT, C.NOMBRE, CT.DOCTO, CT.FECHA_APLI  "
                    + "FROM FACTF01 F "
                    + "INNER JOIN "
                    + "CUEN_DET01 CT ON F.CVE_DOC = CT.NO_FACTURA "
                    + "INNER JOIN "
                    + "CLIE01 C ON F.CVE_CLPV = C.CLAVE "
                    + "WHERE F.STATUS <> 'C' AND F.IMP_TOT1>0 AND F.TIP_DOC_E IN ('R','O') AND CT.CTLPOL=0 AND CT.FECHA_APLI BETWEEN '" + f1 + "' AND '" + f2 + "'";
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
                    f.setNombreProveedor(rsFactf1.getString("NOMBRE"));
                    f.setNoPoliza(rsFactf1.getString("DOCTO").trim());
                    f.setFechaPago(rsFactf1.getDate("FECHA_APLI"));
                    String fecDoc[] = null;
                    fecDoc = f.getFechaPago().toString().split("-");
                    String ejercicio = fecDoc[0];
                    String ejercicioAnio = fecDoc[0].substring(2);
                    String mesPeriodo = fecDoc[1];

                    //**FOLIO MÁXIMO PÓLIZA**//
                    // String folioConsecutivo = maxPoliza(ejercicioAnio, mesPeriodo, ejercicio);
                    String folioConsecutivo = f.getNoPoliza();
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
                            folioConsecutivo = " " + folioConsecutivo;
                            break;
                        case 5:
                            folioConsecutivo = "" + folioConsecutivo;
                            break;
                        default:
                            folioConsecutivo = "" + folioConsecutivo;
                            break;
                    }
                    //**ACTUALIZAR EL FOLIO DE ACUERDO AL NÚMERO DE PÓLIZA GENERADA**//
                    //actualizarFolio(mesPeriodo, ejercicio, folioConsecutivo);
                    String nomProv = nombreProveedor(f.getCveClpv());

                    insertarAuxiliaresClienteSubcuenta(f.getCveDoc(), mesPeriodo, ejercicio, folioConsecutivo, f.getFechaPago().toString(), nomProv, f.getTipcamb(), f.getImporte(), f.getImpTot1(), f.getImpTot4(), ejercicioAnio);
                    //int totalPartidas = buscarTotalPartidas(ejercicioAnio, mesPeriodo, folioConsecutivo);

                }
            }
            CerrarSae();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
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
    public void insertarAuxiliaresClienteSubcuenta(String cveDoc, String mesPeriodo, String ejercicioAnio, String numPoliz, String fechaDoc, String nombreProveedor, Double tipoCambio, Double importe, Double imp1, Double imp4, String ej) {
        String auxliar = "AUXILIAR" + ejercicioAnio.substring(2);
        //String numpol = folioPoliza(cveDoc, auxliar);
        String numpol = numPoliz;
        try {
            ConectarCoi();
            //**210200100000000000002 I.E.P.S. PAGADO HABER**//
            int sumaPartidas = buscarTotalPartidasAuxiliar(ejercicioAnio, mesPeriodo, numpol, auxliar);
            String sql3 = "INSERT INTO " + auxliar + " (TIPO_POLI, NUM_POLIZ, NUM_PART, PERIODO, EJERCICIO, NUM_CTA, FECHA_POL, CONCEP_PO, DEBE_HABER, MONTOMOV, NUMDEPTO,TIPCAMBIO,CONTRAPAR,ORDEN, CCOSTOS, CGRUPOS) "
                    + "VALUES ('Ig','" + numpol + "'," + sumaPartidas + ",'" + mesPeriodo + "','" + ejercicioAnio + "','210200100000000000002','" + fechaDoc + "','TRA-" + numpol.trim() + " ABONO DE CLIENTES F-" + cveDoc + " / " + nombreProveedor + "','H','" + imp1 + "','0','" + tipoCambio + "','0','" + sumaPartidas + "','0','0');";
            PreparedStatement ps3 = getCnCoi().prepareStatement(sql3);
            ps3.executeUpdate();
            //**210100100000000000002 I.E.P.S. PENDIENTE DE PAGO DEBE**//
            int sumaPartidas2 = buscarTotalPartidasAuxiliar(ejercicioAnio, mesPeriodo, numpol, auxliar);
            String sql4 = "INSERT INTO " + auxliar + " (TIPO_POLI, NUM_POLIZ, NUM_PART, PERIODO, EJERCICIO, NUM_CTA, FECHA_POL, CONCEP_PO, DEBE_HABER, MONTOMOV, NUMDEPTO,TIPCAMBIO,CONTRAPAR,ORDEN, CCOSTOS, CGRUPOS) "
                    + "VALUES ('Ig','" + numpol + "'," + sumaPartidas2 + ",'" + mesPeriodo + "','" + ejercicioAnio + "','210100100000000000002','" + fechaDoc + "','TRA-" + numpol.trim() + " ABONO DE CLIENTES F-" + cveDoc + " / " + nombreProveedor + "','D','" + imp1 + "','0','" + tipoCambio + "','0','" + sumaPartidas2 + "','0','0');";
            PreparedStatement ps4 = getCnCoi().prepareStatement(sql4);
            ps4.executeUpdate();
            actualizarEstado(cveDoc);
            actualizarEncabezadoPoliza(ejercicioAnio, mesPeriodo, numpol, f.getFechaDoc().toString(), f.getCveDoc(), nombreProveedor, sumaPartidas2);
            listarFacturasPendientes();
            CerrarCoi();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public String folioPoliza(String factura, String aux) {
        String folio = "";
        try {
            ConectarCoi();
            String sql = "SELECT TOP(1) NUM_POLIZ FROM " + aux + " WHERE TIPO_POLI='Ig' AND CONCEP_PO LIKE '%" + factura + "%'";
            Statement st = getCnCoi().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (!rs.isBeforeFirst()) {
            } else {
                while (rs.next()) {
                    folio = rs.getString("NUM_POLIZ");
                }
            }
            CerrarCoi();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return folio;
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

    public int buscarTotalPartidasAuxiliar(String ejercicioAnio, String mesPeriodo, String numPoliz, String au) {
        int total = 0;
        try {
            ConectarCoi();
            String sql = "SELECT COUNT (NUM_POLIZ) +1 AS TOTAL FROM " + au + " WHERE TIPO_POLI='Ig' AND PERIODO='" + mesPeriodo + "' AND NUM_POLIZ='" + numPoliz + "';";
            Statement st = getCnCoi().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (!rs.isBeforeFirst()) {
            } else {
                while (rs.next()) {
                    total = rs.getInt("TOTAL");
                }
            }
            //CerrarCoi();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return total;
    }

    public void actualizarEncabezadoPoliza(String ejercicioAnio, String mesPeriodo, String numPoliz, String fechaDoc, String cveDoc, String nombreProveedor, int totPartida) {
        String poliza = "POLIZAS" + ejercicioAnio.substring(2);
        try {
            ConectarCoi();
            String sql = "UPDATE " + poliza + " SET NUM_PART=" + totPartida + " WHERE NUM_POLIZ='" + numPoliz + "' AND PERIODO='" + mesPeriodo + "' AND EJERCICIO='" + ejercicioAnio + "' AND TIPO_POLI='Ig'";
            PreparedStatement ps = getCnCoi().prepareStatement(sql);
            ps.executeUpdate();
            CerrarCoi();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void actualizarEstado(String docu) {
        try {
            ConectarSae();
            //String sql = "UPDATE SEGUIMIENTO_FACTURAS SET PROCESADO=1 WHERE CVE_DOC='" + docu + "'";
            String sql = "UPDATE CUEN_DET01 SET CTLPOL=1 WHERE NO_FACTURA='" + docu + "'";
            PreparedStatement ps = getCnSae().prepareStatement(sql);
            ps.executeUpdate();
            CerrarSae();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void onRowSelect(SelectEvent<Factf01> event) {
        FacesMessage msg = new FacesMessage("Product Selected", String.valueOf(event.getObject().getCveDoc()));
        listaFacturas.add(String.valueOf(event.getObject().getCveDoc()));
        getListaFacturas();
        PrimeFaces.current().ajax().update("frmPrincipal:polpend");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent<Factf01> event) {
        FacesMessage msg = new FacesMessage("Product Unselected", String.valueOf(event.getObject().getCveDoc()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<String> completeCliente(String cliente) throws SQLException {
        ConectarSae();
        PreparedStatement st = this.getCnSae().prepareStatement("SELECT DISTINCT (NOMBRE) FROM CLIE01 WHERE NOMBRE LIKE'" + cliente + "%' AND STATUS='A'");
        ResultSet rs = st.executeQuery();
        listaCliente = new ArrayList<>();
        if (!rs.isBeforeFirst()) {
            listaCliente.add("No hay resultados para tu búsqueda");
        } else {
            while (rs.next()) {
                listaCliente.add(rs.getString("NOMBRE"));
            }
        }
        CerrarSae();
        return listaCliente;
    }

}
