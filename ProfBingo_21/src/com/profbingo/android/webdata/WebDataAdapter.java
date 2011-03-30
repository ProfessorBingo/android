package com.profbingo.android.webdata;

import java.util.Collection;

import com.profbingo.android.model.Category;
import com.profbingo.android.model.GameBoard;
import com.profbingo.android.model.Professor;
import com.profbingo.android.model.School;


public interface WebDataAdapter {
    
    public String login(String email, String password);
    
    public boolean login(String authCode);
    
    public boolean logout();
    
    public boolean isLoggedIn();
    
    public Collection<Category> getCategoriesForSchool(School school);
    
    public Collection<Professor> getProfessorsForSchool(School school);
    
    public GameBoard getNewBoard(Professor professor);
}