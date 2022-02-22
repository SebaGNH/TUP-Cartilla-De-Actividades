/*
Modificar el ejercicio de los cursos y alumnos para que se implemente composición entre las clases Curso y Alumno. 
Para ello incluir en la clase Curso un vector de objetos Alumno. 
Además modificar la clase Alumno para que almacene las notas en un vector de enteros y no guarde el promedio como un atributo
*/
import java.util.Scanner;
public class tup23_cursoAlumnos {
    public static void main(String[] args) throws Exception{

/* Inicio <-- Declaracion de variables iniciales ----- ----- ----- ----- ----- ----- ----- */
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese el nombre del curso");
        String nombreCurso = sc.next();
        
        System.out.println("Ingrese la cantidad de alumnos Inscriptos");
        int cantAlumnos = sc.nextInt();
        
        Curso curso = new Curso(nombreCurso,cantAlumnos); 
        Alumno nuevoAlumno;       
/* Fin <<<<-- Declaracion de variables iniciales ----- ----- ----- ----- ----- ----- ----- */


/* Inicio <-- Carga Curso con alumnos ----- ----- ----- ----- ----- ----- ----- */
        for (int i = 0; i < cantAlumnos; i++) {
            System.out.println("Ingrese el nombre del Alumno "+ (i+1));
            String nombreAlumno = sc.next();
            System.out.println("Ingrese el legajo de "+ nombreAlumno);
            int legajo = sc.nextInt();
            System.out.println("Ingrese la cantidad de notas del alumno "+ nombreAlumno);
            int cantidadNotas = sc.nextInt();

            nuevoAlumno = new Alumno(nombreAlumno, legajo, cantidadNotas);


            for (int j = 0; j < cantidadNotas; j++) {
                System.out.println("Ingrese la nota numero: "+ (j+1));
                int nota = sc.nextInt();
                nuevoAlumno.agregarNotas(nota); 
            } 
            curso.agregarAlumno(nuevoAlumno);
            
        }
/* Fin <<<<-- Carga Curso con alumnos ----- ----- ----- ----- ----- ----- ----- */

/* Inicio <-- Salida de resultados ----- ----- ----- ----- ----- ----- ----- */
        System.out.println("El nombre del curso es: "+ nombreCurso);
        System.out.println("Cantidad Alumnos con promedio mayor a 8: "+ curso.getCantMayorOcho());
        String promedioGeneral = String.format("%.2f",curso.getPromedioGeneral());//Método que muestra dos decimales
        System.out.println("El promedio general es de: "+ promedioGeneral); 
        //System.out.println("El promedio general es de: "+ curso.getPromedioGeneral());

        System.out.println("Los alumnos son: \n"+ curso.getListadoAlumnos());
        System.out.println("-----------------------------------------------------------");
        System.out.println("Lista de alumnos usando StringBuilder: \n"+ curso.getListadoStringBuilder());
/* Fin <<<<-- Salida de resultados ----- ----- ----- ----- ----- ----- ----- */
        
        sc.close();
        
    }
}


class Alumno{
    private String nombreAlumno;
    private int legajo;
    private int[] notas;

    public void setnombreAlumno (String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }
    public String getnombreAlumno () {
        return nombreAlumno;
    }

    public void setlegajo (int legajo) {
        this.legajo = legajo;
    }
    public int getlegajo () {
        return legajo;
    }   


    public Alumno(){}
    public Alumno(String nombreAlumno, int legajo,int cantidadNotas){
        this.nombreAlumno = nombreAlumno;
        this.legajo = legajo;
        
        this.notas = new int[cantidadNotas];
        for (int i = 0; i < cantidadNotas; i++) {
            this.notas[i] = -1;
        }
    }

    //Agregar nota
    public void agregarNotas(int nota){
        for (int i = 0; i < notas.length; i++) {
            if (notas[i] == -1) {
                notas[i] = nota;
            }
        }
    }

    //Obtener promedio
    public double getPromedio(){
        double promedio = 0;
        int cantidadNotas = 0;

        for (int i = 0; i < notas.length; i++) {
            promedio += notas[i];
            cantidadNotas ++;
        }
        
        return (double)promedio/cantidadNotas;
    }



    @Override
    public String toString(){
        return "Nombre del Alumno: " +this.nombreAlumno+ ", Legajo: "+this.legajo+ ", Promedio: "+ getPromedio(); 
    }
    
}

class Curso{
    private String nombreCurso;
    private Alumno[] alumnos;

    public void setnombreCurso (String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }
    public String getnombreCurso () {
        return nombreCurso;
    }

    public Curso(){}

    public Curso(String nombreCurso, int cantAlumnos){
        this.nombreCurso = nombreCurso;
        this.alumnos = new Alumno[cantAlumnos];
    }

    //Agregar alumnos
    public void agregarAlumno(Alumno nuevoAlumno){
        for (int i = 0; i < alumnos.length; i++) {
            if (alumnos[i] == null) {
                alumnos[i] = nuevoAlumno;
                break;
            }
        }
    }

    //Promedio general de todos los alumnos
    public double getPromedioGeneral(){
        double acumuladorNotas = 0;
        int contador = 0;
        for (int i = 0; i < alumnos.length; i++) {
            if (alumnos[i] !=null) {                
                acumuladorNotas += alumnos[i].getPromedio();
                contador ++;
            }
        }
        return (double)(acumuladorNotas / contador);
    }

    //Obtiene cantidad de alumnos con promedio mayor a 8
    public int getCantMayorOcho(){
        int contadorMayorOcho = 0;
        for (int i = 0; i < alumnos.length; i++) {
            if (alumnos[i].getPromedio() > 8 && alumnos[i]  != null) {
                contadorMayorOcho ++;
            }
        }
        return contadorMayorOcho;
    }

    //Obtiene lista de alumnos
    public String getListadoAlumnos(){
        String listadoAlumnos = "";
        for (int i = 0; i < alumnos.length; i++) {
            if (alumnos[i] != null) {
                listadoAlumnos += alumnos[i].toString()+ "\n";
            }
        }
        return listadoAlumnos;
    }
    //Lista de alumnos utilizando String Builder
    public String getListadoStringBuilder(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < alumnos.length; i++) {
            if (alumnos[i] !=null) {
                sb.append(alumnos[i].toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }





}