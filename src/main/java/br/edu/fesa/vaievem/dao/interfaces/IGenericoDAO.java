
package br.edu.fesa.vaievem.dao.interfaces;

import br.edu.fesa.vaievem.exception.PersistenciaException;
import java.io.Serializable;
import java.util.List;


public interface IGenericoDAO<E> extends Serializable {
    
    List<E> listar() throws PersistenciaException;
    
    E listarPorId(E e) throws PersistenciaException;

    void inserir(E e) throws PersistenciaException;
    
    void alterar(E e) throws PersistenciaException;
    
    void remover(E e) throws PersistenciaException;
}
