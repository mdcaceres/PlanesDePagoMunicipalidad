package Entidades;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

public class Plan implements Comparable<Plan> {
    private UUID id;
    private String nombre;
    private float deuda;
    //estaria bueno hacer que cuotas sea un hashmap de pagos y localDate? con pagos como null al principio y que cuando
    //se haga el pago se cambie ese null por un pago como tal?
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

    public Plan(String nombre, float deuda, int cantCuotas) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.deuda = deuda;
        this.coutas = new HashMap<>();
        this.pagos = new TreeSet<>();
        this.fecha = LocalDate.now();

        for(int i = 0; i < cantCuotas; i++){
            this.coutas.put(i+1,fecha.plusMonths(((long)i+1)));
        }
    }

    public boolean agregarPago(Pago pago){
        int demora = 0;
        //generar un fecha random para probar funcionalidad de suma de intereses
        long minDay = LocalDate.of(2022, 4, 1).toEpochDay();
        long maxDay = LocalDate.of(2022, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        LocalDate date = randomDate; //LocalDate.now(); //LocalDate.of(2022,05,20);
        pago.setFecha(date);
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
        return nombre + " | id: " + id + "\n" + "fechas de cuotas: \n" +  verFechasCoutas();
    }
}
