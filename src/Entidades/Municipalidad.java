package Entidades;

import java.util.*;
import java.util.stream.Collectors;

public class Municipalidad {
    //convertir planes a optional???
    private Map<UUID,Plan> planes;
    private Scanner scan;

    public Municipalidad() {

        this.planes = new TreeMap<>();
    }

    public String agregarPlan() {
        scan = new Scanner(System.in);
        System.out.println("nombre: ");
        String nombre = scan.next();
        System.out.println("deuda: ");
        float deuda = scan.nextFloat();
        System.out.println("cant cuotas: ");
        int cuotas = scan.nextInt();
        Plan p = new Plan(nombre,deuda,cuotas);
        planes.put(p.getId(),p);
        return p.toString();
    }

    public boolean agregarPago(UUID id){
        try{
            System.out.println("Ingrese la cuota a pagar");
            int cuota = scan.nextInt();
            System.out.println("ingrese el monto del pago");
            float monto = scan.nextFloat();
            //como hacer un orElse convertir PLanes en Optional??
            return planes.get(id).agregarPago(new Pago(cuota,monto));
        }catch(NullPointerException ex){
            System.out.println("No se encontro ninguna cuenta relacionada con el id ingresado");
        }
        return false;
    }


    public int cantPlanesPagos() {
        return (int)planes.values().stream()
                .filter(Plan::estaPagadoTotalmente)
                .count();
    }

    public float sumatoriaDeuda() {
        return (float)planes.values().stream()
                .filter(x -> !x.estaPagadoTotalmente())
                .map(Plan::getDeuda)
                .mapToDouble(x -> x)
                .sum();
    }

    public String listadoPagoContribuyente(String nombre){
        return planes.values().stream()
                .filter(plan -> plan.getNombre().equals(nombre))
                .map(Plan::listadoDePagos)
                .collect(Collectors.joining("\nl"));
    }

    public float promedioInteresesCobrados() {
        return (float) planes.values().stream()
                .map(Plan::sumaInteresesCobrados)
                .mapToDouble(x -> x)
                .average() //OptionalDouble
                .orElseGet(() -> planes.values().stream()
                        .map(Plan::sumaInteresesCobrados)
                        .mapToDouble(x->x)
                        .sum()/ (long) planes.size());
    }

    public String verCoutasPlan(UUID id){
        return planes.get(id).verFechasCoutas();
    }









}
