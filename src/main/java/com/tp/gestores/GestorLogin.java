package com.tp.gestores;

import javax.persistence.NoResultException;

import com.tp.dominio.conserje.*;
import com.tp.dto.ConserjeDTO;

public class GestorLogin {

    public static ConserjeDTO getUser(String usuario, String pass) throws NoResultException {
        ConserjeDAO cDAO = new ConserjeSqlDAO();
        Conserje c = cDAO.getConserjeByCriteria(usuario, pass);

        ConserjeDTO result = new ConserjeDTO(c.getNombre(), c.getApellido());

        return result;
    }
    
}
