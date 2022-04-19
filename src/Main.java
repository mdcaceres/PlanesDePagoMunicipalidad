import Entidades.Municipalidad;
import Entidades.Pago;
import Entidades.Plan;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    static Municipalidad municipalidad;
    static Map<Integer, Runnable> menu;
    static Map<Integer, String> botones;
    static boolean salir = false;

    public static void main(String[] args){
        municipalidad = new Municipalidad();
        menu = new HashMap<>();
        botones = new HashMap<>();

        menu.put(1,Main::agregarPlan);
        menu.put(2,Main::agregarPago);
        menu.put(3, Main::sumatoriaDeuda);
        menu.put(4, Main::listarPagosContribuyente);
        menu.put(5, Main::promedioIntereses);
        menu.put(6, Main::listarPlanesPagos);
        menu.put(7, Main::salir);

        //menu.put(7,Main::mostrarFechasCuotas);
        //menu.put(2,Main::agregarPago);
        //menu.put(3, Main::sumatoriaDeuda);
        //menu.put(4, Main::listarPagosContribuyente);
        //menu.put(5, Main::promedioIntereses);
        //menu.put(6, Main::salir);

        botones.put(1,"Crear Plan");
        botones.put(2,"Agregar Pago");
        botones.put(3,"Ver total de la deuda");
        botones.put(4,"Listar los pagos por cliente");
        botones.put(5,"Ver promedio de intereses");
        botones.put(6,"listar planes pagos");
        botones.put(7,"salir");

        while(!salir){
            System.out.println(botones.toString().replace(",","\n"));
            System.out.println("Eliga una opcion");
            Scanner scan = new Scanner(System.in);
            Integer op = scan.nextInt();
            menu.get(op).run();
        }
        /*
        Plan p1 = new Plan("ivonne", 1500,12);
        Plan p2 = new Plan("miguel", 1000,6);
        Plan p3 = new Plan("luna", 500,3);
        //municipalidad.agregarPlan(p1);
        municipalidad.agregarPlan(p2);
        municipalidad.agregarPlan(p3);
        //sumatoria de la deuda
        System.out.println("municipalidad.sumatoriaDeuda()");
        System.out.println(municipalidad.sumatoriaDeuda());
        System.out.println("municipalidad.sumaInteresesCobrados()");
        System.out.println(municipalidad.sumaInteresesCobrados());
        System.out.println("municipalidad.cantPlanesPagos()");
        System.out.println(municipalidad.cantPlanesPagos());
        System.out.println("municipalidad.listadoPagoContribuyente()");
        System.out.println(municipalidad.listadoPagoContribuyente("ivonne"));
        System.out.println("p1.verFechasCoutas()");
        System.out.println(p1.verFechasCoutas());
        System.out.println("p1.listadoDePagos()");
        System.out.println(p1.listadoDePagos());
        Pago p = new Pago(1,1500);
        p1.agregarPago(p);
        p = new Pago(2,1500);
        p1.agregarPago(p);
        p = new Pago(3,1500);
        p1.agregarPago(p);
        p = new Pago(4,1500);
        p1.agregarPago(p);

        System.out.println("p1.listadoDePagos()");
        System.out.println(p1.listadoDePagos());*/


    }

    public static void agregarPlan(){
        System.out.println("creado: " + municipalidad.agregarPlan());
    }

    public static void agregarPago(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese id del plan");
        UUID id = UUID.fromString(scan.next());
        System.out.println("Agregar pago: " + municipalidad.agregarPago(id));
    }

    public static void listarPlanesPagos(){
        System.out.println("Hay " + municipalidad.cantPlanesPagos() + " planes pagos");
    }

    public static void sumatoriaDeuda(){
        System.out.println("sumatoria de la deuda es " + municipalidad.sumatoriaDeuda());
    }

    public static void listarPagosContribuyente(){
        System.out.println("Ingrese nombre del contribuyente");
        Scanner scanner = new Scanner(System.in);
        System.out.println("listado de pagos " + "\n" + municipalidad.listadoPagoContribuyente(scanner.next()));
    }

    public static void promedioIntereses(){
        System.out.println("El promedio de los Intereses el: " + municipalidad.promedioInteresesCobrados());
    }

    public static void salir(){
        salir = true;
        System.out.println("Adios");
    }
}
