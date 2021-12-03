package com.tp.logica.gestores;

import javax.persistence.NoResultException;

import com.tp.interfaz.dto.ConserjeDTO;
import com.tp.logica.dominio.Conserje;
import com.tp.persistencia.dao.ConserjeDAO;
import com.tp.persistencia.dao.ConserjeSqlDAO;

public class GestorLogin {

    public static ConserjeDTO getUser(String usuario, String pass) throws NoResultException {
        ConserjeDAO cDAO = new ConserjeSqlDAO();
        Conserje c = cDAO.getConserjeByCriteria(usuario, pass);

        ConserjeDTO result = new ConserjeDTO(c.getNombre(), c.getApellido());

        return result;
    }
    
}
