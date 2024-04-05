package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.CarrosDAO;
import model.Carro;
import spark.Request;
import spark.Response;

public class CarroService {

    private CarrosDAO carrosDAO;

    public CarroService() {
        try {
            carrosDAO = new CarrosDAO("carro.dat");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Carro add(Request request, Response response) {
        String cor = request.queryParams("cor");
        String placa = request.queryParams("placa");
        int ano = Integer.parseInt(request.queryParams("ano"));
        String marca = request.queryParams("marca");

        Carro carro = new Carro(cor, placa, ano, marca);

        carrosDAO.add(carro);

        response.status(201); // 201 Created
        return carro; // Retornar o objeto Carro adicionado
    }


    public Object get(Request request, Response response) {
        String placa = request.params(":placa");

        Carro carro = carrosDAO.getByPlaca(placa);

        if (carro != null) {
            return carro;
        } else {
            response.status(404); // 404 Not found
            return "Carro não encontrado.";
        }
    }

    public Object update(Request request, Response response) {
        String placa = request.params(":placa");

        Carro carro = carrosDAO.getByPlaca(placa);

        if (carro != null) {
            carro.setCor(request.queryParams("cor"));
            carro.setAno(Integer.parseInt(request.queryParams("ano")));
            carro.setMarca(request.queryParams("marca"));

            carrosDAO.update(carro);

            return placa;
        } else {
            response.status(404); // 404 Not found
            return "Carro não encontrado.";
        }
    }

    public Object remove(Request request, Response response) {
        String placa = request.params(":placa");
        Carro carro = carrosDAO.getByPlaca(placa);

        if (carro != null) {
            System.out.println("Carro encontrado para remoção: " + carro);
            carrosDAO.remove(carro);
            response.status(200); // success
            return placa;
        } else {
            System.out.println("Carro não encontrado para a placa: " + placa);
            response.status(404); // 404 Not found
            return "Carro não encontrado.";
        }
    }

    


    public String getAll(Request request, Response response) {
        List<Carro> carrosList = new ArrayList<>(carrosDAO.getAll().values());
        StringBuilder htmlBuilder = new StringBuilder();
        
        htmlBuilder.append("<html><head><title>Lista de Carros</title></head><body>");
        htmlBuilder.append("<h1>Lista de Carros</h1>");
        htmlBuilder.append("<table class=\"table-style\"><tr><th>Placa</th><th>Cor</th><th>Ano</th><th>Marca</th></tr>");
        
        for (Carro carro : carrosList) {
            htmlBuilder.append("<tr>");
            htmlBuilder.append("<td>").append(carro.getPlaca()).append("</td>");
            htmlBuilder.append("<td>").append(carro.getCor()).append("</td>");
            htmlBuilder.append("<td>").append(carro.getAno()).append("</td>");
            htmlBuilder.append("<td>").append(carro.getMarca()).append("</td>");
            htmlBuilder.append("</tr>");
        }
        
        htmlBuilder.append("</table></body></html>");
        
        return htmlBuilder.toString();
    }


}
