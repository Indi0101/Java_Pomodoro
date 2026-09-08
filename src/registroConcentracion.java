public class registroConcentracion {
    private String fecha;
    private String descanso;
    private String concentracion;
    registroConcentracion(String fecha, String descanso, String concentracion){
        this.fecha=fecha;
        this.descanso=descanso;
        this.concentracion=concentracion;
    }

    public String getFecha(){
        return fecha;
    }
    public  String getConcentracion()
    {
        return concentracion;
    }
    public String getDescanso()
    {
        return descanso;
    }

}
