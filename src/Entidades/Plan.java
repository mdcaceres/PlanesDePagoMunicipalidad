package Entidades;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

public class Plan implements Comparable<Plan> {
    private UUID id;
    private String nombre;
    private float deuda;
    private HashMap<Integer, LocalDate> coutas;
    private Set<Pago> pagos;
    private LocalDate fecha;

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public float getDeuda() {
        return deuda;
    }

    public Plan(String nombre, float deuda, int coutas1) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.deuda = deuda;
        this.coutas = new HashMap<>();
        this.pagos = new TreeSet<>();
        this.fecha = LocalDate.now();
        for(int i = 0; i < coutas1; i++){
            this.coutas.put(i+1,fecha.plusMonths(((long)i+1)));
        }
    }

    public boolean agregarPago(Pago pago){
        int demora = 0;
        LocalDate date = LocalDate.now(); //LocalDate.of(2022,05,20);
        if(date.isAfter(this.coutas.get(pago.getNumeroCuota()))) {
            pago.setDemora((int)DAYS.between(this.coutas.get(pago.getNumeroCuota()),date));
        } else {
            pago.setDemora(0);
        }
        pago.setIntereseAdicionales((pago.getImporte() * ((float)0.5/100)) * pago.getDemora());
        deuda -= pago.getImporte();
        return pagos.add(pago);
    }

    public boolean estaPagadoTotalmente() {
        return pagos.size() == coutas.size();
    }

    public String listadoDePagos(){
        String salida = "";
        salida += pagos.stream()
                .map(Pago::toString)
                .collect(Collectors.joining("\n"));
        return salida;
    }

    public float sumaInteresesCobrados(){
        //float can  not convert to LONG???
        return (float)pagos.stream()
                .map(Pago::getIntereseAdicionales)
                .mapToDouble(x -> x)
                .sum();
    }

    public String verFechasCoutas(){
        String salida = "";
        salida += this.coutas.toString().replace(" ", "\n");
        return salida;
    }

    @Override
    public int compareTo(Plan o) {
        if(o.id == this.id) return 0;
        return 1;
    }

    @Override
    public String toString(){
        return nombre + " " + id;
    }
}
