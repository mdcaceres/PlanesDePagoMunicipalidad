package Entidades;

import java.time.LocalDate;

public class Pago implements Comparable<Pago> {
    private int numeroCuota;
    private int demora;
    private float importe;
    private float intereseAdicionales;
    private LocalDate fecha;

    public Pago(int numeroCuota, float importe) {
        this.numeroCuota = numeroCuota;
        this.importe = importe;
    }

    public int getDemora() {
        return demora;
    }

    public void setDemora(int demora) {
        this.demora = demora;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public float getIntereseAdicionales() {
        return intereseAdicionales;
    }

    public void setIntereseAdicionales(float intereseAdicionales) {
        this.intereseAdicionales = intereseAdicionales;
    }

    public int getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(int numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString(){
        return "demora: " + demora + " | Importe: " + importe + " | Intereses Acumulados: " + intereseAdicionales + " | fecha " + fecha;
    }

    @Override
    public int compareTo(Pago o) {
        return this.numeroCuota - o.numeroCuota;
    }
}
