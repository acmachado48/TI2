package dao;

import model.Carro;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CarrosDAO {
    private Map<String, Carro> carrosMap;
    private File file;

    public CarrosDAO(String filename) throws IOException {
        this.file = new File(filename);
        this.carrosMap = new HashMap<>();
        if (file.exists()) {
            readFromFile();
        }
    }

    public void add(Carro carro) {
        carrosMap.put(carro.getPlaca(), carro);
        saveToFile();
    }

    public Carro getByPlaca(String placa) {
        System.out.println("Buscando carro com a placa: " + placa);
        Carro carro = carrosMap.get(placa);
        if (carro != null) {
            System.out.println("Carro encontrado: " + carro);
        } else {
            System.out.println("Carro não encontrado para a placa: " + placa);
        }
        return carro;
    }


    public void update(Carro carro) {
        carrosMap.put(carro.getPlaca(), carro);
        saveToFile();
    }

    public void remove(Carro carro) {
        carrosMap.remove(carro.getPlaca());
        saveToFile();
    }

    public Map<String, Carro> getAll() {
        return carrosMap;
    }

    private void readFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            carrosMap = (Map<String, Carro>) ois.readObject();
        } catch (Exception e) {
            System.out.println("ERRO ao ler carros do arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(carrosMap);
        } catch (Exception e) {
            System.out.println("ERRO ao salvar carros no arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}