package entidades;

public class Productos {

    private int id;
    private String nombre;
    private String codigo;
    private String presioV;
    private String presioC;
    private String cantidad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPresioV() {
        return presioV;
    }

    public void setPresioV(String presioV) {
        this.presioV = presioV;
    }

    public String getPresioC() {
        return presioC;
    }

    public void setPresioC(String presioC) {
        this.presioC = presioC;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
}
