package Modelos;

public class Personal {
    private int idpersonal;
    private String nombre;
    private String nota;
    private String estado;


    public Personal(int idpersonal, String nombre, String nota, String estado) {
        this.idpersonal = idpersonal;
        this.nombre = nombre;
        this.nota = nota;
        this.estado = estado;
    }

    public Personal(){

    }

    public int getIdpersonal() {
        return idpersonal;
    }

    public void setIdpersonal(int idpersonal) {
        this.idpersonal = idpersonal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
