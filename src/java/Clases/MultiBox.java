package Clases;

import DBMS.DBMS;
import java.util.List;
import org.apache.struts.action.ActionForm;

public class MultiBox extends ActionForm {

    private static final long serialVersionUID = 1L;
    private List items;
    private String[] itemsSeleccionados = {};

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public String[] getItemsSeleccionados() {
        return itemsSeleccionados;
    }

    public void setItemsSeleccionados(String[] itemsSeleccionados) {
        this.itemsSeleccionados = itemsSeleccionados;
    }

    public List getProfesores(String id_departamento) {
        items = DBMS.getInstance().listarProfesoresAEvaluar(id_departamento);
        return items;
    }
    
    public List getMaterias(String id_departamento, String id_profesor) {
        items = DBMS.getInstance().listarMateriasNoDictadas(id_departamento,id_profesor);
        return items;
    }
}