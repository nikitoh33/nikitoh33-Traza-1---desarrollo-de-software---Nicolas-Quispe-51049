import org.example.repositorios.InMemoryRepository;
import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //creo pais y creo provincia y se la agrego
        Pais Argentina = new Pais("Argentina");

        Provincia BuenosAires = new Provincia("Buenos Aires");
        Argentina.AgregarProvincia(BuenosAires);

        Provincia Cordoba = new Provincia("Cordoba");
        Argentina.AgregarProvincia(Cordoba);

        //creo localidad y se la agreguo a provincia
        Localidad Caba = new Localidad("Caba");
        BuenosAires.AgregarLocalidad(Caba);

        Localidad LaPlata = new Localidad("La plata");
        BuenosAires.AgregarLocalidad(LaPlata);

        Localidad CordobaCapital = new Localidad("Cordoba Capital");
        Cordoba.AgregarLocalidad(CordobaCapital);

        Localidad VillaCarlosPaz = new Localidad("Villa carlos paz");
        Cordoba.AgregarLocalidad(VillaCarlosPaz);

        //creo un domicilio y lo agrego a una localidad
        Domicilio domicilio1 = new Domicilio("Calle huamhuaca",9597, 5525);
        Caba.AgregarDomicilio(domicilio1);

        Domicilio domicilio2 = new Domicilio("Calle falsa",4953, 5550);
        Caba.AgregarDomicilio(domicilio2);

        Domicilio domicilio3 = new Domicilio("Calle verdadera",4543, 2550);
        CordobaCapital.AgregarDomicilio(domicilio3);

        Domicilio domicilio4 = new Domicilio("Calle tiraso",4354, 5555);
        VillaCarlosPaz.AgregarDomicilio(domicilio4);

        //consola
        System.out.println("\n----------Creacion de paises----------");
        System.out.println(Argentina.MostrarPais());

        System.out.println("\n----------Creacion de Provincias----------");
        System.out.println(BuenosAires.MostrarProvincia());
        System.out.println(Cordoba.MostrarProvincia());

        System.out.println("\n----------Creacion de Localidades----------");
        System.out.println(Caba.MostrarLocalidad());
        System.out.println(LaPlata.MostrarLocalidad());
        System.out.println(CordobaCapital.MostrarLocalidad());
        System.out.println(VillaCarlosPaz.MostrarLocalidad());

        System.out.println("\n----------Creacion de Sucursales----------");
        Sucursal sucursal1 = new Sucursal("Sucursal Nikitoh33", LocalTime.of(9,0), LocalTime.of(18,0) , true);

        sucursal1.MostrarSucursal();
        sucursal1.AsignarDomicilioConSucursal(domicilio2);
        sucursal1.MostrarDomicilioAsignado();

        System.out.println("\n----------Prueba con Repositorio de Empresas----------");
        InMemoryRepository<Empresa> repoEmpresa = new InMemoryRepository<>();

        Empresa empresa1 = new Empresa("MiEmpresa", "SRL", 123456789, "logo.png");
        Empresa empresa2 = new Empresa("OtraEmpresa", "SA", 987654321, "otra.png");
        Empresa empresa3 = new Empresa("EmpresaX", "SA", 555555555, "x.png");

        repoEmpresa.save(empresa1);
        repoEmpresa.save(empresa2);
        repoEmpresa.save(empresa3);

        // Mostrar todas las empresas
        System.out.println("\nTodas las empresas:");
        List<Empresa> todas = repoEmpresa.findAll();
        todas.forEach(e -> System.out.println(e.MostrarEmpresa()));

        // Buscar por ID
        System.out.println("\nBuscar empresa con ID=2:");
        repoEmpresa.findById(2L).ifPresent(e ->
                System.out.println("Empresa encontrada: " + e.MostrarEmpresa())
        );

        // Buscar por nombre
        System.out.println("\nBuscar empresa por nombre='OtraEmpresa':");
        repoEmpresa.genericFindByField("nombre", "OtraEmpresa")
                .forEach(e -> System.out.println("Encontrada: " + e.MostrarEmpresa()));

        // Actualizar datos de empresa con ID=1 (cuil cambiado)
        System.out.println("\nActualizar empresa con ID=1 (nuevo CUIT):");
        Empresa empresaActualizada = new Empresa("MiEmpresa", "SRL", 111222333, "logo.png");
        repoEmpresa.genericUpdate(1L, empresaActualizada)
                .ifPresent(e -> System.out.println("Actualizada: " + e.MostrarEmpresa()));

        // Eliminar empresa con ID=3
        System.out.println("\nEliminar empresa con ID=3:");
        repoEmpresa.genericDelete(3L)
                .ifPresent(e -> System.out.println("Eliminada: " + e.MostrarEmpresa()));

        // Mostrar empresas restantes
        System.out.println("\nEmpresas restantes:");
        repoEmpresa.findAll().forEach(e -> System.out.println(e.MostrarEmpresa()));
    }
}
