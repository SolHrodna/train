package com.springapp.mvc.DAO;

import com.springapp.mvc.domain.History;

public interface HistoryDAO extends MainDAO<History>{

	boolean isHistoryExsist(int id);

}
