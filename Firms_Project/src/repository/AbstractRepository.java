package repository;

import domain.*;
import exceptions.RepositoryException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public abstract class AbstractRepository <T extends Identifiable<ID>,ID> implements Repository<T, ID>{
    private Map<ID,T> elem;

    public AbstractRepository(){
        elem= new HashMap<>();
    }

    public void add(T el){
            elem.put(el.getID(),el);
    }

    public void delete(T el){
        if(elem.containsKey(el.getID()))
            elem.remove(el.getID());
    }


    public void update(T el,ID id){
        if(elem.containsKey(id))
            elem.put(el.getID(),el);
        else
            throw new RuntimeException("Element doesn’t exist");
    }

    public T findById( ID id){
        if(elem.containsKey(id))
            return elem.get(id);
        else
            throw new RuntimeException("Element doesn't exist");
    }



    public Iterable<T> findAll(){
        return elem.values();
    }

    @Override
    public void readFromFile() {
        try(BufferedReader reader=new BufferedReader(new FileReader("F:\\Firms_Project\\src\\Denumire_firme.txt"))){
            String line;
            while((line=reader.readLine())!=null){
                String[] el=line.split(";");
                if(el.length!=2){
                    System.err.println("Not a valid number of atributes "+line);
                    continue;
                }

                //int Id=Integer.parseInt(el[0]);
                String id = el[0];
                Firm c = new Firm(id,el[1]);
                add((T) c);


            }
        }
        catch(IOException ex){
            throw new RepositoryException("Error reading"+ex);
        }

    }


}