package com.tp.persistencia.dao;

import com.tp.logica.dominio.Conserje;

public interface ConserjeDAO {
    public Conserje getConserjeByCriteria(String usuario, String pass);
}
