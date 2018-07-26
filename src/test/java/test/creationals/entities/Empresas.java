package test.creationals.entities;

import ar.com.teceng.excelReader.ExcelEntity;

public class Empresas extends ExcelEntity {
    private String empresa;
    private String direccion;
    private String empresaAEditar;

    public Empresas() {
        super();
    }

    public String getEmpresa() {
        return empresa;
    }
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmpresaAEditar() {
        return empresaAEditar;
    }

    public void setEmpresaAEditar(String empresaAEditar) {
        this.empresaAEditar = empresaAEditar;
    }

}
