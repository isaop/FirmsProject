package repository;

import domain.*;
import java.io.*;

import exceptions.RepositoryException;

public class FirmRepositoryFile extends AbstractRepository<Firm, String>{
    private String filename;
    public FirmRepositoryFile(String filename){
        this.filename=filename;
        readFromFile();
    }

    private void ReadFromFile(){
        try(BufferedReader reader=new BufferedReader(new FileReader(filename))){
            String line;
            while((line=reader.readLine())!=null){
                String[] el=line.split(",");
                if(el.length!=7){
                    System.err.println("Not a valid number of atributes "+line);
                    continue;
                }
                try{
                    String Id=el[0];
                    Firm c = new Firm(Id,el[1]);
                    super.add(c);
                }
                catch(NumberFormatException n){
                    System.err.println("The ID is not a valid number "+el[0]);
                }
            }
        }
        catch(IOException ex){
            throw new RepositoryException("Error reading"+ex);
        }
    }
    @Override
    public void add(Firm obj) {
        try{
            super.add(obj);
            writeToFile();
        }
        catch(RuntimeException e){
            throw new RepositoryException("Object wasn�t added" + e + " " + obj);
        }
    }

    private void writeToFile() {
        try(PrintWriter pw = new PrintWriter(filename)) {
            for(Firm el : findAll()) {
                String line = el.getID() + " " + el.getName();
                pw.println(line);
            }
        }
        catch(IOException ex) {
            throw new RepositoryException("Error writing" + ex);
        }
    }

    @Override
    public void delete(Firm obj){
        try{
            super.delete(obj);
            writeToFile();
        }
        catch(RuntimeException ex){
            throw new RepositoryException("Object was not deleted"+ex+" "+obj);
        }
    }

    @Override
    public void update(Firm obj, String id){
        try{
            super.update(obj, id);
            writeToFile();
        }
        catch(RuntimeException ex){
            throw new RepositoryException("Object was not updated" + ex + " " + obj);
        }
    }
}
