package com.dmjsistemas.model;

import java.util.Date;

public class Reporte {

    private String nombre;
    private String rfc;
    private String estado;
    private Date fechaAplicacion;
    private String factura;
    private String folioPoliza;
    private String cveArticulo;
    private String descripcion;
    private String impu01;
    private int cantidad;
    private Double totalImpu01;
    private Double totalPartida;
    private Double precio;
    private String uuid;

    public Reporte() {
    }

    public Reporte(String nombre, String rfc, String estado, Date fechaAplicacion, String factura, String folioPoliza, String cveArticulo, String descripcion, String impu01, int cantidad, Double totalImpu01, Double totalPartida, Double precio, String uuid) {
        this.nombre = nombre;
        this.rfc = rfc;
        this.estado = estado;
        this.fechaAplicacion = fechaAplicacion;
        this.factura = factura;
        this.folioPoliza = folioPoliza;
        this.cveArticulo = cveArticulo;
        this.descripcion = descripcion;
        this.impu01 = impu01;
        this.cantidad = cantidad;
        this.totalImpu01 = totalImpu01;
        this.totalPartida = totalPartida;
        this.precio = precio;
        this.uuid = uuid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(Date fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public String getFolioPoliza() {
        return folioPoliza;
    }

    public void setFolioPoliza(String folioPoliza) {
        this.folioPoliza = folioPoliza;
    }

    public String getCveArticulo() {
        return cveArticulo;
    }

    public void setCveArticulo(String cveArticulo) {
        this.cveArticulo = cveArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImpu01() {
        return impu01;
    }

    public void setImpu01(String impu01) {
        this.impu01 = impu01;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotalImpu01() {
        return totalImpu01;
    }

    public void setTotalImpu01(Double totalImpu01) {
        this.totalImpu01 = totalImpu01;
    }

    public Double getTotalPartida() {
        return totalPartida;
    }

    public void setTotalPartida(Double totalPartida) {
        this.totalPartida = totalPartida;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

   
}
