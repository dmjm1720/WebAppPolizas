package com.dmjsistemas.model;

import java.util.Date;

public class Factf01 {

    private String tipDoc;
    private String cveDoc;
    private String cveClpv;
    private String status;
    private Date fechaDoc;
    private Date fechaEnt;
    private Date fechaVen;
    private double canTot;
    private double impTot1;
    private double impTot4;
    private long cveObs;
    private long numAlma;
    private String actCxc;
    private String actCoi;
    private String enlazado;
    private String tipDocE;
    private long numMoned;
    private double tipcamb;
    private long numPagos;
    private Date fechaelab;
    private double primerpago;
    private String rfc;
    private long ctlpol;
    private String escfd;
    private long autoriza;
    private String serie;
    private long folio;
    private String autoanio;
    private long datEnvio;
    private String contado;
    private long cveBita;
    private String bloq;
    private String formaenvio;
    private double desFinPorc;
    private double desTotPorc;
    private double importe;
    private double comTotPorc;
    private String tipDocAnt;
    private String docAnt;
    private String nombreProveedor;
    private boolean seleccionar;
    private String noPoliza;
    private Date fechaPago;

    public Factf01() {
    }

    public Factf01(String cveDoc) {
        this.cveDoc = cveDoc;
    }

    public Factf01(String tipDoc, String cveDoc, String cveClpv, String status, Date fechaDoc, Date fechaEnt, Date fechaVen, double canTot, double impTot1, double impTot4, long cveObs, long numAlma, String actCxc, String actCoi, String enlazado, String tipDocE, long numMoned, double tipcamb, long numPagos, Date fechaelab, double primerpago, String rfc, long ctlpol, String escfd, long autoriza, String serie, long folio, String autoanio, long datEnvio, String contado, long cveBita, String bloq, String formaenvio, double desFinPorc, double desTotPorc, double importe, double comTotPorc, String tipDocAnt, String docAnt, String nombreProveedor, boolean seleccionar, String noPoliza, Date fechaPago) {
        this.tipDoc = tipDoc;
        this.cveDoc = cveDoc;
        this.cveClpv = cveClpv;
        this.status = status;
        this.fechaDoc = fechaDoc;
        this.fechaEnt = fechaEnt;
        this.fechaVen = fechaVen;
        this.canTot = canTot;
        this.impTot1 = impTot1;
        this.impTot4 = impTot4;
        this.cveObs = cveObs;
        this.numAlma = numAlma;
        this.actCxc = actCxc;
        this.actCoi = actCoi;
        this.enlazado = enlazado;
        this.tipDocE = tipDocE;
        this.numMoned = numMoned;
        this.tipcamb = tipcamb;
        this.numPagos = numPagos;
        this.fechaelab = fechaelab;
        this.primerpago = primerpago;
        this.rfc = rfc;
        this.ctlpol = ctlpol;
        this.escfd = escfd;
        this.autoriza = autoriza;
        this.serie = serie;
        this.folio = folio;
        this.autoanio = autoanio;
        this.datEnvio = datEnvio;
        this.contado = contado;
        this.cveBita = cveBita;
        this.bloq = bloq;
        this.formaenvio = formaenvio;
        this.desFinPorc = desFinPorc;
        this.desTotPorc = desTotPorc;
        this.importe = importe;
        this.comTotPorc = comTotPorc;
        this.tipDocAnt = tipDocAnt;
        this.docAnt = docAnt;
        this.nombreProveedor = nombreProveedor;
        this.seleccionar = seleccionar;
        this.noPoliza = noPoliza;
        this.fechaPago = fechaPago;
    }

    public String getTipDoc() {
        return tipDoc;
    }

    public void setTipDoc(String tipDoc) {
        this.tipDoc = tipDoc;
    }

    public String getCveDoc() {
        return cveDoc;
    }

    public void setCveDoc(String cveDoc) {
        this.cveDoc = cveDoc;
    }

    public String getCveClpv() {
        return cveClpv;
    }

    public void setCveClpv(String cveClpv) {
        this.cveClpv = cveClpv;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getFechaDoc() {
        return fechaDoc;
    }

    public void setFechaDoc(Date fechaDoc) {
        this.fechaDoc = fechaDoc;
    }

    public Date getFechaEnt() {
        return fechaEnt;
    }

    public void setFechaEnt(Date fechaEnt) {
        this.fechaEnt = fechaEnt;
    }

    public Date getFechaVen() {
        return fechaVen;
    }

    public void setFechaVen(Date fechaVen) {
        this.fechaVen = fechaVen;
    }

    public double getCanTot() {
        return canTot;
    }

    public void setCanTot(double canTot) {
        this.canTot = canTot;
    }

    public double getImpTot1() {
        return impTot1;
    }

    public void setImpTot1(double impTot1) {
        this.impTot1 = impTot1;
    }

    public double getImpTot4() {
        return impTot4;
    }

    public void setImpTot4(double impTot4) {
        this.impTot4 = impTot4;
    }

    public long getCveObs() {
        return cveObs;
    }

    public void setCveObs(long cveObs) {
        this.cveObs = cveObs;
    }

    public long getNumAlma() {
        return numAlma;
    }

    public void setNumAlma(long numAlma) {
        this.numAlma = numAlma;
    }

    public String getActCxc() {
        return actCxc;
    }

    public void setActCxc(String actCxc) {
        this.actCxc = actCxc;
    }

    public String getActCoi() {
        return actCoi;
    }

    public void setActCoi(String actCoi) {
        this.actCoi = actCoi;
    }

    public String getEnlazado() {
        return enlazado;
    }

    public void setEnlazado(String enlazado) {
        this.enlazado = enlazado;
    }

    public String getTipDocE() {
        return tipDocE;
    }

    public void setTipDocE(String tipDocE) {
        this.tipDocE = tipDocE;
    }

    public long getNumMoned() {
        return numMoned;
    }

    public void setNumMoned(long numMoned) {
        this.numMoned = numMoned;
    }

    public double getTipcamb() {
        return tipcamb;
    }

    public void setTipcamb(double tipcamb) {
        this.tipcamb = tipcamb;
    }

    public long getNumPagos() {
        return numPagos;
    }

    public void setNumPagos(long numPagos) {
        this.numPagos = numPagos;
    }

    public Date getFechaelab() {
        return fechaelab;
    }

    public void setFechaelab(Date fechaelab) {
        this.fechaelab = fechaelab;
    }

    public double getPrimerpago() {
        return primerpago;
    }

    public void setPrimerpago(double primerpago) {
        this.primerpago = primerpago;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public long getCtlpol() {
        return ctlpol;
    }

    public void setCtlpol(long ctlpol) {
        this.ctlpol = ctlpol;
    }

    public String getEscfd() {
        return escfd;
    }

    public void setEscfd(String escfd) {
        this.escfd = escfd;
    }

    public long getAutoriza() {
        return autoriza;
    }

    public void setAutoriza(long autoriza) {
        this.autoriza = autoriza;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public long getFolio() {
        return folio;
    }

    public void setFolio(long folio) {
        this.folio = folio;
    }

    public String getAutoanio() {
        return autoanio;
    }

    public void setAutoanio(String autoanio) {
        this.autoanio = autoanio;
    }

    public long getDatEnvio() {
        return datEnvio;
    }

    public void setDatEnvio(long datEnvio) {
        this.datEnvio = datEnvio;
    }

    public String getContado() {
        return contado;
    }

    public void setContado(String contado) {
        this.contado = contado;
    }

    public long getCveBita() {
        return cveBita;
    }

    public void setCveBita(long cveBita) {
        this.cveBita = cveBita;
    }

    public String getBloq() {
        return bloq;
    }

    public void setBloq(String bloq) {
        this.bloq = bloq;
    }

    public String getFormaenvio() {
        return formaenvio;
    }

    public void setFormaenvio(String formaenvio) {
        this.formaenvio = formaenvio;
    }

    public double getDesFinPorc() {
        return desFinPorc;
    }

    public void setDesFinPorc(double desFinPorc) {
        this.desFinPorc = desFinPorc;
    }

    public double getDesTotPorc() {
        return desTotPorc;
    }

    public void setDesTotPorc(double desTotPorc) {
        this.desTotPorc = desTotPorc;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getComTotPorc() {
        return comTotPorc;
    }

    public void setComTotPorc(double comTotPorc) {
        this.comTotPorc = comTotPorc;
    }

    public String getTipDocAnt() {
        return tipDocAnt;
    }

    public void setTipDocAnt(String tipDocAnt) {
        this.tipDocAnt = tipDocAnt;
    }

    public String getDocAnt() {
        return docAnt;
    }

    public void setDocAnt(String docAnt) {
        this.docAnt = docAnt;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public boolean getSeleccionar() {
        return seleccionar;
    }

    public void setSeleccionar(boolean seleccionar) {
        this.seleccionar = seleccionar;
    }

    public String getNoPoliza() {
        return noPoliza;
    }

    public void setNoPoliza(String noPoliza) {
        this.noPoliza = noPoliza;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

}
