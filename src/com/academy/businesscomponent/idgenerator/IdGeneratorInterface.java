package com.academy.businesscomponent.idgenerator;

import java.io.IOException;

import com.academy.architecture.dao.DAOException;

public interface IdGeneratorInterface {
	long getNextId() throws DAOException, ClassNotFoundException, IOException;
}
